package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import toolbox.Vector2D;

public class AnimalNeighborhood implements Iterator<AnimalEntity>{

	//HashMap for fast lookup when map densely populated
	private HashMap<Integer,List<AnimalEntity>> creatureEntities = new HashMap<Integer,List<AnimalEntity>>();
	private ArrayList<AnimalEntity> toBeRemoved = new ArrayList<AnimalEntity>();
	private final int numberOfBuckets = 10;
	private int entityCursor = 0;
	private int bucketCursor = 0;
	private double width;
	
	public AnimalNeighborhood(double width){
		this.width = width;
		for(int i=0; i < numberOfBuckets; i++){
			creatureEntities.put(i, new ArrayList<AnimalEntity>());
		}
	}

	@Override
	public boolean hasNext() {
		boolean hasNext = false;
		//!this.creatureEntities.get(bucketCursor).isEmpty() && 
		if(bucketCursor >= this.numberOfBuckets){
			hasNext = false;
			entityCursor = 0;
			bucketCursor = 0;
		}
		else if(entityCursor < this.creatureEntities.get(bucketCursor).size()){
			hasNext = true;
		}else{
			//has a next if there is a further non empty bucket
			if(nextNonEmptyBucket(bucketCursor) < numberOfBuckets){
				hasNext = true;
			}else{
				hasNext = false;
				//rest iterator
				entityCursor = 0;
				bucketCursor = 0;
			}
		}
		return hasNext;
	}

	public int nextNonEmptyBucket(int bucketNumber){
		while(creatureEntities.get(bucketNumber%numberOfBuckets).isEmpty() && bucketNumber < numberOfBuckets){
			bucketNumber++;
		}
		return bucketNumber;
	}
	
	@Override
	public AnimalEntity next() {
		List<AnimalEntity> bucket = creatureEntities.get(bucketCursor%numberOfBuckets);
		//weird bug, not sure what's causing it but this should fix
//		if(entityCursor >= bucket.size()){
//			
//		}
		AnimalEntity animal = null;
		try{
			animal = bucket.get(entityCursor);
		}catch(Exception e){
			System.out.println("Entity Cursor: " + entityCursor);
			System.out.println("Bucket Cursor: " + bucketCursor);
			System.out.println("BucketNum: " + bucketCursor%numberOfBuckets);
			e.printStackTrace();
		}
		entityCursor++;
		if(entityCursor >= bucket.size()){
			bucketCursor++;
			//call this because if current bucket has contents it will return the same
			bucketCursor = nextNonEmptyBucket(bucketCursor);
			entityCursor = 0;
		}
		return animal;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	public void addAnimal(AnimalEntity entity) {
		double x = entity.getPosition().getX();
		int bucket = (int) ( x/this.width * this.numberOfBuckets);
		//System.out.println("place in bucket..." + bucket);
		this.creatureEntities.get(bucket%this.numberOfBuckets).add(entity);
	}
	
	/**
	 * Bucket info eventually becomes outdated and must be updated...
	 */
	public void updateNeighborhood(){
		List<AnimalEntity> holdingCell = new ArrayList<AnimalEntity>();
		
		//add all entities to cell
		for(int key: creatureEntities.keySet()){
			for(AnimalEntity entity: creatureEntities.get(key)){
				holdingCell.add(entity);
			}
		}
		//clear the neighborhood
		for(int key: creatureEntities.keySet()){
			creatureEntities.get(key).clear();
		}
		//add them back
		for(AnimalEntity entity: holdingCell){
			addAnimal(entity);
		}
	}

	/**
	 * This is called throughout iteration of the neighborhood as a result can't remove
	 * them while iterating as that will break the iterator, so mark ones for removal and 
	 * call that at end of step
	 * @param prey
	 */
	public void removeAnimal(AnimalEntity prey) {
		System.out.println("remove animal called");
		toBeRemoved.add(prey);
	}
	
	/**
	 * Removes from neighborhood, because neighborhood is updated every turn... the prey
	 * should be in the proper bucket, if not search whole grid as fail safe but trigger
	 * an error message
	 * @param prey
	 */
	public void executeRemoveAnimals(){
		for (AnimalEntity prey : toBeRemoved) {
			int bucket = Math.min(Math.max(0, (int) (prey.getPosition().getX() / this.width * this.numberOfBuckets)),
					9);
			boolean removed = this.creatureEntities.get(bucket).remove(prey);
			if (!removed) {
				System.err.println("ANIMAL TO BE REMOVED WAS NOT IN ITS PROPER BUCKET!!!");
				System.err.println("This may correspond to severe logic bugs, address this");
			}
		}
		toBeRemoved.clear();
	}
	
	/**
	 * I would just like to say I am especially ashamed of this method and its poor
	 * coding quality, actually most of this class for that matter, there are so
	 * many better and safer ways to carry out these operations
	 * @param position
	 * @param rangeVector2D
	 * @return
	 */
	public LinkedList<AnimalEntity> getNearAnimals(Vector2D position, double rangeVector2D ){
		//determine bucket of interest -- pushing result to 0 or 10 if under or over
		int centerBucket = Math.min(Math.max(0,(int) (position.getX()/ this.width * this.numberOfBuckets)),9);
		//lazy evaluation, for now return the contents of this bucket and the two neigbors
		//even lazier memory managment, create new lists, LOL THIS IS HORRIBLE
		LinkedList<AnimalEntity> nearbyAnimals = new LinkedList<>();
		//i'm so lazy
		//System.out.println("Center Bucket" + centerBucket);
		if(centerBucket > 1){
			nearbyAnimals.addAll(this.creatureEntities.get(centerBucket-1));
		}
		nearbyAnimals.addAll(this.creatureEntities.get(centerBucket));
		//so sloppy
		if(centerBucket < this.numberOfBuckets -1){
			nearbyAnimals.addAll(this.creatureEntities.get(centerBucket+1));
		}
		return nearbyAnimals;
	}
	
}
