import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PlayerTwo extends Tank{
    public PlayerTwo(String img, int x, int y, GamePanel gamePanel, String upimg, String downimg, String leftimg,
            String rightimg) {
        super(img, x, y, gamePanel, upimg, downimg, leftimg, rightimg);
        
    }

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_I:
                // System.out.println("pressed up");
                up = true;
                break;
            case KeyEvent.VK_K:
                down = true;
                break;
            case KeyEvent.VK_J:
                left = true;
                break;
            case KeyEvent.VK_L:
                right = true;
                break;
            case KeyEvent.VK_P:
                attack();
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_I:
                // System.out.println("released up");
                up = false;
                break;
            case KeyEvent.VK_K:
                down = false;
                break;
            case KeyEvent.VK_J:
                left = false;
                break;
            case KeyEvent.VK_L:
                right = false;
                break;
            default:
                break;
        }
    }


    public void move(){
        if(left){
            leftward();
        }
        else if(right){
            rightward();
        }
        else if(up){
            upward();
        }
        else if(down){
            downward();
        }
    }

    @Override
    public void paintSelf(Graphics g){
        // img = img.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
        g.drawImage(img, x, y, null);
        move();
    }
    
    @Override
    public Rectangle getRec(){
        return new Rectangle(x,y, width, height);
    }
}
