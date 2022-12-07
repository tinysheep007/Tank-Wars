import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Bullet extends GameObject {

    //size
    int width = 10;
    int height = 10;
    //speed
    int speed = 7;
    //direction
    Direction direction;

    public Bullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
        super(img, x, y, gamePanel);
        this.direction = direction;
    }

    public void leftward(){
        this.x -= speed ; 
    }

    public void rightward(){
        this.x += speed;
    }

    public void upward(){
        this.y -= speed;
    }

    public void downward(){
        this.y += speed;
    }

    public void go(){
        switch(direction){
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
        }
    }

    @Override
    public void paintSelf(Graphics g){
        g.drawImage(img, x, y, null);
        this.go();
    }

    @Override
    public Rectangle getRec(){
        return new Rectangle(x,y, width, height);
    }
    
}
