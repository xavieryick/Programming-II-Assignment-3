package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This method tests the puzzles class.
 * @author kaydence eng 
 *
 */
class PuzzlesTest {

Puzzles toy;
	
	@Test
	void setPuzzleTypeTest() {
		toy = new Puzzles("1234567890", "balloons", "matel", 15, 10, 5, "logic");
		toy.setPuzzleType("logic");
		String puzzleType = toy.getPuzzleType();
		assertEquals(puzzleType, "logic");
	}
	
	@Test
	void getPuzzleTypeTest() {
		toy = new Puzzles("1234567890", "balloons", "matel", 15, 10, 5, "logic");
		String puzzleType = toy.getPuzzleType();
		assertEquals(puzzleType, "logic");
	}
}
