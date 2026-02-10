package com.m2i.atelier.tp12.model;

import org.jetbrains.annotations.NotNull;

public record   ClientR(int id, String nom, String prenom, double solde){

    @Override
    public @NotNull String toString() {
        return "--->>Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", solde=" + solde +
                '}';
    }
}



