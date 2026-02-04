package com.m2i.atelier.tp6.model;

public class DepotService extends AbstractTransactionService{


    public DepotService(CompteBancaire bancaire){
        super(bancaire);
    }
    @Override
  public   void executerTransaction(double montant) {

        if (montant > 0) {

            compte .setSolde(compte.getSolde() + montant);
            System.out.println("Dépôt effectué : +" + montant);
        } else {
            System.out.println("Montant invalide pour un dépôt.");
        }
        this.afficheSolde();
       // compte.afficherInfos();
    }
}
