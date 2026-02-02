package com.m2i.atelier.tp7.utils;

import com.m2i.atelier.tp7.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class M2iBankUtils {

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


    public static void afficherMenu(){

        System.out.println("******************************************************************");
        System.out.println("*** MENU M2iBank *************************************************");
        System.out.println("*** 1. Créer un Client *******************************************");
        System.out.println("*** 2. Créer un Compte     ***************************************");
        System.out.println("*** 3. Effectuer une transaction *********************************");
        System.out.println("*** 4. Afficher les comptes **************************************");
        System.out.println("*** 5. Afficher l'historique *************************************");
        System.out.println("*** 6. Générer un rapport d'activité ****************************");
        System.out.println("*** 0. Quitter ***************************************************");
        System.out.println("******************************************************************");

       int menu= M2iBankUtils.choisirMenu(getScanner());

       switch (menu){
           case 1->{
             //1. Créer un Client
               creerClient();

           }
           case 2->{
               //2. Créer un Compte
               creerCompte();
           }
           case 3 ->{
               //3. Effectuer une transaction

               effectuerTransaction();
           }
           case 4 ->{
               //4. Afficher les comptes
               afficherComptes();
           }
           case 5 ->{

               // 5. Afficher l'historique

               afficherHistorique();
           }
           case 6 ->{
               //6. Générer un rapport d'activité
               genererRapport();
           }
           case 10 ->{

               //10. Retour au menu principal
               afficherMenu();


           }
           case 0 ->{

               deconnexion();
           }
           default -> {
               System.err.println("*** Votre choix ne figure pas dans le menu ******************************");
              afficherMenu();

           }

       }

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
    private static Scanner getScanner(){
        return  new Scanner(System.in);
    }


    public static long demanderIdCompte(Scanner scanner){
        System.out.println("*****************************************************************");
        System.out.println("*** SAISIR UN ID COMPTE  ****************************************");
        return scanner.nextLong();
    }
    public static String demanderIdClient(Scanner scanner){
        System.out.println("*****************************************************************");
        System.out.println("*** SAISIR USERNAME POUR RECHERCHER LE CLIENT  ****************************************");


        return scanner.nextLine();
    }

    public static   Optional<CompteBancaire> trouverCompte(long idCompte, List<CompteBancaire> comptes){


        Optional<CompteBancaire> compteBancaire1= getComptes().stream()
                .filter(compteBancaire -> compteBancaire.getId() == idCompte)
                .findFirst();
        return compteBancaire1;

    }



    //Cette methode permet de creer un client Premium et l'ajoute a la liste des clients
    public static void creerClient(){

        System.out.println("******************************************************************");
        System.out.println("*** MENU M2iBank *************************************************");
        System.out.println("*** 1. Créer un Client *******************************************");

        String nomClient= saisirText(getScanner(),"SAISISSEZ LE NOM DU CLIENT");
        String username= saisirText(getScanner(),"SAISISSEZ LE USERNAME DU CLIENT");
        String email= saisirText(getScanner(),"SAISISSEZ L'EMAIL DU CLIENT");

        ClientPremium clientPremium= new ClientPremium(nomClient,username,email,0.3,2000);

        clients.add(clientPremium);

        clientPremium.afficherInfos("CLIENT PREMIUM");

        M2iBankUtils.afficherMenu();
    }
    //Cette methode permet de creer un compte  et l'ajoute a la liste des comptes
    public static void creerCompte(){
        System.out.println("******************************************************************");
        System.out.println("*** MENU M2iBank *************************************************");
        System.out.println("*** 2. Créer COMPTE POUR CLIENT **********************************");
        System.out.println("*** LISTE DES CLIENTS ********************************************");

        for(Client client: clients){

            client.afficherInfos("CLIENT PREMIUM");
        }

      String username=   M2iBankUtils.demanderIdClient(getScanner());

      Optional<Client> client= getClients().stream()
              .filter(client1 -> client1.getUsername().equals(username))
              .findFirst();

      if(client.isPresent()){


          comptes.add(new CompteCourant(client.get(),0, 2000));
          comptes.add(new CompteEpargne(client.get(),0, 0.23));
          System.out.println("******************************************************************");
          System.out.println("*** COMPTES CREES *************************************************");
          comptes.stream()
                  .filter(compteBancaire -> compteBancaire.getClient().equals(client.get()))
                  .forEach(CompteBancaire::afficherInfos);
          System.out.println("******************************************************************");

      }else{
          System.err.println("******************************************************************");
          System.err.println("*** CLIENT INTROUVABLE *******************************************");
          System.err.println("******************************************************************");
      }

        M2iBankUtils.afficherMenu();

    }
    //Cette methode permet de creer une transaction liee a un compte et l'ajoute a l'historique des transactions
    public static void effectuerTransaction(){

        System.out.println("*** LISTE DES CLIENTS ********************************************");

        for(Client client: clients){

            client.afficherInfos("CLIENT PREMIUM");
        }


        // ici on va demander a l utilisateur de saisir le username du client pour faciliter la rechercher dans la liste clients
        String username=   M2iBankUtils.demanderIdClient(getScanner());

        // ici on va rechercher le client dans la liste a partir de son username
        Optional<Client> client= getClients().stream()
                .filter(client1 -> client1.getUsername().equals(username))
                .findFirst();

        // ici on veirifie si le client est presents
        if(client.isPresent()){

            System.out.println("***LES COMPTES DU CLIENT *****************************************");
            System.out.println("******************************************************************");
            //ici on va afficher les comptes du client

            if(comptes.stream().anyMatch(compteBancaire -> compteBancaire.getClient().equals(client.get()))){

                comptes.stream()
                        .filter(compteBancaire -> compteBancaire.getClient().equals(client.get()))
                        .forEach(CompteBancaire::afficherInfos);

                //ici on va demander au l utilisateur de saisir le compte pour effectuer la transaction
                long compteID= demanderIdCompte(getScanner());

                Optional<CompteBancaire> compteBancaire= trouverCompte(compteID,getComptes());

                if(compteBancaire.isPresent()){

                    System.out.println("******************************************************************");
                    System.out.println("*** CHOISIR LE TYPE D'OPÉRATION *********************************");
                    System.out.println("*** 1. DEPOT; 2- RETRAIT; 3- VIREMENT ****************************");
                    System.out.println("******************************************************************");
                    int typeOp= getScanner().nextInt();

                    switch (typeOp){
                        case 1->{

                            double montant= saisirMontant(getScanner());
                            Transaction transaction = new Transaction(compteBancaire.get(),montant,TypeOperation.DEPOT, LocalDate.now());

                            compteBancaire.get().deposer(montant);

                            historique.ajouterTransaction(transaction);
                            System.out.println("******************************************************************");
                            System.out.println("*** DEPOT EFFECTUE AVEC SUCCESS **********************************");
                            transaction.afficherDetails();
                        }
                        case 2->{

                            double montant= saisirMontant(getScanner());
                            Transaction transaction = new Transaction(compteBancaire.get(),montant,TypeOperation.RETRAIT, LocalDate.now());
                            compteBancaire.get().retirer(montant);
                            historique.ajouterTransaction(transaction);
                            System.out.println("******************************************************************");
                            System.out.println("*** RETRAIT EFFECTUE AVEC SUCCESS **********************************");
                            transaction.afficherDetails();
                        }
                        case 3->{
                            double montant= saisirMontant(getScanner());
                            Transaction transaction = new Transaction(compteBancaire.get(),montant,TypeOperation.VIREMENT, LocalDate.now());
                            compteBancaire.get().retirer(montant);
                            historique.ajouterTransaction(transaction);
                            System.out.println("******************************************************************");
                            System.out.println("*** VIREMENT EFFECTUE AVEC SUCCESS **********************************");
                            transaction.afficherDetails();
                        }
                        default->{
                            System.err.println("*** Votre choix ne figure pas dans le menu ******************************");
                        }
                    }

                }else{
                    System.err.println("******************************************************************");
                    System.err.println("*** COMPTE INTROUVABLE *******************************************");
                    System.err.println("******************************************************************");

                }
            }else{
                System.err.println("******************************************************************");
                System.err.println("*** CE CLIENT NA PAS DE COMPTE  *********************************");

                creerCompte();

            }


        }else{
            System.err.println("******************************************************************");
            System.err.println("*** CLIENT INTROUVABLE *******************************************");
            System.err.println("******************************************************************");
        }


        afficherMenu();
    }
    //Cette methode permet d afficher l'historique des transactions
    public static void afficherHistorique(){

        System.out.println("******************************************************************");
        System.out.println("*** MENU M2iBank *************************************************");
        System.out.println("*** 5. Affichier historique transactions *************************");
        historique.getTransactions().forEach(Transaction::afficherDetails);

        afficherMenu();


    }
    //Cette methode permet d afficher la listes des comptes
    public static void afficherComptes(){

        System.out.println("******************************************************************");
        System.out.println("*** MENU M2iBank *************************************************");
        System.out.println("*** 4. Afficher les comptes **************************************");
        comptes.forEach(CompteBancaire::afficherInfos);

        afficherMenu();


    }
    //Cette methode permet de generer le rapport d'activite
    public static void genererRapport(){

        System.out.println("******************************************************************");
        System.out.println("*** MENU M2iBank *************************************************");
        System.out.println("*** 6. Générer un rapport d'activité *****************************");

        RapportActivite.generer(clients,comptes,historique);
        afficherMenu();

    }

    //Cette methode permet de quitter le menu interactif
    public static void deconnexion(){
        System.out.println("******************************************************************");
        System.out.println("*** MENU M2iBank *************************************************");
        System.out.println("*** Aurevoir et a Bientot ****************************************");
        getScanner().close();
    }

}

