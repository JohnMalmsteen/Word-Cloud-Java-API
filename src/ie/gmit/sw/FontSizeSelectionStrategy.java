package ie.gmit.sw;

import java.util.List;

public interface FontSizeSelectionStrategy {
	public WordFrequencyKeyValue[] getFontSizes(WordFrequencyKeyValue[] arrayOfWordKeyValues);
}
