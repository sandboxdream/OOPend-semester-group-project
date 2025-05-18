package RestaurantManagementSystem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;


/**
 * RestaurantManagementSystemGUI - GUI implementation of the Restaurant Management System
 */
public class RestaurantManagementSystemGUI extends JFrame {
    // Main components
    private final RestaurantBilling billing;
    private final HashMap<String, Ingredient> ingredientInventory;
    private final ArrayList<Meal> mealsList;
    // 预设餐点列表
    private final ArrayList<Meal> presetMeals;

    // Main panels
    private final JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel contentPanel;

    // Components for displaying information
    private JTextArea displayArea;
    private JScrollPane scrollPane;

    /**
     * Constructor - initializes the GUI and restaurant billing system
     */
    public RestaurantManagementSystemGUI() {
        // Set up the frame
        super("Restaurant Management System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT_ON_CLOSE to DISPOSE_ON_CLOSE
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Initialize restaurant components
        billing = new RestaurantBilling();
        ingredientInventory = new HashMap<>();
        mealsList = new ArrayList<>();
        presetMeals = new ArrayList<>();

        // Add initial ingredients
        addSampleIngredients();

        // 创建预设餐点
        createPresetMeals();

        // Set up the main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());

        // Create menu panel with buttons
        createMenuPanel();

        // Create content panel for displaying information
        createContentPanel();

        // Add panels to the main panel
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);

        // Display the frame
        setVisible(true);
    }

    /**
     * Add sample ingredients to the inventory for demonstration
     */
    private void addSampleIngredients() {
        ingredientInventory.put("Tomato", new Ingredient("Tomato", 0.75));
        ingredientInventory.put("Lettuce", new Ingredient("Lettuce", 0.50));
        ingredientInventory.put("Cheese", new Ingredient("Cheese", 1.25));
        ingredientInventory.put("Beef Patty", new Ingredient("Beef Patty", 3.50));
        ingredientInventory.put("Chicken Breast", new Ingredient("Chicken Breast", 2.75));
        ingredientInventory.put("Bun", new Ingredient("Bun", 0.80));
        ingredientInventory.put("French Fries", new Ingredient("French Fries", 1.50));
        ingredientInventory.put("Rice", new Ingredient("Rice", 1.00));
        ingredientInventory.put("Pasta", new Ingredient("Pasta", 1.20));
        ingredientInventory.put("Marinara Sauce", new Ingredient("Marinara Sauce", 1.75));
    }

    /**
     * 创建预设餐点
     */
    private void createPresetMeals() {
        // 汉堡套餐
        Meal hamburgerMeal = new Meal("Hamburger Set");
        hamburgerMeal.addIngerdient(ingredientInventory.get("Beef Patty"));
        hamburgerMeal.addIngerdient(ingredientInventory.get("Bun"));
        hamburgerMeal.addIngerdient(ingredientInventory.get("Lettuce"));
        hamburgerMeal.addIngerdient(ingredientInventory.get("Tomato"));
        hamburgerMeal.addIngerdient(ingredientInventory.get("Cheese"));
        hamburgerMeal.addIngerdient(ingredientInventory.get("French Fries"));
        presetMeals.add(hamburgerMeal);
        mealsList.add(hamburgerMeal);

        // 意大利面
        Meal pastaMeal = new Meal("Pasta");
        pastaMeal.addIngerdient(ingredientInventory.get("Pasta"));
        pastaMeal.addIngerdient(ingredientInventory.get("Marinara Sauce"));
        pastaMeal.addIngerdient(ingredientInventory.get("Cheese"));
        presetMeals.add(pastaMeal);
        mealsList.add(pastaMeal);

        // 鸡肉饭
        Meal chickenRice = new Meal("Chicken Rice");
        chickenRice.addIngerdient(ingredientInventory.get("Chicken Breast"));
        chickenRice.addIngerdient(ingredientInventory.get("Rice"));
        presetMeals.add(chickenRice);
        mealsList.add(chickenRice);
    }

    /**
     * Create the menu panel with buttons for different operations
     */
    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Create buttons for different operations
        JButton manageIngredientsButton = new JButton("Manage Ingredients");
        JButton manageMealsButton = new JButton("Manage Meals");
        JButton manageMenuButton = new JButton("Manage Menu");
        JButton manageOrdersButton = new JButton("Manage Orders");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        manageIngredientsButton.addActionListener(e -> manageIngredients());
        manageMealsButton.addActionListener(e -> manageMeals());
        manageMenuButton.addActionListener(e -> manageMenu());
        manageOrdersButton.addActionListener(e -> manageOrders());
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the menu panel
        menuPanel.add(createButtonWithMargin(manageIngredientsButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(manageMealsButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(manageMenuButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(manageOrdersButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(exitButton));
    }

    /**
     * Helper method to create a button with consistent size and margin
     */
    private JButton createButtonWithMargin(JButton button) {
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    /**
     * Create the content panel for displaying information
     */
    private void createContentPanel() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create text area for displaying information
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Add text area to a scroll pane
        scrollPane = new JScrollPane(displayArea);

        // Add scroll pane to the content panel
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Display welcome message
        displayArea.setText("Welcome to the Restaurant Management System!\n\n" +
                "Please select an operation from the menu on the left.");
    }

    /**
     * Manage ingredients
     */
    private void manageIngredients() {
        JDialog ingredientDialog = new JDialog(this, "Manage Ingredients", false);
        ingredientDialog.setSize(600, 400);
        ingredientDialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Ingredient");
        JButton viewButton = new JButton("View All Ingredients");
        JButton removeButton = new JButton("Remove Ingredient");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(closeButton);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add Ingredient action
        addButton.addActionListener(e -> {
            JDialog addDialog = new JDialog(ingredientDialog, "Add Ingredient", true);
            addDialog.setSize(300, 150);
            addDialog.setLocationRelativeTo(ingredientDialog);

            JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Ingredient Name:");
            JTextField nameField = new JTextField(15);
            JLabel priceLabel = new JLabel("Price (RMB):");
            JTextField priceField = new JTextField(15);
            JButton submitButton = new JButton("Add");
            JButton cancelButton = new JButton("Cancel");

            formPanel.add(nameLabel);
            formPanel.add(nameField);
            formPanel.add(priceLabel);
            formPanel.add(priceField);
            formPanel.add(submitButton);
            formPanel.add(cancelButton);

            submitButton.addActionListener(event -> {
                try {
                    String name = nameField.getText().trim();
                    double price = Double.parseDouble(priceField.getText().trim());

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(addDialog, "Please enter an ingredient name.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (ingredientInventory.containsKey(name)) {
                        JOptionPane.showMessageDialog(addDialog, "An ingredient with this name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Ingredient newIngredient = new Ingredient(name, price);
                    ingredientInventory.put(name, newIngredient);
                    textArea.append("Added ingredient: " + newIngredient + "\n");
                    addDialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addDialog, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            cancelButton.addActionListener(event -> addDialog.dispose());

            addDialog.add(formPanel);
            addDialog.setVisible(true);
        });

// View All Ingredients action
        viewButton.addActionListener(e -> {
            if (ingredientInventory.isEmpty()) {
                textArea.setText("No ingredients in inventory.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("===== INGREDIENT INVENTORY =====\n");
            for (Ingredient ingredient : ingredientInventory.values()) {
                sb.append(ingredient).append("\n");
            }
            sb.append("==============================\n");
            textArea.setText(sb.toString());
        });

        // Remove Ingredient action
        removeButton.addActionListener(e -> {
            if (ingredientInventory.isEmpty()) {
                textArea.setText("No ingredients in inventory to remove.");
                return;
            }

            String[] ingredientNames = ingredientInventory.keySet().toArray(new String[0]);
            String selectedName = (String) JOptionPane.showInputDialog(
                    ingredientDialog,
                    "Select an ingredient to remove:",
                    "Remove Ingredient",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    ingredientNames,
                    ingredientNames[0]
            );

            if (selectedName != null) {
                ingredientInventory.remove(selectedName);
                textArea.append("Ingredient '" + selectedName + "' removed successfully.\n");
            }
        });
        // Close action
        closeButton.addActionListener(e -> ingredientDialog.dispose());

        ingredientDialog.add(mainPanel);
        ingredientDialog.setVisible(true);
    }

    /**
     * Manage meals
     */
    private void manageMeals() {
        JDialog mealDialog = new JDialog(this, "Manage Meals", false);
        mealDialog.setSize(600, 400);
        mealDialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton createButton = new JButton("Create New Meal");
        JButton viewButton = new JButton("View All Meals");
        JButton addToMenuButton = new JButton("Add Meal to Menu");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(createButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(addToMenuButton);
        buttonPanel.add(closeButton);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create New Meal action
        createButton.addActionListener(e -> {
            if (ingredientInventory.isEmpty()) {
                textArea.setText("No ingredients available. Please add ingredients first.");
                return;
            }

            JDialog createDialog = new JDialog(mealDialog, "Create Meal", true);
            createDialog.setSize(400, 500);
            createDialog.setLocationRelativeTo(mealDialog);
            createDialog.setLayout(new BorderLayout());

            JPanel formPanel = new JPanel(new BorderLayout());
            formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel nameLabel = new JLabel("Meal Name:");
            JTextField nameField = new JTextField(15);
            topPanel.add(nameLabel);
            topPanel.add(nameField);

            JPanel centerPanel = new JPanel();
            centerPanel.setBorder(BorderFactory.createTitledBorder("Available Ingredients"));
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            // 使用复选框替代JList
            String[] ingredientNames = ingredientInventory.keySet().toArray(new String[0]);
            JCheckBox[] checkBoxes = new JCheckBox[ingredientNames.length];

            for (int i = 0; i < ingredientNames.length; i++) {
                checkBoxes[i] = new JCheckBox(ingredientNames[i]);
                centerPanel.add(checkBoxes[i]);
            }

            JScrollPane checkBoxScrollPane = new JScrollPane(centerPanel);
            checkBoxScrollPane.setPreferredSize(new Dimension(300, 300));

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton createMealButton = new JButton("Create");
            JButton cancelCreateButton = new JButton("Cancel");
            bottomPanel.add(createMealButton);
            bottomPanel.add(cancelCreateButton);

            formPanel.add(topPanel, BorderLayout.NORTH);
            formPanel.add(checkBoxScrollPane, BorderLayout.CENTER);

            createDialog.add(formPanel, BorderLayout.CENTER);
            createDialog.add(bottomPanel, BorderLayout.SOUTH);

            createMealButton.addActionListener(event -> {
                String mealName = nameField.getText().trim();
                if (mealName.isEmpty()) {
                    JOptionPane.showMessageDialog(createDialog, "Please enter a meal name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ArrayList<String> selectedIngredientNames = new ArrayList<>();
                for (int i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].isSelected()) {
                        selectedIngredientNames.add(ingredientNames[i]);
                    }
                }

                if (selectedIngredientNames.isEmpty()) {
                    JOptionPane.showMessageDialog(createDialog, "Please select at least one ingredient.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Meal newMeal = new Meal(mealName);
                for (String ingredientName : selectedIngredientNames) {
                    newMeal.addIngerdient(ingredientInventory.get(ingredientName));
                }

                mealsList.add(newMeal);
                textArea.setText("Meal '" + mealName + "' created successfully.\n" + newMeal.toString());
                createDialog.dispose();
            });

            cancelCreateButton.addActionListener(event -> createDialog.dispose());

            createDialog.setVisible(true);
        });

        // View All Meals action
        viewButton.addActionListener(e -> {
            if (mealsList.isEmpty()) {
                textArea.setText("No meals have been created yet.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("===== CREATED MEALS =====\n");
            for (int i = 0; i < mealsList.size(); i++) {
                sb.append((i + 1)).append(". ").append(mealsList.get(i)).append("\n");
            }
            sb.append("=======================\n");
            textArea.setText(sb.toString());
        });

        // Add Meal to Menu action
        addToMenuButton.addActionListener(e -> {
            if (mealsList.isEmpty()) {
                textArea.setText("No meals have been created yet to add to the menu.");
                return;
            }

            // 获取未添加到菜单的餐点
            ArrayList<Meal> availableMeals = new ArrayList<>();
            for (Meal meal : mealsList) {
                if (billing.findMealByName(meal.getName()) == null) {
                    availableMeals.add(meal);
                }
            }

            if (availableMeals.isEmpty()) {
                textArea.setText("All meals have already been added to the menu.");
                return;
            }

            JDialog addToMenuDialog = new JDialog(mealDialog, "Add to Menu", true);
            addToMenuDialog.setSize(400, 400);
            addToMenuDialog.setLocationRelativeTo(mealDialog);
            addToMenuDialog.setLayout(new BorderLayout());

            JPanel selectPanel = new JPanel();
            selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));
            selectPanel.setBorder(BorderFactory.createTitledBorder("Select Meals to Add to Menu"));

            // 使用复选框显示可添加的餐点
            JCheckBox[] mealCheckBoxes = new JCheckBox[availableMeals.size()];
            for (int i = 0; i < availableMeals.size(); i++) {
                mealCheckBoxes[i] = new JCheckBox(availableMeals.get(i).getName());
                selectPanel.add(mealCheckBoxes[i]);
            }

            JScrollPane checkBoxScrollPane = new JScrollPane(selectPanel);
            
            JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton addSelectedButton = new JButton("Add Selected");
            JButton cancelButton = new JButton("Cancel");
            buttonPanel2.add(addSelectedButton);
            buttonPanel2.add(cancelButton);

            addToMenuDialog.add(checkBoxScrollPane, BorderLayout.CENTER);
            addToMenuDialog.add(buttonPanel2, BorderLayout.SOUTH);

            addSelectedButton.addActionListener(event -> {
                boolean anySelected = false;
                StringBuilder resultText = new StringBuilder("Added to menu:\n");
                
                for (int i = 0; i < mealCheckBoxes.length; i++) {
                    if (mealCheckBoxes[i].isSelected()) {
                        anySelected = true;
                        billing.addMeal(availableMeals.get(i));
                        resultText.append("- ").append(availableMeals.get(i).getName()).append("\n");
                    }
                }
                
                if (!anySelected) {
                    JOptionPane.showMessageDialog(addToMenuDialog, "Please select at least one meal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                textArea.setText(resultText.toString());
                addToMenuDialog.dispose();
            });

            cancelButton.addActionListener(event -> addToMenuDialog.dispose());

            addToMenuDialog.setVisible(true);
        });

        // Close action
        closeButton.addActionListener(e -> mealDialog.dispose());

        mealDialog.add(mainPanel);
        mealDialog.setVisible(true);
    }

    /**
     * Manage menu
     */
    private void manageMenu() {
        JDialog menuDialog = new JDialog(this, "Manage Menu", false);
        menuDialog.setSize(600, 400);
        menuDialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton viewButton = new JButton("View Menu");
        JButton removeButton = new JButton("Remove Meal from Menu");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(viewButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(closeButton);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // View Menu action
        viewButton.addActionListener(e -> {
            // Capture output from displayMenu
            StringBuilder sb = new StringBuilder();
            sb.append("================ Menu: ==============\n");

            // Simple implementation to output menu info
            ArrayList<Meal> menu = getMenuItems();
            if (menu.isEmpty()) {
                sb.append("No meals available on the menu.\n");
            } else {
                for (Meal meal : menu) {
                    sb.append(meal.toString()).append("\n");
                }
            }

            sb.append("=====================================\n");
            textArea.setText(sb.toString());
        });

        // Remove Meal action
        removeButton.addActionListener(e -> {

            ArrayList<Meal> menu = getMenuItems();
            if (menu.isEmpty()) {
                textArea.setText("No meals on the menu to remove.");
                return;
            }

            String[] mealNames = new String[menu.size()];
            for (int i = 0; i < menu.size(); i++) {
                mealNames[i] = menu.get(i).getName();
            }

            String selectedName = (String) JOptionPane.showInputDialog(
                    menuDialog,
                    "Select a meal to remove from the menu:",
                    "Remove Meal",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    mealNames,
                    mealNames[0]
            );

            if (selectedName != null) {
                if (billing.removeMeal(selectedName)) {
                    textArea.setText("Meal '" + selectedName + "' removed from the menu.");
                } else {
                    textArea.setText("Failed to remove meal '" + selectedName + "' from the menu.");
                }
            }
        });

        // Close action
        closeButton.addActionListener(e -> menuDialog.dispose());

        menuDialog.add(mainPanel);
        menuDialog.setVisible(true);
    }


    private ArrayList<Meal> getMenuItems() {
        ArrayList<Meal> menuItems = new ArrayList<>();
        for (Meal meal : mealsList) {
            if (billing.findMealByName(meal.getName()) != null) {
                menuItems.add(meal);
            }
        }

        return menuItems;
    }

    /**
     * Manage orders
     */
    private void manageOrders() {
        JDialog orderDialog = new JDialog(this, "Manage Orders", false);
        orderDialog.setSize(600, 400);
        orderDialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton viewMenuButton = new JButton("View Menu");
        JButton addMealButton = new JButton("Add Meal to Order");
        JButton viewOrderButton = new JButton("View Current Order");
        JButton generateBillButton = new JButton("Generate Bill");
        JButton clearOrderButton = new JButton("Clear Order");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(viewMenuButton);
        buttonPanel.add(addMealButton);
        buttonPanel.add(viewOrderButton);
        buttonPanel.add(generateBillButton);
        buttonPanel.add(clearOrderButton);
        buttonPanel.add(closeButton);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // View Menu action
        viewMenuButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("================ Menu: ==============\n");

            ArrayList<Meal> menu = getMenuItems();
            if (menu.isEmpty()) {
                sb.append("No meals available on the menu.\n");
            } else {
                for (Meal meal : menu) {
                    sb.append(meal.toString()).append("\n");
                }
            }

            sb.append("=====================================\n");
            textArea.setText(sb.toString());
        });

        // Add Meal to Order action
        addMealButton.addActionListener(e -> {
            ArrayList<Meal> menu = getMenuItems();
            if (menu.isEmpty()) {
                textArea.setText("No meals on the menu to add to order.");
                return;
            }

            // Create a dialog to display all meals with quantity options
            JDialog selectMealDialog = new JDialog(orderDialog, "Add Meals to Order", true);
            selectMealDialog.setLayout(new BorderLayout());
            selectMealDialog.setSize(500, 400);
            selectMealDialog.setLocationRelativeTo(orderDialog);

            // Create a panel for all meals
            JPanel mealsPanel = new JPanel();
            mealsPanel.setLayout(new BoxLayout(mealsPanel, BoxLayout.Y_AXIS));
            
            // Map to store quantity fields for each meal
            HashMap<String, JTextField> quantityFields = new HashMap<>();
            
            // Add each meal with its own quantity field
            for (Meal meal : menu) {
                String mealName = meal.getName();
                double mealPrice = meal.getPrice();
                
                JPanel mealRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
                
                JLabel nameLabel = new JLabel(String.format("%s (￥%.2f)", mealName, mealPrice));
                nameLabel.setPreferredSize(new Dimension(200, 25));
                
                JLabel qtyLabel = new JLabel("Qty:");
                
                JTextField quantityField = new JTextField("0", 3);
                quantityFields.put(mealName, quantityField);
                
                JButton plusButton = new JButton("+1");
                plusButton.addActionListener(event -> {
                    try {
                        int currentValue = Integer.parseInt(quantityField.getText());
                        quantityField.setText(String.valueOf(currentValue + 1));
                    } catch (NumberFormatException ex) {
                        quantityField.setText("1");
                    }
                });
                
                mealRow.add(nameLabel);
                mealRow.add(qtyLabel);
                mealRow.add(quantityField);
                mealRow.add(plusButton);
                
                mealsPanel.add(mealRow);
            }
            
            // Add scroll support for many meals
            JScrollPane mealsScrollPane = new JScrollPane(mealsPanel);
            mealsScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            // Button panel
            JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton orderButton = new JButton("Add to Order");
            JButton cancelButton = new JButton("Cancel");
            
            btnPanel.add(orderButton);
            btnPanel.add(cancelButton);
            
            // Add components to dialog
            selectMealDialog.add(mealsScrollPane, BorderLayout.CENTER);
            selectMealDialog.add(btnPanel, BorderLayout.SOUTH);
            
            // Order button action
            orderButton.addActionListener(event -> {
                StringBuilder resultMessage = new StringBuilder("Added to order:\n");
                boolean anyAdded = false;
                
                // Process each meal quantity
                for (Meal meal : menu) {
                    String mealName = meal.getName();
                    JTextField qtyField = quantityFields.get(mealName);
                    
                    try {
                        int quantity = Integer.parseInt(qtyField.getText());
                        
                        if (quantity > 0) {
                            // Add the meal to order
                            billing.addMealToOrder(mealName, quantity);
                            
                            // Add to result message
                            resultMessage.append("- ")
                                     .append(mealName)
                                     .append(" x ")
                                     .append(quantity)
                                     .append("\n");
                            
                            anyAdded = true;
                        }
                    } catch (NumberFormatException ex) {
                        // Ignore invalid inputs
                    }
                }
                
                if (anyAdded) {
                    textArea.setText(resultMessage.toString());
                    selectMealDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(selectMealDialog,
                            "Please select at least one meal by setting a quantity greater than 0.",
                            "No Meals Selected",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
            
            // Cancel button action
            cancelButton.addActionListener(event -> selectMealDialog.dispose());
            
            // Show dialog
            selectMealDialog.setVisible(true);
        });

        // View Current Order action
        viewOrderButton.addActionListener(e -> {
            final StringBuilder sb = new StringBuilder();
            sb.append("======== ORDER: ========\n");
            ArrayList<Meal> menu = getMenuItems();
            boolean hasOrder = false;
            
            // 获取订单中餐点的数量
            HashMap<String, Integer> mealQuantities = billing.getMealQuantities();
            
            for (Meal meal : menu) {
                String mealName = meal.getName();
                Integer quantity = mealQuantities.get(mealName);
                if (quantity != null && quantity > 0) {
                    hasOrder = true;
                    double itemTotal = meal.getPrice() * quantity;
                    sb.append(mealName).append(" x ").append(quantity)
                      .append("    Price: ").append(meal.getPrice())
                      .append(" x ").append(quantity)
                      .append(" = ").append(itemTotal).append("\n");
                }
            }

            if (!hasOrder) {
                sb.append("No meals in current order.\n");
            }

            sb.append("Total Price: ").append(billing.calculateBill()).append("\n");
            sb.append("=========================\n");

            textArea.setText(sb.toString());
        });


        // Generate Bill action
        generateBillButton.addActionListener(e -> {

            JDialog billDialog = new JDialog(orderDialog, "Generate Bill", true);
            billDialog.setSize(300, 120);
            billDialog.setLocationRelativeTo(orderDialog);
            billDialog.setLayout(new BorderLayout());

            JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel nameLabel = new JLabel("Customer Name:");
            JTextField nameField = new JTextField(15);
            formPanel.add(nameLabel);
            formPanel.add(nameField);

            JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton generateButton = new JButton("Generate");
            JButton cancelButton = new JButton("Cancel");
            buttonPanel1.add(generateButton);
            buttonPanel1.add(cancelButton);

            billDialog.add(formPanel, BorderLayout.CENTER);
            billDialog.add(buttonPanel1, BorderLayout.SOUTH);

            generateButton.addActionListener(event -> {
                String customerName = nameField.getText().trim();
                if (customerName.isEmpty()) {
                    JOptionPane.showMessageDialog(billDialog, "Please enter a customer name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String bill = billing.generateBill(customerName);
                textArea.setText(bill);

                int response = JOptionPane.showConfirmDialog(
                        billDialog,
                        "Would you like to clear this order now?",
                        "Clear Order",
                        JOptionPane.YES_NO_OPTION
                );

                if (response == JOptionPane.YES_OPTION) {
                    billing.clearOrder();
                    textArea.append("\nOrder cleared.");
                }

                billDialog.dispose();
            });

            cancelButton.addActionListener(event -> billDialog.dispose());

            billDialog.setVisible(true);
        });

        // Clear Order action
        clearOrderButton.addActionListener(e -> {
            billing.clearOrder();
            textArea.setText("Order cleared.");
        });

        // Close action
        closeButton.addActionListener(e -> orderDialog.dispose());

        orderDialog.add(mainPanel);
        orderDialog.setVisible(true);
    }

    /**
     * Main method - entry point of the application
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new RestaurantManagementSystemGUI());
    }
}

