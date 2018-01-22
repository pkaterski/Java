import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Game
 * Created by pkaterski on 2/23/16.
 */

public class Game extends Canvas implements Runnable {

    private boolean fullscreen = true;

    public final int WIDTH = (!fullscreen) ? 800 : Toolkit.getDefaultToolkit().getScreenSize().width;
    public final int HEIGHT = (!fullscreen) ?  WIDTH / 4 * 3 : Toolkit.getDefaultToolkit().getScreenSize().height;


    private JFrame frame;

    private String title = "PoNG";

    private Thread thread;
    private boolean running = false;

    private Handler handler;

    private Color bgColor = Color.BLACK;


    public Game() {
        handler = new Handler(this);

        KeyInput keyInput = new KeyInput(handler);
        addKeyListener(keyInput);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        frame = new JFrame(title);

        if (fullscreen){
            frame.setUndecorated(true);

            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice graphicsDevice = environment.getDefaultScreenDevice();
            graphicsDevice.setFullScreenWindow(frame);

            if (graphicsDevice.isDisplayChangeSupported()) {
                //graphicsDevice.setDisplayMode(new DisplayMode(WIDTH, HEIGHT, 16, DisplayMode.REFRESH_RATE_UNKNOWN));
            } else {
                System.err.println("FULLSCREEN NOT SUPPORTED");
            }
        }
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public void run() {
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        long lastTime = System.nanoTime();
        double hZ = 60.0;
        double ns = 1000000000.0 / hZ;
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

            render();
            frames++;

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;

                frame.setTitle(title + " | " + updates + " ups, " + frames + " FPS.");

                frames = 0;
                updates = 0;
            }

        }
        stop();

    }

    public void update() {
        handler.update();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(bgColor);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();

    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public static void main(String[] args) {

        System.setProperty("sun.java2d.opengl", "True");

        Game game = new Game();
        game.start();
    }


}
