package com.m2i.atelier.tp3.model;

import java.util.Optional;
import java.util.UUID;

public class CompteBancaire {


    protected String titulaire;
    private double solde;

    public static int nbComptes;

//Créez un constructeur par défaut qui initialise solde à 0
    public CompteBancaire(){
        this.solde=0.00;
    }
//Créez un constructeur avec paramètres pour initialiser titulaire et solde

    public CompteBancaire(String titulaire, double solde){
        this.solde=solde;
        this.titulaire=titulaire;
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

}
