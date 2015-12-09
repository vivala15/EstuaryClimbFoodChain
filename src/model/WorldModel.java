package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import toolbox.Vector2D;

public class WorldModel implements Iterable<AnimalEntity>{
	
	//HashMap for fast lookup when map densely populated
	private AnimalNeighborhood neighborhood;
	private GameEventSequencer eventScheduler;
	private static double DEPTH = 25;
	private static double WIDTH = 80;
	private Random rand;
	
	
	public WorldModel(){
		neighborhood = new AnimalNeighborhood(WIDTH);
		rand = new Random();
		//Assign enums, not sure where else to put this, as it can't be in enum constructor
		for(Animal animal : Animal.values()){
			animal.assignPrey();
		}
		eventScheduler = new GameEventSequencer(this);
	}

	public void takeStep(){
		
		this.neighborhood.updateNeighborhood();
		
		
		
		
		for(AnimalEntity animal: this){
			animal.takeStep(.05f, this);
		}
		//only after iterating the animals call this
		//FATAL ERROR If method below called during iteration of this class
		this.neighborhood.executeRemoveAnimals();
		
		//if any animals need to added or replaced , do that here
//		System.out.println("replenish");
//		System.out.println(Animal.Seal.getNumberOfSpecies());
//		System.out.println(Animal.Fish.getNumberOfSpecies());
		replenishWildLife();
	}
	
	
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

	public void addAnimal(Animal animalFlyweightType){
		animalFlyweightType.setNumberOfSpecies(animalFlyweightType.getNumberOfSpecies() + 1);
		//int highestAllowableY = animalFlyweightType.get
		AnimalEntity entity = new AnimalEntity(animalFlyweightType,
				new Vector2D(rand.nextDouble()*WIDTH, 
						animalFlyweightType.getSurfaceLimit()+ rand.nextDouble()*
						(DEPTH - animalFlyweightType.getSurfaceLimit() - 
								animalFlyweightType.getBottomLimit())));
		neighborhood.addAnimal(entity);
	}
	
	public void addAnimal(Animal animalFlyweightType, Vector2D location){
		animalFlyweightType.setNumberOfSpecies(animalFlyweightType.getNumberOfSpecies() + 1);
		//mod by width and depth to make sure its not out of world... is that actually
		//desirable, mabye do want outside????
		AnimalEntity entity = new AnimalEntity(animalFlyweightType,
				new Vector2D(location.getX()%WIDTH, location.getY() %DEPTH));
		neighborhood.addAnimal(entity);
	}
	
	public void addPlayerAnimal(AnimalEntity entity){
		entity.myFlyweight.setNumberOfSpecies(entity.myFlyweight.getNumberOfSpecies() + 1);
		this.neighborhood.addAnimal(entity);
	}

	@Override
	public Iterator<AnimalEntity> iterator() {
		// TODO Auto-generated method stub
		return neighborhood;
	}
	
	
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

	
	public void wasConsumed(AnimalEntity prey){
		prey.myFlyweight.setNumberOfSpecies(prey.myFlyweight.getNumberOfSpecies() - 1);
		this.neighborhood.removeAnimal(prey);
	}

	
	


}
