package com.m2i.atelier.tp12.model;

import com.m2i.atelier.tp12.exceptions.SoldeInsuffisantException;

import java.util.concurrent.locks.ReentrantLock;

public class TestDeadlock  {


    public static  void main(String [] args) throws InterruptedException{

        CompteVerrouille compteSource= new CompteVerrouille("JOHN DOE", 400);
        CompteVerrouille compteCible= new CompteVerrouille("PATRICK DOE", 200);

        Runnable tache= ()->{
            transfererReetranLock(compteSource, compteCible, 200);


        };

        Thread thread1= new Thread(tache, "Thread1");
        Thread thread2= new Thread(tache, "Thread2");

        thread1.start();

        boolean isLook=true;

      //  try{Thread.sleep(200);} catch (InterruptedException e) {throw new RuntimeException(e);}
        int i=0;

        //Cette boucle verifie que les comptes cible et source ne sont pas verrouiller avant de lance la 2ieme tache.
        while (isLook){

            System.out.println( compteSource.getNom()+" isLooked "+compteSource.getLock().isLocked());
            System.out.println(compteCible.getNom()+" isLocked "+compteCible.getLock().isLocked());
           if(!compteSource.getLock().isLocked() && ! compteCible.getLock().isLocked()){
                isLook=false;
                thread2.start();

            }

            i++;

            System.out.println(i);
        }




        Runnable tacheDeadLok= ()->{
            transfertDeadLock(compteSource, compteCible, 300);
        };

        Thread threadDeadLock1= new Thread(tacheDeadLok, "threadDeadLock1");
        Thread threadDeadLock2= new Thread(tacheDeadLok, "threadDeadLock2");

       // threadDeadLock1.start();
        //threadDeadLock2.start();


    }

    public static void transfererReetranLock(CompteVerrouille source, CompteVerrouille cible, double montant){

        boolean sourceLock = false;
        boolean cibleLock = false;

        try {

            //ici on le thread va verrouiller le compte source et cible
            sourceLock=source.getLock().tryLock();
            cibleLock=cible.getLock().tryLock();

            System.out.println(Thread.currentThread().getName()
                    + " verrouille " + source.getNom()+" Status:"+sourceLock);

            System.out.println(Thread.currentThread().getName()
                    + " verrouille " + cible.getNom()+" Status:"+cibleLock);


            //on effectue le transfert si et seulement si les deux comptes sont verouilles
            if(sourceLock && cibleLock){

                //ici on verifit que le debit est bien realise
                if (source.retirer(montant)){
                    cible.deposer(montant);

                    System.out.println(Thread.currentThread().getName()+" Transfert reussi "+montant);

                    System.out.println( Thread.currentThread().getName()+"  Solde Final Compte Cible: "+cible.getSolde());
                    System.out.println( Thread.currentThread().getName()+" Solde Final Compte Source: "+source.getSolde());
                }else{
                    System.out.println(Thread.currentThread().getName()+" Transfert echoue "+montant+", Impossible de faire le retrait dans le compte source");
                }

            }else{

                System.out.println(Thread.currentThread().getName()+" Transfert echoue "+montant);
            }
        }finally {
            //ici on deverouille le deux comptes pour permettre qu'un autre thread dans la fil puisse les utiliser.
            if (sourceLock)source.getLock().unlock();
            if (cibleLock) cible.getLock().unlock();

        }

    }


    public static  void transfertDeadLock(CompteVerrouille source, CompteVerrouille cible, double montant){

        synchronized (source){
            System.out.println(Thread.currentThread().getName()+" Verrouille "+ source.getNom());


            try {Thread.sleep(100);}catch (InterruptedException e){}
            synchronized (cible){
                System.out.println(Thread.currentThread().getName()+" Verrouille "+ cible.getNom());
                if(source.retirer(montant)){
                    cible.deposer(montant);


                    System.out.println( Thread.currentThread().getName()+"  Solde Final Compte Cible: "+cible.getSolde());
                    System.out.println( Thread.currentThread().getName()+" Solde Final Compte Source: "+source.getSolde());
                }else{
                    System.out.println(Thread.currentThread().getName()+" Transfert echoue "+montant+", Impossible de faire le retrait dans le compte source "+source.getNom());

                }
            }
        }

    }
}
