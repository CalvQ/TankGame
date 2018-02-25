public class Point{
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void incX(int dx){
        this.x+=dx;
    }
    public void incY(int dy){
        this.y+=dy;
    }

    public void setX(int val){
        this.x = val;
    }
    public void setY(int val){
        this.y = val;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point && this.x == ((Point) obj).getX() && this.y == ((Point) obj).getY();
    }

    @Override
    public String toString(){
        return x + "," + y;
    }
}
