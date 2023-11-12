package comp249_A2;

//-----------------------------------------------------
// Assignment #2
// Question: Part #1
// Written by: Olivier Maher 40235064
//-----------------------------------------------------
public class BadIsbn13Exception extends Exception{
	
	public BadIsbn13Exception() {
		super("Error: Incorrect ISBN-13");
	}
	/**
	 * 
	 * @param message
	 */
	public BadIsbn13Exception(String message) {
		super("Error: "+message);
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
