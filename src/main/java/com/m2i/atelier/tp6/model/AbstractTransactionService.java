package com.m2i.atelier.tp6.model;

public abstract class AbstractTransactionService {


    //Attribut
    protected CompteBancaire compte;

    //Constructeur
    public AbstractTransactionService(CompteBancaire compteBancaire){
        this.compte=compteBancaire;
    }

    // methode abstraite
   public abstract void  executerTransaction(double montant);
    public  void afficheSolde(){
        System.out.printf("Compte:%d; Titulaire: %s; Solde du compte : %.2f$\n",compte.getId(), compte.getTitulaire(), compte.getSolde());
    }



}
