package com.m2i.atelier.tp12.app;

import com.m2i.atelier.tp12.service.BanquePartagee;

public class TestRaceCondition  {

    public static void main(String[] args) throws InterruptedException{

        BanquePartagee banque=new BanquePartagee(10);

        Runnable tache= ()->{
            for (int i=0; i<20; i++){
                banque.retirer(1);
                System.out.println(i);
            }
        };


        Thread thread1= new Thread(tache,"Tache1");
        Thread thread2= new Thread(tache,"Tache2");

        thread1.start(); thread2.start();
        thread1.join(); thread2.join();

        Thread.sleep(500);
        System.out.println("💰 Solde final : " + banque.getSolde());
    }
}
