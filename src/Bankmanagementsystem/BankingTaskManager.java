/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bankmanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages bank accounts and integrates with task management
 */
public class BankingTaskManager {
    private HashMap<String, BankAccount> accounts;
    private TaskManager taskManager;

    public BankingTaskManager() {
        accounts = new HashMap<>();
        taskManager = new TaskManager();
    }

    public boolean AccountExist(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public void createAccount(String accountNumber, double balance, double annualInterestRate) throws Exception {
        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, new BankAccount(balance, annualInterestRate));
            taskManager.addTask("Created new account: " + accountNumber);
            System.out.println("Account created successfully: " + accountNumber);
        } else {
            throw new Exception("Account number already exists.");
            // System.out.println("Account number already exists.");
        }
    }

    public void deposit(String accountNumber, double amount) throws Exception {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            taskManager.addTask("Deposit RMB " + amount + " to account " + accountNumber);
        } else {
            // System.out.println("Account not found.");
            throw new Exception("Account not found.");

            // System.out.println("Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) throws Exception {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            taskManager.addTask("Withdraw RMB " + amount + " from account " + accountNumber + "*");
        } else {
            // throw new Exception("2222");
            throw new Exception("Account not found.");

            // System.out.println("Account not found.");
        }
    }

    public void viewAccountDetails(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void monthlyProcess(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.monthlyProcess();
            taskManager.addTask("Monthly processing for account " + accountNumber + "*");
        } else {
            System.out.println("Account not found.");
        }
    }

    // Task management methods
    public void addTask(String description) {
        taskManager.addTask(description);
    }

    public void removeTask(String priority, int index) {
        taskManager.removeTask(priority, index);
    }

    public void changePriority(String priority, int index) {
        taskManager.changePriority(priority, index);
    }

    public void promoteTask(int index) {
        taskManager.promoteTask(index);
    }

    public ArrayList<String> getHighPriorityTasks() {
        return taskManager.getHighPriorityTasks();
    }

    public ArrayList<String> getLowPriorityTasks() {
        return taskManager.getLowPriorityTasks();
    }

    public void viewTasks() {
        taskManager.displayTasks();
    }
}