import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameObject{
    public enum Ammo{
        Jet, Snipe, HE, AP, Cluster, Wall, AStrike, Std, E, Lazor
    }
    private double velX;
    private double velY;
    private Ammo type;
    private int counter=0;
    private boolean red;

    public Bullet(Point loc, double angle, int power, Ammo type2, boolean red){
        super(loc);
        this.velX = power*2.5*Math.cos(angle);
        this.velY = -power*3*Math.sin(angle);
        this.type = type2;
        this.red=red;
        if(this.type == Bullet.Ammo.AP){
            counter=25;
        }else if(this.type == Ammo.Lazor){
            counter = 20;
            this.velX = 85*2.5*Math.cos(angle);
            this.velY = -85 * 3 * Math.sin(angle);
        }
    }

    public void tick(){
        int tempX = loc.getX();
        int tempY = loc.getY();
        this.loc = new Point(tempX + (int)(velX*0.05), tempY + (int)(velY*0.05));
        velY+=3;
    }



    public Explosion genExpl(){//todo lazro
        if(this.type!= Ammo.Jet && this.type!= Ammo.Lazor) {
            if (red) {
                Tank temp = ((Tank) GridBasedGameDriver.getDrawables().get(800));
                if (temp.inColBox(this.loc)) {
                    this.loc.setY(temp.getLoc().getY() - 50);
                    return new Explosion(this.loc, type, this.red);
                }
            } else {
                Tank temp = ((Tank) GridBasedGameDriver.getDrawables().get(801));
                if (temp.inColBox(this.loc)) {
                    this.loc.setY(temp.getLoc().getY() - 50);
                    return new Explosion(this.loc, type, this.red);
                }
            }
        }
        if(counter==0){
            if(loc.getX()==0 || loc.getX()==799) {

            }else if(this.type!= Ammo.AP){
                int y = Terrain.getPoints().get(this.loc.getX()).getY();
                if(-20<this.loc.getY()-y && 20>this.loc.getY()-y) {
                    this.loc.setY(y);
                }
            }
            return new Explosion(this.loc, type, this.red);
        }else{
            return null;
        }
    }

    public boolean inCollision(){
        if(this.loc.getX()<=0){
            this.loc.setX(0);
            return true;
        }
        if(this.loc.getX()>=799){
            this.loc.setX(799);
            return true;
        }
        if(this.type == Ammo.Lazor){
            if(counter==0){
                return true;
            }else{
                counter--;
                return false;
            }
        }
        if(this.type!= Ammo.Jet) {
            if (!red) {
                if (((Tank) GridBasedGameDriver.getDrawables().get(801)).inColBox(this.loc)) {
                    return true;
                }
            } else {
                if (((Tank) GridBasedGameDriver.getDrawables().get(800)).inColBox(this.loc)) {
                    return true;
                }
            }
        }
        if(counter>0){
            counter--;
            return false;
        }
        int yOther = Terrain.getPoints().get(this.loc.getX()).getY();//todo AP
        return this.loc.getY() > yOther;
    }

    @Override
    public void draw(Graphics g) {
        try{
            if(this.type== Ammo.Jet){
//                Image tank = ImageIO.read(new File("src/tank.gif"));
                if(!red) {
//                    g.drawImage(tank, loc.getX() + 50, loc.getY() - 50, -100, 50, null);
                    ((Tank)GridBasedGameDriver.getDrawables().get(800)).setX(this.loc.getX());
                    ((Tank)GridBasedGameDriver.getDrawables().get(800)).setY(this.loc.getY());
                }else{
//                    g.drawImage(tank, loc.getX() - 50, loc.getY() - 50, 100, 50, null);
                    ((Tank)GridBasedGameDriver.getDrawables().get(801)).setY(this.loc.getY());
                    ((Tank)GridBasedGameDriver.getDrawables().get(801)).setX(this.loc.getX());
                }
            }else {
                Image bullet = ImageIO.read(new File("src/dot.gif"));
                g.drawImage(bullet, loc.getX() - 2, loc.getY() - 2, 4, 4, null);
            }
        }catch (IOException e ){
            System.out.println("Problem with ...");
            e.printStackTrace();
        }
    }
}
