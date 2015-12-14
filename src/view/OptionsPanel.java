package view;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import controller.Controller;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


/**
 * 
 * Not used, just ended up drawing a custom menu
 * 
 * @deprecated
 *
 */
public class OptionsPanel extends JPanel{
	
	JLabel optionDialog;
	SpringLayout springLayout;
	JButton playAgain;
	JButton exit;
	JButton continueButton;
	Controller controller;
	
//	public OptionsPanel(Controller controller, boolean won, boolean lost) {
	public OptionsPanel(Controller controller) {

		boolean won = true;
		boolean lost = false;
		this.controller = controller;
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		setTextAndCallBack(won, lost);
		
	}
	
	public OptionsPanel() {

		boolean won = true;
		boolean lost = false;
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		setTextAndCallBack(won, lost);
		
	}
	
	public void setController(Controller cont){
		this.controller = cont;
	}
	
	public void setTextAndCallBack(boolean won, boolean lost){
		
		if(won){
			optionDialog = new JLabel("Congratulations - you made it to the top of the food chain");
		}else if(lost){
			optionDialog = new JLabel("Better luck next time - play again?");
		}else{//just a paused game
			optionDialog = new JLabel("Paused");
			continueButton = new JButton("Continue Playing");
			continueButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.continueCallBack();
			    	
				}
			});
			
		}
		playAgain = new JButton("Play Again?");
		exit = new JButton("Exit");
		
		
		playAgain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.continueCallBack();
				
			}
		});
		
		
		playAgain.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == 10){
					controller.continueCallBack();
					controller.intializeWorld();
			    	controller.initGame();
			    	controller.run();
				}
				
			}
		}
			
			);
			
		
			
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.exitCallBack();
			}
		});
		
		
		springLayout.putConstraint(SpringLayout.WEST, playAgain, 70, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, playAgain, -99, SpringLayout.SOUTH, this);
		add(playAgain);
		
		springLayout.putConstraint(SpringLayout.NORTH, optionDialog, 57, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, optionDialog, 169, SpringLayout.WEST, this);
		add(optionDialog);
		
		springLayout.putConstraint(SpringLayout.NORTH, exit, 0, SpringLayout.NORTH, playAgain);
		springLayout.putConstraint(SpringLayout.WEST, exit, 42, SpringLayout.EAST, playAgain);
		add(exit);
		
		
	}
}
