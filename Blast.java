import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Blast extends GameObject {

    static Image[] imgs = new Image[8];

    int imgCount;

    static {
        // imgs[0] = Toolkit.getDefaultToolkit().getImage("images/explosion-6px.png");
        // imgs[0] = Toolkit.getDefaultToolkit().getImage("images/explosion-6px.png");
        // imgs[0] = Toolkit.getDefaultToolkit().getImage("images/explosion-6px.png");
        // imgs[0] = Toolkit.getDefaultToolkit().getImage("images/explosion-6px.png");
        // imgs[0] = Toolkit.getDefaultToolkit().getImage("images/explosion-6px.png");

        for (int i = 0; i < 8; i++) {
            imgs[i] = Toolkit.getDefaultToolkit().getImage("images/explosion-" + ((i + 1) * 6) + "px.png");
        }
    }

    public Blast(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        if (imgCount < 8) {
            g.drawImage(imgs[imgCount], x, y, null);
            imgCount++;
        }
    }

    @Override
    public Rectangle getRec() {
        return null;
    }

}
