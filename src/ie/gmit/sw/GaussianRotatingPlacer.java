package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class GaussianRotatingPlacer implements Placeable {

	private int horizontalCentre = 0;
	private int verticalCentre = 0;
	
	private List<Rectangle> listOfPlacedWords = new ArrayList<Rectangle>();

	private BufferedImage image = null;
	private Graphics2D context = null;
	
	private Random rand = new Random();
	
	private Color [] colors = new Color[5];
	
	
	public GaussianRotatingPlacer() {
		colors[0] = new Color(182, 240, 255);
		colors[1] = new Color(171, 156, 232);
		colors[2] = new Color(255, 97, 189);
		colors[3] = new Color(232, 208, 193);
		colors[4] = new Color(255, 240, 169);
		image = new BufferedImage(1600, 1000, BufferedImage.TYPE_4BYTE_ABGR);
		context = image.createGraphics();
		context.setColor(Color.GRAY);
		context.fillRect(0, 0, 1600, 1000);
		horizontalCentre = 700;
		verticalCentre = 500;
	}

	public void placeString(WordFrequencyKeyValue word, Font font) {
		context.setColor(colors[Math.abs(rand.nextInt())%5]);
		int h = horizontalCentre;
        int v = verticalCentre;
		int k = 1;
		boolean rotated = (rand.nextInt()%5 == 0) ? true : false;
		AffineTransform saveTransform=context.getTransform();
		
		AffineTransform affineTransform = context.getTransform() ;
		
		Rectangle2D rect = context.getFontMetrics(font).getStringBounds(word.getWord(), context);
		Rectangle simpleRect = new Rectangle(h, v-(int)(rect.getHeight()*.8), (int)(rect.getWidth()), (int)(rect.getHeight()));
		if(rotated){
			affineTransform =  new AffineTransform();
			affineTransform.rotate(Math.toRadians(-90), 800, 500);
			context.setTransform(affineTransform);
			
			int xpos;
			int ypos;
			
			xpos = h - 800;
			ypos = v -500;
			
			xpos = (int)(xpos*Math.cos(90) - ypos*Math.sin(90));
			ypos = (int)((h-800)*Math.sin(90) + ypos*Math.cos(90));
			
			xpos += 800;
			ypos += 500;
			
			simpleRect = new Rectangle(xpos, ypos, (int)rect.getHeight(), (int)rect.getWidth());
		}	
		
		
		
		while (detectCollision(simpleRect))
        { 
			// move the initialpoints
			if(!rotated){
				h = (int)(rand.nextGaussian()*(k/40)+horizontalCentre);
				v = (int)(rand.nextGaussian()*(k/100)+verticalCentre);
			}
			else{
				h = (int)(rand.nextGaussian()*(k/100)+horizontalCentre);
				v = (int)(rand.nextGaussian()*(k/40)+verticalCentre);
			}
			
			

			simpleRect = new Rectangle(h, v-(int)(rect.getHeight()*.8), (int)rect.getWidth(), (int)rect.getHeight());
			
			if(rotated){
				int xpos;
				int ypos;
				
				xpos = h - 800;
				ypos = v -500;
				
				xpos = (int)(xpos*Math.cos(90) - ypos*Math.sin(90));
				ypos = (int)((h-800)*Math.sin(90) + ypos*Math.cos(90));
				
				xpos += 800;
				ypos += 500;
				
				simpleRect = new Rectangle(xpos + (int)(rect.getWidth()*.8), ypos, (int)(rect.getHeight()*.8), (int)rect.getWidth());
			}
			
	        k= k+1;
        }

		context.setFont(font);
		
		if(rotated){
			context.setTransform(saveTransform);
			AffineTransform newTx = new AffineTransform();
			newTx.rotate(Math.toRadians(90), simpleRect.getX(), simpleRect.getY());
			context.setTransform(newTx);
			context.drawString(word.getWord(), simpleRect.x, simpleRect.y-(int)(simpleRect.getWidth()*.1));
			context.setTransform(saveTransform);
		}else
		{
			context.drawString(word.getWord(), h, v);
		}
		listOfPlacedWords.add(simpleRect);
	}

	@Override
	public void complete(String outputName) {
		context.dispose();
		try {
			ImageIO.write(image, "png", new File(outputName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private boolean detectCollision(Rectangle proposed){
		for(Rectangle existing : listOfPlacedWords){
			if(proposed.intersects(existing) || proposed.contains(existing) || existing.contains(proposed))
				return true;
		}
		return false;
	}

}
