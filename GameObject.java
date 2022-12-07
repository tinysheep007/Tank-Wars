import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class GameObject {
    //pic
    public Image img;
    //position
    public int x;
    public int y;
    //screen
    public GamePanel gamePanel;

    public GameObject(String img, int x, int y, GamePanel gamePanel){
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x=x;
        this.y=y;
        this.gamePanel = gamePanel;
    }

    public abstract void paintSelf(Graphics g);

    public abstract Rectangle getRec();

}
