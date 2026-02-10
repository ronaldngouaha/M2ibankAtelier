package com.m2i.atelier.tp12.app;

import com.m2i.atelier.tp12.model.*;
import com.m2i.atelier.tp12.service.HistoriqueGlobal;

import java.util.concurrent.Semaphore;

public class TestIncoherence {

    private static final Semaphore limiteur = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException{


        HistoriqueGlobal historiqueGlobal= new HistoriqueGlobal();
        ClientPremium clientPremium= new ClientPremium("John DOE", "ASJAS", "rony@gmail.com",0.2,29.2);

        CompteEpargne compteBancaire= new CompteEpargne((Client) clientPremium,0,10);
        Runnable tache=()->{

            try {
                //Limiter le nombre de threads simultanés a 2
                limiteur.acquire();
                for (int i=0; i<3; i++){
                    System.out.println(Thread.currentThread().getName()
                            + " est lance");
                    historiqueGlobal.ajouter(new Transaction(compteBancaire, 200, TypeOperation.DEPOT));

                    System.out.println(Thread.currentThread().getName()+" add transATION "+i);

                    Thread.sleep(2000);

                }

                historiqueGlobal.getHistorique().forEach(transaction -> {
                    System.out.println(Thread.currentThread().getName()+" read Details :"+transaction.toString());
                });
            }catch (InterruptedException interruptedException){
                Thread.currentThread().interrupt();
            }finally {
                //Rend un permit
                limiteur.release();
            }
        };


        for (int i = 1; i <= 5; i++) {
           Thread currentThread= new Thread(tache, "Thread-" + i);
           currentThread.start();
           currentThread.join();
        }

        System.out.println("📊 Nombre de transactions : "
                + historiqueGlobal.getHistorique().size());


    }
}
