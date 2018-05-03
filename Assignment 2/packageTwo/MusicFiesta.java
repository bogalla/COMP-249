// ----------------------------------------------------------
// Assignment 2
// Question: Part I question 2
// Written by: Brianne O'Gallagher-Roy (40058629)
// This is the MusicFiesta class that extends Festival that extends Event. It has 1 additional
// attribute: an int numBands. it has a default constructor, a parameterized constructor 
// and a copy constructor, as well as a toString(), an equals() and all its getters and setters
// ----------------------------------------------------------

package packageTwo;


public class MusicFiesta extends Festival {
	private int numBands;
	
	public MusicFiesta(){

	}
	public MusicFiesta(int year, int month, int numCities, String name, double ticketPrice, int numDays, int numBands){
		setYear(year);
		setMonth(month);
		setNumCities(numCities);
		setName(name);
		setTicketPrice(ticketPrice);
		setNumDays(numDays);
		this.numBands = numBands;
	}
	public MusicFiesta(MusicFiesta m){
		setYear(m.getYear());
		setMonth(m.getMonth());
		setNumCities(m.getNumCities());
		setName(m.getName());
		setTicketPrice(m.getTicketPrice());
		setNumDays(m.getNumDays());	
		numBands = m.getNumBands();
	}
	
	public String toString(){
		return("The Music Fiesta will be held in month number "+getMonth()+", in the year "+getYear()+", and in "
				+getNumCities()+ " cities. The name of the Fiesta is "+getName()+", the ticket price is "+getTicketPrice()+
				" and it goes on for "+getNumDays()+" days. The number of bands is "+numBands);
	}
	//this protects against the possibility that the object being compared to is null, but the
		//program will still crash if the calling object is null
	public boolean equals(MusicFiesta m){
		if (m == null || getClass() != m.getClass())
			return false;		
		return(getYear() == m.getYear() && m.getMonth() == getMonth() && m.getNumCities() == getNumCities()&&
				getName() == m.getName() && getTicketPrice() == m.getTicketPrice() && getNumDays() == m.getNumDays() && numBands ==m.getNumBands());
	}
	
	//getters and setters
	public int getNumBands(){
		return numBands;
	}
	public void setNumBands(int num){
		numBands = num;
	}
}
