package com.example.projetSiteVoiture.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import javax.persistence.Entity;


@Entity
public class Car {
    private @Id
    @GeneratedValue
    Long id;
    private String marque;
    private String nom;
    private int prix;

    Car() {
    }

    public Car(String marque, String nom, int prix) {

        this.marque = marque;
        this.nom = nom;
        this.prix = prix;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof com.example.projetSiteVoiture.model.Car))
            return false;
        com.example.projetSiteVoiture.model.Car car = (com.example.projetSiteVoiture.model.Car) o;
        return Objects.equals(this.id, car.id) && Objects.equals(this.marque, car.marque)
                && Objects.equals(this.nom, car.nom) && Objects.equals(this.prix, car.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.marque, this.nom, this.prix);
    }

    @Override
    public String toString() {
        return "Voiture{" + "id=" + this.id + ", marque='" + this.marque + '\'' + ", nom='" + this.nom
                + '\'' + ", prix='" + this.prix + '\'' + '}';
    }
}