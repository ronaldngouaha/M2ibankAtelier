package com.m2i.atelier.tp8.utils;

import com.m2i.atelier.tp7.model.Client;
import com.m2i.atelier.tp7.model.CompteBancaire;
import com.m2i.atelier.tp7.model.HistoriqueOperation;
import com.m2i.atelier.tp8.model.StatutCompte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BanqueUtils {

    //Tâche 1 : Contrôle de flux avec if, switch, yield


    private static final List<Client> clients = new ArrayList<>();
    private static final List<CompteBancaire> comptes = new ArrayList<>();
    private static final HistoriqueOperation historique = new HistoriqueOperation();

    public static HistoriqueOperation getHistorique() {
        return historique;
    }

    public static List<Client> getClients() {
        return clients;
    }

    public static List<CompteBancaire> getComptes() {
        return comptes;
    }

//Tâche 1 : Contrôle de flux avec if, switch, yield

    public static String evaluerMontant(double montant){

        String returnVal="";
        if(montant<100){
            returnVal="Montant faible";
        }else if(montant <=1000){
            returnVal="Montant Moyen";
        }else{
            returnVal="Montant Eleve";
        }

        return  returnVal;
    }

    public static String typeOperation(int code){
    return     switch (code){
          case  1-> "DEPOT";
          case 2-> "RETRAIT";
          case  3-> "VIREMENT";
          default-> "INCONNU";
        };

    }
//Tâche 1 : Contrôle de flux avec if, switch, yield
    public static String messageOperation(String type){

        return switch (type){
            case "DEPOT"->{
                System.out.println("Depot en compte realise avec success");
                yield "Depot realiser avec success";
            }
            case "RETRAIT"->{
                System.out.println("Retrait en compte realise avec success");
                yield "Retrait realiser avec success";
            }
            case "VIREMENT"->{
                System.out.println("Virement en compte realise avec success");
                yield "Virement realiser avec success";
            }
            default -> {
                System.out.println("Option inconnue");
                yield "Option inconnue";
            }
        };
    }


     public static void afficherTousStatuts(){

        //Parcourez et affichez toutes les valeurs de StatutCompte
         Arrays.stream(StatutCompte.values()).forEach(System.out::println);
    }


    public static Integer doublerMontant(int montant){

        Integer wrapperNombre=montant*2; // Autoboxing
        return wrapperNombre;
    }

    public static int extraireValeur(Integer montant){

        int nombre=montant;// Unboxing : Conversion automatique en int
        return nombre;
    }
    // cette methode Additionnez tous les montants passés en paramètre
    public static double sommeMontants(double... montants){

        return Arrays.stream(montants).sum(); //Additionnez tous les montants passés en paramètre
    }

}
