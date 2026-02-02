package com.m2i.atelier.tp5.utils;

import com.m2i.atelier.tp5.model.Transaction;
import com.m2i.atelier.tp5.model.TransactionServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Historique {

    private static final Map<Long, List<Transaction>> historiqueParCompte = new HashMap<>();


    public static void ajouterTransaction(long idCompte, List<Transaction> transaction){

        historiqueParCompte.put(idCompte,transaction);
    }

    public static void afficherHistorique(long idCompte){

        historiqueParCompte.get(idCompte).forEach(transaction -> {
            TransactionServiceImpl transactionService= new TransactionServiceImpl();
            transactionService.afficherDetails(transaction);
        });
    }

    public static List <Transaction> getTransactions(long idCompte){
       return historiqueParCompte.get(idCompte);
    }
}
