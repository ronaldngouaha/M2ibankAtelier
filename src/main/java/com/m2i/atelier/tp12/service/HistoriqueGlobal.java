package com.m2i.atelier.tp12.service;

import com.m2i.atelier.tp12.model.Transaction;

import java.util.ArrayList;
import java.util.List;


public class HistoriqueGlobal {
    private List<Transaction> historique;


    public HistoriqueGlobal(){
        historique= new ArrayList<>();
    }
    public List<Transaction> getHistorique() {
        return historique;
    }

    public void ajouter(Transaction t){

        historique.add(t);
    }


}
