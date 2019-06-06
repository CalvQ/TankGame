import java.awt.*;

class DrawChar {
    DrawChar(){
        super();
    }

    //drawing components for digital-clock style
    private void drawVertical(Point p, Graphics g){
        Color temp = g.getColor();
        g.setColor(Color.BLACK);
        int[] xPoints = {p.getX(), p.getX()+1, p.getX()+2, p.getX()+2, p.getX()+1, p.getX()};
        int[] yPoints = {p.getY()+1, p.getY(), p.getY()+1, p.getY()+8, p.getY()+9, p.getY()+8};
        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(temp);
    }
    private void drawHorizontal(Point p, Graphics g){
        Color temp = g.getColor();
        g.setColor(Color.BLACK);
        int[] xPoints = {p.getX(), p.getX()+1, p.getX()+8, p.getX()+9, p.getX()+8, p.getX()+1};
        int[] yPoints = {p.getY()+1, p.getY(), p.getY(), p.getY()+1, p.getY()+2, p.getY()+2};
        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(temp);
    }

    void drawInt(int num, Point p, Graphics g){//Bounding box per character: 10x20
        String out = Integer.toString(num);
        for(int x = 0; x<out.length(); x++){
            switch(out.charAt(x)){
                case '1':
                    drawOne(p,g);
                    p.incX(10);
                    break;
                case '2':
                    drawTwo(p,g);
                    p.incX(10);
                    break;
                case '3':
                    drawThree(p,g);
                    p.incX(10);
                    break;
                case '4':
                    drawFour(p,g);
                    p.incX(10);
                    break;
                case '5':
                    drawFive(p,g);
                    p.incX(10);
                    break;
                case '6':
                    drawSix(p,g);
                    p.incX(10);
                    break;
                case '7':
                    drawSeven(p,g);
                    p.incX(10);
                    break;
                case '8':
                    drawEight(p,g);
                    p.incX(10);
                    break;
                case '9':
                    drawNine(p,g);
                    p.incX(10);
                    break;
                case '0':
                    drawZero(p,g);
                    p.incX(10);
                    break;
            }
        }
    }

    private void drawZero(Point p, Graphics g){
        charLoop(p, g);
    }
    private void drawOne(Point p, Graphics g){
        drawVertical(new Point(p.getX()+7, p.getY()),g);
        drawVertical(new Point(p.getX()+7, p.getY()+10),g);
    }
    private void drawTwo(Point p, Graphics g){
        drawHorizontal(p,g);
        drawVertical(new Point(p.getX()+7, p.getY()),g);
        drawHorizontal(new Point(p.getX(), p.getY()+9),g);
        drawVertical(new Point(p.getX(), p.getY()+10),g);
        drawHorizontal(new Point(p.getX(), p.getY()+18),g);
    }
    private void drawThree(Point p, Graphics g){
        drawHorizontal(p,g);
        drawVertical(new Point(p.getX()+7, p.getY()),g);
        drawHorizontal(new Point(p.getX(), p.getY()+9),g);
        drawVertical(new Point(p.getX()+7, p.getY()+10),g);
        drawHorizontal(new Point(p.getX(), p.getY()+18),g);
    }
    private void drawFour(Point p, Graphics g){
        drawVertical(p,g);
        drawHorizontal(new Point(p.getX(), p.getY()+9),g);
        drawVertical(new Point(p.getX()+7, p.getY()),g);
        drawVertical(new Point(p.getX()+7, p.getY()+10),g);
    }
    private void drawFive(Point p, Graphics g){
        drawHorizontal(p,g);
        drawVertical(p,g);
        drawHorizontal(new Point(p.getX(), p.getY()+9),g);
        drawVertical(new Point(p.getX()+7, p.getY()+10),g);
        drawHorizontal(new Point(p.getX(), p.getY()+18),g);
    }
    private void drawSix(Point p, Graphics g){
        drawHorizontal(p,g);
        drawVertical(p,g);
        drawVertical(new Point(p.getX(), p.getY()+10),g);
        drawHorizontal(new Point(p.getX(), p.getY()+18),g);
        drawVertical(new Point(p.getX()+7, p.getY()+10),g);
        drawHorizontal(new Point(p.getX(), p.getY()+9),g);
    }
    private void drawSeven(Point p, Graphics g){
        drawHorizontal(p,g);
        drawVertical(new Point(p.getX()+7, p.getY()),g);
        drawVertical(new Point(p.getX()+7, p.getY()+10),g);
    }
    private void drawEight(Point p, Graphics g){
        charLoop(p, g);
        drawHorizontal(new Point(p.getX(), p.getY()+9),g);
    }

    private void charLoop(Point p, Graphics g) {
        drawHorizontal(p,g);
        drawVertical(p,g);
        drawVertical(new Point(p.getX(), p.getY()+10),g);
        drawHorizontal(new Point(p.getX(), p.getY()+18),g);
        drawVertical(new Point(p.getX()+7, p.getY()),g);
        drawVertical(new Point(p.getX()+7, p.getY()+10),g);
    }

    private void drawNine(Point p, Graphics g){
        drawHorizontal(p,g);
        drawVertical(p,g);
        drawVertical(new Point(p.getX()+7, p.getY()), g);
        drawVertical(new Point(p.getX()+7, p.getY()+10), g);
        drawHorizontal(new Point(p.getX(), p.getY()+9), g);
    }

}
