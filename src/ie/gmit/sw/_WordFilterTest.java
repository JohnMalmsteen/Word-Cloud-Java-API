package ie.gmit.sw;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class _WordFilterTest {

	@Test
	public void test() {
		WordFilterImpl filter = new WordFilterImpl();
		
		assertTrue(filter.checkWord("and"));
		assertFalse(filter.checkWord("gmit"));
	}
	
	@Test
	public void exceptionTest(){
		WordFilterImpl filter = new WordFilterImpl("junk.txt");
		assertTrue(filter.checkWord("or"));
		assertFalse(filter.checkWord("interesting"));
	}

	@Test
	public void numberStripperTest(){
		WordFilterImpl filter = new WordFilterImpl("warandpeace.txt");
		assertTrue(filter.checkWord("12"));
		assertTrue(filter.checkWord("123"));
		assertFalse(filter.checkWord("2015"));
		assertFalse(filter.checkWord("1-2-3"));
	}
	
}
