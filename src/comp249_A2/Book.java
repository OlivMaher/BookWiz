package comp249_A2;

import java.io.Serializable;

//-----------------------------------------------------
// Assignment #2
// Question: Part #2
// Written by: Olivier Maher 40235064
//-----------------------------------------------------

public class Book implements Serializable{
	private String title;
	private String author;
	private double price;
	private String ISBN;
	private String genre;
	private int year;
	
	/**
	 * 
	 * @param title title of book
	 * @param author author of book
	 * @param price price of book
	 * @param ISBN ISBN of book
	 * @param genre genre of book
	 * @param year year of book
	 */
	public Book(String title, String author, double price, String ISBN, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = ISBN;
		this.genre = genre;
		this.year = year;
	}
	/**
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @param title title of book
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 
	 * @param author author of book
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price pirce of book
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * 
	 * @return ISBN
	 */
	public String getISBN() {
		return ISBN;
	}
	/**
	 * 
	 * @param iSBN ISBN of book
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	/**
	 * 
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * 
	 * @param genre genre of book
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * 
	 * @param year year of book
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return this.title + ","+this.author+","+this.price+","+this.ISBN+","+this.genre+","+this.year;
	}
	/**
	 * 
	 * @param another Book Object
	 * @return result True if objects are the same, false if not
	 */
	public boolean equals(Book another) {
		boolean result = false;
		if (another == null) {
			result = false;
		}
		if (this.title == another.title && this.author == another.author && this.price == another.price && this.ISBN == another.ISBN && this.year == another.year) {
			result = true;
		}
		return result;
	}
	

}
