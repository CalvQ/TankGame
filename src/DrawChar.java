import java.awt.*;

public class DrawChar {//bounds for char: 10x20
    DrawChar(){
        super();
    }

    //always upper-left corner = x,y

    public static void draw1(Graphics g, int x, int y){
        Color temp = g.getColor();
        g.setColor(Color.black);
        g.fillRect(x+3, y+4, 4, 12);
        g.setColor(temp);
    }
    public static void draw2(Graphics g, int x, int y){

    }
    public static void draw3(Graphics g, int x, int y){

    }
    public static void draw4(Graphics g, int x, int y){

    }
    public static void draw5(Graphics g, int x, int y){

    }
    public static void draw6(Graphics g, int x, int y){

    }
    public static void draw7(Graphics g, int x, int y){

    }
    public static void draw8(Graphics g, int x, int y){

    }
    public static void draw9(Graphics g, int x, int y){

    }
}
