/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package RestaurantManagementSystem;

/**
 *
 * @author y'y'f
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RestaurantManagementSystem1 {
    private static final RestaurantService service = new RestaurantService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Restaurant Management System ===");
            System.out.println("1. Manage Ingredients");
            System.out.println("2. Manage Meals");
            System.out.println("3. Manage Orders");
            System.out.println("4. Display Menu");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 5);
            
            switch (choice) {
                case 1 -> manageIngredients();
                case 2 -> manageMeals();
                case 3 -> manageOrders();
                case 4 -> service.billing.displayMenu();
                case 5 -> {
                    System.out.println("Thank you for using our system!");
                    System.exit(0);
                }
            }
        }
    }

    private static void manageIngredients() {
        while (true) {
            System.out.println("\n=== Ingredient Management ===");
            System.out.println("1. Add Ingredient");
            System.out.println("2. View Ingredients");
            System.out.println("3. Remove Ingredient");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 4);
            
            switch (choice) {
                case 1 -> addIngredient();
                case 2 -> displayIngredients();
                case 3 -> removeIngredient();
                case 4 -> {
                    return;
                }
            }
        }
    }

    private static void addIngredient() {
        System.out.print("Enter ingredient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ingredient price: ");
        double price = getDoubleInput();
        service.addIngredient(name, price);
        System.out.println("Ingredient added successfully!");
    }

    private static void displayIngredients() {
        System.out.println("\n=== Current Ingredients ===");
        service.ingredients.values().forEach(System.out::println);
    }

    private static void removeIngredient() {
        System.out.print("Enter ingredient name to remove: ");
        String name = scanner.nextLine();
        if (service.ingredients.containsKey(name)) {
            service.ingredients.remove(name);
            System.out.println("Ingredient removed successfully!");
        } else {
            System.out.println("Ingredient not found!");
        }
    }

    private static void manageMeals() {
        while (true) {
            System.out.println("\n=== Meal Management ===");
            System.out.println("1. Create Meal");
            System.out.println("2. View Menu");
            System.out.println("3. Remove Meal");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 4);
            
            switch (choice) {
                case 1 -> createMeal();
                case 2 -> service.billing.displayMenu();
                case 3 -> removeMeal();
                case 4 -> {
                    return;
                }
            }
        }
    }

    private static void createMeal() {
        System.out.print("Enter meal name: ");
        String mealName = scanner.nextLine();
        
        ArrayList<String> ingredients = new ArrayList<>();
        while (true) {
            System.out.print("Add ingredient (type 'done' to finish): ");
            String ingName = scanner.nextLine();
            if (ingName.equalsIgnoreCase("done")) break;
            if (!service.ingredients.containsKey(ingName)) {
                System.out.println("Ingredient not found!");
                continue;
            }
            ingredients.add(ingName);
        }
        
        service.createMeal(mealName, ingredients);
        System.out.println("Meal created successfully!");
    }

    private static void removeMeal() {
        System.out.print("Enter meal name to remove: ");
        String name = scanner.nextLine();
        if (service.billing.removeMeal(name)) {
            System.out.println("Meal removed successfully!");
        } else {
            System.out.println("Meal not found!");
        }
    }

    private static void manageOrders() {
        HashMap<String, Integer> order = new HashMap<>();
        
        while (true) {
            System.out.println("\n=== Order Management ===");
            System.out.println("1. Add Meal to Order");
            System.out.println("2. View Current Order");
            System.out.println("3. Generate Bill");
            System.out.println("4. Clear Order");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(1, 5);
            
            switch (choice) {
                case 1:
                    addToOrder(order);
                    break;
                case 2:
                    displayOrder(order);
                    break;
                case 3:
                    generateBill(order);
                    break;
                case 4:
                    order.clear();
                    System.out.println("Order cleared!");
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void addToOrder(HashMap<String, Integer> order) {
        service.billing.displayMenu();
        System.out.print("Enter meal name: ");
        String mealName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = getIntInput(1, 100);
        
        if (service.billing.findMealByName(mealName) != null) {
            order.put(mealName, order.getOrDefault(mealName, 0) + quantity);
            System.out.println("Added to order!");
        } else {
            System.out.println("Invalid meal name!");
        }
    }

    private static void displayOrder(HashMap<String, Integer> order) {
        if (order.isEmpty()) {
            System.out.println("Current order is empty");
            return;
        }
        
        System.out.println("\n=== Current Order ===");
        order.forEach((meal, qty) -> {
            Meal m = service.billing.findMealByName(meal);
            System.out.printf("%s x%d = $%.2f\n", meal, qty, m.getPrice() * qty);
        });
    }

    private static void generateBill(HashMap<String, Integer> order) {
        String orderId = service.placeOrder(order);
        double total = service.calculateBill(orderId);
        System.out.println("\n=== Bill Details ===");
        displayOrder(order);
        System.out.printf("\nTotal: $%.2f\n", total);
        order.clear();
    }

    // Input validation methods
    private static int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) return input;
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, please try again: ");
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, please enter a valid price: ");
            }
        }
    }
}