package com.pluralsight;

import java.util.Scanner;

public class LedgerApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
        System.out.println("Coming soon!");
    }

    public static void makePayment() {
        System.out.println("\n--- Make Payment ---");
        System.out.println("Coming soon!");
    }

    public static void displayLedgerScreen() {
        System.out.println("\n--- Ledger Screen ---");
        System.out.println("Coming soon!");
    }
}