package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class LedgerApp {
    static Scanner scanner = new Scanner(System.in);
    static List<Transaction> transactions = TransactionManager.loadTransactionFromFile("src/data/transactions.csv");

    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║       PERSONAL FINANCE TRACKER        ║");
        System.out.println("║        Manage Your Money Wisely       ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println();

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
        LedgerScreen.display(scanner, transactions);
    }

}