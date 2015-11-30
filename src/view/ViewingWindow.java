package view;

import java.awt.Color;

import javax.swing.JFrame;

public class ViewingWindow {

	
    final static int frameWidth = 1000;
    final static int frameHeight = 600;
    
    JFrame frame;

	ViewingPanel panel;
    
    public void initializeWindow(){
    	frame = new JFrame();
    	panel = new ViewingPanel(frame);
    	frame.getContentPane().add(panel);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    }
    
    
    public JFrame getFrame() {
		return frame;
	}

	public ViewingPanel getPanel() {
		return panel;
	}
    
    
    
    
}
