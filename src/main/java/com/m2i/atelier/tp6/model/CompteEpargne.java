package com.m2i.atelier.tp6.model;

public non-sealed class CompteEpargne extends CompteBancaire implements  InteretCalculable{

    protected double tauxInteret;


    public CompteEpargne(String titulaire, double solde, double tauxInteret) {
        super(titulaire, solde);
        this.tauxInteret=tauxInteret;
    }


    @Override
    public void afficherInfos(){
        System.out.printf(
                "***Detail Compte EPARGNE*** -->>ID: %d;  Titulaire %s: Solde: %.2f$; Taux Interet: %.2f ; CreatedAt: %s%n",
                this.getId(), this.getTitulaire(), this.getSolde(), this.getTauxInteret()*100,  this.getCreatedAt());

    }

    @Override
    public void retirer(double montant) {


        if (montant > 0 && this.getSolde() >= montant) {
            this.setSolde(this.getSolde() - montant);
            System.out.println("Retrait effectué : -" + montant);
        } else {
            System.out.println("Retrait impossible (montant invalide ou solde insuffisant).");


        }
    }

    @Override
    public void deposer(double montant) {

        if (montant > 0) {
            this.setSolde(this.getSolde() + montant);
            System.out.println("Dépôt effectué : +" + montant);
        } else {
            System.out.println("Montant invalide pour un dépôt.");
        }
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    @Override
    public double calculerInteret() {
        return this.getSolde()* tauxInteret;
    }

    @Override
    public void afficherInteret() {
        InteretCalculable.super.afficherInteret();
    }



}
