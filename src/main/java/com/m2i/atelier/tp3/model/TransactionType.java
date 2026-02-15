package com.m2i.atelier.tp3.model;



 enum TransactionType {

    DEBIT ("DEBIT"), CREDIT("CREDIT"), TRANSFER("TRANSFER");
    private final String type;

    //constructeur qui en prend en entree l'attribut type
     TransactionType(String type){
    this.type=type;
    }
    //getter qui renvoit le type de transaction
    public String getType(){
         return this.type;
    }


}
