package shape.bouncebox;

import java.awt.Graphics2D;

import java.awt.geom.Path2D;

import shape.bounceboxapp.Main;
import shape.bounceboxapp.ShapeService;
import shape.bounceboxframework.Shape;

/**
 *
 * @author ashongtical
 */
public class Triangle extends Shape {

    private double contactRadius;
    private int base, height;

    public Triangle(int x, int y, int base, int height) {
        super(x, y);
        this.base = base;
        this.height = height;
        contactRadius = (height * height + base * base * 0.25) / (2 * height);
    }

    public double getContactRadius() {
        return contactRadius;
    }

    public double getMass() {
        return (double) base * height / 2;
    }

    public void draw(Graphics2D g) {
        g.setColor(getColor());

        Path2D triangle = new Path2D.Double();
        triangle.moveTo(getX(), getY() - contactRadius); // Starting point
        double tem = Math.sqrt((contactRadius * contactRadius - base * base * 0.25));
        triangle.lineTo(getX() - base * 0.5, getY() + tem); // The second vertex
        triangle.lineTo(getX() + base * 0.5, getY() + tem); // The third vertex
        triangle.closePath(); // Close the path and connect back to the starting point
        // Draw a triangle
        g.fill(triangle);

    }

    // "constructArgs" stores the parameters required for constructing the current
    // class,
    // and "otherArgs" stores the parameters for setting the speed or color
    public static Triangle parseByString(String[] constructArgs, String[] otherArgs) {
        if (constructArgs.length != ShapeService.argsMap.get("Triangle"))
            return null;
        try {
            int x = Integer.parseInt(constructArgs[0]);
            int y = Integer.parseInt(constructArgs[1]);
            int base = Integer.parseInt(constructArgs[2]);
            int height = Integer.parseInt(constructArgs[3]);
            Triangle c = new Triangle(x, y, base, height);
            if (base <= 0 || height <= 0 || base > Main.bounceBoxWidth || height > Main.bounceBoxHeight)
                return null;
            if (x < 0 || y < 0 || x > Main.bounceBoxWidth || y > Main.bounceBoxHeight)
                return null;
            if (c.getMass() > Main.bounceBoxHeight * Main.bounceBoxWidth)
                return null;
            c.handle(otherArgs);
            return c;
        } catch (NumberFormatException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
