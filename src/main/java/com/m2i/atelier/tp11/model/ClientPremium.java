package com.m2i.atelier.tp11.model;


import com.m2i.atelier.tp11.service.AvantageClient;

public class ClientPremium extends Client implements AvantageClient {

    private final double tauxRemuneration;
    private final double decouvertAutorise;


    public ClientPremium(String nom, String username, String email, double tauxRemuneration, double decouvertAutorise){

        super( nom,  username,  email);

        this.decouvertAutorise = decouvertAutorise;
        this.tauxRemuneration=tauxRemuneration;

    }

    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public double getTauxRemuneration() {
        return tauxRemuneration;
    }

    /*
    Implémentez une interface AvantageClient (dans com.m2i.service) avec une méthode :
    void afficherAvantages()
    Redéfinissez la méthode afficherInfos() pour afficher les avantages premium.

    * */
    @Override
    public void afficherAvantages() {
        System.out.println("Avantages Premium :");
        System.out.println("- Taux de rémunération : " + tauxRemuneration + "%");
        System.out.println("- Découvert autorisé : " + decouvertAutorise + " $");

    }

    @Override
    public  void afficherInfos(String prefix){
        super.afficherInfos(prefix);
        afficherAvantages();
    }

}
