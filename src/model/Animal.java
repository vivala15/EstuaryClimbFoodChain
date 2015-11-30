package model;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Acts as flyweights for each animal species
 * @author chris
 *
 */
public enum Animal{
	
	Seal(5, "seal",1,6){
		
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			// TODO Auto-generated method stub
			
		}
		
		
	},
	Fish(6, "fish",1,6){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			// TODO Auto-generated mthod stub
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			// TODO Auto-generated method stub
			
		}
		
	},
	Shrimp(3, "shrimp",1,6){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			// TODO Auto-generated method stub
			
		}
		
	},
	Plankton(5, "plankton", 1,6){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			// TODO Auto-generated method stub
			
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
	
	private Animal(int speed, String animationFolderName, int speciesCount, int animationSeqLength){
		this.INTENDED_SPECIES_COUNT = speciesCount;
		this.speed = speed;
		this.animationFolderName = animationFolderName;
		this.maxAnimationFrame = animationSeqLength;
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
