package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.CustomException;

class BoardGamesTest {

BoardGames toy;
	
	@Test
	void setMinimumPlayerCountTest() {
		toy = new BoardGames("1234567890", "battleship", "matel", 15, 10, 5, 5, 7, "shippers");
		toy.setMinimumPlayerCount(5);
		int minimumPlayerCount = toy.getMinimumPlayerCount();
		assertEquals(minimumPlayerCount, 5);
	}
	
	@Test
	void getMinumumPlayerCountTest() {
		toy = new BoardGames("1234567890", "battleship", "matel", 15, 10, 5, 5, 7, "shippers");
		int minimumPlayerCount = toy.getMinimumPlayerCount();
		assertEquals(minimumPlayerCount, 5);
	}
	
	@Test
	void setMaxPlayerCountTest() {
		toy = new BoardGames("1234567890", "battleship", "matel", 15, 10, 5, 5, 7, "shippers");
		try {
			toy.setMaxPlayerCount(7);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			// might change it to throw custom exception
			// fix this later 
			System.out.println("Maximum players can't be lower than the minimum.");
			e.printStackTrace();
		}
		int maxPlayerCount = toy.getMaxPlayerCount();
		assertEquals(maxPlayerCount, 7);
	}
	
	@Test
	void getMaxPlayerCountTest() {
		toy = new BoardGames("1234567890", "battleship", "matel", 15, 10, 5, 5, 7, "shippers");
		int maxPlayerCount = toy.getMaxPlayerCount();
		assertEquals(maxPlayerCount, 7);
	}
	
	@Test
	void setBoardGameDesginersTest() {
		toy = new BoardGames("1234567890", "battleship", "matel", 15, 10, 5, 1, 4, "shippers");
		toy.setBoardGameDesigners("shippers");
		String boardGameDesigners = toy.getBoardGameDesigners();
		assertEquals(boardGameDesigners, "shippers");
	}
	
	@Test
	void getBoardGameDesginersTest() {
		toy = new BoardGames("1234567890", "battleship", "matel", 15, 10, 5, 1, 4, "shippers");
		String boardGameDesigners = toy.getBoardGameDesigners();
		assertEquals(boardGameDesigners, "shippers");
	}
}
