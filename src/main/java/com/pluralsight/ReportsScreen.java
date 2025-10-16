package com.pluralsight;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReportsScreen {

    // Display the Reports Screen menu and handle user choices
    public static void display(Scanner scanner, List<Transaction> transactions) {
        boolean backToLedger = false;

        while (!backToLedger) {
            String options = """
                
                =======================================
                         REPORTS SCREEN
                =======================================
                
                  1) Month To Date
                  2) Previous Month
                  3) Year To Date
                  4) Previous Year
                  5) Search by Vendor
                  0) Back - Return to Ledger
                
                Please select an option: """;

            System.out.print(options);
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayMonthToDate(transactions);
                    break;
                case "2":
                    displayPreviousMonth(transactions);
                    break;
                case "3":
                    displayYearToDate(transactions);
                    break;
                case "4":
                    displayPreviousYear(transactions);
                    break;
                case "5":
                    searchByVendor(scanner, transactions);
                    break;
                case "0":
                    backToLedger = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    // Display Month To Date transactions
    private static void displayMonthToDate(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);

        List<Transaction> filtered = TransactionManager.getTransactionsByDateRange(transactions, startOfMonth, today);

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found for this month.\n");
            return;
        }

        System.out.println("\n=== Month To Date (" + startOfMonth + " to " + today + ") ===");
        displayNewestFirst(filtered);
    }

    // Display Previous Month transactions
    private static void displayPreviousMonth(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate startOfLastMonth = LocalDate.of(lastMonth.getYear(), lastMonth.getMonth(), 1);
        LocalDate endOfLastMonth = startOfLastMonth.plusMonths(1).minusDays(1);

        List<Transaction> filtered = TransactionManager.getTransactionsByDateRange(transactions, startOfLastMonth, endOfLastMonth);

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found for previous month.\n");
            return;
        }

        System.out.println("\n=== Previous Month (" + startOfLastMonth + " to " + endOfLastMonth + ") ===");
        displayNewestFirst(filtered);
    }

    // Display Year To Date transactions
    private static void displayYearToDate(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(today.getYear(), 1, 1);

        List<Transaction> filtered = TransactionManager.getTransactionsByDateRange(transactions, startOfYear, today);

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found for this year.\n");
            return;
        }

        System.out.println("\n=== Year To Date (" + startOfYear + " to " + today + ") ===");
        displayNewestFirst(filtered);
    }

    // Display Previous Year transactions
    private static void displayPreviousYear(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        int lastYear = today.getYear() - 1;
        LocalDate startOfLastYear = LocalDate.of(lastYear, 1, 1);
        LocalDate endOfLastYear = LocalDate.of(lastYear, 12, 31);

        List<Transaction> filtered = TransactionManager.getTransactionsByDateRange(transactions, startOfLastYear, endOfLastYear);

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found for previous year.\n");
            return;
        }

        System.out.println("\n=== Previous Year (" + startOfLastYear + " to " + endOfLastYear + ") ===");
        displayNewestFirst(filtered);
    }

    // Search by Vendor
    private static void searchByVendor(Scanner scanner, List<Transaction> transactions) {
        System.out.print("\nEnter vendor name to search: ");
        String vendorName = scanner.nextLine();

        List<Transaction> filtered = TransactionManager.getTransactionsByVendor(transactions, vendorName);

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found for vendor: " + vendorName + "\n");
            return;
        }

        System.out.println("\n=== Transactions for Vendor: " + vendorName + " ===");
        displayNewestFirst(filtered);
    }

    // Helper method to display transactions newest first
    private static void displayNewestFirst(List<Transaction> transactions) {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
        System.out.println();
    }
}