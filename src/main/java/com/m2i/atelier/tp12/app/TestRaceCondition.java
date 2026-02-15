package com.m2i.atelier.tp12.app;

import com.m2i.atelier.tp12.service.BanquePartagee;

import java.security.PublicKey;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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

        Table table= new Table();
        Chaise chaise= new Chaise();

        Thread alice= new Thread(()->{
            while (true){
                if(table.verrou.tryLock()){
                    try {
                        if(chaise.verrou.tryLock()){
                            try {
                                System.out.println("Alice A LA TABLE ET LA CHAISE");
                                break;
                            }finally {
                                chaise.verrou.unlock();
                            }
                        }
                    }finally {
                        table.verrou.unlock();
                    }
                }
                System.out.println("Alice attend");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });


        Thread bob= new Thread(()->{
            while (true){
                if(chaise.verrou.tryLock()){
                    try {
                        if(table.verrou.tryLock()){
                            try {
                                System.out.println("Bob A LA TABLE ET LA CHAISE");
                                break;
                            }finally {
                                table.verrou.unlock();
                            }
                        }
                    }finally {
                        chaise.verrou.unlock();
                    }
                }
                System.out.println("Bob attend");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread threadAlice= new Thread(alice, "Alice");
        Thread threadBob= new Thread(bob, "Bob");
        threadAlice.start();
        threadBob.start();

    }

}


   class Chaise{
       public  static ReentrantLock verrou= new ReentrantLock();
        public Chaise(){}
   }

   class Table{
    public  static ReentrantLock verrou= new ReentrantLock();
    public Table(){}
    }
