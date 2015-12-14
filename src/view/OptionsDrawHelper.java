package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import controller.Player;
import toolbox.Vector2D;

/**
 * 
 * Draws the option menu and checks when clicks whether they selected a button or not
 * 
 * can't use swing and it sucks so going hard old school ... this is going to be very very
 * hacky
 * 
 *
 */
public class OptionsDrawHelper {

	public boolean OPEN_OPTIONS_MENU = false;
	private Controller controller;
	private String optionDialog = "LOREM EPSUM YAH LATIN LATIN WORDS DO THE THING";
	private JFrame frame;
	private int frameWidth;
	private int frameHeight;
	
	private int playAgainPosXOff = -230;
	private int playAgainPosYOff = -20;
	private int playAgainWidth = 140;
	private int playAgainHeight = 50;
	
	private int exitPositionXOff = 100;
	private int exitPositionYOff = -20;
	private int exitWidth = 100;
	private int exitHeight = 50;
	
	private Player player;
	
	
	public OptionsDrawHelper(JFrame frame){
		this.frame = frame;
		
	}
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	 * If painting turned on then draw the buttons and options meunu
	 * @param g
	 */
	public void paintOptions(Graphics g){
		
		
	    if(OPEN_OPTIONS_MENU){
	    	paintBackground(g);
	    	drawPlayAgainExit(g);
	    }

	    
		
	}
	
	/**
	 * Colors background of menu
	 * @param g
	 */
	private void paintBackground(Graphics g){
		
		g.setColor(Color.decode("#0D47A1"));
		g.fillRoundRect(frame.getWidth()/2 - 250, frame.getHeight()/2 - 200, 500, 260, 50, 50);
		
		
		Graphics2D g2 = (Graphics2D)g;
	        
//	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//	            RenderingHints.VALUE_ANTIALIAS_ON);
	    Font font = new Font("Serif", Font.PLAIN, 20);
	    g2.setFont(font);

	    g.setColor(Color.decode("#FFD54F"));
	    g2.drawString(optionDialog, frame.getWidth()/2 - 230, frame.getHeight()/2 - 170); 
	}
	
	/**
	 * Colors in the buttons
	 * @param g
	 */
	private void drawPlayAgainExit(Graphics g){
		g.setColor(Color.decode("#1565C0"));
		g.fillRoundRect(frame.getWidth()/2 + playAgainPosXOff, frame.getHeight()/2 +playAgainPosYOff ,
				playAgainWidth, playAgainHeight, 10, 10);
		
		g.fillRoundRect(frame.getWidth()/2 +exitPositionXOff, frame.getHeight()/2 + exitPositionYOff ,
				exitWidth, exitHeight, 10, 10);
		
		
		Graphics2D g2 = (Graphics2D)g;
		Font font = new Font("Serif", Font.PLAIN, 14);
		g.setColor(Color.ORANGE);
		 
		g2.drawString("Play Again?", frame.getWidth()/2 + playAgainPosXOff  + 10,
				frame.getHeight()/2 +playAgainPosYOff + 35); 
		
		g2.drawString("Exit", frame.getWidth()/2 + exitPositionXOff  + 10,
				frame.getHeight()/2 +exitPositionYOff + 35); 
		
	}

	/**
	 * inside the playagain button?
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean playAgainContains(int x, int y){
		if (frame.getWidth()/2 + playAgainPosXOff + playAgainWidth >x &&
				x  > frame.getWidth()/2 + playAgainPosXOff &&
				frame.getHeight()/2 +playAgainPosYOff  + playAgainHeight > y &&
				 y > frame.getHeight()/2 +playAgainPosYOff){
			return true;
		}
		return false;
	}
	
	/**
	 * inside the exit button?
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean exitContains(int x, int y){
		if (frame.getWidth()/2 +exitPositionXOff + exitWidth >x &&
				x  >frame.getWidth()/2 +exitPositionXOff &&
				frame.getHeight()/2 + exitPositionYOff  + exitHeight > y &&
				 y > frame.getHeight()/2 + exitPositionYOff){
			return true;
		}
		return false;
	}

	/**
	 * sets open options to true and the dialogue accordingly
	 * @param controller
	 * @param won
	 * @param lost
	 */
	public void openStopOption(Controller controller, boolean won, boolean lost) {
		this.controller = controller;
		setDialogueText(won,lost);
		OPEN_OPTIONS_MENU = true;
	}
	/**
	 * set dialogue based on whether person has won lost, or neither and just paused
	 * @param won
	 * @param lost
	 */
	private void setDialogueText(boolean won, boolean lost){
		if(won){
			optionDialog = "Congrats - you Won!";
		}else if(lost){
			optionDialog = "Better luck next time - play again?";
		}else{//just a paused game
			optionDialog = "Paused";
		}
		
	}
	
	/**
	 * read in mouse clickc coordinates and set if hit any of the buttons
	 * @param mouseClickLoc
	 */
	protected void receiveOnClickData(Vector2D mouseClickLoc){
		System.out.println(mouseClickLoc);
		System.out.println("Recieved on click");
		if(playAgainContains((int)mouseClickLoc.getX(),(int)mouseClickLoc.getY())){
			System.out.println("Hit play again");
			//play again
			player.restart = true;
		}else if(exitContains((int)mouseClickLoc.getX(),(int)mouseClickLoc.getY())){
			System.out.println("hit exit");
			//exit
			player.exit = true;
		}else{
			System.out.println("hit nothing so continue");
			//continue
			player.cont = true;
			
		}
		OPEN_OPTIONS_MENU = false;
	}
}
