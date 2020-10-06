package com.example.projetSiteVoiture.controller;

import com.example.projetSiteVoiture.exception.CarException;
import com.example.projetSiteVoiture.model.Car;
import com.example.projetSiteVoiture.model.FileResponse;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projetSiteVoiture.model.FileResponse;
import com.example.projetSiteVoiture.properties.IFileSytemStorage;
import com.example.projetSiteVoiture.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CarController {

    @Autowired
    private final CarRepository repository;
    @Autowired
    IFileSytemStorage fileSytemStorage;

    private final CarModelAssembler assembler;

    public CarController(CarRepository repository, CarModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/cars")
    List<Car> all() {
        List<Car> cars = (List<Car>) repository.findAll();
        return cars;
    }

    @PostMapping("/add")
    ResponseEntity<?> newcar(@RequestBody Car nouvCar) {

        EntityModel<Car> entityModel = assembler.toModel(repository.save(nouvCar));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PostMapping("/uploadfile")
    public ResponseEntity<FileResponse> uploadSingleFile (@RequestParam("file") MultipartFile file) {
        String upfile = fileSytemStorage.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download/")
                .path(upfile)
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(upfile,fileDownloadUri,"File uploaded with success!"));
    }

    @PutMapping("/cars/{id}")
    ResponseEntity<?> replaceVoiture(@RequestBody Car nouvCar, @PathVariable Long id) {

         Car majCar = repository.findById(id) //
                .map(car -> {
                    car.setMarque(nouvCar.getMarque());
                    car.setNom(nouvCar.getNom());
                    car.setPrix(nouvCar.getPrix());
                    return repository.save(car);
                }) //
                .orElseGet(() -> {
                    nouvCar.setId(id);
                    return repository.save(nouvCar);
                });

        EntityModel< Car> entityModel = assembler.toModel(majCar);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/cars/{id}")
    EntityModel< Car> one(@PathVariable Long id) {
         Car car = repository.findById(id) //
                .orElseThrow(() -> new CarException(id));
        return assembler.toModel(car);
    }

    @DeleteMapping("/cars/{id}")
    ResponseEntity<?> suppVoiture(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}