package com.m2i.atelier.tp7.model;

import com.m2i.atelier.tp7.service.Exportable;

public class Conseiller extends Employe implements Exportable {

    public Conseiller(String nom, String matricule, double salaire){
        super(nom,matricule, salaire);
    }


    //La prime ne dépasse jamais 2 000 $.
    @Override
    double calculerPrime() {

        double prime = salaire * 0.15;
        return Math.min(prime, 2000);
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
