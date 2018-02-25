import java.awt.*;
import java.util.ArrayList;

public class Terrain extends GameObject{
    private static ArrayList<Point> points = new ArrayList<>();
    private int y1;
    private int y2;
    private int y3;
    private int interval1 = 0;
    private int interval2 = 0;
    private int interval3 = 0;

    public Terrain(Point loc){
        super(loc);
        points.add(loc);
        y1 = loc.getY();
        y2 = y1+50;
        y3 = y2+50;
    }

    public void removeTo(int toHeight){
        this.remove(toHeight-this.loc.getY());
    }
    public void remove(int cut){//remove function for different colors
        loc.setY(loc.getY()+cut);
        if(cut<0){
            while(cut<0){
                if(interval3>0){
                    interval3--;
                }else if (interval2>0){
                    interval2--;
                }else{
                    interval1--;
                }
                cut++;
            }
        }
        if(cut>0) {
            while (cut > 0) {
                if (interval1 < 50) {
                    interval1++;
                } else if (interval2 < 50) {
                    interval2++;
                } else {
                    interval3++;
                }
                cut--;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        Color temp = g.getColor();

        g.setColor(new Color(147, 241, 119));
        if(y1+interval1 != y2) {
            g.drawLine(loc.getX(), y1 + interval1, loc.getX(), y2);
        }
        g.setColor(new Color(113, 249, 77));
        if(y2+interval2 != y3) {
            g.drawLine(loc.getX(), y2 + interval2, loc.getX(), y3);
        }
        g.setColor(new Color(35, 77, 16));
        if(y3+interval3 != 600) {
            g.drawLine(loc.getX(), y3 + interval3, loc.getX(), 600);
        }

        g.setColor(temp);
    }

    public static ArrayList<Point> getPoints() {
        return points;
    }
}
