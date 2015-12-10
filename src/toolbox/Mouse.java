package toolbox;

import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;

import view.ViewingWindow;

/**
 * Because making a common sense moue is just too much for swing despite all the 
 * other shit they think they should automatically create
 * @author chris
 *
 */
public class Mouse {

	JFrame frame;
	private double worldToPixel;
	
	private Vector2D cameraShift = new Vector2D();
	
	public Mouse(ViewingWindow window){
		this.frame = window.getFrame();
		this.worldToPixel = window.getPanel().getWORLD_TO_PIXEL();
	}
	
	public void setCameraShift(Vector2D cameraShift){
		this.cameraShift = cameraShift;
	}
	
	public Vector2D getMousePointerInWorldCoord(){
		
		Point pointer = MouseInfo.getPointerInfo().getLocation();
		return new Vector2D((pointer.getX() - frame.getX() )/ this.worldToPixel - cameraShift.getX(),
							(pointer.getY() - frame.getY() )/this.worldToPixel - cameraShift.getY());
	}
	
}
