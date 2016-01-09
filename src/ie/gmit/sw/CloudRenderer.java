package ie.gmit.sw;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
public class CloudRenderer {
	
	private ArrayList<Rectangle> listOfDrawnStrings = new ArrayList<>();
	
	
	private ParseableFactory parserFactory = ParseableFactory.getInstance();
	
	private Parseable parser;
	private FontSizeSelectionStrategy strategy = null;
	private WordFrequencyKeyValue[] weightedArray = null;
	
	public CloudRenderer(String source, SourceType sourceType, int width, int height){
		parser = parserFactory.getParseable(sourceType);
		
		WordFrequencyKeyValue[] unweightedArray = parser.parse(source);
		
		// here i am demonstrating the context element of the strategy pattern employed for font weighting, if there are less than 50 disrete words in the list then we will use
		// the linear font strategy, otherwise we will use the weighted strategy
		// this is done at run time.
		
		if(unweightedArray[unweightedArray.length-1] == null){
			strategy = new LinearFontSizeStrategy();
		}
		else{
			strategy = new WeightedFontStrategy();
		}
		
		weightedArray = strategy.getFontSizes(unweightedArray);
	}

	
	public void drawNewString(Graphics context, String str, Font font, int x, int y){
		context.setFont(font);
		Rectangle2D rect = context.getFontMetrics(font).getStringBounds(str, context);
		Rectangle simpleRect = new Rectangle(x, y, (int)rect.getWidth()-30, (int)rect.getHeight()-30);
		
		listOfDrawnStrings.add(simpleRect);
		context.drawString(str, x, y);
	}

}
