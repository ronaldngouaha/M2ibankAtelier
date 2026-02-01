package com.m2i.atelier.tp5.utils;

import com.m2i.atelier.tp5.model.CompteBancaire;

import java.util.List;
import java.util.Scanner;

public  class BanqueUtils {


    public static void afficherMenu(){

        System.out.println("******************************************************************");
        System.out.println("*** MENU M2iBank *************************************************");
        System.out.println("*** 1. Créer un compte *******************************************");
        System.out.println("*** 2. Déposer de l’argent ***************************************");
        System.out.println("*** 3. Retirer de l’argent ***************************************");
        System.out.println("*** 4. Afficher les comptes **************************************");
        System.out.println("*** 5. Afficher les transactions *********************************");
        System.out.println("*** 10. Revenir au Menu Principal ********************************");
        System.out.println("*** 0. Quitter ***************************************************");
          System.out.println("******************************************************************");



    }

    public static double saisirMontant(Scanner sc){
          System.out.println("*****************************************************************");
        System.out.println("*** Montant Transaction *****************************************");
       return sc.nextDouble();
    }

    public static String saisirText(Scanner scanner, String message){
        System.out.println("*****************************************************************");
        System.out.println("*** "+message+ " *****************************************");
        return scanner.nextLine();
    }

    public static int choisirMenu(Scanner scanner){
        System.out.println("*****************************************************************");
        System.out.println("*** Faites votre choix SVP ************************************");
        return scanner.nextInt();
    }

    public static long demanderIdCompte(Scanner scanner){
          System.out.println("*****************************************************************");
        System.out.println("*** SAISIR UN ID COMPTE  ****************************************");
        return scanner.nextLong();
    }

    public static CompteBancaire trouverCompte(long idCompte, List<CompteBancaire> comptes){

      return   comptes.stream()
                .filter(compteBancaire -> compteBancaire.getId()==idCompte )
                .findFirst().get();

    }



}
