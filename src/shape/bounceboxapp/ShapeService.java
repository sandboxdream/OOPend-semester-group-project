// package bounceboxapp;
package shape.bounceboxapp;

import shape.bouncebox.BounceBox;
import shape.bouncebox.Circle;
import shape.bouncebox.Rectangle;
import shape.bouncebox.Square;
import shape.bouncebox.Triangle;
import shape.bounceboxframework.*;
import shape.textfiles.*;

import java.awt.Color;
// import java.awt.Shape;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Box;
import java.util.Map;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import java.util.ArrayList;

public class ShapeService {

    /*
     * The following annotation content is transplanted from the Main function, but
     * it does not affect understanding.
     * The coupling degree is low. If you want to add a new shape, just write the
     * corresponding
     * parseByString function and initialize it in the main function. For example:
     * argsMap.put("Circle", 3);
     * functionMap.put("Circle", (constructArgs, otherArgs) -> {
     * return Circle.parseByString(constructArgs, otherArgs);
     * });
     * In this way, the parsing of parameters can be achieved
     * Decouple from the Handle function
     *
     * The handle function has been added to the Shape class to extract the
     * content related to setting the color or speed. In this way, if there are
     * any other Settings you want to make later, you can directly modify them in
     * handle.
     *
     */

    public static HashMap<String, Integer> argsMap = new HashMap<>();

    // The parseByString function used to store each derived class is convenient
    // for direct invocation
    public static HashMap<String, BiFunction<String[], String[], shape.bounceboxframework.Shape>> functionMap = new HashMap<>();

    public BounceBox box = new BounceBox(700, 500);

    private static ShapeService instance;

    private StringBuilder outputString = new StringBuilder();

    private ShapeService() {
    }

    public static ShapeService getInstance() {
        if (instance == null) {
            instance = new ShapeService();
        }
        return instance;
    }

    public void preWork() {
        // The number of constructor parameters required to initialize each derived
        // class
        argsMap.put("Circle", 3);
        argsMap.put("Square", 3);
        argsMap.put("Rectangle", 4);
        argsMap.put("Triangle", 4);

        // Initialize the parseByString function of each derived class
        functionMap.put("Circle", (constructArgs, otherArgs) -> {
            return Circle.parseByString(constructArgs, otherArgs);
        });
        functionMap.put("Rectangle", (constructArgs, otherArgs) -> {
            return Rectangle.parseByString(constructArgs, otherArgs);
        });
        functionMap.put("Square", (constructArgs, otherArgs) -> {
            return Square.parseByString(constructArgs, otherArgs);
        });
        functionMap.put("Triangle", (constructArgs, otherArgs) -> {
            return Triangle.parseByString(constructArgs, otherArgs);
        });
    }

    public ArrayList<Shape> parseFile(String filename) {
        ArrayList<Shape> shapes = new ArrayList<>();

        try {
            FileInputStream flieIn = new FileInputStream(filename);

            Scanner scan = new Scanner(flieIn);
            int currentLineNumber = 0;
            while (scan.hasNextLine()) {
                currentLineNumber++;
                String line = scan.nextLine();
                String args[] = line.split("\\s+");

                if ((args.length <= 0) || (args[0].isEmpty()))
                    continue;

                // Find the derived class name for this time
                String shapeType = args[0];
                if (argsMap.get(shapeType) == null) {
                    printWithColor(shapeType + " is not a recognized shape" + "\r\n");
                    continue;
                }

                // Find the number of parameters of its constructor
                int constructArgsLength = argsMap.get(shapeType);

                // Divide args into two character arrays: the string of the constructor and
                // the string for setting the speed or color
                String[] constructArgs = Arrays.copyOfRange(
                        args, 1, Math.min(1 + constructArgsLength, args.length));
                String[] otherArgs = Arrays.copyOfRange(args, 1 + constructArgs.length, args.length);

                // Find the corresponding function to be called
                BiFunction<String[], String[], shape.bounceboxframework.Shape> func = functionMap.get(shapeType);
                shape.bounceboxframework.Shape one = func.apply(
                        constructArgs, otherArgs); // Call the function to obtain the instance

                // If it is null, it indicates that the data format is illegal
                if (one == null) {
                    printWithColor("LINE: " + currentLineNumber + ": The parameters of " +
                            shapeType + " are too large or illegal\r\n");
                    continue;
                }

                shapes.add(one);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return shapes;
    }

    public HashMap<String, Integer> countByType(ArrayList<Shape> shapes) {
        HashMap<String, Integer> shapesCount = new HashMap<>();
        for (Shape one : shapes) {
            String className = one.getClass().getSimpleName();
            if (shapesCount.containsKey(className)) {
                shapesCount.put(className, shapesCount.get(className) + 1);
            } else {
                shapesCount.put(className, 1);
            }
        }
        return shapesCount;
    }

    public double totalArea(ArrayList<Shape> shapes) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getMass();
        }
        return totalArea;
    }

    public void displayShapes(ArrayList<Shape> shapes) {

        for (Shape one : shapes) {
            box.addShape(one);
        }
        box.start();

        HashMap<String, Integer> cntMap = countByType(shapes);
        for (Map.Entry<String, Integer> entry : cntMap.entrySet()) {
            println(entry.getKey() + ": " + entry.getValue());
        }

        print("Total Area is ");
        print(Double.toString(totalArea(shapes)));
        println("");

    }

    private void printWithColor(String str) {
        String formattedText = "\u001B[31m" + str + "\u001B[0m";
        outputString.append(formattedText);
    }

    private void print(String str) {
        outputString.append(str);
    }

    private void println(String str) {
        outputString.append(str + "\r\n");
    }

    public void printOutputString() {
        System.out.println(outputString.toString());
    }

    public void writeToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            String str = outputString.toString();
            str = str.replace("\u001B[31m", "");
            str = str.replace("\u001B[0m", "");
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
