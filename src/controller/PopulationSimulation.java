package controller;

import model.WorldModel;

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
