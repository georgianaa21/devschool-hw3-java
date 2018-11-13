package com.ing.devschool;

import java.util.*;


public class Transaction {
    private Integer transactionId;
    private String date;
    private String time;
    private HashMap<String, Integer> itemsSummary = new HashMap<String, Integer>();

    public Transaction(int transactionId, String date, String time) {
        this.transactionId = transactionId;
        this.date = date;
        this.time = time;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, Integer> getItemsSummary() {
        return itemsSummary;
    }

    public void add(String item) {
        if(this.itemsSummary.containsKey(item)) {
            Integer val = this.itemsSummary.get(item);
            val++;
            this.itemsSummary.put(item, val);

        }
        else {
            this.itemsSummary.put(item, 1);
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", itemsSummary=" + itemsSummary +
                '}';
    }
}
