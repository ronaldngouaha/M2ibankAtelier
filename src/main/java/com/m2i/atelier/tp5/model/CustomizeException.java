package com.m2i.atelier.tp5.model;

public class CustomizeException {



    public static class SoldeInsuffisantException extends Throwable {
        SoldeInsuffisantException(String message) {
            super(message);
        }
    }


}
