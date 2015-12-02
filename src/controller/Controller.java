package controller;

import java.awt.Color;

import javax.swing.JFrame;

import model.Animal;
import model.AnimalEntity;
import model.WorldModel;
import toolbox.Mouse;
import toolbox.Vector2D;
import view.ViewingWindow;


public class Controller {

	ViewingWindow window;
	WorldModel model;
	AssetLoader loader;
	Player player;
	
	public Controller(){
		model = new WorldModel();
		loader = AssetLoader.getAssetLoader();
		
	}
	
	 //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	Controller controller = new Controller();
    	

    	controller.intializeWorld();
    	controller.initGame();
    	controller.run();
    
    	
    }
    
    public void intializeWorld(){
    	window = new ViewingWindow();
    	window.initializeWindow();
    	loader.loadAnimalAnimations();
    	window.getPanel().setWorldHeight(model.getDEPTH());
    	window.getPanel().setWorldWidth(model.getWIDTH());
    	//setting background requires knowlegge of world height so call after
    	loader.loadBackground(window.getPanel());
    }
    
    /**
     * Set level specific features such as player 
     */
    public void initGame(){
    	AnimalEntity playerAnimal = new AnimalEntity(Animal.Shrimp, new Vector2D(1,2));
    	player = new Player(playerAnimal, new Mouse(window));
    	window.getPanel().setPlayer(player);
    	model.addPlayerAnimal(playerAnimal);
    	
    	
    }
    
    
    public void run(){
    	
    	/**
    	 * Loop broken on exit, may have to add a button for full screen mode
    	 */
    	while(true){
    		player.readInput();
    		
    		model.takeStep();
    		
    		drawEntities();
    		
    		window.getFrame().repaint();
    		
    		try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
  
    }
	
    public  void drawEntities(){
//    	int count = 0;
    	for(AnimalEntity animal : model){
    		//damn this worked when they were all in the first bucket lol
//    		System.out.println(count);
//    		count++;
    		window.getPanel().drawEntity(animal);
    	}
    }
	
	
}
