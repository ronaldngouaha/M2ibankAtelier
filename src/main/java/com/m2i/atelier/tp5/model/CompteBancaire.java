package com.m2i.atelier.tp5.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public class CompteBancaire {


    private static int compteur=0;

    protected String titulaire;
    protected String createdAt;
    private double solde;

    public  long id;

/*
Constructeurs :
Un constructeur avec String titulaire et double solde
Incrémente compteur et affecte un ID unique

* */

    public CompteBancaire(String titulaire, double solde){
        this.solde=solde;
        this.titulaire=titulaire;

        long uniqueNum = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.id=uniqueNum;

        compteur+=1;

        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedNow = now.format(formatter);
        this.createdAt = formattedNow;

    }

    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    public String getTitulaire() {

        Optional<String> titulaire = Optional.ofNullable(this.titulaire);

        return titulaire.get();
    }
    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }


    public long getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }



    public void afficherInfos(){
        System.out.println(String.format("***Detail Compte*** -->>ID: %d;  Titulaire %s: Solde: %.2f$; CreatedAt: %s", this.getId(), this.getTitulaire(), this.getSolde(), this.getCreatedAt()));


    }
}
