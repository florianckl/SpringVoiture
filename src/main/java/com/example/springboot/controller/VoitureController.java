package com.example.projetSiteVoiture.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.projetSiteVoiture.repository.VoitureRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.projetSiteVoiture.model.Voiture;
import com.example.projetSiteVoiture.exception.VoitureException;


import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;


@RestController
public class VoitureController {

    private final VoitureRepository repository;

    private final VoitureModelAssembler assembler;

    public VoitureController(VoitureRepository repository, VoitureModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/voitures")
    CollectionModel<EntityModel<Voiture>> all() {

        List<EntityModel<Voiture>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(VoitureController.class).all()).withSelfRel());
    }

    @PostMapping("/voitures")
    ResponseEntity<?> newEmployee(@RequestBody Voiture nouvVoiture) {

        EntityModel<Voiture> entityModel = assembler.toModel(repository.save(nouvVoiture));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/voitures/{id}")
    ResponseEntity<?> replaceVoiture(@RequestBody Voiture nouvVoiture, @PathVariable Long id) {

        Voiture majVoiture = repository.findById(id) //
                .map(voiture -> {
                    voiture.setNom(nouvVoiture.getNom());
                    voiture.setMarque(nouvVoiture.getMarque());
                    voiture.setPrix(nouvVoiture.getPrix());
                    return repository.save(voiture);
                }) //
                .orElseGet(() -> {
                    nouvVoiture.setId(id);
                    return repository.save(nouvVoiture);
                });

        EntityModel<Voiture> entityModel = assembler.toModel(majVoiture);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/voitures/{id}")
    EntityModel<Voiture> one(@PathVariable Long id) {
        Voiture employee = repository.findById(id) //
                .orElseThrow(() -> new VoitureException(id));
        return assembler.toModel(employee);
    }

    @DeleteMapping("/voitures/{id}")
    ResponseEntity<?> suppVoiture(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}