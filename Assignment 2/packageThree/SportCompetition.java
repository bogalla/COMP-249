// ----------------------------------------------------------
// Assignment 2
// Question: Part I question 2
// Written by: Brianne O'Gallagher-Roy (40058629)
// This is the SportCompetition class that extends Event. It has 2 additional
// attributes: an enum Season that can be Summer, Fall, Winter or Spring, and an int
// numActivities. it has a default constructor, a parameterized constructor 
// and a copy constructor, as well as a toString(), an equals() and all its getters and setters
// ----------------------------------------------------------

package packageThree;
import packageOne.Event;


public class SportCompetition extends Event {
	private int numActivities;
	public enum Season {Summer, Fall, Winter, Spring};
	private Season season;
	
	public SportCompetition(){
		
	}
	public SportCompetition(int year, int month, int numCities, int numActivities, Season season){
		setYear(year);
		setMonth(month);
		setNumCities(numCities);
		this.numActivities = numActivities;
		this.season = season;
	}
	public SportCompetition(SportCompetition s){
		setYear(s.getYear());
		setMonth(s.getMonth());
		setNumCities(s.getNumCities());
		this.numActivities = s.getNumActivities();
		this.season = s.getSeason();
	}
	
	public String toString(){
		return("The Sport Competition will be held in month number "+getMonth()+", in the year "+getYear()+" and in "
				+getNumCities()+ " cities. There will be "+numActivities+" activities and it will be held in the "+season);
	}
	//this protects against the possibility that the object being compared to is null, but the
		//program will still crash if the calling object is null
	public boolean equals(SportCompetition s){
		if (s == null || getClass() != s.getClass())
			return false;		
		return(getYear() == s.getYear() && s.getMonth() == getMonth() && s.getNumCities() == getNumCities()&&
				numActivities == s.getNumActivities() && season == s.getSeason());
	}
	//getters and setters
	public void setNumActivities(int numActivities) {
		this.numActivities = numActivities;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public int getNumActivities(){
		return numActivities;
	}
	public Season getSeason(){
		return season;
	}
}
