package ie.gmit.sw;

import static org.junit.Assert.*;

import org.junit.Test;

public class _WordFrequencyComparatorTest {

	@Test
	public void basicTest() {
		WordFrequencyComparator comparer = new WordFrequencyComparator();
		WordFrequencyKeyValue keyValue = new WordFrequencyKeyValue("newTest", 777);
		WordFrequencyKeyValue keyValue1 = new WordFrequencyKeyValue("newTest1", 777);
		
		assertTrue(comparer.compare(keyValue, keyValue1) == 0);
		
		keyValue1.setFrequency(778);
		
		assertFalse(comparer.compare(keyValue, keyValue1) == 0);
		assertTrue(comparer.compare(keyValue, keyValue1) == 1);
	}
	
	@Test(expected = Exception.class)
	public void breakTest(){
		WordFrequencyComparator comparer = new WordFrequencyComparator();
		WordFrequencyKeyValue keyValue = new WordFrequencyKeyValue("newTest", 777);
		WordFrequencyKeyValue keyValue1 = new WordFrequencyKeyValue("newTest1", 777);
		
		keyValue.setFrequency((Integer)null);
		
		comparer.compare(keyValue, keyValue1);
	}

}
