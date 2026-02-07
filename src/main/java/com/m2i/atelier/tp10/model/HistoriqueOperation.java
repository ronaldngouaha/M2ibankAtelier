package com.m2i.atelier.tp10.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HistoriqueOperation {

    // Cet attribut contient la liste des transaction sur laquelle nous allons faire des traitements.
   private final List<Transaction> transactions;


   public HistoriqueOperation(){
       // Dans mon constructeur, j'initialise ma liste.
       this.transactions=new ArrayList<>();
   }


   // cette methode ajoute une transaction dans la liste des transactions
    public  void ajouterTransaction(Transaction t){
        transactions.add(t);
    }

    //cette methode filtre les transactions par type et retourne une liste
    public List <Transaction> filtrerParType( TypeOperation type){
        Predicate<Transaction> matchType= transaction -> transaction.getType().equals(type);
        return   getTransactions().stream()
                .filter(matchType)
                .collect(Collectors.toList());

    }
    //cette methode filtre les transactions par date et retourne une liste
    public List < Transaction> filtrerParDate(LocalDate paramDate){
        Predicate<Transaction> compareDate= strVal-> strVal.getDate().equals(paramDate);
        return   getTransactions().stream()

                .filter(compareDate)
                .collect(Collectors.toList());
    }



    // Cette methode filtre les transactions par montant minim et maxi et retourne une liste
    public List <Transaction> filtrerParMontant(double min, double max){
        Predicate <Transaction > compare= transaction-> transaction.getMontant()>=min && transaction.getMontant()<=max;

     return   getTransactions().stream()
               .filter(compare)
               .collect(Collectors.toList());
    }


    // Cette fonction retourne la liste des transactions
    public List<Transaction> getTransactions() {
        return transactions;
    }


}
