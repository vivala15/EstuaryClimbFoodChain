package toolbox;

public class Vector2D {

	private double x;

	private double y;
	
	public Vector2D(){
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getL2(){
		return x*x + y*y;
	}
	public void normalize(){
		double s2 = x*x + y*y;
		this.x = x*Math.abs(x) / s2;
		this.y = y*Math.abs(y) / s2;
	}
	
	public void addX(double addToX){
		this.x += addToX;
	}
	
	public void addY(double addToY){
		this.y += addToY;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public static Vector2D sum(Vector2D a, Vector2D b){
		return new Vector2D(a.getX()+b.getX(), a.getY()+ b.getY());
	}
	public Vector2D add(Vector2D other){
		this.x += other.getX();
		this.y += other.getY();
		return this;
	}
	@Override
	public String toString(){
		return Double.toString(x) + "  " + Double.toString(y);
	}
}
