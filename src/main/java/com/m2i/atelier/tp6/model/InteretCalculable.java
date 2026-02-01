package com.m2i.atelier.tp6.model;

public interface InteretCalculable {

    double calculerInteret();
    default  void afficherInteret(){
        System.out.println(formaterInteret(calculerInteret()));
    }

    // Méthode privée (utilisée uniquement par l’interface)
    private String formaterInteret(double interet) {
        return "Intérêt calculé : " + interet + " €";
    }

}
