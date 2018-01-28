import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

/**
 * Main class
 * Created by pkaterski on 2/18/16.
 */

public class Main extends Canvas implements Runnable {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    public static final int POLEWIDTH = WIDTH / 50;
    public static final int POLEHEIGHT = HEIGHT / 4 * 3;

    public static final int POLEX = WIDTH / 6;
    public static final int POLEY = HEIGHT / 4;

    private static String title = "Towers of Hanoi";

    private static boolean running = false;

    private JFrame frame;

    private static Color bgCol = Color.LIGHT_GRAY;

    private Handler handler;

    /*  rotate frame fields  */

    private static boolean rot = false;
    private double r = 0.0;
    private int rotSpeed = 1;
    private Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

    /*  end of rotate frame fields  */


    //border around rectangles
    private int border = 3;


    public Main(int n) {

        handler = new Handler(n);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame(title);
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void run() {
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }

            frames++;
            render();

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                frame.setTitle( title + " | " + updates + " ups, "
                        + frames + " FPS.");

                frames = 0;
                updates = 0;
            }

        }
    }

    public void update() {

        handler.update();



        /*  victory rotation  */

        if (rot) {
            r++;
            frame.setLocation((int)(Math.cos(r / rotSpeed) * 100) + (scrSize.width / 2) - (frame.getWidth() / 2),
                    (int)(Math.sin(r / rotSpeed) * 100) + (scrSize.height / 2) - (frame.getHeight() / 2));

            if ((int)r % 5 == 0) {
                rotSpeed++;
            }

            if ((int)r % 10 == 0) {
                bgCol = Color.BLACK;
            } else if ((int)r % 10 == 5) {
                bgCol = Color.YELLOW;
            }


            if (r >= 120) {
                System.exit(0);
            }
        }

        /* end victory rotation  */

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(bgCol);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.fillRect(1 * POLEX - (POLEWIDTH / 2) - border, POLEY - border, POLEWIDTH + 2 * border, POLEHEIGHT + 2 * border);
        g.fillRect(3 * POLEX - (POLEWIDTH / 2) - border, POLEY - border, POLEWIDTH + 2 * border, POLEHEIGHT + 2 * border);
        g.fillRect(5 * POLEX - (POLEWIDTH / 2) - border, POLEY - border, POLEWIDTH + 2 * border, POLEHEIGHT + 2 * border);



        g.setColor(new Color(0x009944));
        g.fillRect(1 * POLEX - (POLEWIDTH / 2), POLEY, POLEWIDTH, POLEHEIGHT);
        g.fillRect(3 * POLEX - (POLEWIDTH / 2), POLEY, POLEWIDTH, POLEHEIGHT);
        g.fillRect(5 * POLEX - (POLEWIDTH / 2), POLEY, POLEWIDTH, POLEHEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] args) {

        System.setProperty("sun.java2d.opengl", "True");

        int numOfDisks = 0;
        int animSpeed = 0;

        /*  set the number of disks  */

        boolean validNum;
        do {
            try {
                validNum = true;
                String s = JOptionPane.showInputDialog(null, "Enter the number of disks");
                if (s == null) {
                    System.exit(0);
                }
                numOfDisks = Integer.parseInt(s);


                if (numOfDisks <= 0) numOfDisks = 1;
                if (numOfDisks > 10) numOfDisks = 10;


                /*
                if (numOfDisks <= 0 || numOfDisks > 10) {
                    validNum = false;
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 10", "error.", JOptionPane.ERROR_MESSAGE);
                }
                */


            } catch (NumberFormatException e) {
                validNum = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid number", "error.", JOptionPane.ERROR_MESSAGE);
            }
        } while (!validNum);

        /* end setting the number of disks  */


        /*  set the speed  */

        do {
            try {
                validNum = true;
                String s = JOptionPane.showInputDialog(null, "Enter the speed");
                if (s == null) {
                    System.exit(0);
                }
                animSpeed = Integer.parseInt(s);


                if (animSpeed <= 0) animSpeed = 0;
                if (animSpeed > 100000) animSpeed = 100000;

                /*
                if (animSpeed <= 0 || animSpeed > 100000) {
                    validNum = false;
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 100000", "error.", JOptionPane.ERROR_MESSAGE);
                }
                */



            } catch (NumberFormatException e) {
                validNum = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid number", "error.", JOptionPane.ERROR_MESSAGE);
            }
        } while (!validNum);

        /* end setting the speed  */

        Main main = new Main(numOfDisks);
        main.handler.setAnimSpeed(animSpeed);
        main.start();

        //main.handler.show();
        Handler.wait(.5);
        main.handler.Move(numOfDisks, 0, 2);

        rot = true;

    }

}
