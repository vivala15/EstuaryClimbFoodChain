package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.Animal;
import view.ViewingPanel;

public class AssetLoader {
	
	
	private static AssetLoader assetLoader = new AssetLoader();
	private String animationFolder = "res/testing_animations/";
	
	public static AssetLoader getAssetLoader(){
		return assetLoader;
	}
	
	
	private AssetLoader(){
		
	}
	
	public void loadBackground(ViewingPanel viewingPanel){
		BufferedImage seqImage = createImage(this.animationFolder+"/background/estuaryBackground.png");
		viewingPanel.setBackgroundImage(seqImage);
	}
	
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
		} 
			 
	}
	
	//Read image from file and return
    private BufferedImage createImage(String fileLoc){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(fileLoc));
    		return bufferedImage;
    	} catch (IOException e) {
    		System.err.println("Could not read file: " + fileLoc);
    		e.printStackTrace();
    	}
    	return null;
    }

}
