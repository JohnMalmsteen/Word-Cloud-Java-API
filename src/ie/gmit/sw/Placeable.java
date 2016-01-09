package ie.gmit.sw;

import java.awt.Font;

public interface Placeable {

	void placeString(WordFrequencyKeyValue word, Font font);
	void complete(String outputName);

}