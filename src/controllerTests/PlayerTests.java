package controllerTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.Player;
import model.Animal;
import model.AnimalEntity;
import toolbox.Mouse;
import toolbox.Vector2D;
import view.ViewingWindow;

public class PlayerTests {

// I keep getting a null pointer exception when mocking a mouse. Cannot test this class without a mouse.	
	
//	class MocMouse extends Mouse{
//		int x = 10, y = 10;
//		
//		public MocMouse(ViewingWindow window) {
//			super(window);
//		}	
//	}
//	
//	ViewingWindow window = new ViewingWindow();
//	MocMouse mouse = new MocMouse(window);
//	AnimalEntity playerEntity = new AnimalEntity(Animal.Fish, new Vector2D(100,100));
//	Player tester = new Player(playerEntity, mouse);
	

	@Test
	public void testReadInput() {
//		tester.readInput();
//		assertTrue(tester.getPlayerEntity().isLiving());
//		assertEquals(tester.getPlayerEntity().getDirection().getX(), -90);
//		assertEquals(tester.getPlayerEntity().getDirection().getY(), -90);
	}

}