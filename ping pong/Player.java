import java.awt.*;

/**
 * Player
 * Created by pkaterski on 2/23/16.
 */

public class Player extends GameObject {

    private Color color = Color.WHITE;
    private int width;
    private int height;

    public Player(int x, int y) {
        super(x, y);
    }

    public Player(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
