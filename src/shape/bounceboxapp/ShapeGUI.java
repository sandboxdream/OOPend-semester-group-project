package shape.bounceboxapp;

import shape.bounceboxframework.Shape;

import javax.swing.*;

import MainGUI.MainGUI;
import Zoo.Animal;
import Zoo.ZooManagementSystemGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShapeGUI {
    private JFrame mainFrame;
    private JButton showShapesButton;

    String fileName = "src/shape/textfiles/practical4.txt";
    String fileOutName = "src/shape/textfiles/shapesArea.txt";

    public ShapeGUI() {
        // Create the main frame
        mainFrame = new JFrame("Shape GUI");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT_ON_CLOSE to DISPOSE_ON_CLOSE
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

        if (MainGUI.isStartFromMainGUI) {
            SaveAnimal();
        }

        mainFrame.setLocationRelativeTo(null); // Center on screen
        mainFrame.setVisible(true);
    }

    private void SaveAnimal() {
        HashMap<String, Integer> AnimalSpeciesmap = new HashMap<String, Integer>();
        int nums;
        int MaxAnimalNums = 0;
        nums = ZooManagementSystemGUI.southernZone.getCounter();
        for (int i = 0; i < nums; i++) {
            Animal animal = ZooManagementSystemGUI.southernZone.getAnimal(i);
            if (animal == null) {
                continue;
            }
            // System.out.println(animal.getSpecies());
            String species = animal.getSpecies();
            if (AnimalSpeciesmap.containsKey(species)) {
                AnimalSpeciesmap.put(species, AnimalSpeciesmap.get(species) + 1);
            } else {
                AnimalSpeciesmap.put(species, 1);
            }
            MaxAnimalNums = Math.max(MaxAnimalNums, AnimalSpeciesmap.get(species));
        }

        nums = ZooManagementSystemGUI.northernZone.getCounter();
        for (int i = 0; i < nums; i++) {
            Animal animal = ZooManagementSystemGUI.northernZone.getAnimal(i);
            if (animal == null) {
                continue;
            }
            String species = animal.getSpecies();
            // System.out.println(animal.getSpecies());

            if (AnimalSpeciesmap.containsKey(species)) {
                AnimalSpeciesmap.put(species, AnimalSpeciesmap.get(species) + 1);
            } else {
                AnimalSpeciesmap.put(species, 1);
            }
            MaxAnimalNums = Math.max(MaxAnimalNums, AnimalSpeciesmap.get(species));

        }

        int Signalwidth = Math.min(600 / AnimalSpeciesmap.size(), 40);
        int SignalHeight = Math.min(400 / MaxAnimalNums, 40);
        int SignalInterval = 10;
        int StartX = 50;
        int StartY = 400;

        Random rand = new Random();
        try (FileWriter writer = new FileWriter(fileName)) {

            // FileWriter writer = new FileWriter(fileName);

            for (Map.Entry<String, Integer> entry : AnimalSpeciesmap.entrySet()) {

                int ToX = StartX + Signalwidth / 2;
                int ToY = StartY - SignalHeight * entry.getValue() / 2;

                String str = "Rectangle " + ToX + " " + ToY + " " + Signalwidth + " " + SignalHeight * entry.getValue()
                        + " " + 0 + " " + 0 + " " + rand.nextInt(255) + " " + rand.nextInt(255) + " "
                        + rand.nextInt(256)
                        + "\r\n";
                writer.write(str);
                System.out.print(str);

                StartX += SignalInterval + Signalwidth;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Handle the logic for showing shapes, similar to the main method in Main.java
     */
    private void handleShowShapes() {

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
