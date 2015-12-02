package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Acts as flyweights for each animal species
 * @author chris
 *
 */
public enum Animal{
	
	//Constructor info
	//int speed, String animationFolderName,int speciesCount, int animationSeqLength,MovementStrategy ms
	Seal(5, "seal",2,1, new BrownianMotion(), 200){
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 3);
			
		}

		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Fish );
			this.setPreyList(prey);
			
		}
		
		
	},
	Fish(6, "fish",1,6,new BrownianMotion(), 25){

		
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 3);
			
		}
		
		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Shrimp );
			this.setPreyList(prey);
		}
		
	},
	Shrimp(3, "shrimp",1,6,new BrownianMotion(), 5){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 3);
			
		}
		
		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add( Animal.Plankton);
			this.setPreyList(prey);
		}
		
	},
	Plankton(1, "plankton", 50,6,new BrownianMotion(), 1){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 3);
			
		}
		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<>();
			this.setPreyList(prey);
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
	private int foodValue = 0;
	private MovementStrategy ms;
	private  List<Animal> preyList;
	
	private Animal(int speed, String animationFolderName,
			int speciesCount, int animationSeqLength,
			MovementStrategy ms, int foodValue){
		this.INTENDED_SPECIES_COUNT = speciesCount;
		this.speed = speed;
		this.animationFolderName = animationFolderName;
		this.maxAnimationFrame = animationSeqLength;
		this.ms = ms;
		//this must be called elsewhere, why, because all the enums must have
		//already been contucted as assignPrey calls them... wow that  I learned
		//this.assignPrey();
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
	public List<Animal> getPreyList() {
		return preyList;
	}
	public void setPreyList(List<Animal> preyList) {
		this.preyList = preyList;
	}
	public int getFoodValue() {
		return foodValue;
	}
	public void setFoodValue(int foodValue) {
		this.foodValue = foodValue;
	}
	
	
	public abstract void move(AnimalEntity animalEntity, WorldModel model );
	public abstract void resolveCollision(AnimalEntity animalEntity, WorldModel model );
	protected abstract void assignPrey();

}
