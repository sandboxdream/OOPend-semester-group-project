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

public abstract class RestaurantBillingSystem {
    protected ArrayList<Meal> menu = new ArrayList<>();

    public void addMeal(Meal meal) { menu.add(meal); }

    public boolean removeMeal(String mealName) {
        return menu.removeIf(meal -> meal.getName().equals(mealName));
    }

    public void displayMenu() {
        System.out.println("===== Menu =====");
        menu.forEach(System.out::println);
    }

    public Meal findMealByName(String name) {
        return menu.stream().filter(meal -> meal.getName().equals(name)).findFirst().orElse(null);
    }

    public abstract void addMealToOrder(String mealName, int quantity);
    public abstract double calculateBill();
}
