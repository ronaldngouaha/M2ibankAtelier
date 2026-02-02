package com.m2i.atelier.tp7.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HistoriqueOperation {

    // Cet attribut contient la liste des transaction sur laquelle nous allons faire des traitements.
   private  List<Transaction> transactions;


   public HistoriqueOperation(){
       // Dans mon constructeur, j'initialise ma liste.
       this.transactions=new ArrayList<>();
   }


   // cette methode ajoute une transaction dans la liste des transactions
    public  void ajouterTransaction(Transaction t){
        transactions.add(t);
    }

    //cette methode filtre les transactions par type et retourne une liste
    public List <Transaction> filtrerParType(TypeOperation type){
     return   getTransactions().stream()
               .filter(transaction -> transaction.getType()==type)
               .collect(Collectors.toList());
    }
    //cette methode filtre les transactions par date et retourne une liste
    public List <Transaction> filtrerParDate(LocalDate paramDate){
     return   getTransactions().stream()
               .filter(transaction -> transaction.getDate().equals(paramDate))
               .collect(Collectors.toList());
    }


    // Cette methode filtre les transactions par montant minim et maxi et retourne une liste
    public List <Transaction> filtrerParMontant(double min, double max){
     return   getTransactions().stream()
               .filter(transaction -> transaction.getMontant()>=min && transaction.getMontant()<=max)
               .collect(Collectors.toList());
    }


    // Cette fonction retourne la liste des transactions
    public List<Transaction> getTransactions() {
        return transactions;
    }


}
