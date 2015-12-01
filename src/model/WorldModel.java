package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import toolbox.Vector2D;

public class WorldModel implements Iterable<AnimalEntity>{
	
	//HashMap for fast lookup when map densely populated
	private AnimalNeighborhood neighborhood;
	private double DEPTH = 25;
	private double WIDTH = 80;
	private Random rand;
	
	public WorldModel(){
		neighborhood = new AnimalNeighborhood(WIDTH);
		rand = new Random();
	}

	public void takeStep(){
		
		for(AnimalEntity animal: this){
			animal.takeStep(.1f, this);
		}
		
		//if any animals need to added or replaced , do that here
//		System.out.println("replenish");
//		System.out.println(Animal.Seal.getNumberOfSpecies());
//		System.out.println(Animal.Fish.getNumberOfSpecies());
		replenishWildLife();
	}
	
	
	public void replenishWildLife(){
		for(Animal animal : Animal.values()){
			while(animal.getNumberOfSpecies() < animal.getINTENDED_SPECIES_COUNT()){
				addAnimal(animal);
			}
		}
	}

	public void addAnimal(Animal animalFlyweightType){
		animalFlyweightType.setNumberOfSpecies(animalFlyweightType.getNumberOfSpecies() + 1);
		AnimalEntity entity = new AnimalEntity(animalFlyweightType,
				new Vector2D(rand.nextDouble()*WIDTH, rand.nextDouble()* DEPTH));
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
		return null;
		
	}
	public double getDEPTH() {
		return DEPTH;
	}

	public double getWIDTH() {
		return WIDTH;
	}

	
	


}
