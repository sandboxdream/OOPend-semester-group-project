package MainGUI;

public class MainGUI {
    public static void main(String[] args) {
        // Create the main frame
        javax.swing.JFrame frame = new javax.swing.JFrame("Zoo Management System");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the main panel
        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new java.awt.BorderLayout());

        // Create a vertical panel for buttons
        javax.swing.JPanel buttonPanel = new javax.swing.JPanel();
        buttonPanel.setLayout(new javax.swing.BoxLayout(buttonPanel, javax.swing.BoxLayout.Y_AXIS));

        // Create buttons for launching different GUIs
        javax.swing.JButton zooButton = new javax.swing.JButton("Zoo Management System");
        javax.swing.JButton shapeButton = new javax.swing.JButton("Shape GUI");
        javax.swing.JButton restaurantButton = new javax.swing.JButton("Restaurant Management System");
        javax.swing.JButton bankingButton = new javax.swing.JButton("Banking Task Management System");

        // Add action listeners to buttons
        zooButton.addActionListener(e -> Zoo.ZooManagementSystemGUI.main(new String[]{}));
        shapeButton.addActionListener(e -> shape.bounceboxapp.ShapeGUI.main(new String[]{}));
        restaurantButton.addActionListener(e -> RestaurantManagementSystem.RestaurantManagementSystemGUI.main(new String[]{}));
        bankingButton.addActionListener(e -> Bankmanagementsystem.BankingTaskListGUI.main(new String[]{}));

        // Add buttons to the button panel
        buttonPanel.add(zooButton);
        buttonPanel.add(javax.swing.Box.createVerticalStrut(10)); // Add spacing between buttons
        buttonPanel.add(shapeButton);
        buttonPanel.add(javax.swing.Box.createVerticalStrut(10));
        buttonPanel.add(restaurantButton);
        buttonPanel.add(javax.swing.Box.createVerticalStrut(10));
        buttonPanel.add(bankingButton);

        // Add the button panel to the main panel
        panel.add(buttonPanel, java.awt.BorderLayout.CENTER);

        // Create the menu bar
        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();

        // Create the "File" menu
        javax.swing.JMenu fileMenu = new javax.swing.JMenu("File");
        menuBar.add(fileMenu);

        // Create the "Exit" menu item
        javax.swing.JMenuItem exitItem = new javax.swing.JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Set the menu bar to the frame
        frame.setJMenuBar(menuBar);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
