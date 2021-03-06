package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
<<<<<<< HEAD
 * Acts as flyweights for each animal species holding general per species charachteristics and
 * animation data
 * "we don't write object hierarchies to satisfy our inner Linnaeus." --reddit
=======
 * Acts as flyweights for each animal species
 * 
>>>>>>> 8ba9b537df5f3ebe36ab860d80e2781a49b05dc0
 * @author chris
 *
 */
public enum Animal implements java.io.Serializable {

	// Constructor info
	// int speed, String animationFolderName,int speciesCount, int
	// animationSeqLength,MovementStrategy ms
	Seal(5, "seal", 2, 6, new BrownianMotion(), 200, 400, 1) {
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Fish);
			this.setPreyList(prey);
			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);
		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(8);
			this.setBottomLimit(3);

		}

	},
	Fish(6, "fish", 5, 6, new BrownianMotion(), 25, 50, .8) {

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Shrimp);
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			predator.add(Animal.Seal);
			this.setPredatorList(predator);
		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(10);
			this.setBottomLimit(3);

		}

	},
	Shrimp(4, "shrimp", 15, 6, new BrownianMotion(), 5, 10, .2) {

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Plankton);
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			predator.add(Animal.Fish);
			this.setPredatorList(predator);
		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(10);
			this.setBottomLimit(3);

		}

	},
	Plankton(1, "plankton", 50, 6, new BrownianMotion(), 1, 4, .02) {

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<>();
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			predator.add(Animal.Shrimp);
			this.setPredatorList(predator);
		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(10);
			this.setBottomLimit(3);

		}

	},

	WadingBird(5, "wadingbird", 3, 6, new BrownianMotion(), 2, 8000, .05) {
		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		public void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Fish);
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);
		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(3);
			this.setBottomLimit(18);

		}

	},

	Oil(1, "oil", 5, 1, new BrownianMotion(), -5, 10, 0) {

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveOilCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		protected void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			prey.add(Animal.Fish);
			prey.add(Animal.Shrimp);
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);

		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(4);
			this.setBottomLimit(18);

		}

	},

	Bubble(1, "bubble", 40, 6, new BrownianMotion(), -5, 10, .1) {

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		protected void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);

		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(10);
			this.setBottomLimit(2);

		}
	},

	Bubble2(1, "bubble2", 100, 6, new BrownianMotion(), -5, 10, .1) {

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		protected void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);

		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(10);
			this.setBottomLimit(2);

		}
	},

	Bubble3(1, "bubble3", 100, 6, new BrownianMotion(), -5, 10, .1) {

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		protected void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);

		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(10);
			this.setBottomLimit(2);

		}
	},

	shell(1, "shell", 10, 9, new BrownianMotion(), -5, 10, .1) {

		@Override
		public void move(AnimalEntity animalEntity, WorldModel model) {
			this.getMovementStrategy().setMove(animalEntity, model);

		}

		@Override
		public void resolveCollision(AnimalEntity animalEntity, WorldModel model) {
			CollisionHandler.resolveCollision(animalEntity, model, this.getBottomLimit(), this.getSurfaceLimit());

		}

		@Override
		protected void assignPrey() {
			ArrayList<Animal> prey = new ArrayList<Animal>();
			this.setPreyList(prey);

			ArrayList<Animal> predator = new ArrayList<Animal>();
			this.setPredatorList(predator);

		}

		@Override
		protected void setFieldInfo() {
			this.setSurfaceLimit(20);
			this.setBottomLimit(1);

		}
	};

	private double surfaceLimit;
	private double bottomLimit;
	private String animationFolderName;
	private double speed;
	private int imgHeight;
	private int imgWidth;
	private List<BufferedImage> movingAnimationSequence;
	private List<BufferedImage> movingBackAnimationSequence;
	private BufferedImage deadImage;
	private int maxAnimationFrame = 0;
	private int INTENDED_SPECIES_COUNT = 0;
	private int numberOfSpecies = 0;
	private int foodValue = 0;
	private MovementStrategy ms;
	private List<Animal> preyList;
	private List<Animal> predatorList;
	private int foodRepro;
	private double starvationRate;

	private Animal(int speed, String animationFolderName, int speciesCount, int animationSeqLength, MovementStrategy ms,
			int foodValue, int upperFood, double starvationRate) {
		this.INTENDED_SPECIES_COUNT = speciesCount;
		this.speed = speed;
		this.animationFolderName = animationFolderName;
		this.maxAnimationFrame = animationSeqLength;
		this.ms = ms;
		this.foodValue = foodValue;
		this.foodRepro = upperFood;
		this.setStarvationRate(starvationRate);
		this.setFieldInfo();
		// this must be called elsewhere, why, because all the enums must have
		// already been contucted as assignPrey calls them... wow that I learned
		// this.assignPrey();
	}
	
	/**
	 * Calls a movemeent strategy to set the direction of the animal entity - the actual
	 * movement is called separately and just integrates a velocity, but this sets the that
	 * direction
	 * @param animalEntity entity to have its animal specific direction set
	 * @param model include world data incase that's relevant to its motion strategy
	 */
	public abstract void move(AnimalEntity animalEntity, WorldModel model );
	/**
	 * Resolve any collision the animal may have had including interactions with other 
	 * animals and world edge collisions
	 * @param animalEntity amimal to have collisions resolved
	 * @param model model included for info about world like nearby animals and edges
	 */
	public abstract void resolveCollision(AnimalEntity animalEntity, WorldModel model );
	/**
	 * A separate method required to set predators and prey because all the enums
	 * aren't instantiated when some are in their constructors... due, so these
	 * values have to be set after animal constructed...
	 */
	protected abstract void assignPrey();
	/**
	 * A helper method for setting more data that just became ridiculous to set 
	 * in the constructor... sort of like a factory but because its an enum with specific
	 * per data just used this method to set that extra stuff
	 */
	protected abstract void setFieldInfo(); //because its uncanny to put everything in one line
	
	/* ********************************************************************************/
	//Just getters and setters below
	
	
	
	public MovementStrategy getMovementStrategy(){

		return this.ms;
	}

	public String getAnimationFolderName() {
		return this.animationFolderName;
	}

	public void setAnimationSequence(List<BufferedImage> sequence) {
		this.maxAnimationFrame = sequence.size();
		this.movingAnimationSequence = sequence;
	}

	public int getMaxAnimationFrame() {
		return this.maxAnimationFrame;
	}

	public List<BufferedImage> getAnimationSequence() {
		return this.movingAnimationSequence;
	}

	public double getSpeed() {
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

	public double getStarvationRate() {
		return starvationRate;
	}

	public void setStarvationRate(double starvationRate) {
		this.starvationRate = starvationRate;
	}

	public double getSurfaceLimit() {
		return surfaceLimit;
	}

	public void setSurfaceLimit(double surfaceLimit) {
		this.surfaceLimit = surfaceLimit;
	}

	public double getBottomLimit() {
		return bottomLimit;
	}

	public void setBottomLimit(double bottomLimit) {
		this.bottomLimit = bottomLimit;
	}

	public void setDeadDrawable(BufferedImage deadImage) {
		this.deadImage = deadImage;
	}

	public BufferedImage getDeadDrawable() {
		return this.deadImage;
	}

	public List<BufferedImage> getMovingBackAnimationSequence() {
		return movingBackAnimationSequence;
	}

	public void setMovingBackAnimationSequence(List<BufferedImage> movingBackAnimationSequence) {
		this.movingBackAnimationSequence = movingBackAnimationSequence;
	}

}
