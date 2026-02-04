package com.m2i.atelier.tp9.model;

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

    public TypeClient getType() {
        return type;
    }


    public   void afficherInfos(String prefix) {
        System.out.printf("%s : %s; Type: %s; Email: %s " , prefix, getNom(), getType(),getEmail());

    }


    //Redéfinissez la méthode toString() dans toutes les classes principales
    @Override
    public String toString(){
        return "Nom :"+getNom()+"; type: "+getType()+"; Email: "+getEmail();

    }
}
