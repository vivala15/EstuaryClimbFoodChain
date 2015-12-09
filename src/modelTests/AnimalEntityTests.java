package modelTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Animal;
import model.AnimalEntity;
import model.WorldModel;
import toolbox.Vector2D;

public class AnimalEntityTests {
	AnimalEntity tester;
	
	@Before
	public void setUp(){
		tester = new AnimalEntity(Animal.Fish, new Vector2D(100,100));
	}

	@Test
	public void testChangeAnimal() {
		tester.changeAnimal(Animal.Seal);
		assertTrue(tester.myFlyweight.equals(Animal.Seal));
	}
	@Test
	public void testTakeStep() {
		System.out.println(tester.getPosition().getX());
		tester.takeStep(2, new WorldModel());
		
		System.out.println(tester.getPosition().getX());
		System.out.println(tester.getPosition().getY());
// 		Why are these numbers different from test to test?
//		assertEquals(tester.getPosition().getX(), ....);
//		assertEquals(tester.getPosition().getY(), ....);
	}
	@Test
	public void testDigestAnimal() {
		tester.digestAnimal(new AnimalEntity(Animal.Shrimp, new Vector2D()));
//		System.out.println(tester.getTotalFoodConsumed());
//		System.out.println(tester.getFoodLevel());
		assertEquals(5, tester.getTotalFoodConsumed());
		assertEquals(5, tester.getFoodLevel());
		tester.digestAnimal(new AnimalEntity(Animal.Plankton, new Vector2D()));
		assertEquals(6, tester.getTotalFoodConsumed());
		assertEquals(6, tester.getFoodLevel());
	}

}
