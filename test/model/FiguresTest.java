package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This method tests the figures class.
 * @author kaydence eng 
 *
 */
class FiguresTest {

Figures toy;
	
	@Test
	void setFigureClassificationTest() {
		toy = new Figures("1234567890", "cuddle dolly", "matel", 15, 10, 5, "doll");
		toy.setFigureClassification("doll");
		String figureClassification = toy.getFigureClassification();
		assertEquals(figureClassification, "doll");
	}
	
	@Test
	void getFigureClassificationTest() {
		toy = new Figures("1234567890", "cuddle dolly", "matel", 15, 10, 5, "doll");
		String figureClassification = toy.getFigureClassification();
		assertEquals(figureClassification, "doll");
	}
}
