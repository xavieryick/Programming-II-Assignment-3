package model;

import controller.CustomException;
/**
 * This class represents the general attributes each toy has in the database.
 * @author kaydence eng
 * @author xavier yick
 *
 */
public abstract class Toy {
	
	public String serialNumber;
	public String toyName;
	public String toyBrand;
	public double toyPrice;
	public int availableCount;
	public int appropriateAge;
	/**
	 * This class initializes the serial number, toy name, toy brand, toy price, available count, and appropriate age.
	 * @param serialNumber
	 * @param toyName
	 * @param toyBrand
	 * @param toyPrice
	 * @param availableCount
	 * @param appropriateAge
	 */
	public Toy(String serialNumber, String toyName, String toyBrand, double toyPrice, int availableCount, int appropriateAge) {
		this.serialNumber = serialNumber;
		this.toyName = toyName;
		this.toyBrand = toyBrand;
		this.toyPrice = toyPrice;
		this.availableCount = availableCount;
		this.appropriateAge = appropriateAge;
	}
	/**
	 * This method sets the serial number.
	 * @param serialNumber
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * This method returns the serial number.
	 * @return serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * This method sets the toy name. 
	 * @param toyName
	 */
	public void setToyName(String toyName) {
		this.toyName = toyName;
	}
	/**
	 * This method returns the toy name. 
	 * @return toyName
	 */
	public String getToyName() {
		return toyName;
	}
	/**
	 * This method sets the toy brand. 
	 * @param toyBrand
	 */
	public void setToyBrand(String toyBrand) {
		this.toyBrand = toyBrand;
	}
	/**
	 * This method returns the toy brand.
	 * @return toyBrand
	 */
	public String getToyBrand() {
		return toyBrand;
	}
	/**
	 * This method sets the toys price and checks to make sure it's not negative. 
	 * @param toyPrice
	 * @throws CustomException
	 */
	public void setToyPrice(double toyPrice) throws CustomException {
		if (toyPrice < 0) {
			throw new CustomException("Prices must be a number that can't be negative!"); 
		}
		this.toyPrice = toyPrice;
	}
	/**
	 * This method returns the toy price.
	 * @return toyPrice
	 */
	public double getToyPrice() {
		return toyPrice;
	}
	/**
	 * This method sets the available amount of a toy. 
	 * @param availableCount
	 */
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}
	/**
	 * This method returns how many of a toy are left.
	 * @return availableCount
	 */
	public int getAvailableCount() {
		return availableCount;
	}
	/**
	 * This method sets the appropriate age for a toy.
	 * @param appropriateAge
	 */
	public void setAppropriateAge(int appropriateAge) {
		this.appropriateAge = appropriateAge;
	}
	/**
	 * This method returns the appropriate age for a toy.
	 * @return appropriateAge
	 */
	public int getAppropriateAge() {
		return appropriateAge;
	}
	/**
	 * This method formats the data to a string when a toy is printed to the console.
	 */
	public String toString() {
		return "Serial Number: " + serialNumber +
			   "\nToy Name: " + toyName + 
			   "\nToy Brand: " + toyBrand +
			   "\nPrice: " + toyPrice + 
			   "\nAvailable Count: " + availableCount +
			   "\nAppropriate Age: " + appropriateAge;
	}
	/**
	 * This method formats the toy data into a readable format.
	 * @return formatted toy data
	 */
	public String format() {
		return serialNumber + ";" 
			 + toyName + ";" 
			 + toyBrand + ";" 
			 + toyPrice + ";" 
			 + availableCount + ";" 
			 + appropriateAge;
	}
	
}
