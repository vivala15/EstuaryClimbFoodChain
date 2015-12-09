package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Player;
import model.AnimalEntity;
import toolbox.Maths;
import toolbox.Vector2D;

public class ViewingPanel extends JPanel{

	private double WORLD_TO_PIXEL = 100; 
//	private double worldEdgeViewH = 0;
//	private double worldEdgeViewV = 1;
	
	//world width and height in world coords not pixels
	private double worldWidth;
	private double worldHeight;
	
	private Player player;
	private Vector2D cameraShift = new Vector2D();
	
	private JFrame frame;
	
	private BufferedImage backgroundImage;
	private int numberBackgroundTiles;
	
	public ViewingPanel(JFrame frame){
		this.frame = frame;
	}
	

	public void setPlayer(Player player){
		this.player = player;
	}

	private void updateCameraShift(){
		Vector2D x = this.player.getPlayerEntity().getPosition();
		Vector2D y = player.getMouse().getMousePointerInWorldCoord();
		Vector2D midpoint = Maths.weightedMid(x,
				y, .8);

		double playerPointX = midpoint.getX() ;
		double playerPointY = midpoint.getY() ;
		
		if(playerPointX  - frame.getWidth()/this.WORLD_TO_PIXEL/2.0< 0){
			cameraShift.setX(0);
		}else if(playerPointX + frame.getWidth()/this.WORLD_TO_PIXEL/2.0> this.worldWidth){
			cameraShift.setX(-this.worldWidth + frame.getWidth()/this.WORLD_TO_PIXEL );
		}else{
			cameraShift.setX(-midpoint.getX()  + frame.getWidth()/this.WORLD_TO_PIXEL/2.0);
		}
		
		if(playerPointY - frame.getHeight()/this.WORLD_TO_PIXEL/2.0< 0){
			cameraShift.setY(0);
		}else if(playerPointY +frame.getHeight()/this.WORLD_TO_PIXEL/2.0 > this.worldHeight){
			cameraShift.setY(-this.worldHeight + frame.getHeight()/this.WORLD_TO_PIXEL );
		}else{
		cameraShift.setY(-midpoint.getY() + frame.getHeight()/this.WORLD_TO_PIXEL/2.0);
		}
		//for debugging fixed world and camera
//		cameraShift.setX(0);
//		cameraShift.setY(0);
		
		this.player.getMouse().setCameraShift(cameraShift);
	
	}


	private ArrayList<AnimalEntity> toBeDrawnAnimals = new ArrayList<AnimalEntity> ();
	
	public void drawEntity(AnimalEntity entity){
		toBeDrawnAnimals.add(entity);
	}
	
	//Override this JPanel's paint method to cycle through picture array and draw images
	/**
	 * 
	 */
	@Override
    public void paint(Graphics g) {
		updateCameraShift();

		
		//DrawBackground
		for(int i =0 ; i < this.numberBackgroundTiles; i++){
			
			g.drawImage(this.backgroundImage, (int) (cameraShift.getX() * this.WORLD_TO_PIXEL+ this.backgroundImage.getWidth()*i),
					(int)(cameraShift.getY()*this.WORLD_TO_PIXEL), this);
	
		}
		
		
		//Draw Entities
//		System.out.println("Drawing number entities: " + toBeDrawnAnimals.size());
		for(AnimalEntity entity : toBeDrawnAnimals ){
//			System.out.println((int)entity.getPosition().getY());
			//below if check of distance to only draw nearby bitmaps in event
			//that it would allow a pickup in loop, but it didn't, swing sucks.
			//no it doens't suck but isn't meant for this
//			if(Math.abs(entity.getPosition().getX() + cameraShift.getX()) < 12 && 
//					Math.abs(entity.getPosition().getY() + cameraShift.getY()) < 12){
			
				g.drawImage(entity.getDrawable(),
						(int)((entity.getPosition().getX()+cameraShift.getX())*this.WORLD_TO_PIXEL /*+frame.getWidth()/2.0 */),
						(int)((entity.getPosition().getY()+cameraShift.getY())*this.WORLD_TO_PIXEL /* +frame.getHeight()/2.0*/),
						this);
				

			//}
		}


		drawGui(g);
		//debug draw mouse  and draw world box
//		Vector2D debugPointer = this.player.getMouse().getMousePointerInWorldCoord();
//		 g.setColor(Color.RED);
//		g.drawRect((int)((debugPointer.getX()+cameraShift.getX())*this.WORLD_TO_PIXEL),
//				(int)((debugPointer.getY()+cameraShift.getY())*this.WORLD_TO_PIXEL),
//						100,
//						100);
//		
//		g.drawRect((int)(cameraShift.getX()*this.WORLD_TO_PIXEL),
//				(int)(cameraShift.getY()*this.WORLD_TO_PIXEL),
//				(int)(this.getWorldWidth()*this.WORLD_TO_PIXEL),
//				(int)(this.getWorldHeight()*this.WORLD_TO_PIXEL));
		
		
		
		toBeDrawnAnimals.clear();
    }
	
	public void drawGui(Graphics g){
		//food level 
		//backdrop
		g.setColor(Color.BLACK);
		g.fillRoundRect(50, 50, 250, 20, 10, 10);
		
		//food level
		//hsb, hue range 0 (red) - 125(green)
		//satu at 95
		//brightness 55
		//this.player.getPlayerEntity().
		//food bar
		double percentFullFood = this.player.getPlayerEntity().getFoodLevel()/ this.player.getPlayerEntity().getLevelUpFood();
		//System.out.println(percentFullFood);
		g.setColor(Color.getHSBColor((float) (125 *percentFullFood)/360f , .95f, .55f));
		g.fillRoundRect(55, 55, (int) (230 * percentFullFood), 10, 10, 10);
		
		 Graphics2D g2 = (Graphics2D)g;
	        
//	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//	            RenderingHints.VALUE_ANTIALIAS_ON);
	        Font font = new Font("Serif", Font.PLAIN, 20);
	        g2.setFont(font);

	        g2.drawString("Food Level", 50, 40); 
		
	}
	
	
	public double getWORLD_TO_PIXEL() {
		return WORLD_TO_PIXEL;
	}

	public void setWORLD_TO_PIXEL(double wORLD_TO_PIXEL) {
		WORLD_TO_PIXEL = wORLD_TO_PIXEL;
	}
	
//	public double getWorldEdgeViewH() {
//		return worldEdgeViewH;
//	}
//
//
//	public void setWorldEdgeViewH(double worldEdgeViewH) {
//		this.worldEdgeViewH = worldEdgeViewH;
//	}
//
//
//	public double getWorldEdgeViewV() {
//		return worldEdgeViewV;
//	}
//
//
//	public void setWorldEdgeViewV(double worldEdgeViewV) {
//		this.worldEdgeViewV = worldEdgeViewV;
//	}

	public double getWorldWidth() {
		return worldWidth;
	}


	public void setWorldWidth(double worldWidth) {
		this.worldWidth = worldWidth;
	}


	public double getWorldHeight() {
		return worldHeight;
	}


	public void setWorldHeight(double worldHeight) {
		this.worldHeight = worldHeight;
	}

	public void setBackgroundImage(BufferedImage seqImage) {
		//backgroundImage = seqImage;
		//int imageHeight = backgroundImage.getHeight();
		double scale = this.worldHeight*this.WORLD_TO_PIXEL / seqImage.getHeight();
//		System.out.println(scale);
//		System.out.println(this.worldHeight);
//		System.out.println(seqImage.getHeight());
		//Scale to appropriate size
		int w = (int) (scale*seqImage.getWidth());
		int h = (int) (scale*seqImage.getHeight());
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scale, scale);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(seqImage, after);
		System.out.println(after.getHeight());
		this.backgroundImage = after;
		this.numberBackgroundTiles = (int) (this.worldWidth*this.WORLD_TO_PIXEL/ this.backgroundImage.getWidth() + 1);
	
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1759142305649302050L;


}
