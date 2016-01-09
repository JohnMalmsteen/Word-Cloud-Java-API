package ie.gmit.sw;

public class Runner {

	public static void main(String args[]) throws Exception{		
		CloudRenderer renderer = new CloudRenderer("warandpeace.txt", SourceType.FILE, "image");
		renderer.draw();
		
		CloudRenderer trump = new CloudRenderer("http://donaldjtrump.com/", SourceType.WEB, "unstumpable");
		trump.draw();
	}
}
