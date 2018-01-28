import java.util.Stack;
import java.awt.*;

/**
 * Handler class
 * Created by pkaterski on 2/18/16.
 */

public class Handler {
    private int n;
    private Stack<Disk>[] disks = new Stack[3];

    private int animSpeed = 20;


    public Handler(int n) {
        this.n = n;

        disks[0] = new Stack<>();
        disks[1] = new Stack<>();
        disks[2] = new Stack<>();

        int poleNLen = Main.POLEHEIGHT / n;

        for (int i = this.n; i >= 1; i--) {
            disks[0].push(new Disk(i, (Main.WIDTH / 6) - ((20 + 20 * i) / 2),
                    Main.HEIGHT - (n - i + 1) * poleNLen));
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < disks.length; i++) {
            // synchronize to prevent ConcurrentModificationException
            synchronized (disks[i]) {
                for (Disk d : disks[i]) {
                    d.render(g);
                }
            }
        }
    }

    public void update() {
        for (int i = 0; i < disks.length; i++) {
            // synchronize to prevent ConcurrentModificationException
            synchronized (disks[i]) {
                for (Disk d : disks[i]) {
                    d.update();
                }
            }
        }
    }

    public void Move(int n, int pos1, int pos2) {

        int pos3 = Pos3(pos1, pos2);

        if (n == 1) {
            Disk tempDisk;
            // synchronize to prevent ConcurrentModificationException
            synchronized (disks[pos1]) {
                tempDisk = disks[pos1].pop();
            }
            synchronized (disks[pos2]) {
                disks[pos2].add(tempDisk);
            }
            animate(pos1, pos2);
            //show(); //print positions as text
        } else if (n > 1) {
            Move(n-1, pos1, pos3);
            Move(1, pos1, pos2);
            Move(n-1, pos3, pos2);
        } else {
            return;
        }

    }

    private int Pos3(int pos1, int pos2) {

        if (pos1 + pos2 == 1) {
            return 2;
        } else if (pos1 + pos2 == 2) {
            return 1;
        } else if (pos1 + pos2 == 3) {
            return 0;
        }

        return -1;
    }

    public void show() {
        for (int i = 0; i < disks.length; i++) {
            synchronized (disks[i]) {
                System.out.println(disks[i]);
            }
        }
    }

    private void animate(int pos1, int pos2) {

        int const1;
        int const2;

        switch (pos1) {
            case 0:
                const1 = 1;
                break;
            case 1:
                const1 = 3;
                break;
            case 2:
                const1 = 5;
                break;
            default:
                return;
        }

        switch (pos2) {
            case 0:
                const2 = 1;
                break;
            case 1:
                const2 = 3;
                break;
            case 2:
                const2 = 5;
                break;
            default:
                return;
        }



        int poleNLen = Main.POLEHEIGHT / n;


        /*  first destination  */

        int yDest = Main.POLEY / 2;

        disks[pos2].peek().setVelY(-animSpeed);
        int diskyPos = disks[pos2].peek().getyPos();
        while (diskyPos > yDest){
            synchronized (disks[pos2].peek()) {
                diskyPos = disks[pos2].peek().getyPos();
            }
        }
        disks[pos2].peek().setVelY(0);
        disks[pos2].peek().setyPos(yDest);


        /*  second destination  */

        int xDest = (const2 * Main.POLEX) - (disks[pos2].peek().getWidth() / 2);

        if (const1 < const2) {
            disks[pos2].peek().setVelX(animSpeed);
            int diskxPos = disks[pos2].peek().getxPos();
            while (diskxPos < xDest) {
                synchronized (disks[pos2].peek()) {
                    diskxPos = disks[pos2].peek().getxPos();
                }
            }
            disks[pos2].peek().setVelX(0);
            disks[pos2].peek().setxPos(xDest);
        } else {
            disks[pos2].peek().setVelX(-animSpeed);
            int diskxPos = disks[pos2].peek().getxPos();
            while (diskxPos > xDest) {
                synchronized (disks[pos2].peek()) {
                    diskxPos = disks[pos2].peek().getxPos();
                }
            }
            disks[pos2].peek().setVelX(0);
            disks[pos2].peek().setxPos(xDest);
        }


        /*  final destination  */

        yDest = Main.HEIGHT - (disks[pos2].size() * poleNLen);

        disks[pos2].peek().setVelY(animSpeed);
        diskyPos = disks[pos2].peek().getyPos();
        while (diskyPos < yDest){
            synchronized (disks[pos2].peek()) {
                diskyPos = disks[pos2].peek().getyPos();
            }
        }
        disks[pos2].peek().setVelY(0);
        disks[pos2].peek().setyPos(yDest);

    }

    public static void wait(double sec) {
        long lastTime = System.nanoTime();
        double ns = 1000000000 * sec;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                return;
            }
        }
    }

    public int getAnimSpeed() {
        return animSpeed;
    }

    public void setAnimSpeed(int animSpeed) {
        this.animSpeed = animSpeed;
    }
}
