package com.example.projetSiteVoiture.controller;

import com.example.projetSiteVoiture.model.Voiture;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VoitureModelAssembler implements RepresentationModelAssembler<Voiture, EntityModel<Voiture>> {

    @Override
    public EntityModel<Voiture> toModel(Voiture voiture) {

        return EntityModel.of(voiture,
                linkTo(methodOn(VoitureController.class).one(voiture.getId())).withSelfRel(),
                linkTo(methodOn(VoitureController.class).all()).withRel("voitures"));
    }
}