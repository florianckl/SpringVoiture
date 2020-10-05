package com.example.projetSiteVoiture.repository;

import com.example.projetSiteVoiture.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}