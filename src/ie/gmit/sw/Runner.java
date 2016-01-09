package ie.gmit.sw;

public class Runner {

	public static void main(String args[]) throws Exception{		
		CloudRenderer warandpeace = new CloudRenderer("warandpeace.txt", SourceType.FILE, DrawingPattern.GAUSSIAN, "image");
		warandpeace.draw();
		
		CloudRenderer trump = new CloudRenderer("http://www.donaldjtrump.com/", SourceType.WEB, DrawingPattern.LOGARITHMIC_SPIRAL, "unstumpable");
		trump.draw();
		
		CloudRenderer gmit = new CloudRenderer("http://www.gmit.ie/", SourceType.WEB, DrawingPattern.GAUSSIAN, "gmit");
		gmit.draw();
	}
}
