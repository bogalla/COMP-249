import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Brianne O'Gallagher-Roy 40058629 
 * COMP249  Assignment # 4
 * Due Date Monday, April 16 2018
 * @author Brianne
 *
 *This program implements the CourseList/CourseNode and Course classes. It reads a text document called "syllabus"
 *and creates a CourseList out of the courses posted there, so that each instance contains a course and a pointer to the 
 *next node. Then the user will enter a "Request#.txt" file to find out if the student has the pre requisites and the 
 *co requisites to enroll in the requested classes. then the user will enter some course IDs, and the program will
 *tell if they are directly related or not. then the program will test the functions from CourseList
 */
public class EnrolmentResults {

	public static void main(String[] args) {
		System.out.println("WELOCME TO MY PROGRAM\n\n");
		//create a scanner to read user inputs and one to read syllabus
		Scanner lock = new Scanner(System.in);
		Scanner syllreader = null;
	
		try{
			syllreader = new Scanner(new FileInputStream("Syllabus.txt"));
		}catch(FileNotFoundException e){  //terminate if could not open
			String s = e.getMessage();
			System.out.println(s+"\nProgram will terminate immediately");
			System.exit(0);
		}
		
		
		//create the list
		CourseList syllabus = new CourseList();
		Course c = null;
		while(syllreader.hasNextLine()){
			String s = syllreader.nextLine(); //go to next line
			if(s.contains("COMP")){
				String[] line1 = s.split("\\s+"); //split the line with spaces and ignore multiple whitespaces
				String ID = line1[0]; //ID is always the first thing
				String name = line1[1];		//name is the second thing
				String credits = line1[2];	//credit is the last thing on the line, 
													//so you must increment line
				s = syllreader.nextLine();
				String[] line2 = s.split("\\s+");
				String preReq;
				if(line2.length == 2)	//if there is a prereq
					preReq = line2[1];
				else					//if not
					preReq = null;

				s = syllreader.nextLine();
				String[] line3 = s.split("\\s+");
				String coReq;
				if(line3.length == 2)	//if there is a coreq
					coReq = line3[1];
				else
					coReq = null;		//if not
				double credit = Double.parseDouble(credits); //change the credit string into a double
				
				c = new Course(ID, name, credit, preReq, coReq); // create new course!!
				if(!syllabus.contains(c.getCourseID())) //if it contains it already, don't add it
					syllabus.addToStart(c);
			}
		}
		System.out.println("\n\nSyllabus has been read.\n");
		System.out.println("enter a request file: ");
		String request = lock.next();
		
		syllreader.close();
		Scanner sc = null;
		try{
			sc = new Scanner(new FileInputStream(request)); 
		}catch(FileNotFoundException e){ //will end if name is wrong
			String s = e.getMessage();
			System.out.println(s+"\nProgram will now end");
			System.exit(0);
		}
		//create an arrayList for each section
		ArrayList<String> finished = new ArrayList<String>();
		ArrayList<String> requested = new ArrayList<String>();
		
		//fill "finished" arraylist as well as "requested"
		String s = sc.nextLine();
		while(sc.hasNextLine()){
			if(s.contains("Finished")){
				s = sc.nextLine();
				while(!s.contains("Requested")){
					finished.add(s);
					s = sc.nextLine();
				}
			}
			if(sc.hasNextLine()){
				s = sc.nextLine();
				requested.add(s);
			}
		}
		sc.close();
		
		//go through requested and look at course ID
		for(int i = 0; i < requested.size();i++){
			boolean preboolean = false;
			boolean coboolean = false;
			String courseID = requested.get(i);
			CourseList.CourseNode t = syllabus.find(courseID);
			
			String pre = t.getCourse().getPreReqID();
			String co = t.getCourse().getCoReqID();
			
			//in the case that the co-req and pre req are the same, make co-req have precedence
			if(pre.equals(co))
				pre = null;
			
			//PREREQ
			if(pre == null || finished.contains(pre))
				preboolean = true;
			//COREQ
			if(co == null || finished.contains(co) || requested.contains(co))
				coboolean = true;
			//if any if them are false print this
			if(!(coboolean && preboolean))
				System.out.println("Student can't enroll in "+courseID+" as he/she doensn't have sufficient background needeed");
			//DECIDE WHICH ERROR MESSAGE TO PRINT
			else if(co == null && pre != null && preboolean)
				System.out.println("Student can enroll in "+courseID+" as he/she has completed pre-requisite "+pre);
			else if(pre == null && co != null && coboolean)
				System.out.println("Student can enroll in "+courseID+" as he/she has completed or will complete co-requisite "+co);
			else if(pre != null && co != null && coboolean && preboolean)
				System.out.println("Student can enroll in "+courseID+" as he/she has completed pre-requisite "+pre+" and co-requisite "+co);
			else if(co == null && pre == null)
				System.out.println("Student can enroll in "+courseID+" as there is no pre-requisite or co-requisite");
			
		}
		
		if(requested.size() == 0)
			System.out.println("No enrollment classes found.");
			
		System.out.println("\n\nNow enter a course ID for course 1");
		
		//create pointer to the courseNode1
		String userID = lock.next();
		CourseList.CourseNode courseNode1 = syllabus.find(userID);
		
		System.out.println("great! now enter another");
		
		//create pointer to the courseNode2
		userID = lock.next();
		CourseList.CourseNode courseNode2 = syllabus.find(userID);
		
		System.out.println("and one more!");
		
		//create pointer to the courseNode3
		userID = lock.next();
		CourseList.CourseNode courseNode3 = syllabus.find(userID);
		
		//create course1
		Course c1 = courseNode1.getCourse();
		//create course 2
		Course c2 = courseNode2.getCourse();
		//create course3
		Course c3 = courseNode3.getCourse();
		
//TEST ISDIRECTLYRELATED()
		System.out.println("\n\ntest if "+c1.getCourseID()+" and "+c2.getCourseID()+" are directly related:");
		System.out.println(c1.isDirectlyRelated(c2));
		
		System.out.println("\n\ntest if "+c1.getCourseID()+" and "+ c3.getCourseID()+" are directly related:");
		System.out.println(c1.isDirectlyRelated(c3));
		
		System.out.println("\n\ntest if "+c3.getCourseID()+" and "+c2.getCourseID()+" are directly related:");
		System.out.println(c3.isDirectlyRelated(c2));
		
		
//TESTING COURSELIST FUNCTIONS
		System.out.println("Okay! will now test some functions\n\n");
		CourseList test1 = new CourseList(); //an empty list
		System.out.println("testing toString on an empty list:");
		System.out.println(test1.toString());	
		
		
		System.out.println("\nInsert at index 0:");
		test1.insertAtIndex(c1,0);
		System.out.println(test1.toString());
		
		
		System.out.println("\ntesting contains course c(should be true):");
		System.out.println(test1.contains(c1.getCourseID()));
		
		System.out.println("\ndelete from start");
		test1.deleteFromStart();
		System.out.println(test1.toString());
		
		System.out.println("\ntesting contains course c(should be false):");
		System.out.println(test1.contains(c1.getCourseID()));
		
		System.out.println("\nAdd to start c1 then c2:");
		test1.addToStart(c1);
		
		test1.addToStart(c2);
		System.out.println(test1.toString());
		
		
		System.out.println("testing copy constructor, here is test2:");
		CourseList test2 = new CourseList(test1);
		System.out.println(test2.toString());
		
		System.out.println("checking equals method(should be true):");
		System.out.println(test1.equals(test2));
		
		
		System.out.println("\ntest2 Replace at index 1:");
		test2.replaceAtIndex(c3,1);
		System.out.println(test2.toString());
		
		System.out.println("now testing equals again");
		System.out.println(test1.equals(test2));
		
		System.out.println("\ntest2 delete from index 1");
		test2.deleteFromIndex(1);
		System.out.println(test2.toString());
		
		System.out.println("\ninsert c3 into test2 at index 1");
		test2.insertAtIndex(c3, 1);
		System.out.println(test2.toString());
		
		
		System.out.println("PROGRAM IS FINISHED. THANK YOU");
	}

}
