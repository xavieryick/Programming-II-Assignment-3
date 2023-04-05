package exceptions;
/**
 * This class extends from exception to return custom throw messages.
 * It is NOT located in the controller class.
 * 
 * @author Kaydence Eng
 * @author Xavier Yick
 *
 */
public class CustomException extends Exception{
	
	public CustomException(String errorMessage) {
		
		super(errorMessage);
	}
	
	
	
}
