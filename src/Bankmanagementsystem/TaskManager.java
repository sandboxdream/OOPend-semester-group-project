/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bankmanagementsystem;

import java.util.ArrayList;

/**
 * Manages high and low priority tasks
 */
public class TaskManager {
    private ArrayList<String> highPriority;
    private ArrayList<String> lowPriority;

    public TaskManager() {
        highPriority = new ArrayList<>();
        lowPriority = new ArrayList<>();
    }

    public void addTask(String task) {
        if (task.contains("*")) {
            highPriority.add(task);
            System.out.println("Added high priority task: " + task);
        } else {
            lowPriority.add(task);
            System.out.println("Added low priority task: " + task);
        }
    }

    public void removeTask(String priority, int index) {
        if (priority.equalsIgnoreCase("high")) {
            if (index >= 0 && index < highPriority.size()) {
                highPriority.remove(index);
            }
        } else if (priority.equalsIgnoreCase("low")) {
            if (index >= 0 && index < lowPriority.size()) {
                lowPriority.remove(index);
            }
        }
    }

    public void changePriority(String priority, int index) {
        if (priority.equalsIgnoreCase("high")) {
            if (index >= 0 && index < highPriority.size()) {
                String task = highPriority.remove(index);
                lowPriority.add(task.replace("*", ""));
            }
        } else if (priority.equalsIgnoreCase("low")) {
            if (index >= 0 && index < lowPriority.size()) {
                String task = lowPriority.remove(index);
                highPriority.add(task + "*");
            }
        }
    }

    public void promoteTask(int index) {
        if (index > 0 && index < highPriority.size()) {
            String task = highPriority.remove(index);
            highPriority.add(index - 1, task);
        }
    }

    public ArrayList<String> getHighPriorityTasks() {
        return new ArrayList<>(highPriority);
    }

    public ArrayList<String> getLowPriorityTasks() {
        return new ArrayList<>(lowPriority);
    }

    public void displayTasks() {
        System.out.println("\nHigh Priority Tasks:");
        for (int i = 0; i < highPriority.size(); i++) {
            System.out.println(i + ". " + highPriority.get(i));
        }

        System.out.println("\nLow Priority Tasks:");
        for (int i = 0; i < lowPriority.size(); i++) {
            System.out.println(i + ". " + lowPriority.get(i));
        }
    }
}
