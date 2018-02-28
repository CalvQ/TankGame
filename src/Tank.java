import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tank extends GameObject{
    private double angle;
    private int health;//0-100
    private boolean red;
    private boolean turn;
    private int power;
    private Bullet.Ammo[] types = {Bullet.Ammo.AP, Bullet.Ammo.Cluster, Bullet.Ammo.HE
            , Bullet.Ammo.Jet, Bullet.Ammo.Snipe, Bullet.Ammo.Wall, Bullet.Ammo.AStrike, Bullet.Ammo.Std, Bullet.Ammo.Lazor};
    private int typesIndex=0;

    public Tank(int x, double angle, boolean color){
        super(new Point(x, 0));
        this.angle = angle;
        this.health = 100;
        this.turn = false;
        this.red = color;
        this.power = 100;
        this.loc.setY(Terrain.getPoints().get(this.loc.getX()).getY());
    }

    public Bullet fire(){
        return new Bullet(new Point(this.loc.getX(), this.loc.getY()-25), angle, power, types[typesIndex], this.red);
    }

    public boolean inColBox(Point test){
        return test.getY()>this.loc.getY()-50 && test.getY()<this.loc.getY()
                && test.getX()>this.loc.getX()-50 && test.getX()<this.loc.getX()+50;
    }

    public void damage(int dmg){
        this.health-=dmg;
    }

    public void update(){
        this.loc.setY(Terrain.getPoints().get(this.loc.getX()).getY());
    }

    public void next(){
        typesIndex++;
        if(typesIndex==9){
            typesIndex=0;
        }
    }
    public void prev(){
        typesIndex--;
        if(typesIndex==-1){
            typesIndex=8;
        }
    }

    public void moveLeft(){
        if(turn)
        this.loc.incX(-1);
        if(this.loc.getX()<=0){
            this.loc.setX(0);
        }
        int y = Terrain.getPoints().get(this.loc.getX()).getY();
        if(y-this.loc.getY()>-14) {
            this.loc.setY(y);
        }else{
            this.loc.incX(1);
        }
    }
    public void moveRight(){
        if(turn)
        this.loc.incX(1);
        if(this.loc.getX()>=1599){
            this.loc.setX(1599);
        }
        int y = Terrain.getPoints().get(this.loc.getX()).getY();
        if(y-this.loc.getY()>-14) {
            this.loc.setY(y);
        }else {
            this.loc.incX(-1);
        }
    }
    public void power(int dp){
        if(turn)
            this.power+=dp;
        if(this.power>100){
            power=100;
        }
        if(this.power<5){
            power=5;
        }
    }
    public void angle(double angles){
        if(turn){
            this.angle+= angles;
        }
    }
    public boolean getRed(){
        return red;
    }
    public boolean getTurn(){
        return turn;
    }
    public void toggleTurn(){
        turn = !turn;
    }
    public void setX(int x){
        this.getLoc().setX(x);
    }
    public void setY(int y){
        this.getLoc().setY(y);
    }
    public int getHealth(){
        return this.health;
    }

    @Override
    public void draw(Graphics g) {
        if(turn){
            Color temp = g.getColor();
            g.setColor(Color.RED);
            g.drawLine(this.loc.getX(), this.loc.getY()-25,
                    (int) (this.loc.getX()+(2*power*Math.cos(angle))), (int) (this.loc.getY()-(2*power*Math.sin(angle)))-25);
            g.setColor(temp);
        }
        try{
            Image tank = ImageIO.read(new File("src/tank.gif"));
            if(red){
                g.drawImage(tank, this.loc.getX()+50, this.loc.getY()-50, -100, 50, null);

            }else{
                g.drawImage(tank, this.loc.getX()-50, this.loc.getY()-50, 100, 50, null);
            }

            //TODO ammo number
            Image symbol;
            switch(types[typesIndex]){
                case AP:
                    symbol = ImageIO.read(new File("src/pics/ap.gif"));
                    break;
                case HE:
                    symbol = ImageIO.read(new File("src/pics/he.gif"));
                    break;
                case Jet:
                    symbol = ImageIO.read(new File("src/pics/jets.gif"));
                    break;
                case Wall:
                    symbol = ImageIO.read(new File("src/pics/wall.gif"));
                    break;
                case Snipe:
                    symbol = ImageIO.read(new File("src/pics/snipe.gif"));
                    break;
                case Cluster:
                    symbol = ImageIO.read(new File("src/pics/cluster.gif"));
                    break;
                case AStrike:
                    symbol = ImageIO.read(new File("src/pics/plane2.gif"));
                    break;
                case Std:
                    symbol = ImageIO.read(new File("src/pics/dot.gif"));
                    break;
                case Lazor:
                    symbol = ImageIO.read(new File("src/pics/lazor.gif"));
                    break;
                    default:
                        symbol = null;
            }
            if(turn) {
                if (!red) {
                    g.drawImage(symbol, 0, 0, 40, 40, null);
                } else {
                    g.drawImage(symbol, 1560, 0, 40, 40, null);
                }
            }
        }catch (IOException e ){
            System.out.println("Problem with ...");
            e.printStackTrace();
        }
        g.drawRect(this.loc.getX()-51, this.loc.getY()+2, 102, 7);
        g.fillRect(this.loc.getX()-50, this.loc.getY()+3, health, 5);
    }
}
