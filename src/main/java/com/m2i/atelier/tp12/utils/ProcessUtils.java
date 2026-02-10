package com.m2i.atelier.tp12.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ProcessUtils {
    public static void lancerScriptSauvegarde(String ...command){

        ProcessBuilder processBuilder= new ProcessBuilder(command);

        try {
         Process process=   processBuilder.start();


          //  System.out.println(process.info());
            suivreProcessus(process);

            BufferedReader reader= new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println(STR."\{command} ------>>>>");
            while ((line=reader.readLine())!=null){
                System.out.println(line);
            }
            System.out.println("END InputStream----->>>>>");


                 /*
            Toujours lire getInputStream() et getErrorStream() pour éviter que les tampons de sortie (stdout) ou d’erreur (stderr)
             se remplissent et que le processus se bloque.
            * */
            BufferedReader output = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

           if(output.lines().findAny().isPresent()){
               System.out.println("output printed counted :"+ output.lines().count());
               while ((line = output.readLine()) != null) {
                   System.out.println(line);
               }

               System.out.println("END Output----->>>>>");
           }

            BufferedReader errors = new BufferedReader(
                    new InputStreamReader(process.getErrorStream())
            );

            if(errors.lines().findAny().isPresent()){

                System.err.println("Error printed counted :"+ errors.lines().count());
                while ((line = errors.readLine()) != null) {
                    System.err.println(line);
                }
                System.out.println("END Errors----->>>>>");

            }
        } catch (IOException e) {
            // Gérez les exceptions et affichez un message d’erreur si le processus échoue.
            System.err.println(e.getMessage());
            //throw new RuntimeException(e);
        }

    }

    public static void suivreProcessus(Process process){

        Optional<ProcessHandle> handle= ProcessHandle.of(process.pid());

        handle.ifPresent(processHandle -> System.out.println(
                STR."->>PID: \{processHandle.pid()} User: \{processHandle.info().user()} Instant demarrage: \{processHandle.info().startInstant().get()} Durree Exec: \{processHandle.info().totalCpuDuration().get().toString()}"
        ));

    }



}
