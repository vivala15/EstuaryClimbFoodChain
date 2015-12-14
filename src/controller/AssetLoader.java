package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.Animal;
import view.ViewingPanel;


/**
 * Class to load in animation pngs with some helper methods
 * 
 *
 */
public class AssetLoader {
	
	//Create instance that can be assessible with a getter rather than pass around program
	private static AssetLoader assetLoader = new AssetLoader();
	//hardcoded to read from this folder
	private String animationFolder = "res/testing_animations/";
	
	
	public static AssetLoader getAssetLoader(){
		return assetLoader;
	}
	
	
	private AssetLoader(){
		
	}
	
	/**
	 * Loads background image and sets it as background in the viewing panel
	 * @param viewingPanel custom swing panel to have the given background
	 */
	public void loadBackground(ViewingPanel viewingPanel){
		BufferedImage seqImage = createImage(this.animationFolder+"/background/estuaryBackground.png");
		viewingPanel.setBackgroundImage(seqImage);
	}
	
	/**
	 * This method iterates the Animal enum and for each one loads in the animations for
	 * forward motion, backward motion, or death png if they exist. Tries both
	 * animation.png and animation[number] png given by a value in Animal. If image 
	 * is one png it is broken into the sequence using number in the enum
	 */
	public void loadAnimalAnimations(){
		for(Animal animal: Animal.values()){
			//Open folder and get image
			BufferedImage seqImage = createImage(this.animationFolder+animal.getAnimationFolderName()+ "/animation.png");
			//break image into animated components
			ArrayList<BufferedImage> pics = new ArrayList<BufferedImage>();
			if (seqImage != null) {
				int imgWidth = seqImage.getWidth()/animal.getMaxAnimationFrame();
				int imgHeight = seqImage.getHeight();
				
				animal.setImgHeight(imgHeight);
				animal.setImgWidth(imgWidth);
				//break animation into its sequence
				for (int i = 0; i < animal.getMaxAnimationFrame(); i++){
					pics.add(seqImage.getSubimage(imgWidth * i, 0, imgWidth, imgHeight));
				}
			}else{
				//failed to read in plan animation.png, check if numbered sequence
				for (int i = 0; i < animal.getMaxAnimationFrame(); i++){
					BufferedImage img = createImage(this.animationFolder+animal.getAnimationFolderName()+ "/animation"+Integer.toString(i)+".png");
					pics.add(img);
					int imgWidth = img.getWidth();
					int imgHeight = img.getHeight();
					animal.setImgHeight(imgHeight);
					animal.setImgWidth(imgWidth);
					
				}
				
			}
			animal.setAnimationSequence(pics);
			//backwards animations...
			
			//Open folder and get image
			BufferedImage backSeqImage = createImage(this.animationFolder+animal.getAnimationFolderName()+ "/re_animation.png");
			//break image into animated components
			ArrayList<BufferedImage> backPics = new ArrayList<BufferedImage>();
			if (seqImage != null) {
				int imgWidth = seqImage.getWidth()/animal.getMaxAnimationFrame();
				int imgHeight = seqImage.getHeight();
				
				animal.setImgHeight(imgHeight);
				animal.setImgWidth(imgWidth);
				//break up if all in one image animation.png
				for (int i = 0; i < animal.getMaxAnimationFrame(); i++){
					backPics.add(seqImage.getSubimage(imgWidth * i, 0, imgWidth, imgHeight));
				}
			}else{
				//failed to read in plan animation.png, check if numbered sequence
				for (int i = 0; i < animal.getMaxAnimationFrame(); i++){
					BufferedImage backImg = createImage(this.animationFolder+animal.getAnimationFolderName()+ "/re_animation"+Integer.toString(i)+".png");
					if(backImg == null){
						break;
					}
					backPics.add(backImg);
					int imgWidth = backImg.getWidth();
					int imgHeight = backImg.getHeight();
					animal.setImgHeight(imgHeight);
					animal.setImgWidth(imgWidth);
					
				}
				
			}
			//set the backward animation
			animal.setMovingBackAnimationSequence(backPics);
			
			
			
			
			//load dead animation - may be null, handled in Animal enum
			BufferedImage deadImage = createImage(this.animationFolder+animal.getAnimationFolderName()+ "/oil_anim.png");
			animal.setDeadDrawable(deadImage);
		} 
			 
	}
	
	//Read image from file and return
    private BufferedImage createImage(String fileLoc){
    	BufferedImage bufferedImage;
    	try {
    		//return bufferedImage if readable
    		bufferedImage = ImageIO.read(new File(fileLoc));
    		return bufferedImage;
    	} catch (IOException e) {
    		//print error stating could not read file but allow program to continue
    		//as this occurs for a number of file types that are checked but not expected
    		//to exist, in this case return null
    		System.err.println("Could not read file: " + fileLoc);
    	}
    	return null;
    }

}
