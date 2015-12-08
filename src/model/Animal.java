package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Acts as flyweights for each animal species
 * @author chris
 *
 */
public enum Animal implements java.io.Serializable {
	
	//Constructor info
	//int speed, String animationFolderName,int speciesCount, int animationSeqLength,MovementStrategy ms
	Seal(5, "seal",2,6, new BrownianMotion(), 200,400){
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 6);
			
		}

		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Fish );
			this.setPreyList(prey);
			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);
		}
		
		
	},
	Fish(6, "fish",5,6,new BrownianMotion(), 25,50){

		
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 9);
			
		}
		
		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Shrimp );
			this.setPreyList(prey);
			
			ArrayList<Animal> predator = new ArrayList<Animal>();
			predator.add(Animal.Seal);
			this.setPredatorList(predator);
		}
		
	},
	Shrimp(4, "shrimp",15,6,new BrownianMotion(), 5,10){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 9);
			
		}
		
		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add( Animal.Plankton);
			this.setPreyList(prey);
			
			ArrayList<Animal> predator = new ArrayList<Animal>();
			predator.add(Animal.Fish);
			this.setPredatorList(predator);
		}
		
	},
	Plankton(1, "plankton", 50,6,new BrownianMotion(), 1,4){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 3, 9);
			
		}
		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<>();
			this.setPreyList(prey);
			
			ArrayList<Animal> predator = new ArrayList<Animal>();
			predator.add(Animal.Shrimp);
			this.setPredatorList(predator);
		}
		
	},
	
	WadingBird(5, "wadingbird",3,6, new BrownianMotion(), 2,8000){
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 15, 3);
			
		}

		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Fish );
			this.setPreyList(prey);
			
			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);
		}
		
		
	},
	
	Oil(1,"oil", 3,1, new BrownianMotion(), -5, 10 ){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 15, 3);
			
		}

		@Override
		protected void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Fish );
			prey.add(Animal.Shrimp );
			this.setPreyList(prey);
			
			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);
			
		}
		
		
	},
	
	Bubble(1,"bubble", 40,6, new BrownianMotion(), -5, 10 ){

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);
			
		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, 15, 3);
			
		}

		@Override
		protected void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			this.setPreyList(prey);
			
			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);
			
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
	private  List<Animal> predatorList;
	private int foodRepro;
	
	private Animal(int speed, String animationFolderName,
			int speciesCount, int animationSeqLength,
			MovementStrategy ms, int foodValue,
			int upperFood){
		this.INTENDED_SPECIES_COUNT = speciesCount;
		this.speed = speed;
		this.animationFolderName = animationFolderName;
		this.maxAnimationFrame = animationSeqLength;
		this.ms = ms;
		this.foodValue = foodValue;
		this.foodRepro = upperFood;
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
	public int getFoodRepro() {
		return foodRepro;
	}
	public void setFoodRepro(int foodRepro) {
		this.foodRepro = foodRepro;
	}
	public List<Animal> getPredatorList() {
		return predatorList;
	}
	public void setPredatorList(List<Animal> predatorList) {
		this.predatorList = predatorList;
	}

}
