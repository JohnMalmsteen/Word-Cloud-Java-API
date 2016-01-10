package ie.gmit.sw;

import static org.junit.Assert.*;

import org.junit.Test;

public class _WordFrequencyKeyValueTest {

	@Test
	public void creationTest() {
		WordFrequencyKeyValue keyValue = new WordFrequencyKeyValue("test", 2015);
		assertArrayEquals(new String [] {"test"}, new String [] {keyValue.getWord()});
		assertEquals((long)2015, (long)keyValue.getFrequency());
		assertNotEquals((long)1, (long)keyValue.getFrequency());
	}
	
	@Test
	public void getterSetterTest(){
		WordFrequencyKeyValue keyValue = new WordFrequencyKeyValue("newTest", 777);
		keyValue.setFontSize(12);
		assertEquals((long)keyValue.getFontSize(), (long)12);
		keyValue.setWord("updateWord");
		assertArrayEquals(new String [] {"updateWord"}, new String [] {"updateWord"});
	}

	@Test
	public void toStringTest(){
		WordFrequencyKeyValue keyValue = new WordFrequencyKeyValue("stringTest", 888);
		assertTrue(keyValue.toString() instanceof String);
	}
}
