package model;

import java.util.List;

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
		resolveFoodChain( entity, model);
	}
	
	/*
	 * Searches local area for neighbors and calls relevant eating methods if they are
	 * within range and are listed as prey for this entity.
	 */
	public static void resolveFoodChain(AnimalEntity entity, WorldModel model){
		List<AnimalEntity> nearbyAnimals = model.getNearbyAnimals(entity.getPosition(), 2);
		for(AnimalEntity animEntity : nearbyAnimals){
			if(animEntity != entity){ //make sure not comparing animal to itself...
				//definitely not most efficient way but should be fine since small list and 
				//neighborhood search
				if(entity.myFlyweight.getPreyList().contains(animEntity.myFlyweight)){
					model.wasConsumed(animEntity);  //remove consumed animal
					entity.digestAnimal(animEntity); //compensate food value
				}
			}
		}
		
		
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
