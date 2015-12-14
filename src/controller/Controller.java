package controller;

import java.awt.event.WindowEvent;

import model.Animal;
import model.AnimalEntity;
import model.WorldModel;
import toolbox.Mouse;
import toolbox.Vector2D;
import view.OptionsPanel;
import view.StartScreen;
import view.ViewingWindow;

/**
 * Controller class acts as controller for game keeping the game loop running and holding 
 * references to both model and view entities. Used to initiate a game/pause/continue or
 * bring up options 
 * 
 *
 */
public class Controller {

	//Custom class to hold jFrame and other data
	ViewingWindow window;
	//The physical model
	WorldModel model;
	//Loader
	AssetLoader loader;
	Player player;
	//If options Open draw option screen
	private boolean optionsOpen = false; 
	public static int i = 0;
	//Sets way game logic updates, currently only Fixed_pop works, other 
	//dynamic population allows feedback and populations adjust such that animals
	//reproduce at certain food levels and are not immediately replaced if they die
	public static GameMode GAME_MODE = GameMode.FIXED_POPULATION;
	
	/**
	 * Initiates a world model and sets internally the loader to the globally available
	 */
	public Controller(){
		model = new WorldModel();
		loader = AssetLoader.getAssetLoader();
		
	}
	
	/**
	 * Main entrance... opens start screen
	 * @param args
	 */
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

    }
    
    /**
     * Initialize world by opening a frame object setting relevant data and loading
     * in the animaions and background
     */
    public void intializeWorld(){
    	window = new ViewingWindow();
    	window.initializeWindow();
    	loader.loadAnimalAnimations();
    	//Sets the world height and depth, not the computer pixel values!
    	window.getPanel().setWorldHeight(model.getDEPTH());
    	window.getPanel().setWorldWidth(model.getWIDTH());
    	//setting background requires knowlegge of world height so call after
    	loader.loadBackground(window.getPanel());
    }
    
    /**
     * Set level specific features such as player and adds it to the view for input
     * reading and the model
     */
    public void initGame(){
    	AnimalEntity playerAnimal = new AnimalEntity(Animal.Shrimp,
    			new Vector2D(2,11));
    	player = new Player(playerAnimal, new Mouse(window));
    	window.getPanel().setPlayer(player);
    	model.addPlayerAnimal(playerAnimal);
    	
    	
    }
    
    /**
     * Main game loop, reads input, steps through model, calls draw for the view,
     * and checks relevant win/pause conditions
     */
    public void run(){
    	
    	boolean modelRunning = true;
    	/**
    	 * Loop broken on exit, may have to add a button for full screen mode
    	 */
    	while(true){
    		player.readInput();
    		
    		if ( modelRunning){
    			model.takeStep();
    		}
    		
    		drawEntities();
    		
    		window.getFrame().repaint();
    		
    		if(player.hasWon() || player.hasLost() || player.hasPaused() && modelRunning){
    			System.out.println("Player has: " + player.hasWon);
    			openOptions(player.hasWon(), player.hasLost());
    			modelRunning = false;
    			optionsOpen = true;
    		}
    		if(optionsOpen){
    			//check player
    			if(player.getRestart()){
    				//breaks out of loop to reach playAgainMethod
    				break;
    			}else if(player.getExit()){
    				this.exitCallBack();
    			}else if(player.getCont()){
    				System.out.println("Model should run now");
    				modelRunning = true;
    				//reset values, should probably do this in above two branches
    				player.pause = false;
    				player.hasLost = false;
    				player.hasWon = false;
    				player.cont = false;
    				player.exit = false;
    				player.restart = false;
    				optionsOpen = false;
    			}
    		}
    		//THIS IS  horrible loop stratgey, the absolute worst
    		//TODO: rewrite a proper loop that counts elapsed time....
    		try {
				Thread.sleep(50); //swing blows and isn't for games!!!!!
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
    	}
    	//if get here probably restarting... only other things are exit OR CONItinue
    	//both of which canbe called in the loop
    	playAgainCallBack();
    	
    	
    }
	
    
    /**
     * Open options using win lost booleans to determine which string to post
     * @param won has player won?
     * @param lost has player lost?
     */
    private void openOptions(boolean won, boolean lost){
    	//System.out.println("open options");
    	this.window.getPanel().setOptionsView(this, won ,lost);
    	
    }
    
    /**
     * Restart game
     */
    public void playAgainCallBack(){
    	//empty animal counters
    	for(Animal animal : Animal.values()){
    		animal.setNumberOfSpecies(0);
    	}
    	//create new world
    	model = new WorldModel();
    	//set initial game conditions
    	initGame();
    	//call run
    	run();
    }
    
    /**
     * Exit game by calling close on the main window
     */
    public void exitCallBack(){
    	this.window.getFrame().dispatchEvent(new WindowEvent(window.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Continue game - does nothing now because booleans statemetns set in main game loop
     * should probably be moved into this method for modularity.... oh well
     */
	public void continueCallBack() {
		// TODO Auto-generated method stub
		
	}
    
	/**
	 * Iterate through animals in model and call draw for each in the view
	 */
    public  void drawEntities(){
    	for(AnimalEntity animal : model){
    		window.getPanel().drawEntity(animal);
    	}
    }

	
	
}
