package MainGUI;

import java.awt.*;
import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) {
        // Create the main frame
        javax.swing.JFrame frame = new javax.swing.JFrame("Integrated Management System");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Create title label
        JLabel titleLabel = new JLabel("System Management Interface", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for buttons with GridLayout (2x2)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));

        // Create buttons for launching different GUIs
        javax.swing.JButton zooButton = new javax.swing.JButton("Zoo Management System");
        javax.swing.JButton shapeButton = new javax.swing.JButton("Shape GUI");
        javax.swing.JButton restaurantButton = new javax.swing.JButton("Restaurant Management System");
        javax.swing.JButton bankingButton = new javax.swing.JButton("Banking Task Management System");

        // Set preferred size for buttons to make them larger
        Dimension buttonSize = new Dimension(200, 100);
        zooButton.setPreferredSize(buttonSize);
        shapeButton.setPreferredSize(buttonSize);
        restaurantButton.setPreferredSize(buttonSize);
        bankingButton.setPreferredSize(buttonSize);

        // Add action listeners to buttons
        zooButton.addActionListener(e -> Zoo.ZooManagementSystemGUI.main(new String[]{}));
        shapeButton.addActionListener(e -> shape.bounceboxapp.ShapeGUI.main(new String[]{}));
        restaurantButton.addActionListener(e -> RestaurantManagementSystem.RestaurantManagementSystemGUI.main(new String[]{}));
        bankingButton.addActionListener(e -> Bankmanagementsystem.BankingTaskListGUI.main(new String[]{}));

        // Add buttons to the button panel
        buttonPanel.add(zooButton);
        buttonPanel.add(shapeButton);
        buttonPanel.add(restaurantButton);
        buttonPanel.add(bankingButton);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT_ON_CLOSE to DISPOSE_ON_CLOSE


        // Create status bar at bottom
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel statusLabel = new JLabel("Ready to launch modules");
        statusPanel.add(statusLabel);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        // Create the menu bar
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();

        // Create the "File" menu
        javax.swing.JMenu fileMenu = new javax.swing.JMenu("File");
        menuBar.add(fileMenu);

        // Create the "Exit" menu item
        javax.swing.JMenuItem exitItem = new javax.swing.JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Add Help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, 
                    "Integrated Management System v1.0\n" +
                    "Object-Oriented Programming Final Project\n" +
                    "Contains four subsystems:\n" +
                    "1. Zoo Management System\n" +
                    "2. Shape GUI\n" +
                    "3. Restaurant Management System\n" +
                    "4. Banking Task Management System",
                    "About", 
                    JOptionPane.INFORMATION_MESSAGE);
        });
        helpMenu.add(aboutItem);

        // Set the menu bar to the frame
        frame.setJMenuBar(menuBar);

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Center the frame on screen
        frame.setLocationRelativeTo(null);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
