import java.awt.*;

/**
 * Disk class
 * Created by pkaterski on 2/18/16.
 */

public class Disk {
    private int weight;
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    private int velX;
    private int velY;

    //border around rectangles
    private int border = 3;

    public Disk(int weight, int xPos, int yPos) {
        this.weight = weight;
        this.xPos = xPos;
        this.yPos = yPos;

        int diskConst = Main.WIDTH / 35;

        this.width = diskConst + diskConst * weight;
        this.height = diskConst;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(xPos - border, yPos - border, width + 2 * border, height + 2 * border);



        g.setColor(Color.BLUE);
        g.fillRect(xPos, yPos, width, height);
    }

    public void update() {
        xPos += velX;
        yPos += velY;

        /*xPos = (xPos >= 0) ? (xPos % Main.WIDTH) :
                (( Main.WIDTH - ((-xPos) % Main.WIDTH ) ) % Main.WIDTH );

        yPos = (yPos >= 0) ? (yPos % Main.HEIGHT) :
                (( Main.HEIGHT - ((-xPos) % Main.HEIGHT ) ) % Main.HEIGHT );*/
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

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public String toString() {
        return String.format("%s", weight);
    }

}
