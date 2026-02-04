package com.m2i.atelier.tp5.model;

import com.m2i.atelier.tp9.model.TypeClient;

public class ClientDTO {

    private final String nom;
    private final String email;

    private TypeClient type;

    public ClientDTO(String nom, String email, TypeClient type){
        this.email=email;
        this.nom=nom;
        this.type=type;
    }


    public String getNom() {
        return nom;
    }
    public String getEmail() {
        return email;
    }
}
