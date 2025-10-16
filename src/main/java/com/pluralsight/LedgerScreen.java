package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class LedgerScreen {

    // Display the Ledger Screen menu and handle user choices
    public static void display(Scanner scanner, List<Transaction> transactions) {
        boolean backToHome = false;

        while (!backToHome) {
            String options = """
                
                =======================================
                         LEDGER SCREEN
                =======================================
                
                  A) All - Display all entries
                  D) Deposits - Display only deposits
                  P) Payments - Display only payments
                  R) Reports - Pre-defined reports
                  H) Home - Back to home screen
                
                Please select an option: """;

            System.out.print(options);
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    displayAllEntries(transactions);
                    break;
                case "D":
                    displayDeposits(transactions);
                    break;
                case "P":
                    displayPayments(transactions);
                    break;
                case "R":
                    ReportsScreen.display(scanner, transactions);
                    break;
                case "H":
                    backToHome = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    // Display all transactions
    private static void displayAllEntries(List<Transaction> transactions) {
        TransactionManager.displayAllTransactions(transactions);
    }

    // Display only deposits
    private static void displayDeposits(List<Transaction> transactions) {
        List<Transaction> deposits = TransactionManager.getDeposits(transactions);

        if (deposits.isEmpty()) {
            System.out.println("\nNo deposits found.\n");
            return;
        }

        System.out.println("\n=== Deposits Only ===");
        // Display newest first
        for (int i = deposits.size() - 1; i >= 0; i--) {
            System.out.println(deposits.get(i));
        }
        System.out.println();
    }

    // Display only payments
    private static void displayPayments(List<Transaction> transactions) {
        List<Transaction> payments = TransactionManager.getPayments(transactions);

        if (payments.isEmpty()) {
            System.out.println("\nNo payments found.\n");
            return;
        }

        System.out.println("\n=== Payments Only ===");
        // Display newest first
        for (int i = payments.size() - 1; i >= 0; i--) {
            System.out.println(payments.get(i));
        }
        System.out.println();
    }
}