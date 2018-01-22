import java.awt.*;

/**
 * Board
 * Created by pkaterski on 2/25/16.
 */
public class Board extends GameObject {
    private Color color = Color.WHITE;
    private Font font = new Font("Arial", Font.PLAIN, 32);
    private String text = "Hello";
    private Game game;
    private Handler handler;

    public Board(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        centerX(game, g);
        g.drawString(text, x, y);
    }


    public void update() {
    }

    public void centerX(Game game, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(font);
        String player1ScoreSize = handler.getPlayer1Score() + " ";
        int x = game.WIDTH / 2 - metrics.stringWidth(player1ScoreSize);
        setX(x);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
