/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestaurantManagementSystem;

import java.util.ArrayList;

/**
 *
 * @author y'y'f
 */
public class Meal implements Priceable {
    private final String name;
    private final ArrayList<Ingredient> ingredients;

    public Meal(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    
    // 添加兼容旧代码的方法别名
    public void addIngerdient(Ingredient ingredient) {
        addIngredient(ingredient);
    }

    public String getName() { return name; }

    @Override
    public double getPrice() {
        return ingredients.stream().mapToDouble(Ingredient::getPrice).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Meal: %s (￥%.2f)\n", name, getPrice()));
        ingredients.forEach(ing -> sb.append("  - ").append(ing).append("\n"));
        return sb.toString();
    }
}
