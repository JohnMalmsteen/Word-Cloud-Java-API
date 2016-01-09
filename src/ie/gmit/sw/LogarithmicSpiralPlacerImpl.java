package ie.gmit.sw;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;

public class LogarithmicSpiralPlacerImpl {
	
	private final double GROWTH_RATE = 1.618;
	
	private int horizontalCentre = 0;
	private int verticalCentre = 0;
	
	private int n = 45;
	private int turn = 45;
	
	private List<Rectangle> listOfPlacedWords = new ArrayList<Rectangle>();

	private BufferedImage image = null;
	private Graphics context = null;
	
	public LogarithmicSpiralPlacerImpl(){
		//implying 16:10 isn't the best aspect ratio
		// golden ratio 4 life!
		this(1600, 1000);
	}
	
	public LogarithmicSpiralPlacerImpl(int width, int height){
		image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		context = image.getGraphics();
		
		horizontalCentre = width/2;
		verticalCentre = height/2;
	}
	
	public void placeString(WordFrequencyKeyValue word, Graphics context, Font font){
		int h = horizontalCentre;
        int v = verticalCentre;
		int k = 1;
		
		Rectangle2D rect = context.getFontMetrics(font).getStringBounds(word.getWord(), context);
		Rectangle simpleRect = new Rectangle(h, v, (int)rect.getWidth()-30, (int)rect.getHeight()-30);
		
		while (detectCollision(simpleRect))
        { 
            int theta= k*turn %360;
            double L= k*GROWTH_RATE;

            int h_next= (int) Math.round(h+L*Math.cos(theta*Math.PI/180));
            int v_next= (int) Math.round(v+L*Math.sin(theta*Math.PI/180));

	        h= h_next; v= v_next; 
	        simpleRect = new Rectangle(h, v, (int)rect.getWidth()-30, (int)rect.getHeight()-30);
	        k= k+1;
        }
	    
	
	}
	
	
	private boolean detectCollision(Rectangle proposed){
		for(Rectangle existing : listOfPlacedWords){
			if(proposed.intersects(existing))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		LogarithmicSpiralPlacerImpl placer = new LogarithmicSpiralPlacerImpl();
		placer.placeString(new WordFrequencyKeyValue("word", 1), new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR).getGraphics(), new Font("sdfg", 3, 30));
	}

}
