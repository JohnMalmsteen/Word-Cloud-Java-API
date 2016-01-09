package ie.gmit.sw;

public class LinearFontSizeStrategy implements FontSizeSelectionStrategy{

	@Override
	public WordFrequencyKeyValue[] getFontSizes(WordFrequencyKeyValue[] arrayOfWordKeyValues) {
		int i = 155;
		
		for(WordFrequencyKeyValue keyValue : arrayOfWordKeyValues){
			if(keyValue != null)
				keyValue.setFontSize(i);
			
			i -= 3;
		}
		
		return arrayOfWordKeyValues;
	}

}
