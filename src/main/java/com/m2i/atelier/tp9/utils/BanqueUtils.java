package com.m2i.atelier.tp9.utils;

import com.m2i.atelier.tp7.model.Client;
import com.m2i.atelier.tp9.model.ClientDTO;
import com.m2i.atelier.tp9.model.TypeClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

public  class BanqueUtils {


    //Affiche chaque solde du tableau avec son index.
    public static  void afficherSoldes(double[] soldes){


        int i=0;
        System.out.println("___________________________");
        System.out.println("|INDEX        |       SOLDE|");
        for(double solde: soldes){
            System.out.println("|"+i+"        |     "+solde+"|");
            i++;
        }
        System.out.println("|__________________________|");
    }

    //Retourne la moyenne des soldes.
    public static double calculerMoyenne(double[] soldes){
        return Arrays.stream(soldes).average().orElse(0);


    }

    //Affiche chaque ligne et chaque colonne de la matrice représentant des montants de transactions par jour et par client.
    public static void afficherMatriceTransactions(double[][] montants){
        System.out.println("___________________________");
        System.out.println("|TOTAL JOUR        |       TOTAL CLIENT|");
        for(int i=0; i<montants.length; i++){

            for (int j=0; j<montants.length; j++){

                System.out.println("| "+montants[i][j]+"        |");
            }
        }
    }

//Affiche uniquement les clients du type donné (utilise stream().filter(...))
    public static void afficherParType(List<ClientDTO> clients, TypeClient type){

        clients.stream().filter(client -> client.getType().equals(type)).forEach(clientDTO -> clientDTO.afficherInfos("Client"));
    }
}
