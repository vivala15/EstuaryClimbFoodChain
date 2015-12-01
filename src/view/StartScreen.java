package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.thehowtotutorial.splashscreen.JSplash;

import controller.Controller;


public class StartScreen extends JFrame {

	public StartScreen() {
		setTitle("Estuary Explorer");
		setSize(1024, 683);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/start.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JButton start, restart, highscore;

		start = new JButton("Start");
		start.setBounds(600, 200, 300, 80);
		start.setText("Start Game");
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		start.setFont(new Font("Stencil", Font.PLAIN, 35));
		start.setForeground(Color.MAGENTA);

		restart = new JButton("Restart");
		restart.setText("Restart Game");
		restart.setBounds(500, 280, 300, 80);
		restart.setText("Restart");
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		restart.setFont(new Font("Stencil", Font.PLAIN, 35));
		restart.setForeground(Color.CYAN);

		highscore = new JButton("High Score");
		highscore.setText("High Score");
		highscore.setBounds(400, 360, 300, 80);
		highscore.setText("High Score");
		highscore.setOpaque(false);
		highscore.setContentAreaFilled(false);
		highscore.setBorderPainted(false);
		highscore.setFont(new Font("Stencil", Font.PLAIN, 35));
		highscore.setForeground(Color.LIGHT_GRAY);

		getContentPane().add(start);
		getContentPane().add(restart);
		getContentPane().add(highscore);

		pack();
		setVisible(true);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.i = 1;
				
			}
		});

	}

	public void startProgressScreen() {
		
		setVisible(false);

		JSplash splash = new JSplash(this.getClass().getResource("splash.png"), true, true, false, "Ver. 0.0.1", null, Color.white, Color.CYAN);

		splash.setBounds(300, 200, 512, 356);
		splash.splashOn();
		try {
			
			//Shorted for more effective testing
			splash.setProgress(10, "Starting");
			Thread.sleep(50);

			splash.setProgress(30, "Starting");
			Thread.sleep(75);

			splash.setProgress(60, "Loading");
			Thread.sleep(50);

			splash.setProgress(90, "Finishing");
			Thread.sleep(50);

			splash.setProgress(99, "Finishing");
			Thread.sleep(20);
			
			splash.splashOff();
			
			Controller controller = new Controller();
	    	

	    	controller.intializeWorld();
	    	controller.initGame();
	    	controller.run();

		} catch (InterruptedException ee1) {
			ee1.printStackTrace();
		}

	}

	/*
	 * try { Thread.sleep(5000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 */
}
