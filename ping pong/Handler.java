import java.awt.*;
import java.util.Random;

/**
 * Handler
 * Created by pkaterski on 2/23/16.
 */

public class Handler {

    private Game game;
    private Player player1;
    private Player player2;
    private Ball ball;

    private Board board;
    private int player1Score = 0;
    private int player2Score = 0;


    /*  Speeds  */
    private int playerSpeed = 15; //default 15
    private int ballXSpeedMax = 8; //default 8
    private int ballXSpeedMin = 7; //default 7
    private int ballYSpeedMax = 8; //default 8
    private int ballYSpeedMin = 3; //default 3

    private boolean freestyle = true;

    private int cheatSpeed = 1;
    /*  end  */



    /*  Colors  */
    private int colorLayout = 0; //default 0
    Color bgColor;
    Color fgColor;
    /*  end  */

    private boolean cheats = false;



    public Handler(Game game) {
        this.game = game;

        reset();

    }

    public void reset() {

        player1Score = 0;
        player2Score = 0;

        cheats = false;

        /**
         *  Proportional player dimensions
         */


        /*  Proportions  */
        int widthDiv = 60; //default 60
        int heightDiv = 6;  //default 6

        int xPosRatio = 15; //default 15

        int ballSizeRatio = 84; //default 84

        int boardYRatio = 10; //default 10
        /*  end  */



        int playerWidth = game.WIDTH / widthDiv;
        int playerHeight = game.HEIGHT / heightDiv;

        int player1XPos = (game.WIDTH / xPosRatio) - (playerWidth / 2);
        int player2XPos =  (xPosRatio - 1) * (game.WIDTH / xPosRatio) - (playerWidth / 2);
        int playerYPos = (game.HEIGHT / 2) - (playerHeight / 2);

        int ballRadius = (game.HEIGHT + game.WIDTH) / ballSizeRatio;
        int ballXPos = (game.WIDTH / 2) - (ballRadius / 2);
        int ballYPos = (game.HEIGHT / 2) - (ballRadius / 2);

        int boardY = game.HEIGHT / boardYRatio;

        ColorLayout.setColorLayout(this, colorLayout);

        player1 = new Player(player1XPos, playerYPos);
        player1.setWidth(playerWidth);
        player1.setHeight(playerHeight);
        player1.setColor(fgColor);


        player2 = new Player(player2XPos, playerYPos);
        player2.setWidth(playerWidth);
        player2.setHeight(playerHeight);
        player2.setColor(fgColor);

        ball = new Ball(ballXPos, ballYPos);
        ball.setRadius(ballRadius);
        ball.setColor(fgColor);

        board = new Board(game, this);
        board.setY(boardY);
        board.setFont(new Font("Arial", Font.BOLD, 48));
        board.setText(player1Score + " | " + player2Score);
        board.setColor(fgColor);

        this.game.setBgColor(bgColor);

    }


    public void reColor() {
        player1.setColor(fgColor);
        player2.setColor(fgColor);
        ball.setColor(fgColor);
        board.setColor(fgColor);
        game.setBgColor(bgColor);

    }

    public void render(Graphics g) {
        player1.render(g);
        player2.render(g);
        ball.render(g);
        board.render(g);
    }

    public void update() {
        physics();
        player1.update();
        player2.update();
        ball.update();
    }

    public void physics() {


        if (player1.getY() < 0) {
            player1.setVelY(0);
            player1.setY(0);
        }

        if (player1.getY() > game.HEIGHT - player1.getHeight()) {
            player1.setVelY(0);
            player1.setY(game.HEIGHT - player1.getHeight());
        }

        if (player1.getX() < 0) {
            player1.setVelX(0);
            player1.setX(0);
        }

        if (player1.getX() > game.WIDTH - player1.getWidth()) {
            player1.setVelX(0);
            player1.setX(game.WIDTH - player1.getWidth());
        }

        if (player2.getY() < 0) {
            player2.setVelY(0);
            player2.setY(0);
        }

        if (player2.getY() > game.HEIGHT - player2.getHeight()) {
            player2.setVelY(0);
            player2.setY(game.HEIGHT - player2.getHeight());
        }

        if (player2.getX() < 0) {
            player2.setVelX(0);
            player2.setX(0);
        }

        if (player2.getX() > game.WIDTH - player2.getWidth()) {
            player2.setVelX(0);
            player2.setX(game.WIDTH - player2.getWidth());
        }


        if (ball.getY() < 0) {
            ball.setVelY( -ball.getVelY() );
            ball.setY(0);
        }

        if (ball.getY() > game.HEIGHT - ball.getRadius()) {
            ball.setVelY( -ball.getVelY() );
            ball.setY( game.HEIGHT - ball.getRadius() );
        }

        /**
         * fronts
         */


        if (ball.getX() < (player1.getX() + player1.getWidth())
                && ball.getX() > player1.getX()
                && ball.getY() + ball.getRadius() > player1.getY()
                && ball.getY() < player1.getY() + player1.getHeight()) {

            ball.setVelX( -ball.getVelX() + player1.getVelX() );

            ball.setX( player1.getX() + player1.getWidth() );

        }

        if (ball.getX() + ball.getRadius() > player2.getX()
                && ball.getX() + ball.getRadius() < player2.getX() + player2.getWidth()
                && ball.getY() + ball.getRadius() > player2.getY()
                && ball.getY() < (player2.getY() + player2.getHeight())) {

            ball.setVelX( -ball.getVelX() + player2.getVelX() );

            ball.setX( player2.getX() - ball.getRadius() );
        }


        /**
         * backs
         */

        if (ball.getX() + ball.getRadius() > player1.getX()
                && ball.getX() + ball.getRadius() < player1.getX() + player1.getWidth()
                && ball.getY() + ball.getRadius() > player1.getY()
                && ball.getY() < player1.getY() + player1.getHeight()) {

            ball.setVelX( -ball.getVelX() + player1.getVelX() );

            ball.setX( player1.getX() - ball.getRadius() );


        }

        if (ball.getX() < player2.getX() + player2.getWidth()
                && ball.getX() > player2.getX()
                && ball.getY() + ball.getRadius() > player2.getY()
                && ball.getY() < (player2.getY() + player2.getHeight())) {

            ball.setVelX( -ball.getVelX() + player2.getVelX() );

            ball.setX( player2.getX() + player2.getWidth() );
        }






        /**
         * edges
         */


        if (ball.getX() < (player1.getX() + player1.getWidth())
                && ball.getX() + ball.getRadius() > player1.getX()
                && ball.getY() + ball.getRadius() > player1.getY()
                && ball.getY() + ball.getRadius() < player1.getY() + player1.getHeight()) {

            ball.setY( player1.getY() - ball.getRadius() );
            ball.setVelY( -ball.getVelY() + player1.getVelY() );

        }

        if (ball.getX() < (player1.getX() + player1.getWidth())
                && ball.getX() + ball.getRadius() > player1.getX()
                && ball.getY() < player1.getY() + player1.getHeight()
                && ball.getY() > player1.getY() + player1.getHeight()) {

            ball.setY( player1.getY() + player1.getHeight() );
            ball.setVelY( -ball.getVelY() + player1.getVelY() );

        }

        if (ball.getX() + ball.getRadius() > (player2.getX())
                && ball.getX() < (player2.getX() + player2.getWidth())
                && ball.getY() + ball.getRadius() > player2.getY()
                && ball.getY() + ball.getRadius() < player2.getY() + player2.getHeight()) {

            ball.setY( player2.getY() - ball.getRadius() );
            ball.setVelY( -ball.getVelY() + player2.getVelY() );

        }

        if (ball.getX() < (player2.getX() + player2.getWidth())
                && ball.getX() + ball.getRadius() > player2.getX()
                && ball.getY() < player2.getY() + player2.getHeight()
                && ball.getY() > player2.getY() + player2.getHeight()) {

            ball.setY( player2.getY() + player2.getHeight() );
            ball.setVelY( -ball.getVelY() + player2.getVelY() );

        }




        /*  player1 score  */

        if (ball.getX() > game.WIDTH) {
            player1Score++;
            newRound();
        }


        /*  player2 score  */

        if (ball.getX() < 0) {
            player2Score++;
            newRound();
        }

    }

    public void newRound() {

        board.setText(player1Score + " | " + player2Score);

        ball.setX((game.WIDTH / 2) - (ball.getRadius() / 2));
        ball.setY((game.HEIGHT / 2) - (ball.getRadius() / 2));

        Random random = new Random();
        int ballXSpeed;
        int ballYSpeed;
        do {
            ballXSpeed = random.nextInt(2 * ballXSpeedMax + 1) - ballXSpeedMax;
        } while (Math.abs(ballXSpeed) < ballXSpeedMin);
        do {
            ballYSpeed = random.nextInt(2 * ballYSpeedMax + 1) - ballYSpeedMax;
        } while (Math.abs(ballYSpeed) < ballYSpeedMin);

        ball.setVelX(ballXSpeed);
        ball.setVelY(ballYSpeed);
    }

    public void resetScore() {
        if (cheats) {
            player1Score = 0;
            player2Score = 0;
            board.setText(player1Score + " | " + player2Score);
        }
    }

    public void increaseBallRadius() {
        if (cheats) {
            ball.setRadius( ball.getRadius() + cheatSpeed );
            ball.setVelX(0);
            ball.setVelY(0);
            ball.setX( game.WIDTH / 2 - ball.getRadius() /2 );
            ball.setY( game.HEIGHT / 2 - ball.getRadius() /2 );
        }
    }

    public void decreaseBallRadius() {
        if (cheats) {
            ball.setRadius( ball.getRadius() - cheatSpeed );
            ball.setVelX(0);
            ball.setVelY(0);
            ball.setX( game.WIDTH / 2 - ball.getRadius() /2 );
            ball.setY( game.HEIGHT / 2 - ball.getRadius() /2 );
        }
    }

    public void centerBall() {
        if (cheats) {
            ball.setVelX(0);
            ball.setVelY(0);
            ball.setX( game.WIDTH / 2 - ball.getRadius() /2 );
            ball.setY( game.HEIGHT / 2 - ball.getRadius() /2 );
        }
    }


    public void decreasePlayer1Width() {
        if (cheats)
            player1.setWidth( player1.getWidth() - cheatSpeed );
    }

    public void increasePlayer1Width() {
        if (cheats)
            player1.setWidth( player1.getWidth() + cheatSpeed );
    }

    public void decreasePlayer1Height() {
        if (cheats)
            player1.setHeight( player1.getHeight() - cheatSpeed );
    }

    public void increasePlayer1Height() {
        if (cheats)
            player1.setHeight( player1.getHeight() + cheatSpeed );
    }

    public void decreasePlayer2Width() {
        if (cheats) {
            player2.setX( player2.getX() + cheatSpeed );
            player2.setWidth( player2.getWidth() - cheatSpeed );
        }
    }

    public void increasePlayer2Width() {
        if (cheats) {
            player2.setX( player2.getX() - cheatSpeed );
            player2.setWidth( player2.getWidth() + cheatSpeed );
        }
    }

    public void decreasePlayer2Height() {
        if (cheats)
            player2.setHeight( player2.getHeight() - cheatSpeed );
    }

    public void increasePlayer2Height() {
        if (cheats)
            player2.setHeight( player2.getHeight() + cheatSpeed );
    }


    public void nextLayout() {
        colorLayout++;
        colorLayout %= ColorLayout.numOfLayouts;
        ColorLayout.setColorLayout(this, colorLayout);
        reColor();
    }

    public void previousLayout() {
        colorLayout--;
        if (colorLayout < 0) {
            colorLayout = ColorLayout.numOfLayouts - 1;
        }
        ColorLayout.setColorLayout(this, colorLayout);
        reColor();
    }

    public void fuckPlayer1() {
        if (cheats) {
            ball.setVelX(-100);
        }
    }

    public void fuckPlayer2() {
        if (cheats) {
            ball.setVelX(100);
        }
    }

    public boolean isFreestyle() {
        return freestyle;
    }

    public void setFreestyle(boolean freestyle) {
        this.freestyle = freestyle;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        if (cheats) {
            this.playerSpeed = playerSpeed;
        }
    }

    public int getBallXSpeedMax() {
        return ballXSpeedMax;
    }

    public void setBallXSpeedMax(int ballXSpeedMax) {
        this.ballXSpeedMax = ballXSpeedMax;
    }

    public int getBallXSpeedMin() {
        return ballXSpeedMin;
    }

    public void setBallXSpeedMin(int ballXSpeedMin) {
        this.ballXSpeedMin = ballXSpeedMin;
    }

    public int getBallYSpeedMax() {
        return ballYSpeedMax;
    }

    public void setBallYSpeedMax(int ballYSpeedMax) {
        this.ballYSpeedMax = ballYSpeedMax;
    }

    public int getBallYSpeedMin() {
        return ballYSpeedMin;
    }

    public void setBallYSpeedMin(int ballYSpeedMin) {
        this.ballYSpeedMin = ballYSpeedMin;
    }

    public Color getFgColor() {
        return fgColor;
    }

    public void setFgColor(Color fgColor) {
        this.fgColor = fgColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public boolean getCheats() {
        return cheats;
    }

    public void setCheats(boolean cheats) {
        this.cheats = cheats;
    }
}
