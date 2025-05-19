/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bankmanagementsystem;

/**
 * Represents a bank account with balance, interest rate, and transaction
 * tracking
 */
public class BankAccount {
    private double balance;
    private int depositsCount;
    private int withdrawalsCount;
    private double annualInterestRate;
    private double monthlyServiceCharges;

    public BankAccount(double balance, double annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.depositsCount = 0;
        this.withdrawalsCount = 0;
        this.monthlyServiceCharges = 0;
    }

    public void deposit(double amount) throws Exception {
        if (amount > 0) {
            balance += amount;
            depositsCount++;
            System.out.println("Deposited RMB " + amount + ". New balance: RMB " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
            throw new Exception("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) throws Exception {
        if (!isActive()) {
            System.out.println("Account is inactive. Minimum balance required: RMB 25.00");
            throw new Exception("Account is inactive. Minimum balance required: RMB 25.00");
            // return;
        }

        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                withdrawalsCount++;
                System.out.println("Withdrawn RMB " + amount + ". New balance: RMB " +
                        balance);
            } else {
                // System.out.println("Insufficient funds for withdrawal.");
                throw new Exception("Insufficient funds for withdrawal.");
            }
        } else {
            // System.out.println("Invalid withdrawal amount.");
            throw new Exception("Invalid withdrawal amount.");
        }
    }

    public void calcInterest() {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        double monthlyInterest = balance * monthlyInterestRate;
        balance += monthlyInterest;
    }

    public void monthlyProcess() {
        balance -= monthlyServiceCharges;
        calcInterest();
        depositsCount = 0;
        withdrawalsCount = 0;
        monthlyServiceCharges = 0;
        System.out.println("Monthly processing completed.");
    }

    public boolean isActive() {
        return balance >= 25.00;
    }

    public void displayAccountDetails() {
        System.out.println("Balance: RMB " + balance);
        System.out.println("Deposits this month: " + depositsCount);
        System.out.println("Withdrawals this month: " + withdrawalsCount);
        System.out.println("Account Status: " + (isActive() ? "Active" : "Inactive"));
    }

    // Getters
    public double getBalance() {
        return balance;
    }

    public int getDepositsCount() {
        return depositsCount;
    }

    public int getWithdrawalsCount() {
        return withdrawalsCount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double getMonthlyServiceCharges() {
        return monthlyServiceCharges;
    }

    // Setters
    public void setMonthlyServiceCharges(double charges) {
        this.monthlyServiceCharges = charges;
    }
}