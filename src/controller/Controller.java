package controller;

import java.awt.Color;

import javax.swing.JFrame;

import model.Animal;
import model.AnimalEntity;
import model.WorldModel;
import toolbox.Mouse;
import toolbox.Vector2D;
import view.OptionsPanel;
import view.StartScreen;
import view.ViewingWindow;

/**
 * Found this on reddit, thought it was funny and true
 * 
 * 
 *  "we don't write object hierarchies to satisfy our inner Linnaeus."
 *  
 *  
 * @author chris
 *
 */
public class Controller {

	ViewingWindow window;
	OptionsPanel panel;
	WorldModel model;
	AssetLoader loader;
	Player player;
	public static int i = 0;
	public static GameMode GAME_MODE = GameMode.FIXED_POPULATION;
	
	public Controller(){
		model = new WorldModel();
		loader = AssetLoader.getAssetLoader();
		
	}
	
	 //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	StartScreen s = new StartScreen();

		while (i == 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		s.startProgressScreen();
		
    	//Controller controller = new Controller();
    	

		
//    	Controller controller = new Controller();
//    	
//
//    	controller.intializeWorld();
//    	controller.initGame();
//    	controller.run();
    	
//    	//Load the Start Screen
//    	StartScreen s = new StartScreen();
//
//		while (i == 0) {
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		s.startProgressScreen();
		
    

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
    	AnimalEntity playerAnimal = new AnimalEntity(Animal.Shrimp,
    			//new Vector2D(model.getWIDTH()/2,model.getDEPTH() - 5)
    			new Vector2D(2,4));
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
    		
    		if(player.hasWon() || player.hasLost()){
    			System.out.println("Player has: " + player.hasWon);
    			break;
    		}
    		//THIS IS  horrible loop stratgey, the absolute worst
    		//TODO: rewrite a proper loop that counts elapsed time....
    		try {
				Thread.sleep(50); //swing blows and isn't for games!!!!!
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
    	}
    	System.out.println("open options");
    	//Outside loop, either lost, or won, or will eventually add, paused the game
    	openOptions(player.hasWon(), player.hasLost());
    	
    	
    }
	
    private void openOptions(boolean won, boolean lost){
    	//for now jsut restart
    	playAgainCallBack();

    	
//    	panel = new OptionsPanel(this, won, lost);
//    	this.window.getFrame().getContentPane().add(panel);
    }
    
    public void playAgainCallBack(){
    	for(Animal animal : Animal.values()){
    		animal.setNumberOfSpecies(0);
    	}
    	model = new WorldModel();
    	initGame();
    	run();
    }
    
    public void exitCallBack(){
    	// TODO write options
    }

	public void continueCallBack() {
		// TODO Auto-generated method stub
		
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
