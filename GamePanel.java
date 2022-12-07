import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

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
    int state = 0;

    // int state temp
    int stateTemp = 0;

    // the location of the Select cursor
    int y = 150;
    PlayerOne p1 = new PlayerOne("images/P1-Up.png", 125, 510, this, "images/P1-Up.png", "images/P1-Down.png",
            "images/P1-Left.png", "images/P1-Right.png");

    //game componenents
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();

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
        // redraw the screen
        while (true) {
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
            tempScreenPen.drawString("Start Game", 220, 100);
            if (state == 1) {
                tempScreenPen.drawString("Single Player", 220, 200);
            } else if (state == 2) {
                tempScreenPen.drawString("Two Players", 220, 300);
            }

            // add game components
            p1.paintSelf(tempScreenPen);
            for(Bullet b : bulletList){
                b.paintSelf(tempScreenPen);
            }

        }
        g.drawImage(tempScreen, 0, 0, null);

    }

    class KeyMointor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            // System.out.println(e.getKeyChar());
            int key = e.getKeyChar();
            switch (key) {
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
                    break;
                default:
                    p1.keyPressed(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            p1.keyReleased(e);
        }

    }

    public static void main(String[] args) {
        GamePanel gp = new GamePanel();
        gp.launch();
    }
}