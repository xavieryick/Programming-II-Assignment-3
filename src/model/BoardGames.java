package model;

import exceptions.CustomException;
/**
 * This class represents the toy type board games and extends from the toy class.
 * @author kaydence eng
 * @author xavier yick
 *
 */
public class BoardGames extends Toy{

	private int minimumPlayerCount;
	private int maximumPlayerCount;
	private String playerCountRange;
	private String boardGameDesigners;
	/**
	 * This method initializes the general attributes of toy and board game specific attributes.
	 * @param serialNumber
	 * @param toyName
	 * @param toyBrand
	 * @param toyPrice
	 * @param availableCount
	 * @param appropriateAge
	 * @param minimumPlayerCount
	 * @param maximumPlayerCount
	 * @param boardGameDesigners
	 */
	public BoardGames(String serialNumber, String toyName, String toyBrand, double toyPrice, int availableCount, int appropriateAge, int minimumPlayerCount, int maximumPlayerCount, String boardGameDesigners) {
		super(serialNumber, toyName, toyBrand, toyPrice, availableCount, appropriateAge);
		this.minimumPlayerCount = minimumPlayerCount;
		this.maximumPlayerCount = maximumPlayerCount;
//		this.playerCountRange = playerCountRange;
		this.boardGameDesigners = boardGameDesigners;
	}
	/**
	 * This method sets the allowed amount of players as a range.
	 * @param playerCountRange
	 */
	public void setPlayerCountRange(String playerCountRange) {
		this.playerCountRange = playerCountRange;
	}
	/**
	 * This method returns the allowed amount of players as a range.
	 * @return playerCountRange
	 */
	public String getPlayerCountRange() {
		return playerCountRange;
	}
	/**
	 * This method sets the minimum amount of players needed for a board game.
	 * @param minimumPlayerCount
	 */
	public void setMinimumPlayerCount(int minimumPlayerCount) {
		this.minimumPlayerCount = minimumPlayerCount;
	}
	/**
	 * This method returns the minimum amount of players needed for a board game.
	 * @return minimumPlayerCount
	 */
	public int getMinimumPlayerCount() {
		return minimumPlayerCount;
	}
	/**
	 * This method sets the maximum amount of 
	 * players for a board game and throws a custom exception 
	 * if the max player count is less than the minimum.
	 * @param maxPlayerCount
	 * @throws CustomException
	 */
	public void setMaxPlayerCount(int maxPlayerCount) throws CustomException {
		if(minimumPlayerCount > maximumPlayerCount) {
			throw new CustomException("Max players can't be higher than minimum players needed!");
		}
		this.maximumPlayerCount = maxPlayerCount;
	}
	/**
	 * This method returns the max amount of players allowed for a board game.
	 * @return maximumPlayerCount
	 */
	public int getMaxPlayerCount() {
		return maximumPlayerCount;
	}
	/**
	 * This methods sets the designers for a board game.
	 * @param boardGameDesigners
	 */
	public void setBoardGameDesigners(String boardGameDesigners) {
		this.boardGameDesigners = boardGameDesigners;
	}
	/**
	 * This method returns the designers for a board game.
	 * @return boardGameDesigners
	 */
	public String getBoardGameDesigners() {
		return boardGameDesigners;
	}
	/**
	 * This method turns the toy data into a string and overrides the abstract class toy.
	 */
	@Override
	public String toString() {
		return "Serial Number: " + serialNumber +
			   "\nToy Name: " + toyName + 
			   "\nToy Brand: " + toyBrand +
			   "\nPrice: " + toyPrice + 
			   "\nAvailable Count: " + availableCount +
			   "\nAppropriate Age: " + appropriateAge + 
			   "\nMinimum Player Count: " + minimumPlayerCount +
			   "\nMaximum Player Count: " + maximumPlayerCount +
			   "\nBoard Game Designers: " + boardGameDesigners;
	}
	/**
	 * This method formats the toy data and overrides the method in abstract class toy.
	 */
	@Override
	public String format() {
		return serialNumber + ";" 
			 + toyName + ";" 
			 + toyBrand + ";" 
			 + toyPrice + ";" 
			 + availableCount + ";" 
			 + appropriateAge + ";"
			 + minimumPlayerCount + "-" + maximumPlayerCount + ";"
			 + boardGameDesigners;
	}
}
