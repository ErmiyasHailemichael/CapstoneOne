package com.pluralsight;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        // Plan of the day - decide how many classes I need - 4 classes
        // Main -
        // Transaction
        // LedgerApp
        // FileManager
        /*
        Main.java
├─ Creates Scanner
├─ Creates FileManager
├─ Creates LedgerApp (pass in Scanner and FileManager)
└─ Calls ledgerApp.run()

LedgerApp.java
├─ Has the Scanner and FileManager
├─ displayHomeScreen()
├─ displayLedgerScreen()
├─ displayReportsScreen()
├─ addDeposit()
├─ makePayment()
└─ run() - main application loop

FileManager.java
├─ loadTransactions() - read CSV
├─ saveTransaction() - write to CSV
└─ Manages the ArrayList<Transaction>

Transaction.java
├─ Fields: date, time, description, vendor, amount
├─ Constructor, getters
├─ toString()
└─ toCsvString()
         */

    }
}