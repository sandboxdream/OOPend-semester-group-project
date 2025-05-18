/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagementSystem;

/**
 *
 * @author y'y'f
 */
import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantService {
    final HashMap<String, Ingredient> ingredients = new HashMap<>();
    final RestaurantBilling billing = new RestaurantBilling();

    public void addIngredient(String name, double price) {
        ingredients.put(name, new Ingredient(name, price));
    }

    public void createMeal(String mealName, ArrayList<String> ingredientNames) {
        Meal meal = new Meal(mealName);
        ingredientNames.forEach(name -> {
            Ingredient ing = ingredients.get(name);
            if (ing != null) meal.addIngredient(ing);
        });
        billing.addMeal(meal);
    }

    public String placeOrder(HashMap<String, Integer> mealQuantities) {
        mealQuantities.forEach((mealName, qty) -> billing.addMealToOrder(mealName, qty));
        return "ORDER-123"; // 示例订单ID
    }

    public double calculateBill(String orderId) {
        return billing.calculateBill();
    }
}
