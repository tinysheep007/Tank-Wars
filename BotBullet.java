import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BotBullet extends Bullet{

    public BotBullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
        super(img, x, y, gamePanel, direction);
        
    }

    public void hitPlayer(){
        ArrayList<Tank> players = this.gamePanel.playerList;
        for(Tank p : players){
            if(this.getRec().intersects(p.getRec())){
                // this.gamePanel.blastList.add(new Blast("", p.x-30, p.y-15, this.gamePanel));
                this.gamePanel.playerList.remove(p); 
                p.alive = false;
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    @Override
    public void paintSelf(Graphics g){
        g.drawImage(img, x, y, null);
        this.go();
        this.hitPlayer();
    }

    @Override
    public Rectangle getRec(){
        return new Rectangle(x,y, width, height);
    }
    
    
}
