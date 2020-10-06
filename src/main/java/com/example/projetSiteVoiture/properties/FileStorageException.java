package com.example.projetSiteVoiture.properties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileStorageException extends RuntimeException {
    private String message;
}
