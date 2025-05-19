package Zoo;

import javax.swing.*;

import Bankmanagementsystem.BankingTaskListGUI;
import MainGUI.MainGUI;

import java.awt.*;
import java.awt.event.*;

/**
 * ZooManagementSystemGUI - GUI implementation of the Zoo Management System
 */
public class ZooManagementSystemGUI extends JFrame {
    // Zoo instances
    private Zoo southernZone;
    private Zoo northernZone;

    // Main panels
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel contentPanel;

    // Components for displaying zoo information
    private JTextArea displayArea;
    private JScrollPane scrollPane;

    /**
     * Constructor - initializes the GUI and zoo instances
     */
    public ZooManagementSystemGUI() {
        // Set up the frame
        super("Zoo Management System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT_ON_CLOSE to DISPOSE_ON_CLOSE
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Initialize zoo instances
        southernZone = new Zoo("Southern-Zone Zoo");
        northernZone = new Zoo("Northern-Zone Zoo");

        if (southernZone == null || northernZone == null) {
            JOptionPane.showMessageDialog(this, "Zoo instances could not be created.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Add initial animals to zoos
        initializeZoos();

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
     * Initialize zoos with sample animals
     */
    private void initializeZoos() {
        // Add 5 animals to Southern-Zone Zoo
        southernZone.addAnimal(new Animal("Simba", "African Lion", 6));
        southernZone.addAnimal(new Animal("Dumbo", "African Elephant", 12));
        southernZone.addAnimal(new Animal("Luna", "Gray Wolf", 4));
        southernZone.addAnimal(new Animal("Poe", "Raven", 3));
        southernZone.addAnimal(new Animal("Benny", "Grizzly Bear", 8));

        // Add 5 animals to Northern-Zone Zoo
        northernZone.addAnimal(new Animal("Arctic", "Polar Bear", 7));
        northernZone.addAnimal(new Animal("Blizzard", "Snow Leopard", 5));
        northernZone.addAnimal(new Animal("Frost", "Arctic Fox", 3));
        northernZone.addAnimal(new Animal("Penguin", "Emperor Penguin", 4));
        northernZone.addAnimal(new Animal("Aurora", "Caribou", 6));
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
        JButton displaySouthernButton = new JButton("Display Southern-Zone");
        JButton displayNorthernButton = new JButton("Display Northern-Zone");
        JButton moveAnimalButton = new JButton("Move Animal");
        JButton addAnimalButton = new JButton("Add Animal");
        JButton removeAnimalButton = new JButton("Remove Animal");
        JButton findAnimalButton = new JButton("Find Animal");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        displaySouthernButton.addActionListener(e -> displayAnimals(southernZone));
        displayNorthernButton.addActionListener(e -> displayAnimals(northernZone));
        moveAnimalButton.addActionListener(e -> moveAnimal());
        addAnimalButton.addActionListener(e -> addAnimal());
        removeAnimalButton.addActionListener(e -> removeAnimal());
        findAnimalButton.addActionListener(e -> findAnimal());
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the menu panel
        menuPanel.add(createButtonWithMargin(displaySouthernButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(displayNorthernButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(moveAnimalButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(addAnimalButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(removeAnimalButton));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(createButtonWithMargin(findAnimalButton));
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
        displayArea.setText("Welcome to the Zoo Management System!\n\n" +
                "Please select an operation from the menu on the left.");
    }

    /**
     * Display all animals in the specified zoo
     */
    private void displayAnimals(Zoo zoo) {
        StringBuilder sb = new StringBuilder();
        sb.append("---- Animals in ").append(zoo.getName()).append(" Zoo (")
                .append(zoo.getCounter()).append("/10)----\n");

        for (int i = 0; i < zoo.getCounter(); i++) {
            Animal animal = zoo.getAnimal(i);
            sb.append("Animal Name: ").append(animal.getName())
                    .append("\t,Species: ").append(animal.getSpecies())
                    .append("\t,Age: ").append(animal.getAge())
                    .append("\n");
        }

        sb.append("---------------------------------\n");
        displayArea.setText(sb.toString());
    }

    /**
     * Move an animal between zoos
     */
    private void moveAnimal() {
        // Create a dialog for selecting source and destination zoos
        JDialog moveDialog = new JDialog(this, "Move Animal", true);
        moveDialog.setLayout(new BorderLayout());
        moveDialog.setSize(400, 300);
        moveDialog.setLocationRelativeTo(this);

        JPanel selectionPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create radio buttons for selecting source zoo
        JLabel sourceLabel = new JLabel("Select source zoo:");
        JRadioButton southernSourceButton = new JRadioButton("Southern-Zone Zoo");
        JRadioButton northernSourceButton = new JRadioButton("Northern-Zone Zoo");
        ButtonGroup sourceGroup = new ButtonGroup();
        sourceGroup.add(southernSourceButton);
        sourceGroup.add(northernSourceButton);
        southernSourceButton.setSelected(true);

        JPanel sourcePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sourcePanel.add(sourceLabel);
        sourcePanel.add(southernSourceButton);
        sourcePanel.add(northernSourceButton);

        // Create text field for entering animal name
        JPanel animalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel animalLabel = new JLabel("Enter animal name:");
        JTextField animalField = new JTextField(20);
        animalPanel.add(animalLabel);
        animalPanel.add(animalField);

        // Create buttons for confirming or canceling
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Add panels to the dialog
        selectionPanel.add(sourcePanel);
        selectionPanel.add(animalPanel);
        moveDialog.add(selectionPanel, BorderLayout.CENTER);
        moveDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to the cancel button
        cancelButton.addActionListener(e -> moveDialog.dispose());

        // Add action listener to the confirm button
        confirmButton.addActionListener(e -> {
            String animalName = animalField.getText().trim();
            if (animalName.isEmpty()) {
                JOptionPane.showMessageDialog(moveDialog, "Please enter an animal name.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Determine source and destination zoos
            // Zoo sourceZoo = southernSourceButton.isSelected() ? southernZone :
            // northernZone;
            // Zoo destZoo = southernSourceButton.isSelected() ? northernZone :
            // southernZone;
            // Zoo sourceZoo = southernSourceButton.isSelected()
            // ? (Zoo) southernZone
            // : (Zoo) northernZone;
            // Zoo destZoo = southernSourceButton.isSelected()
            // ? (Zoo) northernZone
            // : (Zoo) southernZone;
            // boolean isSouthernSelected = southernSourceButton.isSelected();
            // Zoo sourceZoo = isSouthernSelected ? southernZone : northernZone;
            // Zoo destZoo = isSouthernSelected ? northernZone : southernZone;
            Zoo sourceZoo, destZoo;
            if (southernSourceButton.isSelected() == true) {
                sourceZoo = southernZone;
                destZoo = northernZone;
            } else {
                sourceZoo = northernZone;
                destZoo = southernZone;
            }

            // Check if animal exists in source zoo
            int index = sourceZoo.findAnimal(animalName);
            if (index == -1) {
                JOptionPane.showMessageDialog(moveDialog, "Animal not found in " + sourceZoo.getName(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create logistics dialog
            moveDialog.dispose();
            createLogisticsDialog(sourceZoo, destZoo, animalName);
        });

        moveDialog.setVisible(true);
    }

    /**
     * Create a dialog for entering logistics information
     */
    private void createLogisticsDialog(Zoo sourceZoo, Zoo destZoo, String animalName) {
        JDialog logisticsDialog = new JDialog(this, "Logistics Information", true);
        logisticsDialog.setLayout(new BorderLayout());
        logisticsDialog.setSize(400, 350);
        logisticsDialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Vehicle information
        formPanel.add(new JLabel("Vehicle Name:"));
        JTextField vehicleNameField = new JTextField("Transport Truck");
        formPanel.add(vehicleNameField);

        formPanel.add(new JLabel("Vehicle Code:"));
        JTextField vehicleCodeField = new JTextField("VEH_001");
        formPanel.add(vehicleCodeField);

        formPanel.add(new JLabel("Vehicle Cost (RMB):"));
        JTextField vehicleCostField = new JTextField();
        formPanel.add(vehicleCostField);

        // Fuel information
        formPanel.add(new JLabel("Fuel Name:"));
        JTextField fuelNameField = new JTextField("Diesel Fuel");
        formPanel.add(fuelNameField);

        formPanel.add(new JLabel("Fuel Code:"));
        JTextField fuelCodeField = new JTextField("FUEL_001");
        formPanel.add(fuelCodeField);

        formPanel.add(new JLabel("Fuel Cost (RMB):"));
        JTextField fuelCostField = new JTextField();
        formPanel.add(fuelCostField);

        // Caretaker information
        JPanel caretakerPanel = new JPanel(new BorderLayout());
        caretakerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel numCaretakerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        numCaretakerPanel.add(new JLabel("Number of Caretakers (1-3):"));
        JComboBox<Integer> numCaretakersCombo = new JComboBox<Integer>(new Integer[] { 1, 2, 3 });
        numCaretakerPanel.add(numCaretakersCombo);

        JPanel caretakerNamesPanel = new JPanel();
        caretakerNamesPanel.setLayout(new BoxLayout(caretakerNamesPanel, BoxLayout.Y_AXIS));

        JTextField[] caretakerFields = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel.add(new JLabel("Caretaker " + (i + 1) + ":"));
            caretakerFields[i] = new JTextField(20);
            panel.add(caretakerFields[i]);
            caretakerNamesPanel.add(panel);
            // Initially show only the first caretaker field
            panel.setVisible(i == 0);
        }

        // Add listener to update visible caretaker fields
        numCaretakersCombo.addActionListener(e -> {
            int numCaretakers = (Integer) numCaretakersCombo.getSelectedItem();
            for (int i = 0; i < 3; i++) {
                caretakerFields[i].getParent().setVisible(i < numCaretakers);
            }
            caretakerNamesPanel.revalidate();
            caretakerNamesPanel.repaint();
        });

        caretakerPanel.add(numCaretakerPanel, BorderLayout.NORTH);
        caretakerPanel.add(caretakerNamesPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Add panels to the dialog
        logisticsDialog.add(formPanel, BorderLayout.NORTH);
        logisticsDialog.add(caretakerPanel, BorderLayout.CENTER);
        logisticsDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to the cancel button
        cancelButton.addActionListener(e -> logisticsDialog.dispose());

        // Add action listener to the confirm button
        confirmButton.addActionListener(e -> {
            try {
                // Validate input
                String vehicleName = vehicleNameField.getText().trim();
                String vehicleCode = vehicleCodeField.getText().trim();
                double vehicleCost = Double.parseDouble(vehicleCostField.getText().trim());

                String fuelName = fuelNameField.getText().trim();
                String fuelCode = fuelCodeField.getText().trim();
                double fuelCost = Double.parseDouble(fuelCostField.getText().trim());

                int numCaretakers = (Integer) numCaretakersCombo.getSelectedItem();
                String[] caretakers = new String[numCaretakers];
                for (int i = 0; i < numCaretakers; i++) {
                    caretakers[i] = caretakerFields[i].getText().trim();
                    if (caretakers[i].isEmpty()) {
                        throw new Exception("Please enter all caretaker names.");
                    }
                }

                // Create items and logistics
                Item vehicle = new Item(vehicleName, vehicleCode);
                vehicle.setPrice(vehicleCost);

                Item fuel = new Item(fuelName, fuelCode);
                fuel.setPrice(fuelCost);

                Logistics logistics = new Logistics(vehicle, fuel, caretakers);

                if (MainGUI.isStartFromMainGUI == true)
                    BankingTaskListGUI.taskManager.withdraw(MainGUI.UserID, logistics.getTotalCost());

                // Move the animal
                sourceZoo.moveAnimal(animalName, destZoo, logistics);

                // Display updated zoo information
                StringBuilder sb = new StringBuilder();
                sb.append("Animal ").append(animalName)
                        .append(" moved from ").append(sourceZoo.getName())
                        .append(" to ").append(destZoo.getName()).append("\n\n");

                sb.append("Updated ").append(sourceZoo.getName()).append(":\n");
                for (int i = 0; i < sourceZoo.getCounter(); i++) {
                    Animal animal = sourceZoo.getAnimal(i);
                    sb.append("Animal Name: ").append(animal.getName())
                            .append("\t,Species: ").append(animal.getSpecies())
                            .append("\t,Age: ").append(animal.getAge())
                            .append("\n");
                }

                sb.append("\nUpdated ").append(destZoo.getName()).append(":\n");
                for (int i = 0; i < destZoo.getCounter(); i++) {
                    Animal animal = destZoo.getAnimal(i);
                    sb.append("Animal Name: ").append(animal.getName())
                            .append("\t,Species: ").append(animal.getSpecies())
                            .append("\t,Age: ").append(animal.getAge())
                            .append("\n");
                }

                displayArea.setText(sb.toString());
                logisticsDialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(logisticsDialog, "Please enter valid numbers for costs.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(logisticsDialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        logisticsDialog.setVisible(true);
    }

    /**
     * Add a new animal to a zoo
     */
    private void addAnimal() {
        JDialog addDialog = new JDialog(this, "Add Animal", true);
        addDialog.setLayout(new BorderLayout());
        addDialog.setSize(400, 250);
        addDialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Zoo selection
        formPanel.add(new JLabel("Select Zoo:"));
        JComboBox<String> zooCombo = new JComboBox<String>(new String[] { "Southern-Zone Zoo", "Northern-Zone Zoo" });
        formPanel.add(zooCombo);

        // Animal information
        formPanel.add(new JLabel("Animal Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Species:"));
        JTextField speciesField = new JTextField();
        formPanel.add(speciesField);

        formPanel.add(new JLabel("Age:"));
        JTextField ageField = new JTextField();
        formPanel.add(ageField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Add panels to the dialog
        addDialog.add(formPanel, BorderLayout.CENTER);
        addDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to the cancel button
        cancelButton.addActionListener(e -> addDialog.dispose());

        // Add action listener to the add button
        addButton.addActionListener(e -> {
            try {
                // Validate input
                String name = nameField.getText().trim();
                String species = speciesField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());

                if (name.isEmpty() || species.isEmpty()) {
                    JOptionPane.showMessageDialog(addDialog, "Please fill in all fields.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Determine which zoo to add to
                // Zoo selectedZoo = (zooCombo.getSelectedIndex() == 0) ? (Zoo) southernZone :
                // (Zoo) northernZone;
                // Zoo selectedZoo = southernZone;

                // boolean isSouthernSelected = zooCombo.getSelectedIndex() == 0;

                // Zoo selectedZoo = isSouthernSelected ? southernZone : northernZone;

                Zoo selectedZoo;
                if (zooCombo.getSelectedIndex() == 0) {
                    selectedZoo = southernZone;
                } else {
                    selectedZoo = northernZone;
                }
                // Create and add the new animal
                Animal newAnimal = new Animal(name, species, age);
                int result = selectedZoo.addAnimal(newAnimal);

                if (result == -1) {
                    JOptionPane.showMessageDialog(addDialog,
                            selectedZoo.getName() + " is full, cannot add more animals.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Display updated zoo
                    displayAnimals(selectedZoo);
                    addDialog.dispose();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addDialog, "Please enter a valid number for age.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        addDialog.setVisible(true);
    }

    /**
     * Remove an animal from a zoo
     */
    private void removeAnimal() {
        JDialog removeDialog = new JDialog(this, "Remove Animal", true);
        removeDialog.setLayout(new BorderLayout());
        removeDialog.setSize(400, 200);
        removeDialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Zoo selection
        formPanel.add(new JLabel("Select Zoo:"));
        JComboBox<String> zooCombo = new JComboBox<String>(new String[] { "Southern-Zone Zoo", "Northern-Zone Zoo" });
        formPanel.add(zooCombo);

        // Animal name
        formPanel.add(new JLabel("Animal Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton removeButton = new JButton("Remove");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);

        // Add panels to the dialog
        removeDialog.add(formPanel, BorderLayout.CENTER);
        removeDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to the cancel button
        cancelButton.addActionListener(e -> removeDialog.dispose());

        // Add action listener to the remove button
        removeButton.addActionListener(e -> {
            // Validate input
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(removeDialog, "Please enter an animal name.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Determine which zoo to remove from
            // Zoo selectedZoo = (zooCombo.getSelectedIndex() == 0) ? (Zoo) southernZone :
            // (Zoo) northernZone;
            // boolean isSouthernSelected = zooCombo.getSelectedIndex() == 0;

            // Zoo selectedZoo = isSouthernSelected ? southernZone : northernZone;
            // Zoo selectedZoo = southernZone;

            Zoo selectedZoo;
            if (zooCombo.getSelectedIndex() == 0) {
                selectedZoo = southernZone;
            } else {
                selectedZoo = northernZone;
            }

            // Check if zoo has animals
            if (selectedZoo.getCounter() == 0) {
                JOptionPane.showMessageDialog(removeDialog, "No animals to remove from " + selectedZoo.getName(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Remove the animal
            int index = selectedZoo.findAnimal(name);
            if (index == -1) {
                JOptionPane.showMessageDialog(removeDialog,
                        "Animal '" + name + "' not found in " + selectedZoo.getName(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                selectedZoo.deleteAnimal(name);
                // Display updated zoo
                displayAnimals(selectedZoo);
                removeDialog.dispose();
            }
        });

        removeDialog.setVisible(true);
    }

    /**
     * Find an animal in both zoos
     */
    private void findAnimal() {
        JDialog findDialog = new JDialog(this, "Find Animal", true);
        findDialog.setLayout(new BorderLayout());
        findDialog.setSize(400, 150);
        findDialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Animal name
        formPanel.add(new JLabel("Animal Name:"));
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton findButton = new JButton("Find");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(findButton);
        buttonPanel.add(cancelButton);

        // Add panels to the dialog
        findDialog.add(formPanel, BorderLayout.CENTER);
        findDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to the cancel button
        cancelButton.addActionListener(e -> findDialog.dispose());

        // Add action listener to the find button
        findButton.addActionListener(e -> {
            // Validate input
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(findDialog, "Please enter an animal name.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Search for animal in both zoos
            int indexSouthern = southernZone.findAnimal(name);
            int indexNorthern = northernZone.findAnimal(name);

            StringBuilder result = new StringBuilder();

            // Display results based on where (if) animal was found
            if (indexSouthern != -1) {
                Animal animal = southernZone.getAnimal(indexSouthern);
                result.append("Animal found in Southern-Zone Zoo:\n");
                result.append("Animal Name: ").append(animal.getName())
                        .append("\t,Species: ").append(animal.getSpecies())
                        .append("\t,Age: ").append(animal.getAge());
            } else if (indexNorthern != -1) {
                Animal animal = northernZone.getAnimal(indexNorthern);
                result.append("Animal found in Northern-Zone Zoo:\n");
                result.append("Animal Name: ").append(animal.getName())
                        .append("\t,Species: ").append(animal.getSpecies())
                        .append("\t,Age: ").append(animal.getAge());
            } else {
                result.append("Animal '").append(name).append("' not found in either zoo.");
            }

            displayArea.setText(result.toString());
            findDialog.dispose();
        });

        findDialog.setVisible(true);
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
        SwingUtilities.invokeLater(() -> new ZooManagementSystemGUI());
    }
}
