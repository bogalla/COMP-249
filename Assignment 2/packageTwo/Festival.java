// ----------------------------------------------------------
// Assignment 2
// Question: Part I question 2
// Written by: Brianne O'Gallagher-Roy (40058629)
// This is the Festival class that extends event and is the ancestor of both CuturalFiesta and
// MusicFiesta. It has 3 additional attributes: a String name, a double ticketPrice and an 
// int numDays. it has a default constructor, a parameterized constructor and a copy constructor,
// as well as a toString(), an equals() and all its getters and setters
// ----------------------------------------------------------

package packageTwo;
import packageOne.Event;


public class Festival extends Event {
	private String name;
	private double ticketPrice;
	private int numDays;
	
	public Festival(){
		
	}
	public Festival(int year, int month, int numCities, String name, double ticketPrice, int numDays){
		setYear(year);
		setMonth(month);
		setNumCities(numCities);
		this.name = name;
		this.ticketPrice = ticketPrice;
		this.numDays = numDays;
	}
	public Festival(Festival f){
		setYear(f.getYear());
		setMonth(f.getMonth());
		setNumCities(f.getNumCities());
		name = f.getName();
		ticketPrice = f.getTicketPrice();
		numDays = f.getNumDays();	
	}
	
	public String toString(){
		return("The Festival will be held in month number "+getMonth()+", in the year "+getYear()+", and in "
				+getNumCities()+ " cities. The name of the Fiesta is "+name+", the ticket price is "+ticketPrice+
				" and it goes on for "+numDays+" days.");
	}
	//this protects against the possibility that the object being compared to is null, but the
		//program will still crash if the calling object is null
	public boolean equals(Festival f){
		if (f == null || getClass() != f.getClass())
			return false;		
		return(getYear() == f.getYear() && f.getMonth() == getMonth() && f.getNumCities() == getNumCities()&&
				name == f.getName() &&ticketPrice == f.getTicketPrice() && numDays == f.getNumDays());
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public int getNumDays() {
		return numDays;
	}
	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
	
	
}
