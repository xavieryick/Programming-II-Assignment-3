package controller;
/**
 * This class extends from exception to return custom throw messages.
 * 
 * @author Kaydence Eng
 * @author Xavier Yick
 *
 */
public class CustomException extends Exception{
	//Some help: https://www.baeldung.com/java-new-custom-exception
	
	public CustomException(String errorMessage) {
		
		super(errorMessage);
	}

}
