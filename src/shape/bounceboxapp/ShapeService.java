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

    ArrayList<Shape> parseFile(String filename) {
        ArrayList<Shape> shapes = new ArrayList<>();

        return null;
    }

    HashMap<String, Shape> countByType(String filename) {
        HashMap<String, Shape> shapes = new HashMap<>();
        return shapes;
    }

    double totalArea(ArrayList<Shape> shapes) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getMass();
        }
        return totalArea;
    }

    void displayShapes(ArrayList<Shape> shapes) {
        // for (Shape shape : shapes) {
        // System.out.println(shape);
        // }
    }

}
