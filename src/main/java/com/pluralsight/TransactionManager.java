package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    // load transaction
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

    // Save transaction to the CSV file
    public static void saveTransactionToFile(String fileName, Transaction transactions){
    try{
        FileWriter fileWriter = new FileWriter(fileName,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(transactions.toCsvString());
        bufferedWriter.close();
        System.out.println("Transaction saved successfully! You can look at it at: " +fileName);
    } catch (IOException e){
        System.out.println("Error saving transaction: " +e.getMessage());
    }
    }

    // Display all transactions - newest first
    public static void displayAllTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("\nNo transactions found.\n");
            return;
        }

        System.out.println("\n=== All Transactions ===");
        // Loop to show newest first
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
        System.out.println();
    }

    // Get only deposits
    public static List<Transaction> getDeposits(List<Transaction> transactions) {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.isDeposit()) {
                deposits.add(transaction);
            }
        }
        return deposits;
    }

    // Get only payments
    public static List<Transaction> getPayments(List<Transaction> transactions) {
        List<Transaction> payments = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.isPayment()) {
                payments.add(transaction);
            }
        }
        return payments;
    }

    // Get transactions within a date range
    public static List<Transaction> getTransactionsByDateRange(List<Transaction> transactions, LocalDate startDate, LocalDate endDate) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions) {
            // Check if transaction date is between start and end (inclusive)
            if (!transaction.getDate().isBefore(startDate) && !transaction.getDate().isAfter(endDate)) {
                filtered.add(transaction);
            }
        }
        return filtered;
    }

    // Search transactions by vendor name
    public static List<Transaction> getTransactionsByVendor(List<Transaction> transactions, String vendorName) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions) {
            // Case-insensitive partial match
            if (transaction.getVendor().toLowerCase().contains(vendorName.toLowerCase())) {
                filtered.add(transaction);
            }
        }
        return filtered;
    }
    // Custom search
    public static List<Transaction> customSearch(List<Transaction> transactions,
                                                 LocalDate startDate, LocalDate endDate,
                                                 String description, String vendor,
                                                 Double amount) {
        List<Transaction> filtered = new ArrayList<>();

        for (Transaction transaction : transactions) {
            boolean matches = true;

            // Filter by start date
            if (startDate != null && transaction.getDate().isBefore(startDate)) {
                matches = false;
            }

            // Filter by end date
            if (endDate != null && transaction.getDate().isAfter(endDate)) {
                matches = false;
            }

            // Filter by description & case-sensitive
            if (description != null && !description.isEmpty()) {
                if (!transaction.getDescription().toLowerCase().contains(description.toLowerCase())) {
                    matches = false;
                }
            }

            // Filter by vendor & case-insensitive
            if (vendor != null && !vendor.isEmpty()) {
                if (!transaction.getVendor().toLowerCase().contains(vendor.toLowerCase())) {
                    matches = false;
                }
            }

            // Filter by amount - exact match
            if (amount != null) {
                if (Math.abs(transaction.getAmount() - amount) > 0.01) {
                    matches = false;
                }
            }

            if (matches) {
                filtered.add(transaction);
            }
        }

        return filtered;
    }
}
