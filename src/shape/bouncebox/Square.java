/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shape.bouncebox;

import java.awt.Graphics2D;

import shape.bounceboxapp.Main;
import shape.bounceboxframework.Shape;

/**
 *
 * @author ashongtical
 */
public class Square extends Shape {

    private double contactRadius;
    private int width;

    public Square(int x, int y, int width) {
        super(x, y);
        this.width = width;
        contactRadius = Math.sqrt(width * width / 2);
    }

    public int getWidth() {
        return width;
    }

    public double getContactRadius() {
        return contactRadius;
    }

    public double getMass() {
        return width * width;
    }

    public void draw(Graphics2D g) {
        g.setColor(getColor());

        int left = (int) (getX() - width / 2);
        int top = (int) (getY() - width / 2);
        g.fillRect(left, top, width, width);

    }

    // "constructArgs" stores the parameters required for constructing the current
    // class,
    // and "otherArgs" stores the parameters for setting the speed or color
    public static Square parseByString(String[] constructArgs, String[] otherArgs) {
        if (constructArgs.length != Main.argsMap.get("Square"))
            return null;
        try {
            int x = Integer.parseInt(constructArgs[0]);
            int y = Integer.parseInt(constructArgs[1]);
            int width = Integer.parseInt(constructArgs[2]);
            Square c = new Square(x, y, width);
            if (width <= 0 || width > Main.bounceBoxHeight || width > Main.bounceBoxWidth)
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
