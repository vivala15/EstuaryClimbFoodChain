package controller;

import java.awt.MouseInfo;
import java.awt.Point;

import model.AnimalEntity;
import toolbox.Mouse;
import toolbox.Vector2D;

public class Player {

	AnimalEntity playerEntity;

	Mouse mouse;
	public Player(AnimalEntity myEntity, Mouse mouse){
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
		System.out.println(pointer);
		System.out.println(position);
//		playerEntity.setPosition(new Vector2D(pointer.getX(), pointer.getY()));
		
	}


	public void readInput() {
		setDirectionWithMouse();
//		System.out.println(this.getPlayerEntity().getPosition());
		
	}
	
	public AnimalEntity getPlayerEntity() {
		return playerEntity;
	}
	
	public Mouse getMouse() {
		return mouse;
	}


}
