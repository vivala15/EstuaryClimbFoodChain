package model;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Acts as flyweights for each animal species
 * @author chris
 *
 */
public enum Animal{
	
	//Constructor info
	//int speed, String animationFolderName,int speciesCount, int animationSeqLength,MovementStrategy ms
	Seal(5, "seal",2,6, new BrownianMotion()){
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 3);
			
		}
		
		
	},
	Fish(6, "fish",10,6,new BrownianMotion()){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 3);
			
		}
		
	},
	Shrimp(3, "shrimp",20,6,new BrownianMotion()){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 3);
			
		}
		
	},
	Plankton(5, "plankton", 30,6,new BrownianMotion()){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 3);
			
		}
		
	};

	private String animationFolderName;
	private double speed;
	private int imgHeight;
	private int imgWidth;
	private List<BufferedImage> movingAnimationSequence;
	private int maxAnimationFrame = 0;
	private int INTENDED_SPECIES_COUNT = 0;
	private int numberOfSpecies = 0;
	private MovementStrategy ms;
	
	private Animal(int speed, String animationFolderName,
			int speciesCount, int animationSeqLength,
			MovementStrategy ms){
		this.INTENDED_SPECIES_COUNT = speciesCount;
		this.speed = speed;
		this.animationFolderName = animationFolderName;
		this.maxAnimationFrame = animationSeqLength;
		this.ms = ms;
	}
	public MovementStrategy getMovementStrategy(){
		return this.ms;
	}
	
	public String getAnimationFolderName(){
		return this.animationFolderName;
	}
	public void setAnimationSequence( List<BufferedImage> sequence){
		this.maxAnimationFrame = sequence.size();
		this.movingAnimationSequence = sequence;
	}
	
	public int getMaxAnimationFrame(){
		return this.maxAnimationFrame;
	}
	
	public List<BufferedImage> getAnimationSequence(){
		return this.movingAnimationSequence;
	}
	
	public double getSpeed(){
		return this.speed;
	}
	
	public int getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	public int getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}
	public int getNumberOfSpecies() {
		return numberOfSpecies;
	}
	public void setNumberOfSpecies(int numberOfSpecies) {
		this.numberOfSpecies = numberOfSpecies;
	}
	public int getINTENDED_SPECIES_COUNT() {
		return INTENDED_SPECIES_COUNT;
	}
	
	public abstract void move(AnimalEntity animalEntity, WorldModel model );
	public abstract void resolveCollision(AnimalEntity animalEntity, WorldModel model );
	
}
