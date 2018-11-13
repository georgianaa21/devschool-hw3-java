package com.ing.devschool;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import java.io.*;
import java.util.HashMap;
import java.util.Map;



public class HomeWork {

    public static void main(String[] args) {
        // todo:
        Map<Integer, Transaction> transactionsMap =  new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("F:\\devschool\\hw3-java\\src\\main\\resources\\bakery-transactions.csv"));
            CSVReader csvReader = new CSVReader(br);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                try{
                    String date = nextRecord[0];
                    String time = nextRecord[1];
                    Integer transactionId = Integer.parseInt(nextRecord[2]);
                    String item = nextRecord[3];
                    if(transactionsMap.containsKey(transactionId)) {
                        Transaction tr = transactionsMap.get(transactionId);
                        tr.add(item);
                        transactionsMap.put(transactionId, tr);
                    }
                    else {
                        Transaction tr = new Transaction(transactionId, date, time);
                        tr.add(item);
                        transactionsMap.put(transactionId, tr);
                    }
                }
                catch(NumberFormatException ex){
                    System.out.println(ex.toString());
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            PrintWriter out = new PrintWriter("bakery-summary.json");
            for(Map.Entry<Integer, Transaction> entry : transactionsMap.entrySet()) {
                String json = null;
                try {
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entry.getValue());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                out.println(json);
            }
            out.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
