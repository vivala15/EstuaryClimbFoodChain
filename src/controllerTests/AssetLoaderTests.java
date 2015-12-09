package controllerTests;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.AssetLoader;
import view.ViewingPanel;

public class AssetLoaderTests {
	
	// Not sure how to test these.
	
	static AssetLoader tester;
	
	@BeforeClass
	public static void setUp(){
		tester = AssetLoader.getAssetLoader();
	}
	@Test
	public void testLoadAnimalAnimations() {

	}
	@Test
	public void testLoadBackground(){
		//tester.loadBackground(new ViewingPanel(new JFrame()));
	}
}
