package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class WordFilterImpl implements WordFilter {
	private HashSet<String> stopwords = new HashSet<String>();
	private String sourcefile = "stopwords.txt";
	
	public WordFilterImpl() throws IOException{
		parseFile();
	}
	
	public WordFilterImpl(String filename) throws IOException{
		sourcefile = filename;
		parseFile();
	}
	
	private void parseFile() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourcefile)));
		String line;
		while((line = br.readLine()) != null){
			line = line.trim();
			line.toLowerCase();
			line.replaceAll("[^a-z]", "");
			if(!stopwords.contains(line)){
				stopwords.add(line);
			}
		}
		br.close();
	}
	
	public boolean checkWord(String word){
		return stopwords.contains(word);
	}
	
	
}