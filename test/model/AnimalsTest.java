package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Animals;
/**
 * This method tests the animal class.
 * @author kaydence eng 
 *
 */
class AnimalsTest {

Animals toy;
	
	@Test
		void setAnimalMaterialTest() {
			toy = new Animals("1234567890", "cow", "matel", 15, 10, 5, "cotton", "small");
			toy.setAnimalMaterial("cotton");
			String animalMaterial = toy.getAnimalMaterial();
			assertEquals(animalMaterial, "cotton");
		}
	
	@Test
		void getAnimalMaterialTest() {
		toy = new Animals("1234567890", "cow", "matel", 15, 10, 5, "cotton", "small");
		String animalMaterial = toy.getAnimalMaterial();
		assertEquals(animalMaterial, "cotton");
	}
	
	@Test
		void setAnimalSizeTest() {
		toy = new Animals("1234567890", "cow", "matel", 15, 10, 5, "cotton", "small");
		toy.setAnimalSize("small");
		String animalSize = toy.getAnimalSize();
		assertEquals(animalSize, "small");
	}
	
	@Test
		void getAnimalSizeTest() {
		toy = new Animals("1234567890", "cow", "matel", 15, 10, 5, "cotton", "small");
		String animalSize = toy.getAnimalSize();
		assertEquals(animalSize, "small");
	}

}
