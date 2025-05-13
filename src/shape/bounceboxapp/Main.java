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
import java.util.ArrayList;

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

  // public static ArrayList<shape.bounceboxframework.Shape> shapes = new
  // ArrayList<>();

  public static String fileName = "src/shape/textfiles/practical4.txt";

  public static String fileoutName = "src/shape/textfiles/shapesArea.txt";

  // Record the current number of rows when parsing

  public static int bounceBoxWidth = 700;
  public static int bounceBoxHeight = 500;

  private static FileWriter fileWriter;

  // Initialize the file writer
  static {
    try {
      fileWriter = new FileWriter(fileoutName); // 输出到textfiles/shapesArea.txt
    } catch (IOException e) {
      System.err.println("Unable to create the output file: " + e.getMessage());
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    ShapeService.getInstance().preWork();
    ArrayList<shape.bounceboxframework.Shape> shapes = ShapeService.getInstance().parseFile(fileName);
    ShapeService.getInstance().displayShapes(shapes);
    ShapeService.getInstance().printOutputString();
    ShapeService.getInstance().writeToFile(fileoutName);

  }

}
