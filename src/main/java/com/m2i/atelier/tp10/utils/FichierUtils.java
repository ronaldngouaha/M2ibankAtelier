package com.m2i.atelier.tp10.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class FichierUtils {

 public   static void lireOperations(String chemin){

     Consumer <String> ftDisplay= System.out::println;
     Consumer <String> ftDisplayError= System.err::println;

     //Utilisez BufferedReader et try-with-resources pour lire chaque ligne et afficher les opérations
         try(BufferedReader reader= new BufferedReader(new FileReader(chemin))){
            // String line;

             reader.lines().forEach(ftDisplay);
           /*  while ((line = reader.readLine())!=null){
                 ftDisplay.accept(line);
             }*/
         }catch (IOException ioException){
             ftDisplayError.accept("Exception found: "+ioException.getMessage());
         }

    }
 public   static List<String> extraireFile(String chemin){

     List <String> list= new ArrayList<>();
     Consumer <String> ftDisplay= System.out::println;
     Consumer <String> ftDisplayError= System.err::println;

     //Utilisez BufferedReader et try-with-resources pour lire chaque ligne et afficher les opérations
         try(BufferedReader reader= new BufferedReader(new FileReader(chemin))){
            // String line;

             list.addAll(reader.lines().toList());
           /*  while ((line = reader.readLine())!=null){
                 ftDisplay.accept(line);
             }*/
         }catch (IOException ioException){
             ftDisplayError.accept("Exception found: "+ioException.getMessage());
         }

         return list;
    }


    public static void genererRapport(String chemin, List<String> lignes){

     List<String>fileContent= extraireFile(chemin);

        fileContent.addAll(lignes);

        try{

          Files.write(Paths.get(chemin), fileContent);

            System.out.println("Ecriture realisee avec succes");
        }catch (IOException ioException){
            System.err.println("Error found: "+ioException.getMessage());
        }




    }

    public static void afficherOperationsParType(String chemin, String type){
        List<String>fileContent= extraireFile(chemin);

        fileContent.stream().filter(content->content.contains(type)).forEach(System.out::println);

    }

}
