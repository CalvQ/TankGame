import java.awt.*;

public class DrawChar {//bounds for char: 10x20
    DrawChar(){
        super();
    }

    //always upper-left corner = x,y

    public static void draw1(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);

        g.fillRect(x+4, y+4, 2, 12);
        g.fillRect(x+3, y+14, 4, 2);

        g.setColor(temp);
    }
    public static void draw2(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);

        g.fillArc(x+5, y, 5, 6, -90, 180);
        g.fillRect(x+2, y, 6, 3);

        int[] xPoints = {x+2, x+2, x+8, x+8};
        int[] yPoints = {y+15, y+12, y+3, y+6};
        g.fillPolygon(xPoints, yPoints, 4);

        g.fillRect(x+2, y+12, 8 ,3);

        g.setColor(temp);
    }
    public static void draw3(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);



        g.setColor(temp);
    }
    public static void draw4(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);


        g.setColor(temp);
    }
    public static void draw5(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);


        g.setColor(temp);
    }
    public static void draw6(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);


        g.setColor(temp);
    }
    public static void draw7(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);


        g.setColor(temp);
    }
    public static void draw8(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);


        g.setColor(temp);
    }
    public static void draw9(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);


        g.setColor(temp);
    }
    public static void drawInfin(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);


        g.setColor(temp);
    }
}
