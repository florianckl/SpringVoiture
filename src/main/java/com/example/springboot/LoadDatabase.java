package com.example.projetSiteVoiture;

import com.example.projetSiteVoiture.model.Voiture;
import com.example.projetSiteVoiture.repository.VoitureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(VoitureRepository voitureRepository) {

        return args -> {
            voitureRepository.save(new Voiture("merco", "cla", 30000));
            voitureRepository.findAll().forEach(voiture -> {
                log.info("Preloaded " + voiture);
            });
        };
    }
}