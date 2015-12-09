package model;

import java.awt.image.BufferedImage;

import controller.Controller;
import controller.GameMode;
import toolbox.Vector2D;

public class AnimalEntity {


	Vector2D position;
	Vector2D direction;
	
	//not the best way but only way I can think of right now
	private boolean isLiving = true;
	
	private double speedMultiplier =1.0;
	private int animationFrame = 0;
	private int maxAnimationFrame = 1; //TODO: this should not be here, keep in Animal flyweight 
	private double foodLevel = 0;


	private double starvationRate; //food loss rate per second, read in from Animal, but keep
	private boolean isPlayer = false;
	private boolean oilContamination = false;
	
	//here to make it variable...
	private int totalFoodConsumed = 0;
	
	public long timeOfLastMove = 0;
	
	public Animal myFlyweight;
	
	public AnimalEntity(Animal myFlyweight, Vector2D position){
		this.position = position;
		this.direction = new Vector2D(0, 0);
		this.myFlyweight = myFlyweight;
		this.maxAnimationFrame = this.myFlyweight.getMaxAnimationFrame();
		this.starvationRate = myFlyweight.getStarvationRate();
		
		this.foodLevel = this.myFlyweight.getFoodRepro() /2.0;
	}
	
	public void changeAnimal(Animal newAnimal){
		this.myFlyweight = newAnimal;
		this.maxAnimationFrame = this.myFlyweight.getMaxAnimationFrame();
	}
	/**
	 * More shit code for the shit hack
	 */
	public void setAsPlayer(){
		this.isPlayer = true;
	}
	public void takeStep(float dt, WorldModel model){
		//this is a shit hack, but 12 hrs to go....
		if(!isPlayer){
			this.myFlyweight.move(this, model);
		}
		this.position.addX(speedMultiplier*this.myFlyweight.getSpeed()* direction.getX() * dt);
		this.position.addY(speedMultiplier*this.myFlyweight.getSpeed()* direction.getY() * dt);
		
		this.myFlyweight.resolveCollision(this, model);
		
		this.animationFrame = (this.animationFrame+1) % this.maxAnimationFrame;
		
		if(Controller.GAME_MODE == GameMode.POPULATION_FEEDBACK){

			if (this.foodLevel >= this.myFlyweight.getFoodRepro()) {
				// hmm what to do here? in a population study could increase
				// number
			}
			
			this.depleteFood(dt); // has it starved
			
			if(hasStarved()){
				System.out.println("Someone starved, set living to false...");
				this.isLiving = false; // this will tell model to remove it from world
			}
			
			if(!isLiving()){
				model.wasConsumed(this);
			}
		}
	
	}
	
	public boolean hasStarved(){
		
		return this.foodLevel < 0;
	}
	
	public void depleteFood(float dt){
		this.foodLevel -= dt* this.starvationRate;
		
	}
	
	public BufferedImage getDrawable(){
		if(this.isOilContamination()){
			return this.myFlyweight.getDeadDrawable();
		}
		//check direction -- such a horrible way to write a method
		//redundant checking and ugh.... design this away please...........................
		if(this.getDirection().getX() > 0  && !this.myFlyweight.getMovingBackAnimationSequence().isEmpty()){
			return this.myFlyweight.getMovingBackAnimationSequence().get(animationFrame);
		}
		return this.myFlyweight.getAnimationSequence().get(animationFrame);
	}
	public Vector2D getPosition() {
		return position;
	}


	public void setPosition(Vector2D position) {
		this.position = position;
	}


	public Vector2D getDirection() {
		return direction;
	}


	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
	public void digestAnimal(AnimalEntity darwinsLoser){
		
		this.foodLevel += darwinsLoser.myFlyweight.getFoodValue();
		this.totalFoodConsumed += darwinsLoser.myFlyweight.getFoodValue();
		//System.out.println(this.foodLevel);
	}
	
	

	public int getTotalFoodConsumed() {
		return totalFoodConsumed;
	}
	public int getFoodLevel(){
		return foodLevel;
	}
	public boolean isLiving() {
		return isLiving;
	}

	public void setLiving(boolean isLiving) {
		this.isLiving = isLiving;
	}

	public boolean isOilContamination() {
		return oilContamination;
	}

	public void setOilContamination(boolean oilContamination) {
		if(oilContamination){
			speedMultiplier = .2;
		}else{
			speedMultiplier = 1;
		}
		this.oilContamination = oilContamination;
	}

	
	public void addFood(double foodChange){
		this.foodLevel += foodChange;
	}
	public double getFoodLevel() {
		return foodLevel;
	}
	public double getLevelUpFood(){
		return this.myFlyweight.getFoodRepro();
	}
}
