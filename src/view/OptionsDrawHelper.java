package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;
import toolbox.Vector2D;

/**
 * I can't use swing and it sucks so going hard old school ... this is going to be very very
 * hacky
 * 
 * SUPER HASH TAG -- PRE MADE MENUS ARE FOR SQUARES --
 * @author chris
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
	
//	private Vector2D playAgainPosition = new Vector2D();
//	private int playAgainWidth = 100;
//	private int playAgainHeight = 100;
	
	public OptionsDrawHelper(JFrame frame){
		this.frame = frame;
		
	}
	
	
	public void paintOptions(Graphics g){
		
		
	    if(OPEN_OPTIONS_MENU){
	    	paintBackground(g);
	    	drawPlayAgainExit(g);
	    }

	    
		
	}
	
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

	
	private boolean playAgainContains(int x, int y){
		if (frame.getWidth()/2 + playAgainPosXOff + playAgainWidth >x &&
				x  > frame.getWidth()/2 + playAgainPosXOff &&
				frame.getHeight()/2 +playAgainPosYOff  + playAgainHeight > y &&
				 y > frame.getHeight()/2 +playAgainPosYOff){
			return true;
		}
		return false;
	}
	
	private boolean exitContains(int x, int y){
		if (frame.getWidth()/2 +exitPositionXOff + exitWidth >x &&
				x  >frame.getWidth()/2 +exitPositionXOff &&
				frame.getHeight()/2 + exitPositionYOff  + exitHeight > y &&
				 y > frame.getHeight()/2 + exitPositionYOff){
			return true;
		}
		return false;
	}


	public void openStopOption(Controller controller, boolean won, boolean lost) {
		this.controller = controller;
		setDialogueText(won,lost);
		OPEN_OPTIONS_MENU = true;
	}
	
	private void setDialogueText(boolean won, boolean lost){
		if(won){
			optionDialog = "Congrats - you Won!";
		}else if(lost){
			optionDialog = "Better luck next time - play again?";
		}else{//just a paused game
			optionDialog = "Paused";
		}
		
	}
	
	private void receiveOnClickData(Vector2D mouseClickLoc){
		if(playAgainContains(0,0)){
			//play again
			controller.playAgainCallBack();
		}else if(exitContains(0,0)){
			//exit
			
		}else{
			//continue
		}
	}
}
