/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// package bounceboxapp;
package shape.bounceboxapp;

import java.awt.Color;
import java.awt.Shape;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.swing.Box;

import shape.bouncebox.BounceBox;
import shape.bouncebox.Circle;
import shape.bouncebox.Rectangle;
import shape.bouncebox.Square;
import shape.bouncebox.Triangle;
import shape.textfiles.*;

/**
 *
 * @author ashongtical
 */

public class Main {

  /**
   * @param args the command line arguments
   */

  /*
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

  // It is used to record the number of constructor parameters required for each
  // derived class
  public static HashMap<String, Integer> argsMap = new HashMap<>();

  // The parseByString function used to store each derived class is convenient
  // for direct invocation
  public static HashMap<String, BiFunction<String[], String[], shape.bounceboxframework.Shape>> functionMap = new HashMap<>();

  // Used to store the number of instances of each derived class
  public static HashMap<String, Integer> cntMap = new HashMap<>();

  // Used to store the total area
  public static double areaSum = 0;

  // Record the current number of rows when parsing
  private static int currentLineNumber = 0;

  public static int bounceBoxWidth = 700;
  public static int bounceBoxHeight = 500;

  private static FileWriter fileWriter;

  // Initialize the file writer
  static {
    try {
      fileWriter = new FileWriter(
          "textfiles/shapesArea.txt"); // 输出到textfiles/shapesArea.txt
    } catch (IOException e) {
      System.err.println("Unable to create the output file: " + e.getMessage());
    }
  }

  public static void main(String[] args) throws FileNotFoundException {

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

    simpleDemo();
  }

  // It is used to process each row of data
  public static void Handle(BounceBox box, String args[]) {
    if ((args.length <= 0) || (args[0].isEmpty())) {
      return;
    }
    // Find the derived class name for this time
    String shapeType = args[0];
    if (argsMap.get(shapeType) == null) {
      printWithColor(shapeType + " is not a recognized shape");
      // System.out.println();
      println("");
      return;
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
          shapeType + " are too large or illegal");
      println("");
      return;
    }

    // Increase the number of such instances by 1
    if (cntMap.containsKey(shapeType))
      cntMap.put(shapeType, cntMap.get(shapeType) + 1);
    else
      cntMap.put(shapeType, 1);

    // Count its area in
    areaSum += one.getMass();

    box.addShape(one);
  }

  // Extract the data of each row
  public static void parseLine(BounceBox box) {
    try {
      FileInputStream flieIn = new FileInputStream("src/shape/textfiles/practical4.txt");
      // FileInputStream flieIn = new FileInputStream("textfiles/ex4.txt");

      Scanner scan = new Scanner(flieIn);
      currentLineNumber = 0;
      while (scan.hasNextLine()) {
        currentLineNumber++;
        String line = scan.nextLine();
        String args[] = line.split("\\s+");

        Handle(box, args);
      }
    } catch (FileNotFoundException e) {
      // print("11111");
      e.printStackTrace();
    }
  }

  // Output a 16-bit left-aligned string. If it is insufficient, add Spaces
  public static void printWith16(String str) {
    String formattedText = String.format("%-16s", str);
    print(formattedText);
  }

  // Output a red string (but still black in the file)
  public static void printWithColor(String str) {
    String formattedText = "\u001B[31m" + str + "\u001B[0m";
    System.out.print(formattedText);
    try {
      if (fileWriter != null) {
        fileWriter.write(str);
        fileWriter.flush();
      }
    } catch (IOException e) {
      System.err.println("File writing failed: " + e.getMessage());
    }
  }

  public static void print(String str) {
    System.out.print(str);
    try {
      if (fileWriter != null) {
        fileWriter.write(str);
        fileWriter.flush();
      }
    } catch (IOException e) {
      System.err.println("File writing failed: " + e.getMessage());
    }
  }

  public static void println(String str) {
    System.out.println(str);

    try {
      if (fileWriter != null) {
        fileWriter.write(str + "\r\n");
        fileWriter.flush();
      }
    } catch (IOException e) {
      System.err.println("File writing failed: " + e.getMessage());
    }
  }

  public static void simpleDemo() {
    BounceBox box = new BounceBox(bounceBoxWidth, bounceBoxHeight);
    parseLine(box);
    box.start();

    int current = 0;
    for (Map.Entry<String, Integer> entry : cntMap.entrySet()) {
      printWith16(entry.getKey() + ": " + entry.getValue());
      current++;
      if (current % 2 == 0) {
        println("");
      }
    }
    if (current % 2 == 1) {
      println("");
    }

    print("Total Area is ");
    print(Double.toString(areaSum));
    println("");
  }
}
