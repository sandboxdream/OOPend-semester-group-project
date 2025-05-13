/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shape.bouncebox;

import java.awt.Color;
import java.awt.Graphics2D;
import shape.bounceboxapp.Main;
import shape.bounceboxapp.ShapeService;
import shape.bounceboxframework.Shape;

/**
 *
 * @author ashongtical
 */

public class Circle extends Shape {

    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getContactRadius() {
        return radius;
    }

    public double getMass() {
        return Math.PI * radius * radius;
    }

    public void draw(Graphics2D g) {
        g.setColor(getColor());
        double left = getX() - getRadius();
        double top = getY() - getRadius();
        g.fillOval((int) left, (int) top, getRadius() * 2,
                getRadius() * 2);
    }

    // "constructArgs" stores the parameters required for constructing the current
    // class,
    // and "otherArgs" stores the parameters for setting the speed or color
    public static Circle parseByString(String[] constructArgs, String[] otherArgs) {
        if (constructArgs.length != ShapeService.argsMap.get("Circle"))
            return null;
        try {
            int x = Integer.parseInt(constructArgs[0]);
            int y = Integer.parseInt(constructArgs[1]);
            int radius = Integer.parseInt(constructArgs[2]);
            Circle c = new Circle(x, y, radius);
            if (radius <= 0 || radius > Main.bounceBoxHeight || radius > Main.bounceBoxWidth)
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
