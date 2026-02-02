package com.m2i.atelier.tp7.model;


public abstract class Client {

    protected      String nom;
    protected  String username;
    protected   String email;



    public Client(String nom, String username, String email){

        this.email=email;
        this.nom=nom;
        this.username=username;
    }


    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getUsername() {
        return username;
    }

    public void afficherInfos(String prefix) {
        System.out.printf("%s : %s; Username: %s; Email: %s " , prefix, getNom(), getUsername(),getEmail());

    }


    //Redéfinissez la méthode toString() dans toutes les classes principales
    @Override
    public String toString(){
        return "Nom :"+getNom()+"; Username: "+getUsername()+"; Email: "+getEmail();

    }
}
