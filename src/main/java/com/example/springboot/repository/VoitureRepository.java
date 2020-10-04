package com.example.projetSiteVoiture.repository;

import com.example.projetSiteVoiture.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
}