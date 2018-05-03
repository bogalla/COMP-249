// ----------------------------------------------------------
// Assignment 2
// Question: Part I question 3, Part II
// Written by: Brianne O'Gallagher-Roy (40058629)
// This driver class creates 12 objects from the different classes and displays them to test the 
// toString() method. Then compare various objects to test the equals() method. The program then
// finds the event(s) with the most and least number of cities. The program next determines which
// event happens in the same year and prints them. Next, another array is created and the method
// copyFestival() is used. copyFestival() takes an array of Event objects and returns an array of
// the same length, with only the instances of Festival remaining. the program then tests this method
// and prints the results
// ----------------------------------------------------------

package driverPackage;

import java.util.ArrayList;
import java.util.List;
import packageOne.Event;
import packageTwo.Festival;
import packageTwo.CulturalFiesta;
import packageTwo.MusicFiesta;
import packageThree.SportCompetition;
import packageThree.SportCompetition.Season;
import packageFour.Fair;
import packageFour.Fair.Type;

public class Driver {

	public static void main(String[] args) {
		
		//create 10 event objects from the various classes
		Event e1 = new Event(2007, 06, 6);
		Event e2 = new Event(2007, 06, 6);
		
		Festival f1 = new Festival(2008, 12, 7, "festival", 5.98, 9);
		
		CulturalFiesta c1 = new CulturalFiesta(2009, 04, 8, "samba dance", 7.88, 3, 4);
		CulturalFiesta c2 = new CulturalFiesta(2008, 04, 8, "samba dance", 7.88, 3, 4);
		
		MusicFiesta m1 = new MusicFiesta(2010, 10, 2, "bluesfest", 10.63, 20, 25);
		MusicFiesta m2 = new MusicFiesta(2010, 10, 2, "bluesfest", 10.63, 20, 25);
		
		SportCompetition s1 = new SportCompetition(2008, 07, 5, 10, Season.Summer);
		
		Fair fr1 = new Fair(2010, 8, 4, 20, Type.Career);
		Fair fr2 = new Fair(2010, 8, 4, 20, Type.Science);
		
		
		//check their toStrings()
		System.out.println("testing toString()...");
		System.out.println(e1+"\n"+f1+"\n"+c1+"\n"+m1+"\n"+s1+"\n"+fr1+"\n");
		
		//check the equals method
		System.out.println("testing equals()...");
		System.out.println(e1.equals(e2)+"(should be true)");//two Event objects with identical parameters
		System.out.println(e1.equals(f1)+"(should be false)");//an Event and a Festival are not the same
		System.out.println(m1.equals(m2)+"(should be true)");//two identical MusicFiesta objects
		System.out.println(fr1.equals(fr2)+"(should be false)");//two Fairs with a different parameter
		System.out.println(c1.equals(s1)+"(should be false)");//comparing 2 different classes
		
		//create an array with all 10 Events. initialize max and min as the first one
		Event[] arr = {e1,e2,f1,c1,c2,m1,m2,s1,fr1,fr2};
		Event max = arr[0];
		Event min = arr[0];
		
		//compare the current max number of cities to the next one. if the next one is bigger, it is 
		//the new max. find the index. repeat with the min, except check if the next object is s
		for (int i = 0; i< arr.length-1;i++){
			if(arr[i+1].getNumCities() < min.getNumCities()){
				min = arr[i];
			}
			else if (arr[i+1].getNumCities() > max.getNumCities()){
				max = arr[i];
			}
		}
		
		//print out all the events with the max number of cities and their index
		System.out.println("\nmax number of cities: ");
		for (int i = 0; i < arr.length; i++){
			if(arr[i].getNumCities() == max.getNumCities()){
				System.out.println(arr[i]);
				System.out.println("index of: "+i);
			}
		}
				
		
		//print out all the events with the min number of cities and their index
		System.out.println("\nmin number of cities: ");
		for (int i = 0; i < arr.length; i++){
			if(arr[i].getNumCities() == min.getNumCities()){
				System.out.println(arr[i]);
				System.out.println("index of: "+i);
			}
		}
		
		System.out.println("\nNow find events that take place in the same year:\n");
		
		//create a list and double nested for loop. if the year is on the list already, continue. we do not 
		//want to repeat years. if the year only occurs once, continue because we only want to print out
		//2+ events that happen in the same year. then print that the following happen in the year XXXX
		//and add that year to the list so it is not repeated. then print out all the events from that 
		//year and leave a space
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++){
			for (int j = i+1; j < arr.length-1;j++){
				if(list.contains(arr[i].getYear()) || arr[i].getYear() != arr[j].getYear()){
					continue;
				}
				else{
					System.out.println("the following events took place in "+arr[i].getYear()+"\n"+
							arr[i] + "(index number "+i+")");
						list.add(arr[i].getYear());
				}
				if(arr[i].getYear() == arr[j].getYear()){
					System.out.println(arr[j] + "(index number "+j+")");
				}
			}
			System.out.println("");
		}
		
		//create 2 more events for a total of 12
		Festival f2 = new Festival(2011, 12, 7, "festival", 5.98, 9);
		SportCompetition s2 = new SportCompetition(2008, 07, 5, 10, Season.Winter);
		
		//create an array with the 12 and use copyFestival() to make a copy with only the instances of
		//festival in it
		Event[] array1 = {e1,e2,f1,f2,c1,c2,m1,m2,s1,s2,fr1,fr2};
		Event[] array2 = copyFestival(array1);
		
		//print array1
		System.out.println("print first array");
		for(int i = 0; i < array1.length; i++){
			System.out.println(array1[i]);
		}
		System.out.println("\n");
		
		//print array2
		System.out.println("print second array");
		for(int i = 0; i < array2.length; i++){
			System.out.println(array2[i]);
		}
		//closing message
		System.out.println("====================================");
		System.out.println(" thanks for using! program is over");
		System.out.println("====================================");
	}

	
	//COPY FESTIVAL
	//this method takes an array of events and returns another array of events of the same length.
	//it uses the copy constructor of Event and relies on polymorphism to figure out what type of
	//event it is. it then returns the copied array.
	public static Event[] copyFestival(Event[] e){
		Event[] arr = new Event[e.length];
		for (int i = 0; i < arr.length; i++){
				arr[i] = new Event(e[i]);
		}
		return arr;
	}
	//WHY THIS DOESNT WORK
	//this method actually only returns Events instead of Fesivals, CulturalFiestas, Fairs, etc.
	//this is because the compiler sees the Event copy constructor with an event object being passed,
	//and thus can only create an event class. In the use of copy construtors in this method, 
	//polymorphism is actually switched off. to remedy this problem, a clone method should be created
	//for each class, and then instead of calling the Event copy constructor, the clone() method 
	//is called and polymorphism works again. 

}
