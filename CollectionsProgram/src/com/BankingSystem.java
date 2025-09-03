package com;

import java.util.*;

class Transaction {
    String type;     // Deposit / Withdraw
    double amount;
    Date date;

    Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date(); // current timestamp
    }

   // @Override
//    public String toString() {
//        return "[" + date + "] " + type + " : " + amount;
//    }
}

class BankAccount {
    int accNo;
    String name;
    double balance;
    ArrayList<Transaction> history;

    BankAccount(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
        this.history = new ArrayList<Transaction>();
    }

    void deposit(double amount) {
        balance += amount;
        history.add(new Transaction("Deposit", amount));
        System.out.println("✅ Deposited: " + amount);
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            history.add(new Transaction("Withdraw", amount));
            System.out.println("✅ Withdrawn: " + amount);
        } else {
            System.out.println("❌ Insufficient balance!");
        }
    }

    void showBalance() {
        System.out.println("Account No: " + accNo + ", Name: " + name + ", Balance: " + balance);
    }

    void showHistory() {
        System.out.println("\n📜 Transaction History for Account " + accNo);
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : history) {
                System.out.println(t);
            }
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

        int choice;
        do {
            System.out.println("\n--- Banking Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Balance");
            System.out.println("5. Show Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Account No: ");
                int acc = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Initial Balance: ");
                double bal = sc.nextDouble();
                accounts.add(new BankAccount(acc, name, bal));
                System.out.println("✅ Account created successfully!");
            }
            else if (choice >= 2 && choice <= 5) {
                System.out.print("Enter Account No: ");
                int acc = sc.nextInt();
                BankAccount found = null;

                for (BankAccount b : accounts) {
                    if (b.accNo == acc) {
                        found = b;
                        break;
                    }
                }

                if (found == null) {
                    System.out.println("⚠️ Account not found!");
                    continue;
                }

                if (choice == 2) {
                    System.out.print("Enter deposit amount: ");
                    double amt = sc.nextDouble();
                    found.deposit(amt);
                }
                else if (choice == 3) {
                    System.out.print("Enter withdrawal amount: ");
                    double amt = sc.nextDouble();
                    found.withdraw(amt);
                }
                else if (choice == 4) {
                    found.showBalance();
                }
                else if (choice == 5) {
                    found.showHistory();
                }
            }
        } while (choice != 6);

        sc.close();
    }
}
