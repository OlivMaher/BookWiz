package comp249_A2;
//-----------------------------------------------------
// Assignment #2
// Question: Part #1
// Written by: Olivier Maher 40235064
//-----------------------------------------------------
public class TooManyFieldsException extends Exception{
	
	public TooManyFieldsException() {
		super("Error: Too many Fields");
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
