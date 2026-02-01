package com.m2i.atelier.tp3.model;



import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Transaction {

    private Long id;

    private String date;
    private CompteBancaire compte;
    private Float montant;
    private String type;
    private static Integer nbTransactions;


    public Transaction ( CompteBancaire compte){

        this.setCompte(compte);
    }

    public static Integer getNbTransactions() {
        return nbTransactions;
    }

    public static void setNbTransactions(Integer nbTransactions) {
        Transaction.nbTransactions = nbTransactions;
    }


    public void afficherDetails(){

        Optional<String> titulaire = Optional.ofNullable(this.getCompte().getTitulaire());

        System.out.println(String.format("-->> Titulaire %s: Montant: %f $, Type: %s; Id: %d ", titulaire.get(), this.getMontant(), this.getType(), this.getId()));

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

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
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
