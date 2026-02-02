package com.m2i.atelier.tp7.dto;

public class CompteDTO {

    private final int id;
    private final double solde;
    private final String typeCompte;



    public CompteDTO(int id, double solde, String typeCompte){
        this.id=id;
        this.solde=solde;
        this.typeCompte=typeCompte;
    }

    public double getSolde() {
        return solde;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public int getId() {
        return id;
    }


}
