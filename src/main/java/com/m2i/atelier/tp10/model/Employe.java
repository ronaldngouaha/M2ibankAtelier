package com.m2i.atelier.tp10.model;

public  abstract class Employe {

    protected  String nom;
    protected  String matricule;
    protected  double salaire;


    public Employe (String nom, String matricule, double salaire){
        this.matricule=matricule;
        this.salaire=salaire;
        this.nom=nom;
    }
    abstract double calculerPrime();


    public String getNom() {
        return nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public String getMatricule() {
        return matricule;
    }

    public void afficherInfos( String prefix) {
        System.out.println(prefix+" : " + nom);
        System.out.println("Matricule : " + matricule);
        System.out.println("Salaire : " + salaire + " $");
        System.out.println("Prime : " + calculerPrime() + " $");
    }

    //Redéfinissez la méthode toString() dans toutes les classes principales
    @Override
    public String toString(){
        return "Nom :"+getNom()+"; Matricule: "+getMatricule()+"; Salaire: "+getSalaire()+"; Prime: "+calculerPrime()+"$";

    }
}
