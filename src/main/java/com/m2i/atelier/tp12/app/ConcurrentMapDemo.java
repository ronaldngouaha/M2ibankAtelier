package com.m2i.atelier.tp12.app;

import com.m2i.atelier.tp12.service.BanquePartagee;
import com.m2i.atelier.tp12.service.ClientService;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class ConcurrentMapDemo {

    private static final Semaphore limiter= new Semaphore(2);
    public static void main (String[] args){



        Map<String, BanquePartagee> comtpes= new ConcurrentHashMap<>();
        Runnable depotTask= ()->{

         try {
             limiter.acquire();
             for (int i=0; i< 10; i ++){
                 comtpes.put("Account-"+i,   new BanquePartagee(new Random().nextDouble()));
             }

             comtpes.forEach((acc, solde)->{

                 solde.depot(20);
                 solde.retirer(9);
                 System.out.println(Thread.currentThread().getName()+" solde: "+solde.getSolde());
             });
         }catch (InterruptedException interruptedException){
             Thread.currentThread().interrupt();
         }finally {
             limiter.release();
         }

        };

        for (int j=0; j<5; j++){
            new Thread(depotTask, "Thread- "+j).start();
        }
    }
}
