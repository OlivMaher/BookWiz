package comp249_A2;

//-----------------------------------------------------
// Assignment #2
// Question: Part #1
// Written by: Olivier Maher 40235064
//-----------------------------------------------------
public class BadIsbn10Exception extends Exception{
	
	public BadIsbn10Exception() {
		super("Error: Incorrect ISBN-10");
	}
	/**
	 * 
	 * @param message
	 */
	public BadIsbn10Exception(String message) {
		super("Error: "+message);
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
