package com.example.projetSiteVoiture;

import com.example.projetSiteVoiture.properties.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({
		FileUploadProperties.class
})
public class ProjetSiteVoitureApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjetSiteVoitureApplication.class, args);
	}

}
