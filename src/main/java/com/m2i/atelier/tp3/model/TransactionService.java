package com.m2i.atelier.tp3.model;

public interface TransactionService {

    void  doTransaction(CompteBancaire compteBancaire, double montant, String transactionType) ;

    void afficherDetails(Transaction transaction) ;

    void  accountTracker(CompteBancaire compteBancaire, Transaction transaction,  double oldBalance);

}
