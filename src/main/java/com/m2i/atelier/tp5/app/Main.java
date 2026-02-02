package com.m2i.atelier.tp5.app;

import com.m2i.atelier.tp5.model.CompteBancaire;
import com.m2i.atelier.tp5.model.Transaction;
import com.m2i.atelier.tp5.model.TransactionServiceImpl;
import com.m2i.atelier.tp5.utils.BanqueUtils;
import com.m2i.atelier.tp5.utils.Historique;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


      static void  main(String[] args) {

        ArrayList<CompteBancaire> compteBancaires = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        BanqueUtils.afficherMenu();
        int choix = BanqueUtils.choisirMenu(getScanner());

        MainMenu(choix,getScanner(),compteBancaires,transactions);

    }
    public static void MainMenu(int menu, Scanner scanner, ArrayList <CompteBancaire> compteBancaires, ArrayList <Transaction> transactions){
        TransactionServiceImpl transactionService = new TransactionServiceImpl();

        switch (menu){
            case 1:
                System.out.println("******************************************************************");
                System.out.println("*** MENU M2iBank *************************************************");
                System.out.println("*** 1. Créer un compte *******************************************");


                String titulaire= BanqueUtils.saisirText(new Scanner(System.in),"INDIQUEZ LE NOM DU TITULAIRE");
                CompteBancaire compteBancaire = new CompteBancaire(titulaire,0);
                compteBancaires.add(compteBancaire);
                compteBancaire.afficherInfos();
                BanqueUtils.afficherMenu();

                int choix1= BanqueUtils.choisirMenu(getScanner());
                MainMenu(choix1,getScanner(),compteBancaires,transactions);

                break;
            case 2:
                System.out.println("******************************************************************");
                System.out.println("*** MENU M2iBank *************************************************");
                System.out.println("*** 2. Déposer de l’argent ***************************************");
                long currentAcc= BanqueUtils.demanderIdCompte(getScanner());

                CompteBancaire currentCompte=BanqueUtils.trouverCompte(currentAcc,compteBancaires);
                if(currentCompte != null){
                    currentCompte.afficherInfos();

                    double montant=  BanqueUtils.saisirMontant(getScanner());
                    transactionService.doTransaction(currentCompte,montant,"CREDIT");

                    Transaction transaction = new Transaction(currentCompte,montant,"CREDIT");
                    transactions.add(transaction);

                    Historique.ajouterTransaction(currentAcc, transactions);
                    Historique.afficherHistorique(currentAcc);

                    BanqueUtils.afficherMenu();
                    int choix=  BanqueUtils.choisirMenu(getScanner());
                    MainMenu(choix,getScanner(),compteBancaires,transactions);

                }else{
                    System.out.println("*** Compte Introuvable *************************************************");
                    BanqueUtils.afficherMenu();
                    int choix=  BanqueUtils.choisirMenu(getScanner());
                    MainMenu(choix,getScanner(),compteBancaires,transactions);

                }

                break;
            case 3:
                System.out.println("******************************************************************");
                System.out.println("*** MENU M2iBank *************************************************");
                System.out.println("*** 3. Retirer de l’argent ***************************************");


                long currentAcc2= BanqueUtils.demanderIdCompte(getScanner());

                CompteBancaire currentCompte2=BanqueUtils.trouverCompte(currentAcc2,compteBancaires);
                if(currentCompte2 != null){

                    currentCompte2.afficherInfos();

                    double montant=  BanqueUtils.saisirMontant(getScanner());

                    transactionService.doTransaction(currentCompte2,montant,"DEBIT");
                    Transaction transaction = new Transaction(currentCompte2,montant,"DEBIT");
                    transactions.add(transaction);
                    Historique.ajouterTransaction(currentAcc2, transactions);
                    Historique.afficherHistorique(currentAcc2);

                    BanqueUtils.afficherMenu();
                    int choix=  BanqueUtils.choisirMenu(getScanner());
                    MainMenu(choix,getScanner(),compteBancaires,transactions);

                }else{
                    System.out.println("*** Compte Introuvable *************************************************");
                    BanqueUtils.afficherMenu();
                    int choix=  BanqueUtils.choisirMenu(getScanner());
                    MainMenu(choix,getScanner(),compteBancaires,transactions);

                }


                break;
            case 4:
                System.out.println("******************************************************************");
                System.out.println("*** MENU M2iBank *************************************************");
                System.out.println("*** 4. Afficher les comptes **************************************");
                compteBancaires.forEach(CompteBancaire::afficherInfos);
                BanqueUtils.afficherMenu();
                int choix4= BanqueUtils.choisirMenu(getScanner());
                MainMenu(choix4,getScanner(),compteBancaires,transactions);

                break;
            case 5:
                System.out.println("******************************************************************");
                System.out.println("*** MENU M2iBank *************************************************");
                System.out.println("*** 5.Afficher les transactions **********************************");

                if(!compteBancaires.isEmpty())
                compteBancaires.forEach(compteBancaire1 -> {

                    System.out.println("******************************************************************");
                    compteBancaire1.afficherInfos();
                    List <Transaction> trx= new ArrayList<>();
                    trx=Historique.getTransactions(compteBancaire1.getId());

                    if(trx!=null && !trx.isEmpty()){
                        trx.forEach(Transaction::afficherDetails);
                    }
                    System.out.println("******************************************************************");
                });

                BanqueUtils.afficherMenu();
                int choix5= BanqueUtils.choisirMenu(getScanner());
                MainMenu(choix5,getScanner(),compteBancaires,transactions);
                break;
            case 10:

                BanqueUtils.afficherMenu();
                int choix=  BanqueUtils.choisirMenu(getScanner());
                MainMenu(choix,getScanner(),compteBancaires,transactions);

                break;
            case 0:

                System.out.println("******************************************************************");
                System.out.println("*** MENU M2iBank *************************************************");
                System.out.println("*** Aurevoir et a Bientot ****************************************");
                scanner.close();

                break;

        }


    }


    private static Scanner getScanner(){
        return  new Scanner(System.in);
    }


}



