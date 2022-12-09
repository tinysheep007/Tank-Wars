import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Wall extends GameObject {

    int length = 50;

    public Wall(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, gamePanel);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,length,length);
    }
    
}
