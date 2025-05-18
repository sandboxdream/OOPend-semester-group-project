/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagementSystem;

/**
 *
 * @author y'y'f
 */
import java.util.HashMap;

public class RestaurantBilling extends RestaurantBillingSystem {
    private final HashMap<String, Integer> order = new HashMap<>();

    @Override
    public void addMealToOrder(String mealName, int quantity) {
        if (findMealByName(mealName) != null) {
            order.put(mealName, order.getOrDefault(mealName, 0) + quantity);
        }
    }

    @Override
    public double calculateBill() {
        return order.entrySet().stream()
            .mapToDouble(entry -> {
                Meal meal = findMealByName(entry.getKey());
                return meal.getPrice() * entry.getValue();
            })
            .sum();
    }

    public void clearOrder() { order.clear(); }
}
