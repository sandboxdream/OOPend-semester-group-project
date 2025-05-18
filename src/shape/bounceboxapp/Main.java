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

/**
 *
 * @author ashongtical
 */

public class Main {

  /**
   * @param args the command line arguments
   */

  public static String fileName = "src/shape/textfiles/practical4.txt";

  public static String fileoutName = "src/shape/textfiles/shapesArea.txt";

  // Record the current number of rows when parsing

  public static int bounceBoxWidth = 700;
  public static int bounceBoxHeight = 500;

  public static void main(String[] args) throws FileNotFoundException {
    ShapeService.getInstance().preWork();
    ArrayList<shape.bounceboxframework.Shape> shapes = ShapeService.getInstance().parseFile(fileName);
    ShapeService.getInstance().displayShapes(shapes);
    ShapeService.getInstance().printOutputString();
    ShapeService.getInstance().writeToFile(fileoutName);

  }

}
