import java.awt.*;

/**
 * Created by pkaterski on 2/27/16.
 */
public class ColorLayout {
    public static final int numOfLayouts = 11;


    public static void setColorLayout(Handler handler, int n) {
        Color bgColor;
        Color fgColor;

        switch (n) {
            case 0:
                bgColor = Color.BLACK;
                fgColor = Color.WHITE;
                break;
            case 1:
                bgColor = Color.CYAN;
                fgColor = Color.BLACK;
                break;
            case 2:
                bgColor = Color.ORANGE;
                fgColor = Color.BLACK;
                break;
            case 3:
                bgColor = Color.GREEN;
                fgColor = Color.BLACK;
                break;
            case 4:
                bgColor = Color.BLACK;
                fgColor = Color.YELLOW;
                break;
            case 5:
                bgColor = Color.YELLOW;
                fgColor = Color.BLACK;
                break;
            case 6:
                bgColor = Color.MAGENTA;
                fgColor = Color.BLACK;
                break;
            case 7:
                bgColor = Color.DARK_GRAY;
                fgColor = Color.CYAN;
                break;
            case 8:
                bgColor = Color.BLACK;
                fgColor = Color.BLUE;
                break;
            case 9:
                bgColor = Color.BLACK;
                fgColor = Color.CYAN;
                break;
            case 10:
                bgColor = Color.PINK;
                fgColor = Color.BLACK;
                break;
            default:
                bgColor = Color.BLACK;
                fgColor = Color.WHITE;
                break;
        }

        handler.setBgColor(bgColor);
        handler.setFgColor(fgColor);

    }
}
