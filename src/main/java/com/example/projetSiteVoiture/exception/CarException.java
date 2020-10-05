package com.example.projetSiteVoiture.exception;

public class CarException extends RuntimeException {

    public CarException(Long id) {
        super("aucune voiture avec cette id: " + id);
    }
}