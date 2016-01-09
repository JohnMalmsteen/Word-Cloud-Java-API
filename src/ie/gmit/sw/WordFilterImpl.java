package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class WordFilterImpl implements WordFilter {
	private HashSet<String> stopwords = new HashSet<String>();
	private String sourcefile = "stopwords.txt";
	
	public WordFilterImpl(){
		parseFile();
	}
	
	public WordFilterImpl(String filename) throws IOException{
		sourcefile = filename;
		parseFile();
	}
	
	private void parseFile(){
		
		String line;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourcefile)));
			while((line = br.readLine()) != null){
				line = line.trim();
				line.toLowerCase();
				line.replaceAll("[^a-z]", "");
				if(!stopwords.contains(line)){
					stopwords.add(line);
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkWord(String word){
		return stopwords.contains(word);
	}
	
	
}