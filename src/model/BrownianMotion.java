package model;

import java.util.Calendar;
import java.util.Random;

import toolbox.Vector2D;

/**
 * 
 * A movement strategy that updates the direction of the animal to a random direction
 * every given period of time given as a field
 *
 */
public class BrownianMotion implements MovementStrategy{

	 Calendar cal = Calendar.getInstance();
	 Random rand = new Random();
	 long MOVE_PERIOD = 1000; //in ms
	 
	 public BrownianMotion(){
		 
	 }
	
	@Override
	public void setMove(AnimalEntity animalEntity, WorldModel model){
		//every ceratin amount of time give a new random direction
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - animalEntity.timeOfLastMove;
//		System.out.println("how long");
//		System.out.println(elapsedTime);
		if(elapsedTime > MOVE_PERIOD){
//			System.out.println("New Direction-------------------------------------------");
			animalEntity.timeOfLastMove = currentTime;
			animalEntity.setDirection(new Vector2D(2*rand.nextDouble()-1.0, 2*rand.nextDouble()-1.0));
			animalEntity.getDirection().normalize();
		}
	}
	
}
