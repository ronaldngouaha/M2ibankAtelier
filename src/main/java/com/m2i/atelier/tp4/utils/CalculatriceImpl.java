package com.m2i.atelier.tp4.utils;

public class CalculatriceImpl implements Calculatrice{
    @Override
    public int addition(int a, int b) {
        return a+b;
    }

    @Override
    public double addition(double a, double b) {
        return a+b;
    }

    @Override
    public int addition(int a, int b, int c) {
        return a+b+c;
    }
}
