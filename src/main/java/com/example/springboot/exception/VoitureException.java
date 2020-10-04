package com.example.projetSiteVoiture.exception;

public class VoitureException extends RuntimeException {

    public VoitureException(Long id) {
        super("aucune voiture avec cette id: " + id);
    }
}