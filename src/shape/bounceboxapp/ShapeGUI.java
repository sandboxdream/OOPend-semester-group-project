package shape.bounceboxapp;

import shape.bounceboxframework.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ShapeGUI {
    private JFrame mainFrame;
    private JButton showShapesButton;

    public ShapeGUI() {
        // Create the main frame
        mainFrame = new JFrame("Shape GUI");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 100);
        mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // Create and add the button to display shapes
        showShapesButton = new JButton("Show Shapes");
        showShapesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleShowShapes();
            }
        });

        mainFrame.add(showShapesButton);
    }

    /**
     * Display the GUI
     */
    public void show() {
        mainFrame.setLocationRelativeTo(null); // Center on screen
        mainFrame.setVisible(true);
    }

    /**
     * Handle the logic for showing shapes, similar to the main method in Main.java
     */
    private void handleShowShapes() {
        String fileName = "src/shape/textfiles/practical4.txt";
        String fileOutName = "src/shape/textfiles/shapesArea.txt";

        try {
            ShapeService.getInstance().preWork();
            ArrayList<Shape> shapes = ShapeService.getInstance().parseFile(fileName);
            ShapeService.getInstance().displayShapes(shapes);
            ShapeService.getInstance().printOutputString();
            ShapeService.getInstance().writeToFile(fileOutName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainFrame, 
                "An error occurred: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        // Execute in the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShapeGUI gui = new ShapeGUI();
                gui.show();
            }
        });
    }
}
