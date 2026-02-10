package com.m2i.atelier.tp12.model;


import java.util.Random;

public abstract class Client {

    protected      String nom;
    protected  String username;
    protected   String email;
    private int id;



    public Client(String nom, String username, String email){

        this.id= new Random().nextInt();
        this.email=email;
        this.nom=nom;
        this.username=username;
    }


    public int getId() {
        return id;
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
