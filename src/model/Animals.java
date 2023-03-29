package model;
/**
 * This class represents the toy type animals and extends from the general toy class.
 * @author kaydence eng
 * @author xavier yick
 *
 */
public class Animals extends Toy{

	private String animalSize;
	private String animalMaterial;
	/**
	 * This method initializes the general attributes of the toy class
	 * plus the specific attributes for animals.
	 * @param serialNumber
	 * @param toyName
	 * @param toyBrand
	 * @param toyPrice
	 * @param availableCount
	 * @param appropriateAge
	 * @param animalMaterial
	 * @param animalSize
	 */
	public Animals(String serialNumber, String toyName, String toyBrand, double toyPrice, int availableCount, int appropriateAge, String animalMaterial, String animalSize) {
		super(serialNumber, toyName, toyBrand, toyPrice, availableCount, appropriateAge);
		this.animalMaterial = animalMaterial;
		this.animalSize = animalSize;
	}
	/**
	 * This method sets the animal material.
	 * @param animalMaterial
	 */
	public void setAnimalMaterial(String animalMaterial) {
		this.animalMaterial = animalMaterial;
	}
	/**
	 * This method returns the animal material.
	 * @return animalMaterial
	 */
	public String getAnimalMaterial() {
		return animalMaterial;
	}
	/**
	 * This method sets the animal size.
	 * @param animalSize
	 */
	public void setAnimalSize(String animalSize) {
		this.animalSize= animalSize;
	}
	/**
	 * This method returns the animal size.
	 * @return animalSize
	 */
	public String getAnimalSize() {
		return animalSize;
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
			   "\nAnimal Material: " + animalMaterial +
			   "\nAnimal Size: " + animalSize;
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
			 + animalMaterial + ";"
			 + animalSize;
	}
}
