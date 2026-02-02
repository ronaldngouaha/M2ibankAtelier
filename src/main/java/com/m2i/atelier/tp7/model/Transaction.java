package com.m2i.atelier.tp7.model;





import java.time.LocalDate;

import java.util.Optional;
import java.util.UUID;

public class Transaction {

    private final Long id;
    private static int compteur=0;

    private final LocalDate date;
    private final CompteBancaire compte;
    private final double montant;
    private final TypeOperation type;
    private static Integer nbTransactions;


    /*
         3 - Constructeur :
        Initialise tous les champs
        Incrémente compteur pour l’ID

    * */
    public Transaction(CompteBancaire compte, double montant, TypeOperation type, LocalDate date){
         this.id= UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
         this.compte=compte;
         this.montant=montant;
         this.type=type;
         compteur+=1;
         this.date=date;
    }

    public void  afficherDetails(){
        Optional<String> titulaire = Optional.ofNullable(getCompte().getClient().getNom());
        System.out.printf("***TRX*** -->>ID: %d;  Titulaire %s: Montant: %.2f $, Type: %s; Compte: %d; Date: %s", getId(), titulaire.orElse("inconnu"), getMontant(), getType(), getCompte().getId(), getDate());
        System.out.println();
    }


    public Long getId() {
        return id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public double getMontant() {
        return montant;
    }

    public TypeOperation getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

}
