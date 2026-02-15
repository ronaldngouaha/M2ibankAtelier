package com.m2i.atelier.tp12.app;

import java.util.concurrent.*;

public class TestPool {

    public static void main(String[] args) {


        int nbCoeurs = Runtime.getRuntime().availableProcessors();
        System.out.println("Nombre de cœurs : " + nbCoeurs);


        //Elle crée un pool de threads avec un nombre fixe de threads.
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        /*
        🔹 Si 3 tâches arrivent → elles s’exécutent en parallèle.
        🔹 Si 10 tâches arrivent →
        → 3 s’exécutent
        → 7 attendent dans une file d’attente
        Les threads sont réutilisés (pas recréés à chaque tâche).
        * */
        for(int i=0;i<10;i++){
            int finalI = i;
            executorService.submit(
                    //Soumet une tâche à exécuter par un thread du pool. La tâche est définie comme une expression lambda qui affiche un message indiquant quelle tâche est exécutée et par quel thread.
                    ()->{
                System.out.println("Tache "+ finalI+" executée par "+Thread.currentThread().getName());
            }
            );

        }


            //Arrête le pool de threads de manière ordonnée, en attendant que toutes les tâches soumises soient terminées avant de fermer les threads.
        executorService.shutdown();

        //Elle crée un pool de threads avec un seul thread. Cela signifie que les tâches soumises seront exécutées séquentiellement,
        // une à la fois, par le même thread.
        ExecutorService service= Executors.newSingleThreadExecutor();
        //Soumet une tâche à exécuter par un thread du pool. La tâche est définie comme une expression lambda qui affiche un message indiquant quelle tâche est exécutée et par quel thread.
      for (int j=0; j<5; j++) {
          int finalJ = j;
          service.submit(() -> {
              System.out.println("Tache single " + finalJ + " executée par " + Thread.currentThread().getName());
          });


      }
        //Arrête le pool de threads de manière ordonnée, en attendant que toutes les tâches soumises soient terminées avant de fermer les threads.
        service.shutdown();



      //Elle crée un pool de threads qui peut planifier des tâches à exécuter après un délai spécifié ou périodiquement.
        // Cela permet d'exécuter des tâches de manière différée ou récurrente.
      ScheduledExecutorService scheduler= Executors.newScheduledThreadPool(2);

      for (int k=0; k<5; k++){
          int finalK = k;
          //Soumet une tâche à exécuter après un délai spécifié. La tâche est définie comme une expression lambda qui affiche un message indiquant quelle tâche est exécutée et par quel thread.
          scheduler.schedule(()->{
              System.out.println("Tache scheduled "+ finalK+" executée par "+Thread.currentThread().getName()+" Executé à : "+System.currentTimeMillis());
          }, 2, TimeUnit.SECONDS);
      }

        try {
            //Attends que toutes les tâches soumises soient terminées ou que le délai spécifié soit écoulé avant de continuer.
            // Cela garantit que le programme ne se termine pas avant que les tâches planifiées soient exécutées.
            if(scheduler.awaitTermination(5, TimeUnit.SECONDS))
                //Arrête le pool de threads de manière ordonnée, en attendant que toutes les tâches soumises soient terminées avant de fermer les threads.
                scheduler.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        for(int k=0; k<5; k++){
            int finalK = k;
            //Soumet une tâche à exécuter périodiquement avec un délai initial spécifié et un intervalle de répétition.
            // La tâche est définie comme une expression lambda qui affiche un message indiquant quelle tâche est exécutée et par quel thread.
            scheduler.scheduleAtFixedRate(()->{
                System.out.println("Tache scheduled at fixed rate "+ finalK+" executée par "+Thread.currentThread().getName()+" Executé à : "+System.currentTimeMillis());
            }, 1, 3, TimeUnit.SECONDS);
        }
        //Arrête le pool de threads de manière ordonnée, en attendant que toutes les tâches soumises soient terminées avant de fermer les threads.
        scheduler.schedule(()-> scheduler.shutdown(),10, TimeUnit.SECONDS);

    }


}
