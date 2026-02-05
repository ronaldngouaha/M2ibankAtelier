package com.m2i.atelier.tp10.model;

import com.m2i.atelier.tp10.service.Exportable;

public class Directeur extends Employe implements Exportable {


    public Directeur(String nom, String matricule, double salaire){

        super( nom, matricule, salaire);
    }

    //La prime ne dépasse jamais 5 000 $.
    @Override
     double calculerPrime() {

        double prime = salaire * 0.15;
        return Math.min(prime, 5000);
    }



    @Override
    public void afficherInfos(String prefix) {
        System.out.println(prefix+ exporterText());
    }

    @Override
    public String exporterText() {
        return " : " + nom+ "; Matricule : " + matricule+"; Salaire : " + salaire + " $; Prime : " + calculerPrime() + " $";
    }

}
