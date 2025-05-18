package RestaurantManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * RestaurantService - 为RestaurantManagementSystem提供服务的类
 * 整合了RestaurantBilling和其他功能
 */
public class RestaurantService {
    public final RestaurantBilling billing;
    public final HashMap<String, Ingredient> ingredients;
    private final HashMap<String, HashMap<String, Integer>> orders;
    
    /**
     * 构造函数 - 初始化餐厅服务
     */
    public RestaurantService() {
        billing = new RestaurantBilling() {
            @Override
            public void addMealToOrder(String mealName, int quantity) {
                // 这个方法在RestaurantBilling中已实现，这里为了保持接口一致性
                super.addMealToOrder(mealName);
            }
        };
        ingredients = new HashMap<>();
        orders = new HashMap<>();
    }
    
    /**
     * 添加原料到库存中
     * @param name 原料名称
     * @param price 原料价格
     */
    public void addIngredient(String name, double price) {
        ingredients.put(name, new Ingredient(name, price));
    }
    
    /**
     * 创建新的餐点
     * @param mealName 餐点名称
     * @param ingredientNames 原料名称列表
     */
    public void createMeal(String mealName, ArrayList<String> ingredientNames) {
        Meal meal = new Meal(mealName);
        for (String ingName : ingredientNames) {
            Ingredient ingredient = ingredients.get(ingName);
            if (ingredient != null) {
                meal.addIngredient(ingredient);
            }
        }
        billing.addMeal(meal);
    }
    
    /**
     * 添加已存在的餐点
     * @param meal 要添加的餐点对象
     */
    public void addMeal(Meal meal) {
        billing.addMeal(meal);
    }
    
    /**
     * 下单
     * @param meals 餐点名称和数量的映射
     * @return 订单ID
     */
    public String placeOrder(HashMap<String, Integer> meals) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        orders.put(orderId, new HashMap<>(meals));
        return orderId;
    }
    
    /**
     * 计算指定订单的总费用
     * @param orderId 订单ID
     * @return 总费用
     */
    public double calculateBill(String orderId) {
        HashMap<String, Integer> orderItems = orders.get(orderId);
        if (orderItems == null) {
            return 0;
        }
        
        double total = 0;
        for (String mealName : orderItems.keySet()) {
            Meal meal = billing.findMealByName(mealName);
            if (meal != null) {
                total += meal.getPrice() * orderItems.get(mealName);
            }
        }
        return total;
    }
}
