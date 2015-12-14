package controller;

import model.AnimalEntity;
import toolbox.Mouse;
import toolbox.Vector2D;

/**
 * Player handles input from player and holds the entity controlled by the player. Checks user
 * input and win/lose conditions
 * 
 *
 */
public class Player implements java.io.Serializable{

	AnimalEntity playerEntity;
	boolean hasWon = false;
	boolean hasLost = false;
	boolean pause = false;
	
	public boolean restart = false;
	public boolean cont = false;
	public boolean exit = false;
	
	Mouse mouse;
	/**
	 * Takes animal entity to be controlled by player and mouse object for reading input
	 * @param myEntity
	 * @param mouse
	 */
	public Player(AnimalEntity myEntity, Mouse mouse){
		myEntity.setAsPlayer();
		this.playerEntity = myEntity;
		this.mouse = mouse;
	}
	
	/**
	 * Read mouse coorindates and set direction between mouse ptr and player location
	 */
	private void setDirectionWithMouse(){
		Vector2D pointer = mouse.getMousePointerInWorldCoord();
		
		Vector2D position = playerEntity.getPosition();
		playerEntity.getDirection().setX(pointer.getX() - position.getX());
		playerEntity.getDirection().setY(pointer.getY() - position.getY());
		if(playerEntity.getDirection().getL2() < .4){
			//if mouse close to player set direction to zero to stop player from moving
			playerEntity.getDirection().setX(0);
			playerEntity.getDirection().setY(0);
		}else{
			//normalize otherwise artifcats in velocity
			playerEntity.getDirection().normalize();
		}
		
	}


	/**
	 * Read mouse input and set direction and whether upgrade or loss should be set
	 */
	public void readInput() {
		
		//set direction
		setDirectionWithMouse();
		
		//Did we die?
		checkIfAlive();
		
		//Check if enough food for an upgrade...
		isEnoughFoodForLevelUp();
		
	}
	
	/**
	 * Check if player's animal is still alive, if not set boolean hasLost to true
	 */
	private void checkIfAlive(){
		if(!this.playerEntity.isLiving()){
			this.hasLost = true;
		}
	}
	
	/**
	 * Check if food levels are at reproduction level (species upgrade for player), if so
	 * upgrade the animals
	 */
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
