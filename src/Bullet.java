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
        this.velX = power*2.5*Math.cos(angle) * 1.2;//1.2 multiplier for larger game space
        this.velY = -power*3*Math.sin(angle) * 1.2;
        this.type = type2;
        this.red=red;
        if(this.type == Ammo.AP){//counter for terrain ignore
            counter=25;
        }else if(this.type == Ammo.Lazor){//face has special velocity, counter for delay before halting in air
            counter = 40;
            this.velX = 85*2.5*Math.cos(angle);
            this.velY = -85 * 3 * Math.sin(angle);
        }else if(this.type == Ammo.Jet){
            this.velX = power * 1.5 * Math.cos(angle) * 1.2;
            this.velY = -power * 2 * Math.sin(angle) * 1.2;
        }
    }

    public void tick(){
        int tempX = loc.getX();
        int tempY = loc.getY();
        this.loc = new Point(tempX + (int)(velX*0.05), tempY + (int)(velY*0.05));
        velY+=3;//"acceleration"
    }



    public Explosion genExpl(){//todo lazro
        if(this.type!= Ammo.Jet && this.type!= Ammo.Lazor) {
            if (red) { //if normal round, then see if tank is present, then generate explosion
                Tank temp = ((Tank) GridBasedGameDriver.getDrawables().get(1600));
                if (temp.inColBox(this.loc)) {
                    this.loc.setY(temp.getLoc().getY() - 50);
                    return new Explosion(this.loc, type, this.red);
                }
            } else {
                Tank temp = ((Tank) GridBasedGameDriver.getDrawables().get(1601));
                if (temp.inColBox(this.loc)) {
                    this.loc.setY(temp.getLoc().getY() - 50);
                    return new Explosion(this.loc, type, this.red);
                }
            }
        }
        if(counter==0){
            if(loc.getX()==0 || loc.getX()==1599) {

            }else if(this.type!= Ammo.AP){//if terrain is within 20 pix of explosion, then set y to terrain height
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
        if(this.loc.getX()>=1599){
            this.loc.setX(1599);
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
            if (red) {
                if (((Tank) GridBasedGameDriver.getDrawables().get(1600)).inColBox(this.loc)) {
                    return true;
                }
            } else {
                if (((Tank) GridBasedGameDriver.getDrawables().get(1601)).inColBox(this.loc)) {
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
                if(!red) {
                    ((Tank)GridBasedGameDriver.getDrawables().get(1600)).setX(this.loc.getX());
                    ((Tank)GridBasedGameDriver.getDrawables().get(1600)).setY(this.loc.getY());
                }else{
                    ((Tank)GridBasedGameDriver.getDrawables().get(1601)).setY(this.loc.getY());
                    ((Tank)GridBasedGameDriver.getDrawables().get(1601)).setX(this.loc.getX());
                }
            }else if(this.type == Ammo.Lazor) {
                Image lazor = ImageIO.read(new File("src/pics/lazor.gif"));
                if(red){
                    g.drawImage(lazor, this.loc.getX()+30, this.loc.getY()-30, -60, 60, null);
                }else{
                    g.drawImage(lazor, this.loc.getX()-30, this.loc.getY()-30, 60, 60, null);
                }
            }else{
                Image bullet = ImageIO.read(new File("src/dot.gif"));
                g.drawImage(bullet, loc.getX() - 2, loc.getY() - 2, 4, 4, null);
            }
        }catch (IOException e ){
            System.out.println("Problem with ...");
            e.printStackTrace();
        }
    }
}
