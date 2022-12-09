import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public abstract class Tank extends GameObject{
    // size
    public int width = 40;
    public int height = 50;
    //speed
    private int speed = 3;
    //direction
    public Direction direction = Direction.UP;

    //pic
    private String upimg;
    private String downimg;
    private String leftimg;
    private String rightimg;

    //attack interval
    //if attackCD is in false, it means it is in CD hence can't shoot
    //if attackCD in true, it can shoot
    private boolean attackCD = true;
    //unit is ms(mili second)
    private int attackCDtime = 750;

    public Tank(String img, int x, int y, GamePanel gamePanel, String upimg, String downimg, String leftimg, String rightimg){
        super(img, x, y, gamePanel);
        this.upimg = upimg;
        this.downimg = downimg;
        this.leftimg = leftimg;
        this.rightimg = rightimg;
    }
    
    public void attack(){
        if (attackCD){
            Point p = getHeadPoint();
            Bullet bullet = new Bullet("images/cs50ball-small (2).png", p.x, p.y, this.gamePanel, this.direction);
            this.gamePanel.bulletList.add(bullet);
            // start thread
            new AttackCD().start();
        }
        
    }

    //new thread
    class AttackCD extends Thread {
        public void run(){
            //set attack to cd
            attackCD = false;
            //rest the cd
            try{
                Thread.sleep(attackCDtime);
            }catch(Exception e){
                e.printStackTrace();
            }
            //allow to attack again
            attackCD = true;
            this.stop();
        }
    }

    //(x,y) is coordinate for tank's next move
    //return true if it does hit wall
    //return false if it does not hit wall
    public boolean hitwall(int x, int y){
        ArrayList<Wall> walls = this.gamePanel.wallList;
        Rectangle next = new Rectangle(x, y, width, height);
        for(Wall w : walls){
            if(next.intersects(w.getRec())){
                return true;
            }
        }
        return false;
    }

    //if the tank is going off the border, return true else false
    public boolean moveToBorder(int x, int y){
        //left
        if(x < 0){
            return true;
        }else if( x + width > this.gamePanel.getWidth()){
            return true;
        }else if(y < 0){
            return true;
        }else if( y + height > this.gamePanel.getHeight()){
            return true;
        }
        return false;
    }

    public Point getHeadPoint(){
        switch(direction){
            case UP:
                return new Point(x+width/2, y); 
            case DOWN:
                return new Point(x+width/2, y+height);
            case LEFT:
                return new Point(x, y+width/2); 
            case RIGHT:
                return new Point(x+width, y+height/2);
            default:
                return null;
        }

    }

    public void leftward(){
        direction = Direction.LEFT;
        setimg(this.leftimg);
        if(!hitwall(x-speed, y) && !moveToBorder(x-speed, y)){
            this.x -= speed;
        }
    }

    public void rightward(){
        direction = Direction.RIGHT;
        setimg(this.rightimg);
        if(!hitwall(x+speed, y) && !moveToBorder(x+speed, y)){
            this.x += speed;
        }
    }

    public void upward(){
        direction = Direction.UP;
        setimg(this.upimg);
        if(!hitwall(x, y-speed) && !moveToBorder(x, y-speed)){
            this.y -= speed;
        }
    }

    public void downward(){
        direction = Direction.DOWN;
        setimg(this.downimg);
        if(!hitwall(x, y+speed) && !moveToBorder(x, y+speed)){
            this.y += speed;
        }
    }



    public void setimg(String img){
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    @Override
    public abstract void paintSelf(Graphics g);
    
    @Override
    public abstract Rectangle getRec();

}