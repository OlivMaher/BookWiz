package comp249_A2;

//-----------------------------------------------------
// Assignment #2
// Question: Part #1
// Written by: Olivier Maher 40235064
//-----------------------------------------------------
public class MissingFieldException extends Exception{
	public MissingFieldException() {
		super("Error: Missing Field");
	}
	/**
	 * 
	 * @param message
	 */
	public MissingFieldException(String message) {
		super("Error: "+message);
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
