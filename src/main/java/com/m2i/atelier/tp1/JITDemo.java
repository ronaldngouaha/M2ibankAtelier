package com.m2i.atelier.tp1;

public class JITDemo {
    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            long start = System.nanoTime();
            long sum = 0;
            for (int i = 0; i < 100_000_000; i++) {
                sum += i;
            }
            long end = System.nanoTime();
            System.out.println("Itération " + j + " : " + (end - start) + " ms");
        }
    }
}
