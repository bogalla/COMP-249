// ----------------------------------------------------------
// Assignment 2
// Question: Part I question 2
// Written by: Brianne O'Gallagher-Roy (40058629)
// This is the Fair class that extends Event. It has 2 additional
// attributes: an enum Type that can be Career, Science, Trade or Travel, and an int
// numExhibitors. it has a default constructor, a parameterized constructor 
// and a copy constructor, as well as a toString(), an equals() and all its getters and setters
// ----------------------------------------------------------

package packageFour;
import packageOne.Event;


public class Fair extends Event{
	private int numExhibitors;
	public enum Type {Career, Science, Trade, Travel};
	private Type type;
	
	public Fair(){
		
	}
	public Fair(int year, int month, int numCities, int numExhibitors, Type type){
		setYear(year);
		setMonth(month);
		setNumCities(numCities);
		this.numExhibitors = numExhibitors;
		this.type = type;
	}
	public Fair(Fair f){
		setYear(f.getYear());
		setMonth(f.getMonth());
		setNumCities(f.getNumCities());
		this.numExhibitors = f.getNumExhibitors();
		this.type = f.getType();
	}
	
	public String toString(){
		return("The "+type+" Fair will be held in month number "+getMonth()+", in the year "+getYear()+" and in "
				+getNumCities()+ " cities. There will be "+numExhibitors+" exhibitors");
	}
	//this protects against the possibility that the object being compared to is null, but the
		//program will still crash if the calling object is null
		public boolean equals(Fair f){
			if (f == null || getClass() != f.getClass())
				return false;		
			return(getYear() == f.getYear() && f.getMonth() == getMonth() && f.getNumCities() == getNumCities()&& numExhibitors ==
					f.getNumExhibitors() && type == f.getType());	
		}
	
	//getters and setters
	public int getNumExhibitors(){
		return numExhibitors;
	}
	public Type getType(){
		return type;
	}
	public void setNumExhibitors(int num) {
		this.numExhibitors = num;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
}
