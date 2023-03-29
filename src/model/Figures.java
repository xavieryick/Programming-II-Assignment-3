package model;
/**
 * This class represents the toy type figures and extends from the toy class.
 * @author kaydence eng 
 * @author xavier yick
 */
public class Figures extends Toy {

	private String figureClassification;
	/**
	 * This method initializes the general attributes of a toy plus figure specific traits.
	 * @param serialNumber
	 * @param toyName
	 * @param toyBrand
	 * @param toyPrice
	 * @param availableCount
	 * @param appropriateAge
	 * @param figureClassification
	 */
	public Figures(String serialNumber, String toyName, String toyBrand, double toyPrice, int availableCount, int appropriateAge, String figureClassification) {
		super(serialNumber, toyName, toyBrand, toyPrice, availableCount, appropriateAge);
		this.figureClassification = figureClassification;
	}
	/**
	 * This method sets the figure classification.
	 * @param figureClassification
	 */
	public void setFigureClassification(String figureClassification) {
		this.figureClassification = figureClassification;
	}
	/**
	 * This method returns the figure classification.
	 * @return figureClassification
	 */
	public String getFigureClassification() {
		return figureClassification;
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
			   "\nClassification: " + figureClassification;
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
			 + figureClassification;
	}
}
