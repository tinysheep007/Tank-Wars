import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

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

    public void hitbox(){
        ArrayList<Bot> bots = this.gamePanel.botList;
        for(Bot b : bots){
            if(this.getRec().intersects(b.getRec())){
                this.gamePanel.botList.remove(b); 
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    @Override
    public void paintSelf(Graphics g){
        g.drawImage(img, x, y, null);
        this.go();
        this.hitbox();
    }

    @Override
    public Rectangle getRec(){
        return new Rectangle(x,y, width, height);
    }
    
}
