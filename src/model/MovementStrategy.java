package model;

public interface MovementStrategy {
	
	/**
	 * Different strategies implement this interface which is used to update the direction
	 * of animal based on some criteria specific to the strategy
	 * @param animalEntity entity to have its direction updated 
	 * @param model world model for surroudning info
	 */
	public void setMove(AnimalEntity animalEntity, WorldModel model);

}
