package com.m2i.atelier.tp10.model;

import com.m2i.atelier.tp10.exceptions.SoldeInsuffisantException;
import com.m2i.atelier.tp6.model.InteretCalculable;


public non-sealed class CompteEpargne extends CompteBancaire implements InteretCalculable {

    protected double tauxInteret;


    public CompteEpargne(Client client, double solde, double tauxInteret) {
        super(client, solde);
        this.tauxInteret=tauxInteret;
    }


    @Override
    public void afficherInfos(){


        System.out.printf(
                "***Detail Compte EPARGNE*** -->>ID: %d;  Titulaire %s: Solde: %.2f$; Taux Interet: %.2f ; CreatedAt: %s; Statut: %s",
                this.getId(), this.getClient().getNom(), this.getSolde(), this.getTauxInteret()*100,  this.getCreatedAt(), this.getStatut());

    }

    @Override
    public void retirer(double montant) throws SoldeInsuffisantException {

        if (montant > 0 && this.getSolde() >= montant) {
            this.setSolde(this.getSolde() - montant);
            System.out.println("Retrait effectué : -" + montant);
        } else {

            throw new SoldeInsuffisantException("Retrait impossible (montant invalide ou solde insuffisant).");

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


    @Override
    public void appliquerOperation() {
        // Calcul des intérêts
        solde += solde * tauxInteret / 100;

        System.out.println("APPLIQUE  OP DEPUIS COMPTE ÉPARGNE");
    }




}
