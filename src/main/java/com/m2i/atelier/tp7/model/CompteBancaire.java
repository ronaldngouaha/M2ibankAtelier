package com.m2i.atelier.tp7.model;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public sealed abstract class CompteBancaire permits CompteCourant, CompteEpargne {


    private static int compteur=0;

    protected String createdAt;
    protected double solde;
    protected Client client;
    public  long id;

/*
Constructeurs :
Un constructeur avec String titulaire et double solde
Incrémente compteur et affecte un ID unique

* */

    public CompteBancaire(Client client, double solde){
        this.solde=solde;
        this.client=client;
        this.id= UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        compteur+=1;
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        this.createdAt = now.format(formatter);
    }

    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    public long getId() {
        return id;
    }
    public String getCreatedAt() {
        return createdAt;
    }

    public Client getClient() {
        return client;
    }

    public abstract void afficherInfos();
    public abstract void   retirer(double montant);
    public abstract  void deposer(double montant);


    //Redéfinissez la méthode toString() dans toutes les classes principales
    @Override
    public String toString(){
        return "Titulaire :"+getClient().getNom()+"; Solde: "+getSolde()+"$; CreatedAt: "+getCreatedAt()+"; ID: "+getId();

    }

}
