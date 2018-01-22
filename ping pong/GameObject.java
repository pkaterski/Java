import java.awt.*;

/**
 * Game Object
 * Created by pkaterski on 2/23/16.
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int velX;
    protected int velY;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject() {
    }

    public abstract void render(Graphics g);

    public abstract void update();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
