package ie.gmit.sw;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
public class CloudRenderer {
	
	private static ArrayList<Rectangle> listOfDrawnStrings = new ArrayList<>();
	
	public static void main(String args[]) throws Exception{
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 62);
		BufferedImage image = new BufferedImage(600, 300, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.red);
		graphics.setFont(font);
		
		
		drawNewString(graphics, "Object-Oriented", font, 0, 100);
		font = new Font(Font.SANS_SERIF, Font.ITALIC, 42);
		graphics.setFont(font);
		graphics.setColor(Color.yellow);
		drawNewString(graphics, "Software Development", font, 35, 140);

		System.out.println(listOfDrawnStrings.get(0).intersects(listOfDrawnStrings.get(1)));
		font = new Font(Font.MONOSPACED, Font.PLAIN, 22);
		graphics.setFont(font);
		graphics.setColor(Color.blue);
		graphics.drawString("2012 Assignment", 40, 180);
		graphics.dispose();
		ImageIO.write(image, "png", new File("image.png"));
	}
	
	public static void drawNewString(Graphics context, String str, Font font, int x, int y){
		context.setFont(font);
		Rectangle2D rect = context.getFontMetrics(font).getStringBounds(str, context);
		Rectangle simpleRect = new Rectangle(x, y, (int)rect.getWidth()-30, (int)rect.getHeight()-30);
		
		listOfDrawnStrings.add(simpleRect);
		context.drawString(str, x, y);
	}

}
