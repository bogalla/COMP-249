/**
 * Brianne O'Gallagher-Roy
 * Assignment # 1
 * Due Date Wednesday, January 31, 2018 
 * A book class with title, author, ISBN, price, and number of books as attributes
 * @author Brianne
 *@version 1.0
 */
public class Book {
	private String title;
	private String author;
	private long isbn;
	private double price;
	private static int numBooks = 0;
	
	/**
	 * Constructor - builds a book object, initializes all attributes to zero of 
	 * the type, adds to the number of books
	 */
	public Book(){
		title = null;
		author = null;
		isbn = 0;
		price = 0;
		numBooks++;
	}
	/**
	 * Constructor - builds a book object, initializes all attributes to 
	 * the user's choice, adds to the number of books
	 * 
	 * @param title The title of the book
	 * @param author The author of the book
	 * @param isbn The ISBN number of the book, no decimals
	 * @param price The price of the book, can have decimals
	 */
	public Book(String title, String author, long isbn, double price){
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		numBooks++;
	}

	//getters
	/**
	 * @return a String, the title of the book
	 */
	public String getTitle(){
		return title;
	}
	/**
	 * @return a String, the author of the book
	 */
	public String getAuthor(){
		return author;
	}
	/**
	 * @return a long, the isbn of the book
	 */
	public long getIsbn(){
		return isbn;
	}
	/**
	 * @return a double, the price of the book
	 */
	public double getPrice(){
		return price;
	}
	
	/**
	 * @param title The new title of the book
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * @param author The new author of the book
	 */
	public void setAuthor(String author){
		this.author = author;
	}
	/**
	 * @param isbn The new ISBN of the book
	 */
	public void setIsbn(long isbn){
		this.isbn = isbn;
	}

	/**
	 * @param price The new price of the book
	 */
	public void setPrice(double price){
		this.price = price;
	}
	
	/**
	 * A toString method to print the attributes
	 * @return a String stating the attribute values
	 */
	public String toString(){
		return ("title: " +title+ ", author: " +author+ ", ISBN: " +isbn+ ","
				+ " price: " +price);
	}

	/**
	 * A toString method to print the attributes
	 * @return a String stating the attribute values
	 * @param num (To be paired with an array of Book object) This is the index of the book in the array.
	 */
	public String toString(int num){
		return("Book #"+num+"\nAuthor: " + author+ "\nTitle: "+title+ "\nISBN: "+isbn+ "\nPrice: $" +price);
	}
	
	/**
	 * @return the static int attribute that represents the number of book objects created
	 */
	public static int findNumberOfCreatedBooks(){
		return numBooks;
	}
	
	/**
	 * an equals() method to compare two books
	 * @param b the book to be compared to the calling object
	 * @return true if the price and the ISBN are the same
	 */
	public boolean equals(Book b){
		return (isbn == b.getIsbn() && price == b.getPrice());
	}
	
	/**
	 * finds all books from an array of a specific author
	 * @param a an array of book objects
	 * @param auth The author the user chooses, method finds all books by this author
	 */
	public static void findBooksBy(Book[] a, String auth){
		for (int i = 0; i < a.length; i++){
			if(a[i] != null && a[i].getAuthor().compareTo(auth)==0)
				System.out.println(a[i].toString(i)+"\n");
		}
	}
	
	/**
	 * finds all books from an array under a specific price
	 * @param a an array of book objects
	 * @param pri The price the user chooses, method finds all books under this price
	 */
	public static void findCheaperThan(Book[] a, double pri){
		for (int i = 0; i < a.length; i++){
			if(a[i].getPrice() < pri && a[i] != null)
				System.out.println(a[i].toString(i)+"\n");
		}
	}
	
	
}