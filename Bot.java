import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Bot extends Tank {

    // how many times it moves, over certain limits, it will change directions
    int moveTime = 0;

    public Bot(String img, int x, int y, GamePanel gamePanel, String upimg, String downimg, String leftimg,
            String rightimg) {
        super(img, x, y, gamePanel, upimg, downimg, leftimg, rightimg);
        
    }

    public Direction getDirection(){
        Random r = new Random();
        int dir = r.nextInt(4);
        switch(dir){
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            default:
                return null;
        }
    }

    public void attack(){
        Point p = getHeadPoint();
        Random r = new Random();
        int num = r.nextInt(300);
        if(num < 10){
            this.gamePanel.bulletList.add(new BotBullet("images/BotBullet.png", p.x, p.y, this.gamePanel, direction));
        }
    }

    public void go(){
        attack();
        if(moveTime >= 20){
            direction = getDirection();
            moveTime = 0;
        }
        else{
            moveTime++;
        }
        switch(direction){
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, gamePanel);
        this.go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width, height);
    }
    
}
