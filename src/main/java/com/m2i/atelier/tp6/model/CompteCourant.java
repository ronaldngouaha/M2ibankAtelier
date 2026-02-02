package com.m2i.atelier.tp6.model;

public final  class CompteCourant extends CompteBancaire {

    private double decouvert=0.00;


    public CompteCourant(String titulaire, double solde, double decouvert) {
        super(titulaire, solde);
        this.decouvert=decouvert;

    }


    @Override
    public void afficherInfos(){
        System.out.printf(
                "***Detail Compte COURANT*** -->>ID: %d;  Titulaire %s: Solde: %.2f$; Découvert: %.2f; CreatedAt: %s%n",
                this.getId(), this.getTitulaire(), this.getSolde(), this.getDecouvert(),  this.getCreatedAt());

    }

    @Override
    public void retirer(double montant) {

        if (montant > 0 && this.getSolde() >= montant) {
            this.setSolde(this.getSolde() - montant);
            System.out.println("Retrait effectué : -" + montant);
        } else {
            System.out.println("Retrait impossible (montant invalide ou solde insuffisant).");
            System.out.println("Vous serez a decouvert");

            this.setSolde(0);
            double montantDecouvert=montant-this.getSolde();
            this.setDecouvert(this.getDecouvert() + montantDecouvert);

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

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
