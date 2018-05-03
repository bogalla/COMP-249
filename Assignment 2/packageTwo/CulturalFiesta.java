// ----------------------------------------------------------
// Assignment 2
// Question: Part I question 2
// Written by: Brianne O'Gallagher-Roy (40058629)
// This is the CulturalFiesta class that extends Festival that extends Event. It has 1 additional
// attribute: an int maxNumLanguages. it has a default constructor, a parameterized constructor 
// and a copy constructor, as well as a toString(), an equals() and all its getters and setters
// ----------------------------------------------------------


package packageTwo;


public class CulturalFiesta extends Festival{
	private int maxNumLanguages;
	
	public CulturalFiesta(){
		
	}
	public CulturalFiesta(int year, int month, int numCities, String name, double ticketPrice, int numDays, int maxLanguages){
		setYear(year);
		setMonth(month);
		setNumCities(numCities);
		setName(name);
		setTicketPrice(ticketPrice);
		setNumDays(numDays);
		this.maxNumLanguages = maxLanguages;
	}
	public CulturalFiesta(CulturalFiesta c){
		setYear(c.getYear());
		setMonth(c.getMonth());
		setNumCities(c.getNumCities());
		setName(c.getName());
		setTicketPrice(c.getTicketPrice());
		setNumDays(c.getNumDays());	
		maxNumLanguages = c.getNumLanguages();
	}
	
	public String toString(){
		return("The Cultural Fiesta will be held in month number "+getMonth()+", in the year "+getYear()+", and in "
				+getNumCities()+ " cities. The name of the Fiesta is "+getName()+", the ticket price is "+getTicketPrice()+
				" and it goes on for "+getNumDays()+" days. The maximum number of languages is "+maxNumLanguages);
	}
	//this protects against the possibility that the object being compared to is null, but the
		//program will still crash if the calling object is null
	public boolean equals(CulturalFiesta c){
		if (c == null || getClass() != c.getClass())
			return false;		
		return(getYear() == c.getYear() && c.getMonth() == getMonth() && c.getNumCities() == getNumCities()&&
				getName() == c.getName() && getTicketPrice() == c.getTicketPrice() && getNumDays() == c.getNumDays() && maxNumLanguages ==c.getNumLanguages());
	}
	
	//getters and setters
	public int getNumLanguages(){
		return maxNumLanguages;
	}
	public void setNumLanguages(int num){
		maxNumLanguages = num;		
	}
}
