package com.m2i.atelier.tp12.service;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BanquePartagee {

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private  double solde;

    public BanquePartagee(double soldeInitial){
        this.solde=soldeInitial;
    }

    public synchronized void retirer(double montant){

        if (solde>=montant){

            solde-=montant;
        }

    }

    public synchronized double getSolde() {
        return solde;
    }



    public void depot(double montant){
        rwLock.writeLock().lock();
        try {

            solde+=montant;
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public void retrait(double montant){

        rwLock.writeLock().lock();
        try {
            if(montant<solde)
                solde-=montant;
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public double consulteSolde(double solde){

        rwLock.readLock().lock();
        try {
            return  solde;
        }finally {
            rwLock.readLock().unlock();
        }
    }

}
