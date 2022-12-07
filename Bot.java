import java.awt.Graphics;
import java.awt.Rectangle;

public class Bot extends Tank {

    public Bot(String img, int x, int y, GamePanel gamePanel, String upimg, String downimg, String leftimg,
            String rightimg) {
        super(img, x, y, gamePanel, upimg, downimg, leftimg, rightimg);
        
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, gamePanel);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width, height);
    }
    
}
