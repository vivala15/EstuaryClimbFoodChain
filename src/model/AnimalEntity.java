package model;

import java.awt.image.BufferedImage;

import toolbox.Vector2D;

public class AnimalEntity {


	Vector2D position;


	Vector2D direction;
	private int animationFrame = 0;
	private int maxAnimationFrame = 1;
	private int foodLevel = 0;
	
	public long timeOfLastMove = 0;
	
	Animal myFlyweight;
	
	public AnimalEntity(Animal myFlyweight, Vector2D position){
		this.position = position;
		this.direction = new Vector2D(0, 0);
		this.myFlyweight = myFlyweight;
		this.maxAnimationFrame = this.myFlyweight.getMaxAnimationFrame();
	}
	
	
	public void takeStep(float dt, WorldModel model){
		//this.myFlyweight.move(this, model);
		
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
	}
	
	
}
