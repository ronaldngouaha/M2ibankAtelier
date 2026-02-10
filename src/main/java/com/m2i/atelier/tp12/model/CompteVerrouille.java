package com.m2i.atelier.tp12.model;

import java.util.concurrent.locks.ReentrantLock;

public class CompteVerrouille {

    private final ReentrantLock lock= new ReentrantLock();
    private String nom;
    private double solde;

    public CompteVerrouille(String nom, double soldeInitial){

        this.nom=nom;
        this.solde=soldeInitial;
    }

    public synchronized boolean retirer( double montant){

        boolean success=false;


            if(solde>=montant){
                solde-=montant;
                success=true;
            }


        return success;
    }

    public synchronized void deposer( double montant){


            solde+=montant;

    }

    public String getNom() {
        return nom;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public synchronized double getSolde() {
        return solde;
    }
}
