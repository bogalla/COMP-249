// ----------------------------------------------------------
// Assignment 2
// Question: Part I question 2
// Written by: Brianne O'Gallagher-Roy (40058629)
// This is the Event class that is the ancestor to all the following classes. It has 3 attributes:
// an int year, an int month and an int numCities. it has a default constructor, a parameterized 
// constructor and a copy constructor, as well as a toString(), an equals() and all its getters 
// and setters
// ----------------------------------------------------------

package packageOne;

public class Event {
	protected int year;
	private int month;
	private int numCities;
	//changing the attribute accessibility to private changes a couple things, mostly in the 
	//festival class. in the other classes, if the attributes were package access, nothing would be different
	//because there is only one class per package. if festival had package access or protected access,
	//it wouldn't matter because both of its sub classes are in the same package, so they would both
	//have access to the attributes either way. If event was protected instead of package access, then
	//all of its subclasses could access its attributes no matter if they were in different packages.
	//If you change everything to private, affects the accessibility of the attributes. instead of 
	//directly accessing or changing the attributes, you must now use getters and setters. this 
	//makes the information more secure.

	public Event(){
	
	}
	public Event(int year, int month, int numCities){
		this.year = year;
		this.month = month;
		this.numCities = numCities;
	}
	public Event(Event e){
		year = e.getYear();
		month = e.getMonth();
		numCities = e.getNumCities();
	}
	
	public String toString(){
		return("The event will be held in month number "+month+", in the year "+year+" and in "
				+numCities+ " cities.");
	}
	
	//this protects against the possibility that the object being compared to is null, but the
	//program will still crash if the calling object is null
	public boolean equals(Event e){
		if (e == null || getClass() != e.getClass())
			return false;		
		return(year == e.getYear() && e.getMonth() == month && e.getNumCities() == numCities);
		
	}
	
	//getters and setters
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getNumCities() {
		return numCities;
	}
	public void setNumCities(int numCities) {
		this.numCities = numCities;
	}

}
