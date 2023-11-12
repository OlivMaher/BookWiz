package comp249_A2;

//-----------------------------------------------------
// Assignment #2
// Question: Part #1
// Written by: Olivier Maher 40235064
//-----------------------------------------------------
public class TooFewFieldsException extends Exception{
	public TooFewFieldsException() {
		super("Error: Too few Fields");
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
