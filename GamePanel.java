import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JFrame {
    // set empty screen
    Image tempScreen = null;

    // set window size
    int width = 800;
    int height = 600;

    // get tank image
    Image select = Toolkit.getDefaultToolkit().getImage("images/P1-Up.png").getScaledInstance(50, 50,
            Image.SCALE_DEFAULT);

    // select game mode
    // 1 single player
    // 2 double player
    // 3 game paused (pressed 3 to pause)
    // 5 game victory
    int state = 0;

    // int state temp
    int stateTemp = 0;

    // change this to increase or decrease bot count
    // max amount of bot
    int botMaxCount = 10;

    // amount of bot
    int botCount = 0;

    // amount of times that screen render
    int renderCount = 0;

    // the location of the Select cursor
    int y = 150;
    PlayerOne p1 = new PlayerOne("images/P1-Up.png", 125, 510, this, "images/P1-Up.png", "images/P1-Down.png",
            "images/P1-Left.png", "images/P1-Right.png");

    PlayerTwo p2 = new PlayerTwo("images/P2-Up.png", 625, 510, this, "images/P2-Up.png", "images/P2-Down.png",
            "images/P2-Left.png", "images/P2-Right.png");

    // game componenents
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    ArrayList<Bot> botList = new ArrayList<Bot>();
    ArrayList<Bullet> removeList = new ArrayList<Bullet>();
    ArrayList<Tank> playerList = new ArrayList<Tank>();
    ArrayList<Wall> wallList = new ArrayList<Wall>();
    ArrayList<Base> baseList = new ArrayList<Base>();
    ArrayList<Blast> blastList = new ArrayList<Blast>();

    // start game
    public void launch() {
        // title
        setTitle("Tank Wars");
        // set size
        setSize(width, height);
        // center the frame
        setLocationRelativeTo(null);
        // close the window shut the program
        setDefaultCloseOperation(3);
        // not resized
        setResizable(false);
        // make window visible
        setVisible(true);
        // add event listener
        this.addKeyListener(new GamePanel.KeyMointor());

        // add walls
        for (int i = 0; i < 16; i++) {
            wallList.add(new Wall("images/Wall-Regular.png", i * 50, 150, this));
        }
        wallList.add(new Wall("images/Wall-Regular.png", 300, 500, this));
        wallList.add(new Wall("images/Wall-Regular.png", 300, 550, this));
        wallList.add(new Wall("images/Wall-Regular.png", 350, 500, this));
        wallList.add(new Wall("images/Wall-Regular.png", 400, 500, this));
        wallList.add(new Wall("images/Wall-Regular.png", 400, 550, this));

        // add base
        baseList.add(new Base("images/Base.png", 350, 550, this));

        // redraw the screen

        while (true) {
            // check if game win
            if (botCount == botMaxCount && botList.size() == 0) {
                state = 5;
            }
            // check if game end
            if ((playerList.size() == 0 && (state == 1 || state == 2)) || baseList.size() == 0) {
                state = 4;
            }
            // add bots only after 100 render and game is not paused
            if (renderCount % 100 == 1 && botCount < botMaxCount && (state == 1 || state == 2)) {
                Random r = new Random();
                int rnum = r.nextInt(800);
                botList.add(new Bot("images/Bot-Up.png", rnum, 100, this, "images/Bot-Up.png", "images/Bot-Down.png",
                        "images/Bot-Left.png", "images/Bot-Right.png"));
                botCount++;
            }

            // update graphics
            repaint();
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        // System.out.println(bulletList.size());
        // set temp screen
        if (tempScreen == null) {
            tempScreen = this.createImage(width, height);
        }
        // get pen for temp screen
        Graphics tempScreenPen = tempScreen.getGraphics();

        // set pen color
        tempScreenPen.setColor(Color.CYAN);
        // fill the background
        tempScreenPen.fillRect(0, 0, width, height);
        // change pen color
        tempScreenPen.setColor(Color.BLUE);
        // change fond and size
        tempScreenPen.setFont(new Font("times new roman", Font.BOLD, 50));
        // draw the string on screen
        // game not started yet
        if (state == 0) {
            tempScreenPen.drawString("Tank Wars", 220, 100);
            tempScreenPen.drawString("Single Player", 220, 200);
            tempScreenPen.drawString("Two Players", 220, 300);
            // draw the select
            tempScreenPen.drawImage(select, 160, y, null);

        }
        // state 1 or 2 means game started
        else if (state == 1 || state == 2) {

            tempScreenPen.setFont(new Font("roboto", Font.BOLD, 30));
            tempScreenPen.setColor(Color.RED);
            tempScreenPen.drawString("Left Over Enemy Count: " + botList.size(), 0, 50);

            // add game components
            for (Tank player : playerList) {
                player.paintSelf(tempScreenPen);
            }
            for (Bot b : botList) {
                b.paintSelf(tempScreenPen);
            }
            // remove all disappearing bullet
            bulletList.removeAll(removeList);
            for (Bullet b : bulletList) {
                b.paintSelf(tempScreenPen);
            }
            for (Wall w : wallList) {
                w.paintSelf(tempScreenPen);
            }
            for (Base b : baseList) {
                b.paintSelf(tempScreenPen);
            }
            for (Blast b : blastList) {
                b.paintSelf(tempScreenPen);
            }
            // increase render count
            renderCount++;

        } else if (state == 3) {
            tempScreenPen.drawString("Game Paused", 220, 100);
        } else if (state == 4) {
            tempScreenPen.drawString("Game Lose", 220, 100);
        } else if (state == 5) {
            tempScreenPen.drawString("Game Won", 220, 100);
        }
        g.drawImage(tempScreen, 0, 0, null);

    }

    class KeyMointor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            // System.out.println(e.getKeyChar());
            int key = e.getKeyChar();
            switch (key) {
                case KeyEvent.VK_3:
                    if (state != 3) {
                        stateTemp = state;
                        state = 3;
                    } else {
                        state = stateTemp;
                        if (stateTemp == 0) {
                            stateTemp = 1;
                        }
                    }
                    break;
                case KeyEvent.VK_1:
                    stateTemp = 1;
                    y = 150;
                    break;
                case KeyEvent.VK_2:
                    stateTemp = 2;
                    y = 250;
                    break;
                case KeyEvent.VK_ENTER:
                    state = stateTemp;
                    playerList.add(p1);
                    p1.alive = true;
                    if (state == 2) {
                        playerList.add(p2);
                        p2.alive = true;
                    }
                    break;
                default:
                    p1.keyPressed(e);
                    p2.keyPressed(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            p1.keyReleased(e);
            p2.keyReleased(e);
        }

    }

    public static void main(String[] args) {
        GamePanel gp = new GamePanel();
        gp.launch();
    }
}