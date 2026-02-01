package com.m2i.atelier.tp1;


import java.util.ArrayList;

public class PrimitifType {


    public static void main(String[] args){


        int age= 25;

        double prix= 19.99;
        char lettre= 'J';
        boolean actif= true;

        System.out.println(age);
        System.out.println(prix);
        System.out.println(lettre);
        System.out.println(actif);


        String test= "Java";

        StringBuilder test2= new StringBuilder();

        test2.append("Java");

        int []dataInt={3,34,53,95,34};

        for (int i = 0; i < 4; i++) {
            System.out.println(dataInt[i]);
        }


        ArrayList <String> dataBase= new ArrayList<>();
        dataBase.add("Alice");
        dataBase.add("Bob");
        dataBase.add("John");

        System.out.println(dataBase.size());

        dataBase.forEach(name->{
            System.out.println(" Current name: "+name);
        });

        for (String name: dataBase){
            System.out.println(" Current name 2: "+name);
        }



        Jour days = Jour.MERCREDI;


    }


    public enum Jour{
        LUNDI,MARDI,MERCREDI,JEUDI, VENDREDI;



    }
}


