package com.m2i.atelier.tp3.model;

 enum TransactionType {

    DEBIT ("DEBIT"), CREDIT("CREDIT"), TRANSFER("TRANSFER");



    private final String type;

     TransactionType(String type){
    this.type=type;
    }
    public String getType(){
         return this.type;
    }


}
