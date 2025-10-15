package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class LedgerApp {
    static Scanner scanner = new Scanner(System.in);
    static List<Transaction> transactions = TransactionManager.loadTransactionFromFile("src/data/transactions.csv");

    public static void main(String[] args) {

        // Test for transaction manager - tbd
//        System.out.println("=== TEST 1: Loading transactions ===");
//        List<Transaction> transactions = TransactionManager.loadTransactionFromFile("src/data/transactions.csv");
//        System.out.println("Loading transactions..." + transactions.size() + " transactions");
//        for (Transaction transaction : transactions) {
//            System.out.println(transaction);
//        }
//
//        System.out.println("\n=== TEST 2: Adding a new transaction ===");
//        // Test 2: Create a new transaction
//        Transaction newTransaction = new Transaction(
//                LocalDate.of(2025, 10, 15),
//                LocalTime.of(14, 30, 0),
//                "Coffee and donut",
//                "Dunkin Donuts",
//                -8.50
//        );
//
//        // Save it to the file
//        TransactionManager.saveTransactionToFile("src/data/transactions.csv", newTransaction);
//
//        System.out.println("\n=== TEST 3: Loading transactions again ===");
//        // Test 3: Load again to verify it was saved
//        transactions = TransactionManager.loadTransactionFromFile("src/data/transactions.csv");
//        System.out.println("Loaded " + transactions.size() + " transactions:");
//        for (Transaction transaction : transactions) {
//            System.out.println(transaction);
//        }

        // Comment out the menu for now - we'll use it later
        boolean isMenuStillRunning = false;
        while (!isMenuStillRunning) {
            isMenuStillRunning = homeScreenDisplay();
        }
        System.out.println("Thank you for using the Accounting Ledger!");
        scanner.close();

    }

    public static boolean homeScreenDisplay(){
        String options = """
                =======================================
                    ACCOUNTING LEDGER APPLICATION
                =======================================
                
                Home Screen:
                  D) Add Deposit
                  P) Make Payment (Debit)
                  L) Ledger
                  X) Exit
                
                Please select an option:""";

        System.out.print(options);
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "D":
                addDeposit();
                break;
            case "P":
                makePayment();
                break;
            case "L":
                displayLedgerScreen();
                break;
            case "X":
                return true;  // Exit program
            default:
                System.out.println("Invalid option. Try again.");
                break;
        }

        return false;  // Continue program
    }

    public static void addDeposit() {
        System.out.println("\n--- Add Deposit ---");
        System.out.println("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine().trim());

        System.out.println("Enter the time (HH:MM:SS): )");
        LocalTime time = LocalTime.parse(scanner.nextLine().trim());

        System.out.println("Enter the description: ");
        String description = scanner.nextLine().trim().toUpperCase();

        System.out.println("Enter the vendor (Who paid you?): ");
        String vendor = scanner.nextLine().trim();

        System.out.println("Enter the amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Transaction deposit = new Transaction(date, time, description, vendor, amount);

        transactions.add(deposit);
        TransactionManager.saveTransactionToFile("src/data/transactions.csv", deposit);

        System.out.println("\n Deposit added successfully!");
    }

    public static void makePayment() {
        System.out.println("\n--- Make Payment ---");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        System.out.println("Enter description: ");
        String description = scanner.nextLine().trim();

        System.out.println("Enter the vendor (Who you paid?): ");
        String vendor = scanner.nextLine().trim();

        System.out.println("Enter the amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Transaction payment = new Transaction(date, time, description, vendor, -amount);
        transactions.add(payment);
        TransactionManager.saveTransactionToFile("src/data/transactions.csv", payment);
        System.out.println("\n Payment added successfully!");
    }

    public static void displayLedgerScreen() {
        System.out.println("\n--- Ledger Screen ---");
        System.out.println("Coming soon!");
    }
}