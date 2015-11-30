package toolbox;

public class Maths {

	
	public static Vector2D midpoint(Vector2D a, Vector2D b){
		return new Vector2D((a.getX() + b.getX()) /2.0,(a.getY()+b.getY())/2.0 );
	}
	
	/**
	 * Finds an averaged midppoint, where x is the percent of a, and b weighted
	 * by 1-x
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static Vector2D weightedMid(Vector2D a, Vector2D b, double x){
		return new Vector2D((a.getX()*x + b.getX()*(1-x)),(a.getY()*x+b.getY()*(1-x)) );
	}
}
