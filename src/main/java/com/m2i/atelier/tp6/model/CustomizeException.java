package com.m2i.atelier.tp6.model;

public class CustomizeException {



    public static class SoldeInsuffisantException extends Throwable {
        SoldeInsuffisantException(String message) {
            super(message);
        }
    }


}
