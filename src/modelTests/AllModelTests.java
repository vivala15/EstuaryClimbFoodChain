package modelTests;

import static org.junit.Assert.*;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Test;

import model.Animal;
import model.AnimalEntity;
import model.AnimalNeighborhood;
import model.WorldModel;
import toolbox.Vector2D;

public class AllModelTests {

	
	@Test
	public void testAnimal() {
		Animal tester = Animal.Fish;
		ArrayList<Animal> prey = new ArrayList<Animal>();
		prey.add(Animal.Shrimp);
		ArrayList<Animal> predator = new ArrayList<Animal>();
		prey.add(Animal.Seal);
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		
		tester.setImgHeight(0);
		tester.setImgWidth(0);
		tester.setNumberOfSpecies(0);
		tester.setFoodValue(0);
		tester.setPreyList(prey);
		tester.setPredatorList(predator);
		tester.setAnimationSequence(images);
		tester.setFoodRepro(0);
		assertEquals(0, tester.getImgHeight(), 1);
		assertEquals(0, tester.getImgWidth(), 1);
		assertEquals(0, tester.getNumberOfSpecies(), 1);
		assertEquals(0, tester.getFoodValue(), 1);
		assertEquals(5, tester.getINTENDED_SPECIES_COUNT(), 1);
		assertEquals(prey, tester.getPreyList());
		assertEquals(images, tester.getAnimationSequence());
		assertEquals("fish", tester.getAnimationFolderName());
		assertEquals(predator, tester.getPredatorList());
		assertEquals(0, tester.getFoodRepro());
	}
	@Test
	public void testAnimalEntity() {
		WorldModel world = new WorldModel();
		
		AnimalEntity tester = new AnimalEntity(Animal.Plankton, new Vector2D(100,100));
		tester.takeStep(2, world);
		tester.setLiving(true);
		assertTrue(tester.isLiving());
		
		tester.changeAnimal(Animal.Shrimp);
		tester.takeStep(2, world);
		
		tester.changeAnimal(Animal.Fish);
		tester.takeStep(2, world);
		
		tester.changeAnimal(Animal.WadingBird);
		tester.takeStep(2, world);
		
		tester.changeAnimal(Animal.Oil);
		tester.takeStep(2, world);
		
		tester.changeAnimal(Animal.Bubble);
		tester.takeStep(2, world);
		
		tester.changeAnimal(Animal.Seal);
		assertTrue(tester.myFlyweight.equals(Animal.Seal));
			
		System.out.println(tester.getPosition().getX());
		tester.takeStep(2, world);
		tester.setPosition(new Vector2D(100,100));
		System.out.println(tester.getPosition().getX());
		System.out.println(tester.getPosition().getY());
		
		tester.digestAnimal(new AnimalEntity(Animal.Shrimp, new Vector2D()));
//		System.out.println(tester.getTotalFoodConsumed());
//		System.out.println(tester.getFoodLevel());
		assertEquals(5, tester.getTotalFoodConsumed());
		assertEquals(5, tester.getFoodLevel());
		tester.digestAnimal(new AnimalEntity(Animal.Plankton, new Vector2D()));
		assertEquals(6, tester.getTotalFoodConsumed());
		assertEquals(6, tester.getFoodLevel());
	}
	@Test
	public void testAnimalNeighborhood() {
		AnimalNeighborhood tester = new AnimalNeighborhood(0);
		AnimalEntity fish = new AnimalEntity(Animal.Fish, new Vector2D(100,100));
		tester.addAnimal(fish);
		tester.updateNeighborhood();
		assertTrue(tester.hasNext());
		assertEquals(null, tester.next());
		tester.removeAnimal(fish);
		tester.executeRemoveAnimals();
//		assertFalse(tester.hasNext());
	}
	@Test
	public void testCollisionHandler() {
		
	}
	@Test
	public void testWorldModel() {
		
	}
	

}
