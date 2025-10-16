package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class LedgerApp {
    static Scanner scanner = new Scanner(System.in);
    static List<Transaction> transactions = TransactionManager.loadTransactionFromFile("src/data/transactions.csv");

    public static void main(String[] args) {

        // Test for transaction manager
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
//        TransactionManager.saveTransactionToFile("src/data/transactions.csv", newTransaction);
//
//        System.out.println("\n=== TEST 3: Loading transactions again ===");
//        // Test 3: Load again to verify it was saved
//        transactions = TransactionManager.loadTransactionFromFile("src/data/transactions.csv");
//        System.out.println("Loaded " + transactions.size() + " transactions:");
//        for (Transaction transaction : transactions) {
//            System.out.println(transaction);
//        }

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
                return true;
            default:
                System.out.println("Invalid option. Try again.");
                break;
        }

        return false;
    }

    public static void addDeposit() {
        System.out.println("\n=== Add Deposit ===");

        // Ask if the user want to use today date
        System.out.print("Use today's date? (Y/N): ");
        String useTodayChoice = scanner.nextLine().trim();

        LocalDate date;
        if (useTodayChoice.equals("Y")) {
            date = LocalDate.now();
        } else {
            System.out.print("Enter date (YYYY-MM-DD): ");
            date = LocalDate.parse(scanner.nextLine());
        }

        // use current time
        LocalTime time = LocalTime.now();

        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        // Collect deposit information
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor (who paid you): ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        // Create the transaction
        Transaction deposit = new Transaction(date, time, description, vendor, amount);

        //
        transactions.add(deposit);
        TransactionManager.saveTransactionToFile("src/data/transactions.csv", deposit);

        System.out.println("\n Deposit added successfully!\n");
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

        Transaction payment = new Transaction(date, time, description, vendor, -amount); // -amount since we are losing money
        transactions.add(payment);
        TransactionManager.saveTransactionToFile("src/data/transactions.csv", payment);
        System.out.println("\n Payment added successfully!");
    }

    public static void displayLedgerScreen() {
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
                    displayAllEntries();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    displayReportsScreen();
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
    public static void displayAllEntries() {
        TransactionManager.displayAllTransactions(transactions);
    }

    public static void displayDeposits() {
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

    public static void displayPayments() {
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

    public static void displayReportsScreen() {
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
                    displayMonthToDate();
                    break;
                case "2":
                    displayPreviousMonth();
                    break;
                case "3":
                    displayYearToDate();
                    break;
                case "4":
                    displayPreviousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    backToLedger = true;  // back to Ledger
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }
    public static void displayMonthToDate() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);

        List<Transaction> filtered = TransactionManager.getTransactionsByDateRange(transactions, startOfMonth, today);

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found for this month.\n");
            return;
        }

        System.out.println("\n=== Month To Date (" + startOfMonth + " to " + today + ") ===");
        // Display newest first
        for (int i = filtered.size() - 1; i >= 0; i--) {
            System.out.println(filtered.get(i));
        }
        System.out.println();
    }

    public static void displayPreviousMonth() {
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
        // Display newest first
        for (int i = filtered.size() - 1; i >= 0; i--) {
            System.out.println(filtered.get(i));
        }
        System.out.println();
    }

    public static void displayYearToDate() {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(today.getYear(), 1, 1);

        List<Transaction> filtered = TransactionManager.getTransactionsByDateRange(transactions, startOfYear, today);

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found for this year.\n");
            return;
        }

        System.out.println("\n=== Year To Date (" + startOfYear + " to " + today + ") ===");
        // Display newest first
        for (int i = filtered.size() - 1; i >= 0; i--) {
            System.out.println(filtered.get(i));
        }
        System.out.println();
    }

    public static void displayPreviousYear() {
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

        for (int i = filtered.size() - 1; i >= 0; i--) {
            System.out.println(filtered.get(i));
        }
        System.out.println();
    }

    public static void searchByVendor() {
        System.out.print("\nEnter vendor name to search: ");
        String vendorName = scanner.nextLine();

        List<Transaction> filtered = TransactionManager.getTransactionsByVendor(transactions, vendorName);

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions found for vendor: " + vendorName + "\n");
            return;
        }

        System.out.println("\n=== Transactions for Vendor: " + vendorName + " ===");

        for (int i = filtered.size() - 1; i >= 0; i--) {
            System.out.println(filtered.get(i));
        }
        System.out.println();
    }

}