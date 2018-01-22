import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key Input
 * Created by pkaterski on 2/23/16.
 */
public class KeyInput extends KeyAdapter {
    private Handler handler;


    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_PERIOD:
                handler.reset();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_W:
                handler.getPlayer1().setVelY(-handler.getPlayerSpeed());
                break;
            case KeyEvent.VK_S:
                handler.getPlayer1().setVelY(handler.getPlayerSpeed());
                break;
            case KeyEvent.VK_A:
                if (handler.isFreestyle())
                    handler.getPlayer1().setVelX(-handler.getPlayerSpeed());
                break;
            case KeyEvent.VK_D:
                if (handler.isFreestyle())
                    handler.getPlayer1().setVelX(handler.getPlayerSpeed());
                break;
            case KeyEvent.VK_UP:
                handler.getPlayer2().setVelY(-handler.getPlayerSpeed());
                break;
            case KeyEvent.VK_DOWN:
                handler.getPlayer2().setVelY(handler.getPlayerSpeed());
                break;
            case KeyEvent.VK_LEFT:
                if (handler.isFreestyle())
                    handler.getPlayer2().setVelX(-handler.getPlayerSpeed());
                break;
            case KeyEvent.VK_RIGHT:
                if (handler.isFreestyle())
                    handler.getPlayer2().setVelX(handler.getPlayerSpeed());
                break;
            case KeyEvent.VK_SPACE:
                handler.newRound();
                break;

            case KeyEvent.VK_M:
                handler.nextLayout();
                break;
            case KeyEvent.VK_N:
                handler.previousLayout();
                break;

            /*  CHEATS  */
            case KeyEvent.VK_R:
                handler.resetScore();
                break;
            case KeyEvent.VK_O:
                handler.decreaseBallRadius();
                break;
            case KeyEvent.VK_P:
                handler.increaseBallRadius();
                break;
            case KeyEvent.VK_B:
                handler.centerBall();
                break;
            case KeyEvent.VK_X:
                handler.fuckPlayer2();
                break;
            case KeyEvent.VK_Z:
                handler.fuckPlayer1();
                break;
            case KeyEvent.VK_1:
                handler.decreasePlayer1Width();
                break;
            case KeyEvent.VK_2:
                handler.increasePlayer1Width();
                break;
            case KeyEvent.VK_3:
                handler.decreasePlayer1Height();
                break;
            case KeyEvent.VK_4:
                handler.increasePlayer1Height();
                break;
            case KeyEvent.VK_5:
                handler.decreasePlayer2Width();
                break;
            case KeyEvent.VK_6:
                handler.increasePlayer2Width();
                break;
            case KeyEvent.VK_7:
                handler.decreasePlayer2Height();
                break;
            case KeyEvent.VK_8:
                handler.increasePlayer2Height();
                break;
            case KeyEvent.VK_BACK_SPACE:
                handler.setPlayerSpeed( -handler.getPlayerSpeed() );
                break;

            //fucking..
            case KeyEvent.VK_NUMPAD1:
                handler.fuckPlayer1();
                break;
            case KeyEvent.VK_NUMPAD2:
                handler.fuckPlayer2();
                break;

            //ball control
            case KeyEvent.VK_T:
                if (handler.getCheats())
                    handler.getBall().setVelY( -handler.getPlayerSpeed() );
                break;
            case KeyEvent.VK_G:
                if (handler.getCheats())
                    handler.getBall().setVelY( handler.getPlayerSpeed() );
                break;
            case KeyEvent.VK_F:
                if (handler.getCheats())
                    handler.getBall().setVelX( -handler.getPlayerSpeed() );
                break;
            case KeyEvent.VK_H:
                if (handler.getCheats())
                    handler.getBall().setVelX( handler.getPlayerSpeed() );
                break;
            /*  end cheats  */



        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                handler.getPlayer1().setVelY(0);
                break;
            case KeyEvent.VK_S:
                handler.getPlayer1().setVelY(0);
                break;
            case KeyEvent.VK_A:
                handler.getPlayer1().setVelX(0);
                break;
            case KeyEvent.VK_D:
                handler.getPlayer1().setVelX(0);
                break;
            case KeyEvent.VK_UP:
                handler.getPlayer2().setVelY(0);
                break;
            case KeyEvent.VK_DOWN:
                handler.getPlayer2().setVelY(0);
                break;
            case KeyEvent.VK_LEFT:
                if (handler.isFreestyle())
                    handler.getPlayer2().setVelX(0);
                break;
            case KeyEvent.VK_RIGHT:
                if (handler.isFreestyle())
                    handler.getPlayer2().setVelX(0);
                break;
            case KeyEvent.VK_DELETE:
                handler.setCheats( !handler.getCheats() );
                break;
            case KeyEvent.VK_ENTER:
                handler.setFreestyle( !handler.isFreestyle() );
                handler.reset();
                break;

            /*  cheats  */
            case KeyEvent.VK_T:
                if (handler.getCheats())
                    handler.getBall().setVelY(0);
                break;
            case KeyEvent.VK_G:
                if (handler.getCheats())
                    handler.getBall().setVelY(0);
                break;
            case KeyEvent.VK_F:
                if (handler.getCheats())
                    handler.getBall().setVelX(0);
                break;
            case KeyEvent.VK_H:
                if (handler.getCheats())
                    handler.getBall().setVelX(0);
                break;
            /*  end cheats  */
        }
    }



}
