package com.m2i.atelier.tp5.model;

public class ClientDTO {

    private final String nom;
    private final String email;

    public ClientDTO(String nom, String email){
        this.email=email;
        this.nom=nom;
    }


    public String getNom() {
        return nom;
    }
    public String getEmail() {
        return email;
    }
}
