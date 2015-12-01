package model;

/**
 * Handle going off the edges - flip direction, and food chain hierarchy
 * @author chris
 *
 */
public class CollisionHandler {

	
	/*
	 
	 */
	public static void resolveCollision(AnimalEntity entity, WorldModel model,
			double depthLimitFromBottom, double depthLimitFromSurface){
		//Resolve edge violations
		resolveEdgeCollisions( entity,  model,depthLimitFromBottom,  depthLimitFromSurface);
		
	}
	
	
	public static void resolveEdgeCollisions(AnimalEntity entity, WorldModel model,
			double depthLimitFromBottom, double depthLimitFromSurface){
		//Check if over edges, if so flip direction
		if(entity.getPosition().getX() < 0){
			//over left border -- make x direction positive
			entity.getDirection().setX(Math.abs(entity.getDirection().getX()));
		}else if(entity.getPosition().getX() > model.getWIDTH()){
			//over right border -- make x direction negative
			entity.getDirection().setX(-1*Math.abs(entity.getDirection().getX()));
		}
		if(entity.getPosition().getY() < depthLimitFromSurface){
			//over surface -- make y direction positive
			entity.getDirection().setY(Math.abs(entity.getDirection().getY()));
		}else if(entity.getPosition().getY() > model.getDEPTH() - depthLimitFromBottom ){
			//over sea floor -- make y direction negative
			entity.getDirection().setY(-1*Math.abs(entity.getDirection().getY()));
		}
	}
	
	
	
	
}
