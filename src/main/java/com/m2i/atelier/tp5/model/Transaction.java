package com.m2i.atelier.tp5.model;



import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public class Transaction {

    private Long id;
    private static int compteur=0;

    private String date;
    private CompteBancaire compte;
    private double montant;
    private String type;
    private static Integer nbTransactions;


    /*
         3 - Constructeur :
        Initialise tous les champs
        Incrémente compteur pour l’ID

    * */
    public Transaction ( CompteBancaire compte, double montant, String type){


        long uniqueNum = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedNow = now.format(formatter);

         this.id=uniqueNum;
         this.compte=compte;
         this.montant=montant;
         this.type=type;
         compteur+=1;
         this.date=formattedNow;

    }

    public static Integer getNbTransactions() {
        return nbTransactions;
    }

    public static void setNbTransactions(Integer nbTransactions) {
        Transaction.nbTransactions = nbTransactions;
    }




    public void  afficherDetails(){
        Optional<String> titulaire = Optional.ofNullable(getCompte().getTitulaire());
        System.out.println(String.format("***TRX*** -->>ID: %d;  Titulaire %s: Montant: %.2f $, Type: %s; Id: %d; Date: %s", getId(), titulaire.get(), getMontant(), getType(), getId(), getDate()));

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    /*
    Ajoutez une validation dans le setter de type, les deux valeurs du type doivent être debit ou credit, si la valeur entrée est inexacte afficher un message à l’ecran précisant
    « Type de transaction (type) invalide»

    * */
    public void setType(@NotNull String type) {
        if (!type.equals("DEBIT") && !type.equals("CREDIT") ){
            System.out.println("« Type de transaction (type) invalide» ");
        }
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
