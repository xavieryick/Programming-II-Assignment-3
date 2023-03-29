package model;
/**
 * This class represents the toy type puzzle and extends from the general toy class.
 * @author kaydence eng
 * @author xavier yick
 *
 */
public class Puzzles extends Toy{

	private String puzzleType;
	/**
	 * This method initializes the general attributes of a toy plus puzzle type.
	 * @param serialNumber
	 * @param toyName
	 * @param toyBrand
	 * @param toyPrice
	 * @param availableCount
	 * @param appropriateAge
	 * @param puzzleType
	 */
	public Puzzles(String serialNumber, String toyName, String toyBrand, double toyPrice, int availableCount, int appropriateAge, String puzzleType) {
		super(serialNumber, toyName, toyBrand, toyPrice, availableCount, appropriateAge);
		this.puzzleType = puzzleType;
	}
	/**
	 * The method sets the puzzle type.
	 * @param puzzleType
	 */
	public void setPuzzleType(String puzzleType) {
		this.puzzleType = puzzleType;
	}
	/**
	 * This method returns the puzzle type.
	 * @return puzzleType
	 */
	public String getPuzzleType() {
		return puzzleType;
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
			   "\nPuzzle Type: " + puzzleType; 
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
			 + puzzleType;
	}
}
