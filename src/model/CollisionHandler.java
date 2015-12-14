package model;

import java.util.List;

/**
 * Handle going off the edges - flip direction, and food chain hierarchy
 * @author chris
 *
 */
public class CollisionHandler {

	
	/**
	 * take entity and resolve edge collision and food change if within range of prey
	 * @param entity entity to resolvecolliion
	 * @param model world info
	 * @param depthLimitFromBottom extra offset for edge collision from bottom
	 * @param depthLimitFromSurface  extra offset for edge collision from top
	 */
	public static void resolveCollision(AnimalEntity entity, WorldModel model,
			double depthLimitFromBottom, double depthLimitFromSurface){
		//Resolve edge violations
		resolveEdgeCollisions( entity,  model,depthLimitFromBottom,  depthLimitFromSurface);
		resolveFoodChain( entity, model);
	}
	
	/**
	 * Strategy only used by oil - makes sure it stays in proper zone and then checks
	 * whether to attach to nearby neighbors based on their type
	 * @param entity
	 * @param model
	 * @param depthLimitFromBottom
	 * @param depthLimitFromSurface
	 */
	public static void resolveOilCollision(AnimalEntity entity, WorldModel model,
			double depthLimitFromBottom, double depthLimitFromSurface){
		resolveEdgeCollisions( entity,  model,depthLimitFromBottom,  depthLimitFromSurface);
		oilAttach(entity,model);
		
	}
	
	/**
	 * Searches local area for neighbors and calls relevant eating methods if they are
	 * within range and are listed as prey for this entity.
	 * @param entity entity that is feeding
	 * @param model model for world info
	 */
	private static void resolveFoodChain(AnimalEntity entity, WorldModel model){
		List<AnimalEntity> nearbyAnimals = model.getNearbyAnimals(entity.getPosition(), 1);
		for(AnimalEntity animEntity : nearbyAnimals){
			if(animEntity != entity){ //make sure not comparing animal to itself...
				//definitely not most efficient way but should be fine since small list and 
				//neighborhood search
				if(entity.myFlyweight.getPreyList().contains(animEntity.myFlyweight)){
					model.wasConsumed(animEntity);  //remove consumed animal
					entity.digestAnimal(animEntity); //compensate food value
					animEntity.setLiving(false);	//let entity know it died
				}
			}
		}
		
		
	}
	
	/**
	 * Check if near edge, adjust direction if outside acceptable zones
	 * @param entity entity to be checked for edge breaking
	 * @param model model world
	 * @param depthLimitFromBottom extra distance species should not cross at bottom
	 * @param depthLimitFromSurface extra distance species should not cross at top, no flying fish
	 */
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
	
	/**
	 * Oil has slightly different collision, follows same format as predator prey method
	 * above but sets a few more features such as setting oil contamination
	 * @param entity SHOULD BE AN OIL ENTITY
	 * @param model world info
	 */
	private static void oilAttach(AnimalEntity entity, WorldModel model){
		List<AnimalEntity> nearbyAnimals = model.getNearbyAnimals(entity.getPosition(), 2);
		for(AnimalEntity animEntity : nearbyAnimals){
			if(animEntity != entity){ //make sure not comparing animal to itself...
				//definitely not most efficient way but should be fine since small list and 
				//neighborhood search
				if(entity.myFlyweight.getPreyList().contains(animEntity.myFlyweight)){
					//model.wasConsumed(animEntity);  //remove consumed animal
					entity.digestAnimal(animEntity); //compensate food value
					animEntity.setOilContamination(true);
					animEntity.addFood(entity.myFlyweight.getFoodValue());
					//animEntity.setLiving(false);	//let entity know it died
				}
			}
		}
	}
	
	
	
}
