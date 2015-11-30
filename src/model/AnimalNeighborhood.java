package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AnimalNeighborhood implements Iterator<AnimalEntity>{

	//HashMap for fast lookup when map densely populated
	private HashMap<Integer,List<AnimalEntity>> creatureEntities = new HashMap<Integer,List<AnimalEntity>>();
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
		if(entityCursor < this.creatureEntities.get(bucketCursor).size()){
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
		AnimalEntity animal = bucket.get(entityCursor);
		entityCursor++;
		if(entityCursor >= bucket.size()){
			bucketCursor++;
			entityCursor = 0;
		}
		return animal;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	public void addAnimal(AnimalEntity entity) {
		double x = entity.getDirection().getX();
		int bucket = (int) ( x/this.width * this.numberOfBuckets);
		this.creatureEntities.get(bucket).add(entity);
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
	
	
}
