package comp249_A2;
//-----------------------------------------------------
// Assignment #2
// Question: Part #1
// Written by: Olivier Maher 40235064
//-----------------------------------------------------
public class BadYearException extends Exception{
	public BadYearException() {
		super("Error: Incorrect Year");
	}
	/**
	 * 
	 * @param message
	 */
	public BadYearException(String message) {
		super("Error: "+message);
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
