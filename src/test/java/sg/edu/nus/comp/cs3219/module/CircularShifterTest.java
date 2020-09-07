package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class CircularShifterTest {
	LineStorage inputLineStorage;
	LineStorage afterShiftLineStorage;
	CircularShifter shifter;

	@Before
	public void setUp() {
		inputLineStorage = new LineStorage();
		afterShiftLineStorage = new LineStorage();
		shifter = new CircularShifter(afterShiftLineStorage);
		Set<String> words = new HashSet<>();
		words.add("the");
		words.add("after");
		shifter.setIgnoreWords(words);
		inputLineStorage.addObserver(shifter);
	}

	@Test
	public void test() {
		inputLineStorage.addLine("The Day after Tomorrow");
		assertEquals(2, afterShiftLineStorage.size());

		assertEquals("Day after Tomorrow the", afterShiftLineStorage.get(0).toString());
		assertEquals("Tomorrow the Day after", afterShiftLineStorage.get(1).toString());
	}
	
	@Test
	public void test1() {
		inputLineStorage.addLine("Tutorial three Completed");
		assertEquals(3, afterShiftLineStorage.size());
		
		assertEquals("Tutorial three Completed", afterShiftLineStorage.get(0).toString());
		assertEquals("three Completed Tutorial", afterShiftLineStorage.get(1).toString());
		assertEquals("Completed Tutorial three", afterShiftLineStorage.get(2).toString());
	}
	
	@Test
	public void test2() {
		inputLineStorage.addLine("the after after the the after");
		assertEquals(0, afterShiftLineStorage.size());
	}

}
