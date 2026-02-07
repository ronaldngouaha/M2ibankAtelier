package com.m2i.atelier.tp9.app;

import com.m2i.atelier.tp7.model.Client;
import com.m2i.atelier.tp9.service.Calculateur;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class M2iBankApp {


    public static void main(String[] args){

        Calculateur calculateur= new Calculateur() {
            @Override
            public int calculer(int a, int b) {
                return Integer.sum(a,b);
            }
        };

        //Dans M2iBankApp, utilisez cette interface avec une expression lambda pour afficher le resulat d’un calcul
        System.out.println(calculateur.calculer(20,39));


        //En Java, BiPredicate est une interface fonctionnelle qui prend deux paramètres et retourne un booléen (true ou false)
        BiPredicate<Integer, Integer> estSuperieur = (a, b) -> a > b;
        System.out.println(estSuperieur.test(10, 5)); // true


        Predicate<String> estLong= string -> string.length()>=5;
        System.out.println(estLong.test("Maman"));
        System.out.println(estLong.test("papa"));

        //Function<T, R> → prend T, retourne R
        Function<String,Integer> ftTest= String::length;
        Function<Integer,Integer> ftTest2= non->non*22;

        System.out.println(ftTest.apply("papa"));//.apply() → exécute la fonction
        System.out.println(ftTest2.apply(90));




        BiFunction<Integer, Integer, Integer> ftAddition= Integer::sum; //BiFunction<T, U, R> → (T, U) -> R effectue une operation sur T ET U Et retourne le resultat dans R
        BiFunction<Integer, Integer, Integer> ftMax= Integer::max;
        BiFunction<Integer, Integer, Integer> ftMin= Integer::min;


        //pour afficher un message
        Consumer<String> display= System.out::println;
        display.accept("Hello World");




        Supplier<Double> supplierDouble= Math::random; //Supplier<T> → ne prend aucun paramètre
        Supplier<Integer> supplierInt= ()-> new Random().nextInt(); //Supplier<T> → ne prend aucun paramètre

        display.accept(String.valueOf(supplierDouble.get()));//.get() → retourne la valeur
        display.accept(String.valueOf(supplierInt.get()));//.get() → retourne la valeur


        int a=supplierInt.get(); int b=supplierInt.get();
        System.out.printf("%d+%d= %d\n",a,b, ftAddition.apply(a,b) );

        System.out.printf("%d et  %d le max est %d\n",a,b, ftMax.apply(a,b) );
        System.out.printf("%d et %d le min est %d\n",a,b, ftMin.apply(a,b) );

        BiFunction<Integer, Integer, String> concat = (x, y) -> x + "-" + y;
        System.out.println(concat.apply(3, 9)); // "3-9"




        //En Java, BiConsumer est une interface fonctionnelle qui prend deux paramètres et ne retourne rien (void).
        //Elle est dans java.util.function.
        BiConsumer<Integer, String> cf= (st, df)-> System.out.println(" DE "+st+" d "+df);
        cf.accept(2,"sfs");

        List<String> clients= new ArrayList<>();

        clients.add("JOHN");
        clients.add("ALICE");
        clients.add("PATRICK");
        clients.add("JAMILA");

        System.out.println("\n**************************************");
        System.out.println("afficherClients");
        System.out.println("**************************************");
        afficherClients(clients);
        System.out.println("\n**************************************");



        System.out.println("trierEtAfficherClients");
        System.out.println("**************************************");
        trierEtAfficherClients(clients);
        System.out.println("\n**************************************");


        Set <Integer> comptes= new HashSet<>();
        comptes.add(90);
        comptes.add(3);
        comptes.add(3);
        comptes.add(6);
        comptes.add(9);
        comptes.add(90);


        System.out.println("afficherComptesUniques");
        System.out.println("**************************************");
        System.out.println(comptes.toString());
        afficherComptesUniques(comptes);

        System.out.println("\n**************************************");



        Map<Integer, Double> compteAI= new HashMap<>();

        compteAI.put(0, 239.302);
        compteAI.put(1, 90993.92);
        compteAI.put(2, 83.03);
        compteAI.put(5, 30009.03);

        System.out.println("afficherSoldeParCompte");
        System.out.println("**************************************");
        System.out.println(compteAI.toString());
        afficherSoldeParCompte(compteAI);

        System.out.println("\n**************************************");

        System.out.println("afficherComptesSup1000");
        System.out.println("**************************************");
        System.out.println(compteAI.toString());
        afficherComptesSup1000(compteAI.values().stream().toList());

        System.out.println("\n**************************************");


        System.out.println("filtrerClientsParInitiale with A");
        System.out.println("**************************************");
        System.out.println(clients.toString());
        display.accept(filtrerClientsParInitiale(clients,'A').toString());

        System.out.println("\n**************************************");


    }


    public static void afficherClients(List<String> clients){

        //Affiche tous les noms de clients avec leur index.
        IntStream.range(0, clients.size())
                .forEach(i -> System.out.println(i + " - " + clients.get(i)));
    }

    //cette methode Trie la liste avec Collections.sort() puis l’affiche.
    public static void trierEtAfficherClients(List<String> clients){

        Collections.sort(clients);
        clients.forEach(System.out::println);
    }

    //Cette methode Affiche tous les numéros de comptes sans doublons.
    public static void afficherComptesUniques(Set<Integer> numerosComptes){
        if (numerosComptes == null || numerosComptes.isEmpty()) {
            System.out.println("Aucun compte à afficher.");
            return;
        }
        numerosComptes.stream().distinct().forEach(System.out::println);
    }

    //Affiche chaque numéro de compte et son solde.
    public static void afficherSoldeParCompte(Map<Integer, Double> comptes){

        if (comptes == null || comptes.isEmpty()) {
            System.out.println("Aucun compte à afficher.");
            return;
        }
        comptes.forEach((index,solde)-> System.out.println("Compte: "+index+"; Solde: "+solde+" $"));
    }

    //Cette methode filtre la liste dont les soldes sont >1000 puis les affiche
    public static void afficherComptesSup1000(List<Double> soldes){
        soldes.stream().filter(solde->solde>1000).forEach(System.out::println);
    }


//Retourne une liste des clients dont le nom commence par initiale (utilise filter et collect).
    public static List<String> filtrerClientsParInitiale(List<String> clients, char initiale){

      return   clients.stream()
                .filter(string -> string.startsWith(String.valueOf(initiale)))
                .collect(Collectors.toList());


    }
}
