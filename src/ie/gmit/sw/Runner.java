package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.text.html.HTMLEditorKit.Parser;

public class Runner {
	
	
	
	public static void main(String args[]) throws Exception{		
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 62);
		BufferedImage image = new BufferedImage(600, 300, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.red);
		graphics.setFont(font);
		
		
		//drawNewString(graphics, "Object-Oriented", font, 0, 100);
		font = new Font(Font.SANS_SERIF, Font.ITALIC, 42);
		graphics.setFont(font);
		graphics.setColor(Color.yellow);
		//drawNewString(graphics, "Software Development", font, 35, 140);

		//System.out.println(listOfDrawnStrings.get(0).intersects(listOfDrawnStrings.get(1)));
		font = new Font(Font.MONOSPACED, Font.PLAIN, 22);
		graphics.setFont(font);
		graphics.setColor(Color.blue);
		graphics.drawString("2012 Assignment", 40, 180);
		graphics.dispose();
		ImageIO.write(image, "png", new File("image.png"));
	}
}
