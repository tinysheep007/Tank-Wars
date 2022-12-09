import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Base extends GameObject{
    int length = 50;
    public Base(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override 
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, gamePanel);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y, length, length);
    }
    
}
