package com.m2i.atelier.tp7.vo;

import java.util.regex.Pattern;

public class ClientVO {

    private  final String nom;
    private  final String email;
    private  final String telephone;

    private static final Pattern TELEPHONE_PATTERN =
            Pattern.compile("^\\+?[0-9]{8,15}$");

    public ClientVO(String nom, String email, String telephone){


        if(nom ==null || nom.trim().isEmpty()){
            throw  new IllegalArgumentException("Le nom ne peut pas être vide");
        }

        if(email ==null || email.trim().isEmpty() ){
            throw  new IllegalArgumentException("L'email ne peut pas être vide");
        }

        if(!email.contains("@")){
            throw  new IllegalArgumentException("L'email invalide");
        }
        if (telephone == null || !TELEPHONE_PATTERN.matcher(telephone).matches()) {
            throw new IllegalArgumentException(
                    "Numéro de téléphone invalide (ex: +33612345678)"
            );
        }

        this.nom=nom;
        this.email=email;
        this.telephone=telephone;



    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }


}
