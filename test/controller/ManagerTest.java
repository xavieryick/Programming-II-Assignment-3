package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Toy;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;

import controller.Manager;
import javafx.event.ActionEvent;
import exceptions.CustomException;
/**
 * This method tests methods in the manager class. It doesn't work :(
 * @author kaydence eng
 *
 */
class ManagerTest {
	
	ActionEvent testerEvent = new ActionEvent();
	Manager mg = new Manager();
//	ActionEvent demoEvent = mock(Manager.class);

//	This test doesn't work, sorry!
	@Test
	void addAnimalTest() {
		
		try {
			mg.addToy(testerEvent);
		} catch (exceptions.CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//checking to see if void method is called 
//	@Test
//	void addToyCallTest() {
//		ActionEvent compareEvent = new ActionEvent();
//		doNothing().when(testerEvent).mg.addToy(compareEvent);
//		verify(addToy,times(1)).mg.addToy
//		
//	}

}
