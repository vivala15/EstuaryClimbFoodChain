package controller;

import model.WorldModel;


/**
 * Fun class for playing with population dynamics and modeling predator - prey systems...
 * Just have to get graphs working and atleast I think this would be more fun than the game...
 * 
 * To be used for searching for meta-stable points to set game at when using DynamicPopulation
 * feature...otherwise chances are randomly choosen values will lead to species extinction
 * and unplayability.........
 * @author chris
 *
 */
public class PopulationSimulation {

	
	WorldModel model;
	int totalSteps; //number of steps per simulation
	int time_step; //try to make as high as possible without introducing simulation artifacts
	int howOftenRecord = 200;
	
	public PopulationSimulation(){
		
		
	}
	
	
	public void prepWorld(int numberSteps){
		
		
	}
	
	 public void run(){
	    	
		 
		 double stepNumber = 0;
//		 double lastTime = System.currentTimeMillis();
	    	/**
	    	 * Loop broken on exit, may have to add a button for full screen mode
	    	 */
	    	while(stepNumber < totalSteps){
	    		
	    		model.takeStep();
	    		stepNumber += 1;
	    		
	    		if(stepNumber % howOftenRecord == 0){
	    			recordSpecies();
	    		}
//	    		elapsedTime += System.currentTimeMillis() - lastTime;
//	    		lastTime = elapsedTime;
	    		//have to somehow check...
	    	}
	    	
	    	
	  }
	 
	 public void recordSpecies(){
		 
		 
	 }
	
	
}
