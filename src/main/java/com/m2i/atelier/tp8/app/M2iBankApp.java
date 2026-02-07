package com.m2i.atelier.tp8.app;

import com.m2i.atelier.tp7.model.ClientPremium;
import com.m2i.atelier.tp7.model.CompteBancaire;
import com.m2i.atelier.tp7.model.CompteCourant;
import com.m2i.atelier.tp7.model.CompteEpargne;
import com.m2i.atelier.tp8.model.MessageOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M2iBankApp  {


    public static void main(String[] args)  {

        //
        List<CompteBancaire> comptes = new ArrayList<>();

//Dans M2iBankApp :
//Créez une instance anonyme de cette interface pour afficher : "Opération validée avec succès !"
        MessageOperation messageOperation= new MessageOperation() {
            @Override
            public void afficherMessage() {
                System.out.println("Opération validée avec succès !");
            }
        };

        messageOperation.afficherMessage();
    }



    /*
    Méthode public static void afficherComptes(List<CompteBancaire> comptes)
        Affichez les comptes avec :
        une boucle for classique
        une boucle for-each
        un Stream.forEach
    * */
    public static void afficherComptes(List <CompteBancaire> comptes){



        for(int i=0; i<comptes.size(); i++){
            comptes.get(i).afficherInfos();
        }

        comptes.forEach(CompteBancaire::afficherInfos);
        comptes.forEach(CompteBancaire::afficherInfos);

    }

    public static void afficherComptesPairs(List <CompteBancaire> comptes){
        comptes.stream().filter(compteBancaire -> compteBancaire.getId()%2==0).forEach(CompteBancaire::afficherInfos);

//Affichez uniquement les comptes dont id est pair (utiliser continue)
        for(int i=0; i<comptes.size(); i++){
            if(comptes.get(i).getId()%2!=0){
                continue;
            }
            comptes.get(i).afficherInfos();
        }
    }




}
