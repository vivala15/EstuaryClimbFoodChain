package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import toolbox.Vector2D;

/**
 * 
 * World model holds the bucket reference and handles addition and removal of species
 * into and out of the world
 *
 */
public class WorldModel implements Iterable<AnimalEntity>{
	
	//HashMap for fast lookup when map densely populated
	private AnimalNeighborhood neighborhood;
	private GameEventSequencer eventScheduler;
	private static double DEPTH = 25;
	private static double WIDTH = 80;
	private Random rand;
	
	/**
	 * Create the hashed bucket system and assign some stuff in the animal enum because
	 * where else would we do that
	 */
	public WorldModel(){
		neighborhood = new AnimalNeighborhood(WIDTH);
		rand = new Random();
		//Assign enums, not sure where else to put this, as it can't be in enum constructor
		for(Animal animal : Animal.values()){
			animal.assignPrey();
		}
		eventScheduler = new GameEventSequencer(this);
	}

	/**
	 * Take a step in model
	 */
	public void takeStep(){
		//update neighborhood, - remember hashed buckets
		this.neighborhood.updateNeighborhood();
		
		
		
		//for each animal update its position
		for(AnimalEntity animal: this){
			
			animal.takeStep(.05f, this);
		}
		//only after iterating the animals call this
		//FATAL ERROR If method below called during iteration of this class
		this.neighborhood.executeRemoveAnimals();
		
		//if any animals need to added or replaced , do that here
		replenishWildLife();
	}
	
	
	/**
	 * Replace consumed animals to their required rate
	 */
	public void replenishWildLife(){
		//System.out.println("Call replenish");
		for(Animal animal : Animal.values()){
			//System.out.println(animal);
			while(animal.getNumberOfSpecies() < animal.getINTENDED_SPECIES_COUNT()){
				//System.out.println(animal.getNumberOfSpecies());
				addAnimal(animal);
			}
		}
	}

	/**
	 * Add animal at random allowable position of type flywieght given
	 * @param animalFlyweightType type of new animal
	 */
	public void addAnimal(Animal animalFlyweightType){
		//increment species counter
		animalFlyweightType.setNumberOfSpecies(animalFlyweightType.getNumberOfSpecies() + 1);
		//int highestAllowableY = animalFlyweightType.get
		//create entity with random position within limits
		AnimalEntity entity = new AnimalEntity(animalFlyweightType,
				new Vector2D(rand.nextDouble()*WIDTH, 
						animalFlyweightType.getSurfaceLimit()+ rand.nextDouble()*
						(DEPTH - animalFlyweightType.getSurfaceLimit() - 
								animalFlyweightType.getBottomLimit())));
		neighborhood.addAnimal(entity);
	}
	/**
	 * Add animal at given location of type given
	 * @param animalFlyweightType type of new animal
	 * @param location starting position
	 */
	public void addAnimal(Animal animalFlyweightType, Vector2D location){
		//increment species counter
		animalFlyweightType.setNumberOfSpecies(animalFlyweightType.getNumberOfSpecies() + 1);
		//mod by width and depth to make sure its not out of world... is that actually
		//desirable, mabye do want outside????
		AnimalEntity entity = new AnimalEntity(animalFlyweightType,
				new Vector2D(location.getX()%WIDTH, location.getY() %DEPTH));
		neighborhood.addAnimal(entity);
	}
	
	/**
	 * Add player animal, actually could be used to add any already created entity
	 * @param entity
	 */
	public void addPlayerAnimal(AnimalEntity entity){
		entity.myFlyweight.setNumberOfSpecies(entity.myFlyweight.getNumberOfSpecies() + 1);
		this.neighborhood.addAnimal(entity);
	}

	@Override
	public Iterator<AnimalEntity> iterator() {
		// TODO Auto-generated method stub
		return neighborhood;
	}
	
	/**
	 * Get all nearby aniamls in world within the given range from the position
	 * @param position position from where to check
	 * @param range range of how close animals shouldbe to return them
	 * @return
	 */
	public List<AnimalEntity> getNearbyAnimals(Vector2D position, double range){
		//gets all in adjacent buckets
		LinkedList<AnimalEntity> list =  this.neighborhood.getNearAnimals(position, range);	
		ArrayList<AnimalEntity> inRangeAnimals = new ArrayList<>(list.size());
		//now prune based on distance -- don't mess with removing from list being iterated...
		for(AnimalEntity rangeCheckEntity : list){
			if(Vector2D.distanceSquared(position, rangeCheckEntity.getPosition()) < range*range){
				inRangeAnimals.add(rangeCheckEntity);
			}
		}

		return inRangeAnimals;	
	}
	
	public double getDEPTH() {
		return DEPTH;
	}

	public double getWIDTH() {
		return WIDTH;
	}

	/**
	 * Consumed prey decrements species counter and removes them from the bucket
	 * @param prey
	 */
	public void wasConsumed(AnimalEntity prey){
		prey.myFlyweight.setNumberOfSpecies(prey.myFlyweight.getNumberOfSpecies() - 1);
		this.neighborhood.removeAnimal(prey);
	}

	
	


}
