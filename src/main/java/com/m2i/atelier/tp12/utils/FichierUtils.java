package com.m2i.atelier.tp12.utils;

import com.m2i.atelier.tp12.model.ClientPremium;
import com.m2i.atelier.tp12.model.ClientR;
import com.m2i.atelier.tp12.model.CompteBancaire;

import com.m2i.atelier.tp12.model.Transaction;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FichierUtils {

     final static String directory ="public/media/account/";
     final static String directoryMain ="public/media/";


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


    public static void lireClients(String chemin){


       try (BufferedReader reader= new BufferedReader(new FileReader(chemin))){

           String line;

           while ((line=reader.readLine())!=null){
               String[] clientRecord= line.split(";");
               int id= Integer.parseInt(clientRecord[0]);
               String nom= clientRecord[1];
               String prenom= clientRecord[2];
               double solde= Double.parseDouble(clientRecord[3]);

               ClientR client= new ClientR(id, nom, prenom, solde );

               System.out.println(client.toString());
           }

       } catch (IOException e) {
           System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());

       } catch (NumberFormatException e) {
           System.err.println("Erreur de format dans le fichier : " + e.getMessage());
       }
    }
    public static void lireClients2(String chemin){

        try (FileInputStream fis = new FileInputStream(new File(chemin));
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }

       } catch (IOException e) {
           System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
       } catch (NumberFormatException e) {
           System.err.println("Erreur de format dans le fichier : " + e.getMessage());
       }
    }

    public static void exporterTransactionsCSV(String filename, List <Transaction> transactions){

       DateTimeFormatter formatter= DateTimeFormatter.ISO_INSTANT;

           try(BufferedWriter writer= Files.newBufferedWriter(Paths.get(directoryMain,filename))){

               writer.write("id;type;montant;date");
               for (Transaction t: transactions){

                   String ligne = t.getId() + ";" +
                           t.getType() + ";" +
                           t.getMontant() + ";" +
                           formatter.format(t.getHorodatage());

                   writer.write(ligne);
               }

               System.out.println("✅ Transactions exportées avec succès vers : " + directoryMain+filename);

           }catch (IOException exception){
               System.err.println("❌ Erreur lors de l’export des transactions : " + exception.getMessage());
           }

    }
    public static void exporterTransactionsXLSX(String filename, List <Transaction> transactions){

       try (Workbook workbook= new XSSFWorkbook ()){

           Sheet sheet= workbook.createSheet("Sheet1");

           Row row= sheet.createRow(0);
           row.createCell(0).setCellValue("Id");
           row.createCell(1).setCellValue("Type");
           row.createCell(2).setCellValue("Montant");
           row.createCell(3).setCellValue("Date");
           int i=1;
           for (Transaction transaction: transactions){

               Row row1= sheet.createRow(i);
               row1.createCell(0).setCellValue(transaction.getId());
               row1.createCell(1).setCellValue(transaction.getType().toString());
               row1.createCell(2).setCellValue(transaction.getMontant());
               row1.createCell(3).setCellValue(transaction.getDate());
               i++;
           }
           try(FileOutputStream writer= new FileOutputStream(directoryMain+filename)){

               workbook.write(writer);

               System.out.println("✅ Transactions exportées avec succès vers : " + directoryMain+filename);

           }catch (IOException exception){
               System.err.println("❌ Erreur lors de l’export des transactions : " + exception.getMessage());
           }
       } catch (Exception e) {
           System.err.println("❌ Erreur lors de l’export des transactions : " + e.getMessage());

       }
    }

    public static void genererRapportExcel(String filename, List <ClientR> clientRS){

       try (Workbook workbook= new XSSFWorkbook ()){

           Sheet sheet= workbook.createSheet("Sheet1");

           Row row= sheet.createRow(0);
           row.createCell(0).setCellValue("Id");
           row.createCell(1).setCellValue("Nom");
           row.createCell(1).setCellValue("Prenom");
           row.createCell(2).setCellValue("Solde Total");
           row.createCell(3).setCellValue("Date");
           int i=1;
           for (ClientR clientR: clientRS){

               Row row1= sheet.createRow(i);
               row1.createCell(0).setCellValue(clientR.id());
               row1.createCell(1).setCellValue(clientR.nom());
               row1.createCell(2).setCellValue(clientR.prenom());
               row1.createCell(3).setCellValue(clientR.solde());
               i++;
           }
           try(FileOutputStream writer= new FileOutputStream(directoryMain+filename)){

               workbook.write(writer);

               System.out.println("✅ Transactions exportées avec succès vers : " + directoryMain+filename);

           }catch (IOException exception){
               System.err.println("❌ Erreur lors de l’export des transactions : " + exception.getMessage());
           }
       } catch (Exception e) {
           System.err.println("❌ Erreur lors de l’export des transactions : " + e.getMessage());

       }
    }

}
