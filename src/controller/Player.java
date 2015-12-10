package controller;

import model.AnimalEntity;
import toolbox.Mouse;
import toolbox.Vector2D;

public class Player implements java.io.Serializable{

	AnimalEntity playerEntity;
	boolean hasWon = false;
	boolean hasLost = false;
	boolean pause = false;
	
	public boolean restart = false;
	public boolean cont = false;
	public boolean exit = false;
	
	Mouse mouse;
	public Player(AnimalEntity myEntity, Mouse mouse){
		myEntity.setAsPlayer();
		this.playerEntity = myEntity;
		this.mouse = mouse;
	}
	
	
	private void setDirectionWithMouse(){
		Vector2D pointer = mouse.getMousePointerInWorldCoord();
		
		Vector2D position = playerEntity.getPosition();
		playerEntity.getDirection().setX(pointer.getX() - position.getX());
		playerEntity.getDirection().setY(pointer.getY() - position.getY());
		if(playerEntity.getDirection().getL2() < .4){
			playerEntity.getDirection().setX(0);
			playerEntity.getDirection().setY(0);
		}else{
			playerEntity.getDirection().normalize();
		}
//		System.out.println(playerEntity.getDirection().getX());
//		System.out.println(playerEntity.getDirection().getY());
//		System.out.println(pointer);
//		System.out.println(position);
//		playerEntity.setPosition(new Vector2D(pointer.getX(), pointer.getY()));
		
	}


	public void readInput() {
		
		
		setDirectionWithMouse();
		
		//Did we die?
		checkIfAlive();
		
		//Check if enough food for an upgrade...
		isEnoughFoodForLevelUp();
		
	}
	
	private void checkIfAlive(){
		if(!this.playerEntity.isLiving()){
			this.hasLost = true;
		}
	}
	
	
	private void isEnoughFoodForLevelUp(){
		//Check if enough food for an upgrade...
		if(playerEntity.getFoodLevel() > playerEntity.myFlyweight.getFoodRepro()){
			System.out.println("Enough Food for level Up");
			//TOP OF THE FOOD CHAIN BABY
			if(playerEntity.myFlyweight.getPredatorList().isEmpty()){
				this.hasWon = true;
			}else{
			//below is dirty, should not arbitrarily choose 0, especially as it could crash
			playerEntity.changeAnimal(playerEntity.myFlyweight.getPredatorList().get(0));
			}
		}
		
	}
	
	public AnimalEntity getPlayerEntity() {
		return playerEntity;
	}
	
	public Mouse getMouse() {
		return mouse;
	}


	public boolean hasWon() {
		// TODO Auto-generated method stub
		return this.hasWon;
	}


	public boolean hasLost() {
		// TODO Auto-generated method stub
		return this.hasLost;
	}


	public void pauseRound() {
		System.out.println("called pauseRound in player");
		this.pause = true;
		
	}


	public boolean hasPaused() {
		// TODO Auto-generated method stub
		return this.pause;
	}


	public boolean getRestart() {
		// TODO Auto-generated method stub
		return this.restart;
	}
	
	public boolean getExit(){
		return this.exit;
	}
	
	public boolean getCont(){
		return this.cont;
	}


}
