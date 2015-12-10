package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;

public class ViewingWindow {

	
    final static int frameWidth = 1000;
    final static int frameHeight = 600;
    
    JFrame frame;

	ViewingPanel panel;
//	OptionsPanel optionPanel;
//	JLayeredPane layeredPane;
    
    public void initializeWindow(){
    	frame = new JFrame();

//    	layeredPane = new LayeredPane
    	panel = new ViewingPanel(frame);
//    	optionPanel = new OptionsPanel();
    	
    	frame.getContentPane().add(panel);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.setVisible(true);
//    	panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressed");
//    	panel.getActionMap().put("pressed", pressedAction );
    	
//    	layeredPane.add(panel);
//    	layeredPane.add(optionPanel);
    }
    
    
    public JFrame getFrame() {
		return frame;
	}

	public ViewingPanel getPanel() {
		return panel;
	}
    
    
    
    
}
