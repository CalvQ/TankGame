
public abstract class GameObject implements Drawable{
	Point loc;
	
	public GameObject(Point loc){
	    this.loc = loc;
    }

    public Point getLoc(){
	    return loc;
    }
}
