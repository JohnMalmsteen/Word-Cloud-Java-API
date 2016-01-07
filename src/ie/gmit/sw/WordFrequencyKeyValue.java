package ie.gmit.sw;


public class WordFrequencyKeyValue {
	
	private String word;
	private int frequency;
	
	public WordFrequencyKeyValue(String w, int freq){
		this.word = w;
		this.frequency = freq;
	}
	
	public int hashCode() {
		// TODO Auto-generated method stub
		return word.hashCode();
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof WordFrequencyKeyValue))
			return super.equals(obj);
		
		return ((WordFrequencyKeyValue)obj).getWord().equals(word);

	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "Word: " + this.getWord() + "\tFreq: " + this.getFrequency();
	}

	
	

}
