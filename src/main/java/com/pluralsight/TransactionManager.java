package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
public static List<Transaction> loadTransactionFromFile(String fileName){
    List<Transaction> transactions = new ArrayList<Transaction>();
    try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        bufferedReader.readLine();

        String line;
        while((line = bufferedReader.readLine())!= null){
            String[] transactionData = line.split("\\|");
            LocalDate date = LocalDate.parse(transactionData[0]);
            LocalTime time = LocalTime.parse(transactionData[1]);
            String description = transactionData[2];
            String vendor = transactionData[3];
            double amount = Double.parseDouble(transactionData[4]);

            //transaction object
            Transaction transaction = new Transaction(date,time,description,vendor,amount);

            // add it to the list
            transactions.add(transaction);
        }
        bufferedReader.close();
    } catch (IOException e) {
        System.out.println("Error reading file: " +e.getMessage());
    }
    return transactions;
}
}
