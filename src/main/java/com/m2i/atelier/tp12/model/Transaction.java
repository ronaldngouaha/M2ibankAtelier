package com.m2i.atelier.tp12.model;


import com.m2i.atelier.tp12.exceptions.DonneeInvalideException;

import java.time.Instant;
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


    private Instant horodatage;

    /*
         3 - Constructeur :
        Initialise tous les champs
        Incrémente compteur pour l’ID

    * */
    public Transaction(CompteBancaire compte, double montant, TypeOperation type){
         this.id= UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
         this.compte=compte;
         if(montant<0){
             throw new DonneeInvalideException("Montant invalide");
         }
         this.montant=montant;
         this.type=type;
         compteur+=1;
         this.date=LocalDate.now();

        this.horodatage = Instant.now(); // ⏱️ horodatage UTC

    }

    public void  afficherDetails(){
        Optional<String> titulaire = Optional.ofNullable(getCompte().getClient().getNom());
        System.out.printf("***TRX*** -->>ID: %d;  Titulaire %s: Montant: %.2f $, Type: %s; Compte: %d; Date: %s", getId(), titulaire.orElse("inconnu"), getMontant(), getType(), getCompte().getId(), getDate());
        System.out.println();
    }


    public Instant getHorodatage() {
        return horodatage;
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
