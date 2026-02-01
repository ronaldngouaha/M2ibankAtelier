package com.m2i.atelier.tp4.app;


import com.m2i.atelier.tp4.utils.CalculatriceImpl;

public class TestTp4 {

    public static void  main(String[] args){

        CalculatriceImpl calculatrice = new CalculatriceImpl();

        int a=2; int b=399;
        System.out.println(String.format("->> Result addition %d + %d = %d", a, b,calculatrice.addition(a,b)));


        double c=92.394; double d=3.99;
        System.out.println(String.format("->> Result addition %f + %f = %f ", c, d,calculatrice.addition(c,d)));

        int e=994;
        System.out.println(String.format("->> Result addition %d + %d + %d = %d ", a, b,e, calculatrice.addition(a,b,e)));


    }



}
