package shape.bouncebox;

import java.awt.Graphics2D;

import shape.bounceboxapp.Main;
import shape.bounceboxframework.Shape;

/**
 *
 * @author ashongtical
 */
public class Rectangle extends Shape {

    private double contactRadius;
    private int base, height;

    public Rectangle(int x, int y, int base, int height) {
        super(x, y);
        this.base = base;
        this.height = height;
        contactRadius = Math.sqrt(base * base * 0.25 + height * height * 0.25);
    }

    public double getContactRadius() {
        return contactRadius;
    }

    public double getMass() {
        return (double) base * height;
    }

    public void draw(Graphics2D g) {
        g.setColor(getColor());
        int left = (int) (getX() - base / 2);
        int top = (int) (getY() - height / 2);
        g.fillRect(left, top, base, height);
    }

    // "constructArgs" stores the parameters required for constructing the current
    // class,
    // and "otherArgs" stores the parameters for setting the speed or color
    public static Rectangle parseByString(String[] constructArgs, String[] otherArgs) {
        if (constructArgs.length != Main.argsMap.get("Rectangle"))
            return null;
        try {
            int x = Integer.parseInt(constructArgs[0]);
            int y = Integer.parseInt(constructArgs[1]);
            int base = Integer.parseInt(constructArgs[2]);
            int height = Integer.parseInt(constructArgs[3]);
            Rectangle c = new Rectangle(x, y, base, height);
            if (base <= 0 || height <= 0 || base > Main.bounceBoxWidth || height > Main.bounceBoxHeight)
                return null;
            if ((x < 0) || (y < 0) || (x > Main.bounceBoxWidth) || (y > Main.bounceBoxHeight))
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
