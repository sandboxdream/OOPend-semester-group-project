/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagementSystem;

/*

  @author DJH
 */
import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantBilling extends RestaurantBillingSystem {
    private final ArrayList<Meal> order;
    private final HashMap<String, Integer> mealQuantities;

    //Constructor to initialize order list and meal quantities map
    public RestaurantBilling() {
        super();
        order = new ArrayList<>();
        mealQuantities = new HashMap<>();
    }

    //Add a meal from the menu to the customer's order
    @Override
    public void addMealToOrder(String mealName, int quantity) {
        Meal meal = findMealByName(mealName);
        if (meal != null) {
            if(mealQuantities.containsKey(mealName))
            {
                mealQuantities.put(mealName, mealQuantities.get(mealName) + quantity);
            }
            else
            {
                mealQuantities.put(mealName, quantity);
                order.add(meal);
            }
            System.out.println("Added " + mealName + " to order.");
        } else {
            System.out.println("Meal '" + mealName + "' not found on the menu.");
        }
    }
    
    // 与旧代码兼容的方法
    public void addMealToOrder(String mealName) {
        addMealToOrder(mealName, 1);
    }

    public HashMap<String, Integer> getMealQuantities() {
        return new HashMap<>(mealQuantities);
    }

    //Override the calculateBill() and calculate the total bill for the customer's order
    //return the total bill amount
    @Override
    public double calculateBill() {
        double total = 0.0;
        for (Meal meal : order) {
            total += meal.getPrice() * mealQuantities.get(meal.getName());
        }
        return total;
    }

    //Clear the current order
    public void clearOrder() {
        order.clear();
        mealQuantities.clear();
        System.out.println("Order cleared.");
    }

    //Display the current order with meal quantities and total price
    public void displayOrder() {
        System.out.println("======== ORDER: ========");
        for (Meal meal : order) {
            String mealName = meal.getName();
            int quantity = mealQuantities.get(mealName);
            System.out.println(mealName + " x " + quantity + "    Price: " + meal.getPrice() + " x "+
                    quantity + " = " + (meal.getPrice() * quantity));
        }

        System.out.println("Total Price: " + calculateBill());
        System.out.println("=========================");
    }

    //Generate a formatted bill for the customer
    //customerName: The name of the customer
    //return String representation of the bill
    public String generateBill(String customerName)
    {
        StringBuilder b = new StringBuilder();
        if(order.isEmpty())
        {
            b.append(customerName).append(", have not ordered anything yet.\n");
        }
        else{
            b.append("============== BILL ==============\n");
            b.append("Customer Name: ").append(customerName).append("\n");
            b.append("Ordered Meals:\n");
            b.append("Meal Name\t\tQuantity\n");
            for (Meal meal : order) {
                String mealName = meal.getName();
                int quantity = mealQuantities.get(mealName);
                b.append(mealName).append("\t\t").append(quantity).append("\n").append("\t\t").append("Price: ")
                        .append(meal.getPrice()).append("\n----------------------------------\n");
            }
            b.append("Total Price:\t").append(calculateBill()).append("\n");
            b.append("==================================\n\n");

            b.append("Thank you for dining with us!\n Hope to see you again!\n\n");
            b.append("==================================\n");
        }
        return b.toString();
    }
}
