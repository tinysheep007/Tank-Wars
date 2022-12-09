import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Bullet extends GameObject {

    // size
    int width = 10;
    int height = 10;
    // speed
    int speed = 7;
    // direction
    Direction direction;

    public Bullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
        super(img, x, y, gamePanel);
        this.direction = direction;
    }

    public void leftward() {
        this.x -= speed;
    }

    public void rightward() {
        this.x += speed;
    }

    public void upward() {
        this.y -= speed;
    }

    public void downward() {
        this.y += speed;
    }

    public void go() {
        switch (direction) {
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
        this.hitwall();
        this.moveToBorder();
        this.hitbase();
    }

    // if off border delete the bullet
    public void moveToBorder() {
        if (x < 0) {
            this.gamePanel.removeList.add(this);
        } else if (x + width > this.gamePanel.getWidth()) {
            this.gamePanel.removeList.add(this);
        } else if (y < 0) {
            this.gamePanel.removeList.add(this);
        } else if (y + height > this.gamePanel.getHeight()) {
            this.gamePanel.removeList.add(this);
        }
    }

    public void hitwall() {
        ArrayList<Wall> walls = this.gamePanel.wallList;
        for (Wall w : walls) {
            if (this.getRec().intersects(w.getRec())) {
                this.gamePanel.wallList.remove(w);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void hitbase() {
        ArrayList<Base> bases = this.gamePanel.baseList;
        for (Base b : bases) {
            if (this.getRec().intersects(b.getRec())) {
                this.gamePanel.baseList.remove(b);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void hitbot() {
        ArrayList<Bot> bots = this.gamePanel.botList;
        // ArrayList<Tank> players = this.gamePanel.playerList;
        for (Bot b : bots) {
            if (this.getRec().intersects(b.getRec())) {
                this.gamePanel.blastList.add(new Blast("", b.x - 30, b.y - 15, this.gamePanel));
                this.gamePanel.botList.remove(b);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        this.go();
        this.hitbot();
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }

}
