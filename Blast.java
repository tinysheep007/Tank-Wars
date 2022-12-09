import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Blast extends GameObject {

    static Image[] imgs;
    int imgCount;

    static {
        for(int i=0;i<8;i++){
            imgs[i] = Toolkit.getDefaultToolkit().getImage("images/explosion-"+((i+1)*6)+"px.png");
        }
    }
    public Blast(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        if(imgCount < 8){
            g.drawImage(imgs[imgCount], x, y, gamePanel);
            imgCount++;
        }
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
    
}
