package com.m2i.atelier.tp11.utils;

import com.m2i.atelier.tp11.model.CompteBancaire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FichierUtils {

     final static String directory ="public/media/account/";


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

            System.out.println("Écriture réalisée avec succes");
        }catch (IOException ioException){
            System.err.println("Error found: "+ioException.getMessage());
        }
    }

    public static void afficherOperationsParType(String chemin, String type){
        List<String>fileContent= extraireFile(chemin);
        fileContent.stream().filter(content->content.contains(type)).forEach(System.out::println);

    }

    public static void genererReleveAvecHorodatage(CompteBancaire compte){

        LocalDateTime now= LocalDateTime.now();
        String date= now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        List<String> contentFile=new ArrayList<>();
        String filename=directory +compte.getId()+"_txt";
        try (BufferedReader reader= new BufferedReader(new FileReader(filename))){
             contentFile=extraireFile(filename);
            contentFile.add("Update Time: "+ date);
            Files.write(Paths.get(filename),contentFile);

            System.out.println("File updated "+filename);
        }catch(IOException exception){
            System.out.println(exception.getMessage());
            /// Nous allons creer le fichier a ce niveau comme il n'existe pas encore.
            contentFile.add("Nom du client : "+compte.getClient().getNom());
            contentFile.add("Numéro de compte : "+compte.getId());
            contentFile.add("Solde de compte : "+compte.getSolde()+" $");
            contentFile.add("Date/heure de génération : "+date);
            try {
                Files.write(Paths.get(filename),contentFile);
                System.out.println("File created "+filename);
            } catch (IOException e) {
               // throw new RuntimeException(e);
                System.out.println(e.getMessage());
            }
        }
    }
}
