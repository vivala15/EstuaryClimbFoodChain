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
	
	private int animationFrame = 0;
	private int maxAnimationFrame = 1;
	private int foodLevel = 0;
	private int totalFoodConsumed = 0;
	
	public long timeOfLastMove = 0;
	
	public Animal myFlyweight;
	
	public AnimalEntity(Animal myFlyweight, Vector2D position){
		this.position = position;
		this.direction = new Vector2D(0, 0);
		this.myFlyweight = myFlyweight;
		this.maxAnimationFrame = this.myFlyweight.getMaxAnimationFrame();
	}
	
	public void changeAnimal(Animal newAnimal){
		this.myFlyweight = newAnimal;
		this.maxAnimationFrame = this.myFlyweight.getMaxAnimationFrame();
	}
	
	public void takeStep(float dt, WorldModel model){
		this.myFlyweight.move(this, model);
		
		this.position.addX(this.myFlyweight.getSpeed()* direction.getX() * dt);
		this.position.addY(this.myFlyweight.getSpeed()* direction.getY() * dt);
		
		this.myFlyweight.resolveCollision(this, model);
		
		this.animationFrame = (this.animationFrame+1) % this.maxAnimationFrame;
		
	}
	
	public BufferedImage getDrawable(){
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
		System.out.println(this.foodLevel);
		if(this.foodLevel >= this.myFlyweight.getFoodRepro()){
			// hmm what to do here? in a population study could increase number
			if(Controller.GAME_MODE == GameMode.POPULATION_FEEDBACK){
				
			}
		}
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

	
	
}
