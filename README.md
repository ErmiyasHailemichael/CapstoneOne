# CapstoneOne: Accounting Ledger Application

This project is an **Accounting Ledger Application** built in **Java** as part of a capstone project.
It allows users to **record, display, and manage financial transactions** such as deposits, payments, and transfers.
Transactions are stored in a `.csv` file for persistence.

---

## 📚 Table of Contents

* [📘 Overview](#capstoneone-accounting-ledger-application)
* [🧩 Project Structure](#project-structure)
* [⚙️ Features](#️-features)
* [📸 Screenshots](#-screenshots)
* [🧠 Core Classes Overview](#-core-classes-overview)
* [📋 Project Management](#project-management)
* [🧰 Tools and Technologies](#tools-and-technologies)
* [▶️ How to Run](#how-to-run)
* [👨‍💻 Author](#-author)

---

## 🧩 Project Structure

```
CapstoneOne/
├── src/
│ ├── Data/
│ │ └── transactions.csv
│ ├── main/
│ │ ├── java/
│ │ │ └── com/pluralsight/
│ │ │ ├── LedgerApp.java
│ │ │ ├── LedgerScreen.java
│ │ │ ├── ReportsScreen.java
│ │ │ ├── Transaction.java
│ │ │ └── TransactionManager.java
| | | └── Main.class
```

---

## ⚙️ Features

* 💾 Save transactions to a CSV file (`transactions.csv`)
* 📜 View all transactions (newest first)
* ➕ Filter and display only deposits
* 💸 Manage payments and reports
* 🧮 Organized transaction management via OOP design

---

## 📸 Screenshots

Below are screenshots showing the output of the application in action:

#### 🧾 All Transactions

![All Transaction .png](src/Asset/All%20Transaction%20.png)

#### 💸 Payments Only

![Payments Only](src/Asset/PaymentOnly.png)

#### 📆 Report Screen — Month To Date

![Report Screen — Month To Date](src/Asset/ReportScreenMonthToDate.png)

#### 🏷️ Transaction For Vendor

![Transaction For Vendor](src/Asset/TransactionForVendor.png)

#### 📅 Year To Date

![Year To Date](src/Asset/YearToDate.png)

---

## 🧠 Core Classes Overview

| Class                       | Description                                            |
| --------------------------- | ------------------------------------------------------ |
| **LedgerApp.java**          | Entry point of the application                         |
| **LedgerScreen.java**       | Handles user interactions and UI                       |
| **ReportsScreen.java**      | Displays reports and analytics                         |
| **Transaction.java**        | Defines the structure of a transaction                 |
| **TransactionManager.java** | Manages saving, displaying, and filtering transactions |

## Project Management

This project was managed using a Trello board to track backlog, tasks, and progress.
You can view the live board here:
👉 [Trello Board – Capstone 1: Accounting Ledger Application](https://trello.com/invite/b/64035649c0865d917d538afd/ATTIff978d1d098dc9098b5da87d80dd689a6E424CC6/capstone-1-accounting-ledger-application)

---

## Tools and Technologies

* **Java 17**
* **IntelliJ IDEA**
* **Maven** (for dependency management)
* **GitHub** (for version control)
* **Trello** (for project tracking)

---

## How to Run

1. Clone this repository

   ```bash
   git clone https://github.com/ErmiyasHailemichael/CapstoneOne
   ```

2. Open the project in IntelliJ IDEA

3. Run LedgerApp.java from the main package

4. Follow the on-screen prompts to record or view transactions

---

## 👨‍💻 Author

Ermiyas Hailemichael
