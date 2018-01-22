import java.awt.*;

/**
 * Ball
 * Created by pkaterski on 2/23/16.
 */
public class Ball extends GameObject {

    private Color color = Color.WHITE;
    private int radius;

    public Ball(int x, int y) {
        super(x, y);
    }

    public Ball(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, radius, radius);
    }

    public void update() {
        x += velX;
        y += velY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
