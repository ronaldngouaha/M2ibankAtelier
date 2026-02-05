package com.m2i.atelier.tp10.model;


import com.m2i.atelier.tp10.exceptions.SoldeInsuffisantException;
import com.m2i.atelier.tp10.service.OperationBancaire;
import com.m2i.atelier.tp10.model.StatutCompte;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public sealed abstract class CompteBancaire implements OperationBancaire permits CompteCourant, CompteEpargne {


    private static int compteur=0;

    private StatutCompte statut;
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
        this.statut=StatutCompte.ACTIF;
    }

    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setStatut(StatutCompte statut) {
        this.statut = statut;
    }

    public long getId() {
        return id;
    }

    public StatutCompte getStatut() {
        return statut;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Client getClient() {
        return client;
    }

    public abstract void afficherInfos();
    public abstract void   retirer(double montant) throws SoldeInsuffisantException;
    public abstract  void deposer(double montant);


    //Redéfinissez la méthode toString() dans toutes les classes principales
    @Override
    public String toString(){
        return "Titulaire :"+getClient().getNom()+"; Solde: "+getSolde()+"$; CreatedAt: "+getCreatedAt()+"; ID: "+getId()+"; Statut: "+getStatut();

    }

    abstract class HistoriqueLocal{
        abstract   void afficherDerniereOperation();
    }
}
