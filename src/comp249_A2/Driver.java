package comp249_A2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

// -----------------------------------------------------
 // Assignment #2
 // Question: Part #1
 // Written by: Olivier Maher 40235064
// -----------------------------------------------------


public class Driver {

	public static void main(String[] args) throws IOException {
		
		do_part1();// validating syntax, partition book records based on genre.
		do_part2(); // validating semantics, read the genre files each into arrays of Book objects,
		 			// then serialize the arrays of Book objects each into binary files.
		//Part 2: validates ISBN, Price, and Year
		 do_part3(); // reading the binary files, deserialize the array objects in each file, and
		 				// then provide an interacive program to allow the user to navigate the arrays.
	}
	/**
	 * validating syntax, partition book records based on genre.
	 * @throws IOException 
	 */
	public static void do_part1() throws IOException{
		int lengthOfFile = 0;
		
		BufferedReader br = new BufferedReader(new FileReader("part1_input_file_names.txt")); 
		String line = null;
		//Reads first line to get file length
		try {
			line = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//Reads first line as it is the length of the file
		lengthOfFile = Integer.parseInt(line);
		//Creates an array the length of file 
		String[] arr = new String[lengthOfFile];
		//apends contents of part1_input_file_name.txt file to an array
		for(int i = 0; i < arr.length; i++) {
			try {
				//Saves the names of the files in an array
				arr[i] = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		br.close();
		
		String[] arrOfFiles = null;
		String[] fileFields = null;
		BufferedWriter CCB = new BufferedWriter(new FileWriter("Cartoon_Comics.csv"));
		BufferedWriter HCB = new BufferedWriter(new FileWriter("Hobbies_Collectibles.csv"));
		BufferedWriter MTV = new BufferedWriter(new FileWriter("Movies_TV_Books.csv"));
		BufferedWriter MRB = new BufferedWriter(new FileWriter("Music_Radio_Books.csv"));
		BufferedWriter NEB = new BufferedWriter(new FileWriter("Nostalgia_Eclectic_Books.csv"));
		BufferedWriter OTR = new BufferedWriter(new FileWriter("Old_Time_Radio_Books.csv"));
		BufferedWriter SSM = new BufferedWriter(new FileWriter("Sports_Sports_Memorabilia.csv"));
		BufferedWriter TPA = new BufferedWriter(new FileWriter("Trains_Planes_Automobiles.csv"));
		BufferedWriter error = new BufferedWriter(new FileWriter("syntax_error_file.csv"));
		
		//Loops through the array of txt file names array
		for(int i = 0; i< arr.length; i++) {
			String nameOfFile = arr[i];
			int txtFileLength =0;
			String readerline = null;
			BufferedReader reader = null;
			//Trys to read file names from array, if not found display error message and moves on
			try {
				reader = new BufferedReader(new FileReader(nameOfFile));
					//Reads first line
					readerline = reader.readLine();
					//Loops while until txt file is empty
					while (readerline != null) {
						//Finds size of file
						txtFileLength++;
						//Reads next line
						readerline = reader.readLine();
					}
			}
			 catch(FileNotFoundException e ) {
				System.out.println(e);
			}
			
			//Creates new reader object
			try (BufferedReader reader2 = new BufferedReader(new FileReader(nameOfFile))){
				//Creates array size of txt file
				arrOfFiles = new String[txtFileLength];
				
				//Loops through second text file
				for(int k = 0; k < arrOfFiles.length; k++) {
					//Reads Line from txt file and appends to array
					arrOfFiles[i] = reader2.readLine();
						fileFields = arrOfFiles[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
						//Checks if number of fields is 6
						try {
						exceptionCheckerPart1(fileFields);
						
							//Validates Genre and if valid writes to individual files
							switch (fileFields[4].toUpperCase()) {
							case "CCB":
								CCB.write(arrOfFiles[i]);
								CCB.newLine();
								break;
							case "HCB":
								HCB.write(arrOfFiles[i]);
								HCB.newLine();
								break;
							case "MTV": 
								MTV.write(arrOfFiles[i]);
								MTV.newLine();
								break;
							case "MRB":
								MRB.write(arrOfFiles[i]);
								MRB.newLine();
								break;
							case "NEB":
								NEB.write(arrOfFiles[i]);
								NEB.newLine();
								break;
							case "OTR":	
								OTR.write(arrOfFiles[i]);
								OTR.newLine();
								break;
							case "SSM":
								SSM.write(arrOfFiles[i]);
								SSM.newLine();
								break;
							case "TPA":
								TPA.write(arrOfFiles[i]);
								TPA.newLine();
								break;
							default:
								error.write("syntax error in file: "+nameOfFile+"\n");
								error.write("====================");
								error.newLine();
								error.write("Error: Invalid Genre \n");
								error.write("Record: "+arrOfFiles[i]);
								error.newLine();
								try {
								throw new UnknownGenreException();
								}
								catch(UnknownGenreException e) {
								}
								
							}
						  
						}
						catch(MissingFieldException e) {
							error.write("syntax error in file: "+nameOfFile+"\n");
							error.write("====================");
							error.newLine();
							error.write(e.getMessage());
							error.write("\nRecord: "+arrOfFiles[i]);
							error.newLine();
							error.newLine();
							
						}
						catch(TooManyFieldsException e) {
							error.write("syntax error in file: "+nameOfFile+"\n");
							error.write("====================");
							error.newLine();
							error.write(e.getMessage());
							error.write("\nRecord: "+arrOfFiles[i]);
							error.newLine();
							error.newLine();
						}
						catch(TooFewFieldsException e) {
							error.write("syntax error in file: "+nameOfFile+"\n");
							error.write("====================");
							error.newLine();
							error.write(e.getMessage());
							error.write("\nRecord: "+arrOfFiles[i]);
							error.newLine();
							error.newLine();
						}
						
					
								
				}
			} catch(Exception e) {
				System.out.println(e);
			}
			
		
	
		}
	CCB.close();
	HCB.close();
	MTV.close();
	MRB.close();
	NEB.close();
	OTR.close();
	SSM.close();
	TPA.close();
	error.close();
	}
	
	/**
	 * Custom method
	 * @param fileFields Takes a record as a parameter
	 * @throws TooManyFieldsException Custom Exception
	 * @throws TooFewFieldsException Custom Exception
	 * @throws MissingFieldException Custom Exception
	 */
	public static void exceptionCheckerPart1(String[] fileFields) throws TooManyFieldsException, TooFewFieldsException, MissingFieldException{
		//Checks length of the book record
		if (fileFields.length == 6) {
			//Loops through to check if there are null elements
		for(int i =0; i < fileFields.length; i++) {
			if (i == 0 && (fileFields[i] == null || fileFields[i].trim().length() <= 0)) {
				throw new MissingFieldException("Missing Title");
			}
			if (i == 1 && (fileFields[i] == null || fileFields[i].trim().length() <= 0)) {
				throw new MissingFieldException("Missing Author");
			}
			if (i == 2 &&(fileFields[i] == null || fileFields[i].trim().length() <= 0)) {
				throw new MissingFieldException("Missing Price");
			}
			if (i == 3 &&(fileFields[i] == null || fileFields[i].trim().length() <= 0)) {
				throw new MissingFieldException("Missing ISBN");
			}
			if (i == 4 &&(fileFields[i] == null || fileFields[i].trim().length() <= 0)) {
				throw new MissingFieldException("Missing Genre");
			}
			if (i == 5 &&(fileFields[i] == null || fileFields[i].trim().length() <= 0)) {
				throw new MissingFieldException("Missing Year");
			}
		}
		//Checks if there are too many fields
		} else if(fileFields.length > 6) {
			throw new TooManyFieldsException();
		}
		//Checks if there are too few fields
		else if(fileFields.length < 6) {
			throw new TooFewFieldsException();
		}
	}
	
	
	
	/**
	 * validating semantics, read the genre files each into arrays of Book objects,
	 * then serialize the arrays of Book objects each into binary files.
	 */
	public static void do_part2() {
		
		
		
		String[] nameOfFiles = {"Cartoon_Comics.csv", "Hobbies_Collectibles.csv", "Movies_TV_Books.csv", "Music_Radio_Books.csv","Nostalgia_Eclectic_Books.csv", "Old_Time_Radio_Books.csv", "Sports_Sports_Memorabilia.csv", "Trains_Planes_Automobiles.csv"};
		Book[] CartoonComicsArr =  null;
		Book[] HoobiesCollectiblesArr = null;
		Book[] MoviesTVArr = null;
		Book[] MusicRadioArr = null;
		Book[] EclecticArr =  null;
		Book[] OldTimeRadioArr = null;
		Book[] SportsArr = null;
		Book[] TrainsPlanesAutoArr = null;
		
		
		try {
		BufferedWriter error = new BufferedWriter(new FileWriter("sematic_error_file.csv"));
		
		//Loops thorugh name of files array, which contains all the text files created in do_part1()
		for (int i = 0; i < nameOfFiles.length; i++) {
			String nameOfCurrentFile = nameOfFiles[i];
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(nameOfCurrentFile));
				int lengthOfTxtFile = 0;
				String readerLine = bufferedReader.readLine();
				//Loops to find length of the text file
				while (readerLine != null) {
					lengthOfTxtFile++;
					readerLine = bufferedReader.readLine();
				}
				bufferedReader.close();
				//Creates the size of the genre book Arrays
				switch (i) {
					case 0:
						CartoonComicsArr = new Book[lengthOfTxtFile];
						break;
					case 1: 
						HoobiesCollectiblesArr = new Book[lengthOfTxtFile];
						break;		
					case 2:
						MoviesTVArr = new Book[lengthOfTxtFile];
						break;
					case 3:
						MusicRadioArr = new Book[lengthOfTxtFile];
						break;
					case 4:
						EclecticArr = new Book[lengthOfTxtFile];
						break;
					case 5:
						OldTimeRadioArr = new Book[lengthOfTxtFile];
						break;
					case 6:
						SportsArr = new Book[lengthOfTxtFile];
						break;
					case 7: 
						TrainsPlanesAutoArr = new Book[lengthOfTxtFile];
						break;
				}
			
				
				//String array size of the text file
				String[] detailsOfTxtFile = new String[lengthOfTxtFile];
				try (BufferedReader bf = new BufferedReader(new FileReader(nameOfCurrentFile))) {
					//Loops through the current text file
					for(int k = 0; k < detailsOfTxtFile.length; k++) {
						detailsOfTxtFile[k] = bf.readLine();
						//Splits the current record
						String[] splitDetailsOfFile = detailsOfTxtFile[k].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
						try {
							//Checks exceptions
							exceptionCheckerPart2(splitDetailsOfFile);
							
							//Creates a book object and spllits the record
							Book book = new Book(splitDetailsOfFile[0], splitDetailsOfFile[1], Double.parseDouble(splitDetailsOfFile[2]), splitDetailsOfFile[3], splitDetailsOfFile[4],Integer.parseInt(splitDetailsOfFile[5]));
							//Checks if the book is null and if not appends to the genre array
							if (book != null) {
							switch (i) {
							case 0: 
								CartoonComicsArr[k] = book;
								break;
							case 1: 
								HoobiesCollectiblesArr[k] = book;
								break;
							case 2: 
								MoviesTVArr[k] = book;
								
								break;
							case 3: 
								MusicRadioArr[k] = book;
								break;
							case 4: 
								EclecticArr[k] = book;
								break;
							case 5: 
								OldTimeRadioArr[k] = book;
								break;
							case 6: 
								SportsArr[k] = book;
								break;
							case 7: 
								TrainsPlanesAutoArr[k] = book;
								break;
							}
							}
							
							
						}
						catch (BadIsbn10Exception e) {
							
							error.write("sematic error in file: "+nameOfFiles[i]+"\n");
							error.write("====================");
							error.newLine();
							error.write(e.getMessage());
							error.write("\nRecord: "+detailsOfTxtFile[i]);
							error.newLine();
							error.newLine();
							
							
						}
						catch (BadIsbn13Exception e) {
							
							error.write("sematic error in file: "+nameOfFiles[i]+"\n");
							error.write("====================");
							error.newLine();
							error.write(e.getMessage());
							error.write("\nRecord: "+detailsOfTxtFile[i]);
							error.newLine();
							error.newLine();
						
						}
						catch (BadPriceException e) {
							error.write("sematic error in file: "+nameOfFiles[i]+"\n");
							error.write("====================");
							error.newLine();
							error.write(e.getMessage());
							error.write("\nRecord: "+detailsOfTxtFile[i]);
							error.newLine();
							error.newLine();							
						}
						catch (BadYearException e) {
							error.write("sematic error in file: "+nameOfFiles[i]+"\n");
							error.write("====================");
							error.newLine();
							error.write(e.getMessage());
							error.write("\nRecord: "+detailsOfTxtFile[i]);
							error.newLine();
							error.newLine();
							
						}
						
						
						
						
					}
				} catch (NumberFormatException e) {
					System.out.println(e);
				}	
				
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		error.close();
	}
		catch (IOException e) {
			System.out.println(e);
		}
		
		//Removes the null elements in the array and writes to seralized file
		for (int i = 0; i < nameOfFiles.length; i++) {
			 Book[] removenull = null;
			switch (i) {
			case 0: 
				removenull = removeNull(CartoonComicsArr);
				break;
			case 1:
				removenull = removeNull(HoobiesCollectiblesArr);
				break;	
			case 2:
				removenull = removeNull(MoviesTVArr);
				break;
			case 3:
				removenull = removeNull(MusicRadioArr);
				break;
			case 4:
				removenull = removeNull(EclecticArr);
				break;
			case 5:
				removenull = removeNull(OldTimeRadioArr);
				break;
			case 6:
				removenull = removeNull(SportsArr);
				break;
			case 7:
				removenull = removeNull(TrainsPlanesAutoArr);
				break;
		}
		 serializeBookObject(removenull,nameOfFiles[i]+".ser");
		}
		
	}
	
	
	/**
	 * Custom method
	 * @param fileFields Takes a record as a parameter
	 * @throws BadIsbn10Exception Custom Exception
	 * @throws BadIsbn13Exception Custom Exception
	 * @throws BadPriceException  Custom Exception
	 * @throws BadYearException   Custom Exception
	 */
	public static void exceptionCheckerPart2(String[] fileFields) throws BadIsbn10Exception, BadIsbn13Exception, BadPriceException, BadYearException{
		for(int i = 0; i < fileFields.length; i++) {
			//Checks Year
			if (i == 5) {
				if (Integer.parseInt(fileFields[5]) < 1995 || Integer.parseInt(fileFields[5]) > 2010) {
					throw new BadYearException();
				}
			}
			//Checks price
			if (i == 2) {
				if (Double.parseDouble(fileFields[2]) < 0) {
					throw new BadPriceException();
				}
			}
			//ISBN-10 & ISBN-13 Check
			if (i == 3 && (fileFields[3].length() == 10 || (fileFields[3].length() == 13))) {
				if (fileFields[3].length() == 10) {
					int isbnValidationIsbn10 = 0;
					for(int n = 0; n < 10; n++) {
						char charvalue = fileFields[3].charAt(n);
						int isbn = charvalue - '0';
						switch (n) {
						case 0:
							isbn*=10;
							break;
						case 1:
							isbn*=9;
							break;
						case 2:
							isbn*=8;
							break;
						case 3:
							isbn*=7;
							break;
						case 4:
							isbn*=6;
							break;
						case 5:
							isbn*=5;
							break;
						case 6:
							isbn*=4;
							break;
						case 7:
							isbn*=3;
							break;
						case 8:
							isbn*=2;
							break;
						case 9:
							isbn*=1;
							break;	
						}
						isbnValidationIsbn10 += isbn;
					}
					if(isbnValidationIsbn10 % 11 != 0) {
						throw new BadIsbn10Exception();
					}
				}
				if (fileFields[3].length() == 13) {
					int isbnValidationIsbn13 = 0;
					for (int n = 0; n < 13; n++) {
						char charvalue = fileFields[3].charAt(n);
						int isbn = charvalue - '0';
						switch (n) {
						case 1:
							isbn*=3;
							break;
						case 3:
							isbn*=3;
							break;
						case 5:
							isbn*=3;
							break;
						case 7:
							isbn*=3;
							break;
						case 9:
							isbn*=3;
							break;
						case 11:
							isbn*=3;
							break;	
						default:
							isbn*=1;
						}
						isbnValidationIsbn13 += isbn;
					}
					if (isbnValidationIsbn13 % 10 != 0) {
						throw new BadIsbn13Exception();
					}
				}
			}
			
		}
		
		
		
	}
	
	
	
	/**
	 * Removes thrown book objects from array
	 * @param arr Array which needs null element removed
	 * @return replacement Array without null elements
	 */
	public static Book[] removeNull(Book[] arr) {
		int notNullCount = 0;
		//Loops through the array and increments whenever there is no null object
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				notNullCount++;
			}
		}	//Creates a new array the length of the not null count
		Book[] replacement = new Book[notNullCount];
		int index = 0;
		//Loops through and appends the not null elements to the new array
		for (Book element: arr) {
			if (element != null) {
				replacement[index] = element;
				index++;
			}
		}
		return replacement;
	}
	
	/**
	 * Seralizes book Objects
	 * @param arr Takes in an array of book objects
	 * @param file Destination file
	 */
	public static void serializeBookObject(Book[] arr,String file) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			 objectOutputStream.writeObject(arr);
			 objectOutputStream.flush();
			 objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * reading the binary files, deserialize the array objects in each file, and
	 * then provide an interacive program to allow the user to navigate the arrays.
	 */
	public static void do_part3() {
		String[] nameOfFiles = {"Cartoon_Comics.csv.ser", "Hobbies_Collectibles.csv.ser", "Movies_TV_Books.csv.ser", "Music_Radio_Books.csv.ser","Nostalgia_Eclectic_Books.csv.ser", "Old_Time_Radio_Books.csv.ser", "Sports_Sports_Memorabilia.csv.ser", "Trains_Planes_Automobiles.csv.ser"};
		Book[] cartoonArr = null;
		Book[] hobbieArr = null;
		Book[] MoviesTvArr = null;
		Book[] musicRadioArr = null;
		Book[] EclecticArr = null;
		Book[] oldTimeArr = null;
		Book[] sportArr = null;
		Book[] autoArr = null;
		
		//Loops through by the length of text files
		for (int i = 0 ; i < nameOfFiles.length; i++) {
		try {
			FileInputStream fileIn = new FileInputStream(nameOfFiles[i]);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			//Based on the text file it reads in the object from the seralized file
			switch (i) {
			case 0:
				cartoonArr = (Book[])in.readObject();
			
				break;
			case 1: 
				hobbieArr = (Book[]) in.readObject();

				break;
			case 2:
				MoviesTvArr = (Book[]) in.readObject();

				break;
			case 3: 
				musicRadioArr = (Book[]) in.readObject();

				break;
			case 4: 
				EclecticArr = (Book[]) in.readObject();

				break;
			case 5:
				oldTimeArr = (Book[]) in.readObject();
				break;
			case 6: 
				sportArr = (Book[]) in.readObject();

				break;
			case 7: 
				autoArr = (Book[]) in.readObject();
				break;
				
			}
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}
		
		}
		
		String choice;
		Scanner in = new Scanner(System.in);
		String selectedFile = " ";
		int fileChoice = 0;
		int numOfRecords = 0;
		//2d array so we can call indivdual properties
		Book[][] library = {cartoonArr, hobbieArr, MoviesTvArr, musicRadioArr, EclecticArr, oldTimeArr, sportArr, autoArr};
		
		
		//Interactive menu
		do{	
			menu();
			choice = in.next();
			switch (choice.toLowerCase()) {
			//Select a file to view
			case "s":
				System.out.println("--------------------------------");
				System.out.println("         FILE SUB-MENU             ");
				System.out.println("--------------------------------");
				//Displays the name of the files and their number of records
				for (int i = 0 ; i < nameOfFiles.length; i++) {
					numOfRecords = library[i].length;
					System.out.println(" "+ (i+1) + " " + nameOfFiles[i] +" (" + numOfRecords+" records)");
				}
				System.out.println(" 9 Exit");
				System.out.println("--------------------------------");
				System.out.println("\nEnter Your Choice: ");
				fileChoice = in.nextInt();
				if (fileChoice == 9) {
					System.exit(0);
				}
				break;
			case "v":
				switch (fileChoice) {
				case 1: 
					selectedFile = nameOfFiles[0];
					break;
				case 2: 
					selectedFile = nameOfFiles[1];
					break;
				case 3: 
					selectedFile = nameOfFiles[2];
					break;
				case 4: 
					selectedFile = nameOfFiles[3];
					break;
				case 5: 
					selectedFile = nameOfFiles[4];
					break;
				case 6: 
					selectedFile = nameOfFiles[5];
					break;
				case 7: 
					selectedFile = nameOfFiles[6];
					break;
				case 8: 
					selectedFile = nameOfFiles[7];
					break;
					
				}
				//Displays the selected file and the number of records
				System.out.println("viewing: " + selectedFile + " ("+ library[fileChoice-1].length+" records)");
				int n = 1;
				int currentN = 0;
				int newN;
				//Loops unless user enters 0
				while (n!=0) {
					try {
						System.out.println("Please enter an n value (0 to exit): ");
						n = in.nextInt();
							//Breaks if n = 0
						if (n == 0) {
							break;
						}
						//Adds currentN and if n is greater than 0, it subtracts 1 from n then adds, if not then adds 1
						newN = currentN + (n > 0? n-1: n+1);
						//checks if user entered a value greater than 0, so positive cases
						if (newN > currentN) {
							try {
								//Loop between the current N value and the newly assigned N value
							for(int i = currentN; i <= newN; i++) {
								System.out.println("Book #"+ (i+1) +": "+ library[fileChoice-1][i]);
							}
							}catch(ArrayIndexOutOfBoundsException e) {
								System.out.println("EOF has been reached");
								//Sets the new N value to last value in the text file
								newN = library[fileChoice].length-1;
							}
						}
						//Checks if user entered a value less than 0, so negative cases
						else if (newN < currentN) {
							try {
								//Loops between current N value and the new assigned N value
							for (int i = currentN; i >= newN; i--) {
								System.out.println("Book #"+ (i+1) +": "+ library[fileChoice-1][i]);
							}
							} catch (ArrayIndexOutOfBoundsException e) {
								System.out.println("EOF has been reached");
								//Sets the new N to the first value in the text file
								newN = 0;
							}
						}//Prints current book
						else if (currentN == newN) {
							System.out.println("Book #"+ (currentN+1) +": "+ library[fileChoice-1][currentN]);
						}
						//Updates N value
						currentN = newN;
					//Checks if input is an integer
					}catch (InputMismatchException e) {
						System.out.println("Please enter a valid number: ");
						in.nextLine();
					}
				
				}
				break;
			case "x":
				break;
			default:
				System.out.println("Invalid Choice");
			}
			
		}
		while (!choice.equalsIgnoreCase("x"));
		in.close();
		
			
		
	}
	
	/**
	 * Menu display
	 */
	public static void menu() {
		System.out.println("--------------------------------");
		System.out.println("          MAIN MENU             ");
		System.out.println("--------------------------------");
		System.out.println(" v View the selected file: ");
		System.out.println(" s Select a file to view");
		System.out.println(" x Exit");
		System.out.println("--------------------------------");
		System.out.print("\nEnter Your Choice: ");
	}
	
	
	
	
}
	
