import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Explosion extends GameObject{
    private int radius;
    private Color color;
    private Bullet.Ammo type;
    private int counter = 0;
    private boolean red;

    Explosion(Point loc, Bullet.Ammo type2, boolean red){//Jet, Snipe, HE, AP, Cluster, Wall, AStrike,Strd
        super(loc);
        this.type = type2;
        switch (type){
            case AP:
                color = Color.WHITE;
                radius = 20;
                this.red = red;
                break;
            case HE:
                radius=150;
                color = Color.orange;
                this.red = red;
                break;
            case Jet://this is bullet class
                this.red = red;
                break;
            case Wall:
                radius = 50;
                this.red = red;
                for(int z=-5; z<6; z++){
                    if(this.loc.getX()+z > -1 && this.loc.getX()+z<1600) {
                        ((Terrain) GridBasedGameDriver.getDrawables().get(this.loc.getX() + z)).remove(-400);
                    }
                }
                break;
            case Snipe:
                radius=10;
                this.red = red;
                color = Color.white;
                break;
            case Cluster:
                radius = 40;
                this.red = red;
                color = new Color(255, 135, 0);
                int temp = (int)(Math.random()*3) + 4;
                if(this.loc.getX()==0){
                    this.loc.setX(1);
                }
                if(this.loc.getX()==1599){
                    this.loc.setX(1598);
                }
                for(int i=0; i<temp; i++){
                    GridBasedGameDriver.getList().add(new Bullet(new Point(this.loc.getX(), this.loc.getY()-5)
                            , Math.random()*Math.PI,
                            (int) (Math.random()*25) + 10, Bullet.Ammo.E, red));
                }
                break;
            case AStrike:
                this.red = red;
                color = Color.green;
                radius = 267;
                break;
            case Std:
                this.red = red;
                radius = 20;
                color = Color.BLACK;
                break;
            case E:
                this.red = red;
                radius = 50;
                color = new Color(255, 255, 51);
                break;
            case Lazor:
                this.red = red;
                color = new Color(0,255,255);
                radius = 150;
        }
    }

    public void remExpl(){
        boolean temp = false;
        if(this.type!=Bullet.Ammo.AStrike && this.type!=Bullet.Ammo.Wall && this.type!=Bullet.Ammo.Lazor) {
            for (int k = -radius/2; k < (radius/2) + 1; k++) {
                int h = (int) Math.pow(Math.pow(radius/2, 2) - Math.pow(k, 2), 0.5);
                if (this.loc.getX() + k < 1600 && this.loc.getX() + k > 0) {

                    if(!red){
                        if(((Tank)GridBasedGameDriver.getDrawables().get(1601)).inColBox(new Point(
                                this.loc.getX()+k, this.loc.getY()+h
                        )) || ((Tank)GridBasedGameDriver.getDrawables().get(1601)).inColBox(new Point(
                                this.loc.getX()+k, this.loc.getY()-h
                        ))){
                            temp = true;
                        }
                    }else{
                        if(((Tank)GridBasedGameDriver.getDrawables().get(1600)).inColBox(new Point(
                                this.loc.getX()+k, this.loc.getY()+h
                        )) || ((Tank)GridBasedGameDriver.getDrawables().get(1600)).inColBox(new Point(
                                this.loc.getX()+k, this.loc.getY()-h
                        ))){
                            temp = true;
                        }
                    }

                    if(Terrain.getPoints().get(this.loc.getX()+k).getY()<this.loc.getY()-h){
                        ((Terrain) (GridBasedGameDriver.getDrawables().get(this.loc.getX() + k))).remove(2*h);
                    }else if(Terrain.getPoints().get(this.loc.getX()+k).getY()<=this.loc.getY()+h &&
                            Terrain.getPoints().get(this.loc.getX()+k).getY()>=this.loc.getY()-h){
                        ((Terrain) (GridBasedGameDriver.getDrawables().get(this.loc.getX() + k))).
                                removeTo(this.loc.getY() + h);
                    }
                }
            }
        }

        if(temp){//damage tanks
            switch(type){
                case AP:
                    if(red){
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).damage(20);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).addFuel(40);
                    }else{
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).damage(20);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).addFuel(40);
                    }
                    break;
                case AStrike:
                    break;
                case Cluster:
                    if(red){
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).damage(10);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).addFuel(50);
                    }else{
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).damage(10);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).addFuel(50);
                    }
                    break;
                case Snipe:
                    if(red){
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).damage(65);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).addFuel(80);
                    }else{
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).damage(65);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).addFuel(80);
                    }
                    break;
                case Wall:
                    break;
                case Jet:
                    break;
                case HE:
                    if(red){
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).damage(5);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).addFuel(20);
                    }else{
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).damage(5);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).addFuel(20);
                    }
                    break;
                case E:
                    if(red){
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).damage(7);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).addFuel(10);
                    }else{
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).damage(7);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).addFuel(10);
                    }
                    break;
                case Std:
                    if(red){
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).damage(15);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).addFuel(20);
                    }else{
                        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).damage(15);
                        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).addFuel(20);
                    }
                    break;
            }
        }

        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).update();
        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).update();
    }

    private void lazRemove(){//special remove for 'Lazor'
        if(red){
            for(int i=this.loc.getX(); i>-1; i--){
                //equation: this.loc.getY() + 10 - (0.24 * i) for lower
               int y =  (int) (this.loc.getY() + (0.24*(this.loc.getX()-i)));

                if(Terrain.getPoints().get(i).getY()<y-10){
                    ((Terrain) (GridBasedGameDriver.getDrawables().get(i))).remove(20);
                }else if(Terrain.getPoints().get(i).getY()>=y-10 && Terrain.getPoints().get(i).getY()<=y+10){
                    ((Terrain) (GridBasedGameDriver.getDrawables().get(i))).removeTo(y +10);
                }
            }
        }else{
            System.out.println("here");
            for(int i=this.loc.getX(); i<GridBasedGameDriver.getPanel().getWidth(); i++){
                int y =  (int) (this.loc.getY() + (0.24*(i-this.loc.getX())));

               if(Terrain.getPoints().get(i).getY()<y-10){
                    ((Terrain) (GridBasedGameDriver.getDrawables().get(i))).remove(20);
                }else if(Terrain.getPoints().get(i).getY()>=y-10 && Terrain.getPoints().get(i).getY()<y+10){
                    ((Terrain) (GridBasedGameDriver.getDrawables().get(i))).removeTo(y+10);
                }
            }
        }

        ((Tank)GridBasedGameDriver.getDrawables().get(1600)).update();
        ((Tank)GridBasedGameDriver.getDrawables().get(1601)).update();
    }

    public void tick(){
        counter++;
        if(this.type == Bullet.Ammo.HE){//HE part to tick explosion faster
            counter++;
        }
    }
    public boolean remove(){
        return counter>radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        switch (type){
            case AP:
                g.fillOval(loc.getX()-(radius/2)+(counter/2), loc.getY()-(radius/2)+(counter/2),
                        radius-counter, radius-counter);
                break;
            case HE:
                g.fillOval(loc.getX()-(radius/2)+(counter/2), loc.getY()-(radius/2)+(counter/2),
                        radius-counter, radius-counter);
                break;
            case Jet:
                break;
            case Wall:
                try{
                    Image poof = ImageIO.read(new File("src/pics/poof.gif"));
                    g.drawImage(poof,this.loc.getX()-radius+counter, loc.getY()-(radius/2)+(counter/2)-10,
                            2*(radius-counter), radius-counter, null);
                }catch (IOException e){
                    System.out.println("Problem with ...");
                    e.printStackTrace();
                }
                break;
            case Snipe:
                g.fillOval(loc.getX()-(radius/2)+(counter/2), loc.getY()-(radius/2)+(counter/2),
                        radius-counter, radius-counter);
                break;
            case Cluster:
                g.fillOval(loc.getX()-(radius/2)+(counter/2), loc.getY()-(radius/2)+(counter/2),
                        radius-counter, radius-counter);
                break;
            case AStrike:
                if(radius-counter > 20){
                    g.fillOval(loc.getX()-10, loc.getY()-10,
                            20, 20);
                }else{
                    g.fillOval(loc.getX()-(radius/2)+(counter/2), loc.getY()-(radius/2)+(counter/2),
                            radius-counter, radius-counter);
                }
                if((this.loc.getX()-80)/6==counter){
                    GridBasedGameDriver.getOtherList().add(new Bullet(new Point(6*counter, 85)
                            , 0, 0, Bullet.Ammo.E, red));
                }
                if((this.loc.getX()-40)/6==counter){
                    GridBasedGameDriver.getOtherList().add(new Bullet(new Point(6*counter, 85)
                            , 0, 0, Bullet.Ammo.E, red));
                }
                if((this.loc.getX())/6==counter){
                    GridBasedGameDriver.getOtherList().add(new Bullet(new Point(6*counter, 85)
                            , 0, 0, Bullet.Ammo.E, red));
                }
                if((this.loc.getX()+40)/6==counter){
                    GridBasedGameDriver.getOtherList().add(new Bullet(new Point(6*counter, 85)
                            , 0, 0, Bullet.Ammo.E, red));
                }
                if((this.loc.getX()+80)/6==counter){
                    GridBasedGameDriver.getOtherList().add(new Bullet(new Point(6*counter, 85)
                            , 0, 0, Bullet.Ammo.E, red));
                }

                try{
                    Image plane = ImageIO.read(new File("src/pics/plane.gif"));
                    g.drawImage(plane, 6*counter+30, 65, -60, 20, null);
                }catch (IOException e){
                    System.out.println("Problem with ...");
                    e.printStackTrace();
                }

                break;
            case Std:
                g.fillOval(loc.getX()-(radius/2)+(counter/2), loc.getY()-(radius/2)+(counter/2),
                        radius-counter, radius-counter);
                break;
            case E:
                g.fillOval(loc.getX()-(radius/2)+(counter/2), loc.getY()-(radius/2)+(counter/2),
                        radius-counter, radius-counter);
                break;
            case Lazor:
                //find the slope, and then draw line
                if(red){
                    Color temp = g.getColor();
                    g.setColor(Color.BLACK);
                    g.drawLine(this.loc.getX(), this.loc.getY(), 0, this.loc.getY() + (int) (0.24 * this.loc.getX()));
                    g.setColor(temp);
                }else{
                    int length = GridBasedGameDriver.getPanel().getWidth()-this.loc.getX();
                    Color temp = g.getColor();
                    g.setColor(Color.BLACK);
                    g.drawLine(this.loc.getX(), this.loc.getY(), GridBasedGameDriver.getPanel().getWidth(),
                            this.loc.getY() + (int) (length*0.24));
                    g.setColor(temp);
                }

                if(counter>24 && counter<126) {

                    Color temp = g.getColor();//drawing the lazer
                    int[] xPoints;
                    int[] yPoints;
                    if (red) {
                        if(counter-25 == 50){
                            lazRemove();
                        }
                        if (counter-25 < 51) {
                            g.setColor(new Color(5 * (counter-25), 0, 5 * (50 - (counter-25))));
                            xPoints = new int[]{this.loc.getX(), this.loc.getX(), 0, 0};
                            yPoints = new int[]{this.loc.getY() - ((counter-25) / 5), this.loc.getY() + ((counter-25) / 5),
                                    this.loc.getY() + (int) (0.24 * this.loc.getX()) + ((counter-25) / 5),
                                    this.loc.getY() + (int) (0.24 * this.loc.getX()) - ((counter-25) / 5)};
                            g.fillPolygon(xPoints, yPoints, 4);
                        } else {
                            g.setColor(new Color(5 * (50-((counter-25) - 50)), 0, 5 * ((counter-25) - 50)));
                            xPoints = new int[]{this.loc.getX(), this.loc.getX(), 0, 0};
                            yPoints = new int[]{this.loc.getY() - ((125-counter) / 5), this.loc.getY() + ((125-counter) / 5),
                                    this.loc.getY() + (int) (0.24 * this.loc.getX()) + ((125-counter) / 5),
                                    this.loc.getY() + (int) (0.24 * this.loc.getX()) - ((125-counter) / 5)};
                            g.fillPolygon(xPoints, yPoints, 4);
                        }
                    } else {//remove terrain at 50
                        if(counter-25 == 50){
                            lazRemove();
                        }
                        if ((counter-25) < 51) {
                            g.setColor(new Color(5 * (counter-25), 0, 5 * (50 - (counter-25))));
                            int length = GridBasedGameDriver.getPanel().getWidth()-this.loc.getX();
                            xPoints = new int[]{this.loc.getX(), this.loc.getX(),
                                    GridBasedGameDriver.getPanel().getWidth(), GridBasedGameDriver.getPanel().getWidth()};
                            yPoints = new int[]{this.loc.getY() - ((counter-25) / 5), this.loc.getY() + ((counter-25) / 5),
                                    this.loc.getY() + (int) (0.24 * length) + ((counter-25) / 5),
                                    this.loc.getY() + (int) (0.24 * length) - ((counter-25) / 5)};
                            g.fillPolygon(xPoints, yPoints, 4);
                        } else {
                            g.setColor(new Color(5 * (50-((counter-25) - 50)), 0, 5 * ((counter-25) - 50)));
                            int length = GridBasedGameDriver.getPanel().getWidth()-this.loc.getX();
                            xPoints = new int[]{this.loc.getX(), this.loc.getX(),
                                    GridBasedGameDriver.getPanel().getWidth(), GridBasedGameDriver.getPanel().getWidth()};
                            yPoints = new int[]{this.loc.getY() - ((125-counter) / 5), this.loc.getY() + ((125-counter) / 5),
                                    this.loc.getY() + (int) (0.24 * length) + ((125-counter) / 5),
                                    this.loc.getY() + (int) (0.24 * length) - ((125-counter) / 5)};
                            g.fillPolygon(xPoints, yPoints, 4);
                        }
                    }

                    g.setColor(temp);

                }


                try{//face always goes last
                    Image lazor = ImageIO.read(new File("src/pics/lazor.gif"));
                    if(red){
                        g.drawImage(lazor, this.loc.getX()+30, this.loc.getY()-30, -60, 60, null);
                    }else{
                        g.drawImage(lazor, this.loc.getX()-30, this.loc.getY()-30, 60, 60, null);
                    }
                }catch (IOException e){
                    System.out.println("Problem with ...");
                    e.printStackTrace();
                }

        }
    }
}
