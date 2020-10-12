package com.example.projetSiteVoiture.repository;

import com.example.projetSiteVoiture.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByOrderByPrix();
    List<Car> findByOrderByMarque();


}