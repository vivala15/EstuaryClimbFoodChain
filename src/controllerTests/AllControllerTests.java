package controllerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Player;
import model.Animal;
import model.AnimalEntity;
import toolbox.Mouse;
import toolbox.Vector2D;
import view.ViewingWindow;

public class AllControllerTests {

	@Test
	public void testAssetLoader() {
		
	}
	@Test
	public void testController() {
		
	}
	@Test
	public void testPlayer() {
		ViewingWindow view = new ViewingWindow();
		view.initializeWindow();
		view.getPanel().setWORLD_TO_PIXEL(100);
		Mouse mouse = new Mouse(view);
		AnimalEntity player = new AnimalEntity(Animal.Fish, new Vector2D(100,100));
		Player tester = new Player(player, mouse);
		
		assertEquals(mouse, tester.getMouse());
		assertEquals(player, tester.getPlayerEntity());
		tester.pauseRound();
		assertFalse(tester.hasLost());
		assertTrue(tester.hasPaused());
		assertFalse(tester.hasWon());
		
		tester.readInput();
		tester.getPlayerEntity().setLiving(false);
		tester.readInput();
		
		tester.getPlayerEntity().digestAnimal(new AnimalEntity(Animal.Shrimp, null));
		tester.getPlayerEntity().digestAnimal(new AnimalEntity(Animal.Shrimp, null));
		tester.getPlayerEntity().digestAnimal(new AnimalEntity(Animal.Shrimp, null));
		tester.getPlayerEntity().digestAnimal(new AnimalEntity(Animal.Shrimp, null));
		tester.getPlayerEntity().digestAnimal(new AnimalEntity(Animal.Shrimp, null));
		tester.readInput();	
	}
	
	@Test
	public void testPopulationSimulation() {
		
	}
}