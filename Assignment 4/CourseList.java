import java.util.NoSuchElementException;

/**
 * Brianne O'Gallagher-Roy 40058629 
 * COMP249  Assignment # 4
 * Due Date Monday, April 16 2018
 *
 * A class to create a CourseList, with an inner class CourseNode, with various functions,
 * like adding and deleting nodes
 * @author Brianne
 */
public class CourseList {

	class CourseNode{
		private Course course;
		private CourseNode next;
		
		/**
		 * A basic default constructor with no course and a pointer to null
		 */
		public CourseNode(){
			course = null;
			next = null;
		}
		/**
		 * A basic parameterized constructor with a course and a pointer to the next course
		 */
		public CourseNode(Course course, CourseNode next){
			this.course = course;
			this.next = next;
		}
		/**
		 * A basic copy constructor that copies the course and the pointer to the next course
		 */
		public CourseNode(CourseNode c){
			this.course = c.course;
			this.next = c.next;
		}
		/**
		 * clone method that returns an object of type CourseNode
		 * @returns Object of type CourseNode
		 */
		public Object clone(){
			return new CourseNode(this);
		}
		/**
		 * getter for course
		 * @returns a course
		 */
		public Course getCourse() {
			return course;
		}
		/**
		 * setter for course
		 */
		public void setCourse(Course course) {
			this.course = course;
		}
		/**
		 * getter for next courseNode
		 * @returns CourseNode of next
		 */
		public CourseNode getNext() {
			return next;
		}
		/**
		 * setter for CourseNode next
		 */
		public void setNext(CourseNode next) {
			this.next = next;
		}
		
	}
	private CourseNode head;
	private int size = 0;
	
//DEFAULT CONSTRUCTOR
	/**
	 * basic default constructor that makes a list with size 0 and head pointing to null
	 */
	public CourseList(){
		head = null;
		size = 0;
	}

//COPY CONSTRUCTOR
	/**
	 * copy constructor that takes a CourseList object and copies over the nodes with the
	 * same courses
	 * @param list A pre-existing list that will duplicated
	 */
	public CourseList(CourseList list){
		if(list.head == null){
			head = null;
		}else{
			CourseNode t1 = list.head;
			CourseNode t2 = null;
			CourseNode t3 = null;
			
			while(t1 != null){
				t3 = new CourseNode(t1.course, null);
				if(t2 == null){	//happens only once
					head = t2 = t3;
				}else{
					t2.next =t3;
					t2 = t2.next;
				}
				t1 = t1.next;
			}
			t2 = t3 = null;
		}
		size = list.size;
	}

//TOSTRING	
	/**
	 * a method that will print the information about what a list contains
	 * @return a String with the info
	 */
	public String toString(){
		if(head == null)
			return "the list is empty";
		else{
			CourseNode t = head;
			
			while(t != null){
				System.out.println(t.course.toString());
				t = t.next;
			}
			return "";
		}
	}
	
//ADD TO START
	/**
	 * will add a new courseNode with course c to the start of the list
	 * increase the size.
	 * @param c The course to be added in CourseNode
	 */
	public void addToStart(Course c){
		head = new CourseNode(c, head);
		size++;
	}
	
//INSERT AT INDEX
	/**
	 * Will insert a CourseNode with course c at specified index. 
	 * index will start at 0. if index is out of bounds, throw an exception
	 * increase the size.
	 * @param c The course
	 * @param index The index
	 */
	public void insertAtIndex(Course c, int index){
		if(index==0)
			addToStart(c);
		else{
			try{
				if(index < 0 || index > size-1)
					throw new NoSuchElementException();
				else{
					CourseNode t = head;
					for(int i = 0; i < index-1; i++)
						t=t.next;
					t.next = new CourseNode(c, t.next);
				}
			}
			catch(NoSuchElementException e){
				String m = e.getMessage();
				System.out.println(m+"\nProgram will now terminate.");
				System.exit(0);
			}
		}
		size++;
	}
	
//DELETE FROM INDEX
	
	/**
	 * removes a CourseNode from indicated index. If index is wrong, return.
	 * decrease size.
	 * @param index
	 */
	public void deleteFromIndex(int index){
		if(head == null){
			System.out.println("could not delete as list is empty");
			return;
		}
		try{
			if(index < 0 || index > size-1)
				throw new NoSuchElementException();
			else{
				if(index==0){
					deleteFromStart();
				}else{
					CourseNode t = head;
					for(int i = 0; i< index-1; i++){
						t = t.next;
					}
					t.next = t.next.next;
				}
				size--;
			}
		}
		catch(NoSuchElementException e){
			String m = e.getMessage();
			System.out.println(m+" program will now terminate.");
			System.exit(0);
		}
	}
	
//DELETE FROM START
	
	/**
	 * removes a CourseNode from the beginning of the list
	 * @return true if the method deleted
	 */
	public boolean deleteFromStart(){
		if(head == null)
			return false;
		head = head.next;
		size--;
		return true;
	}
	
//REPLACE AT INDEX
	
	/**
	 * replaces an existing courseNode with another of course c at index index.
	 * @param c the course
	 * @param index the index
	 */
	public void replaceAtIndex(Course c, int index){
		if(head == null){
			System.out.println("empty list. could not replace");
			return;
		}
		if(index < 0 || index > size-1){
			System.out.println("index is out of bounds.");
			return;
		}else{
			CourseNode t = head;
			if(index == 0){
				head = new CourseNode(c, head.next);
			}else{
				for(int i = 0; i < index-1; i++){
					t = t.next;
				}
				t.next = new CourseNode(c,t.next.next);
			}
		}
	}
	
//FIND
	//privacy leak. This returns a pointer to a node that can be accessed and
	//modified and accessed without using getters and setters
	
	/**
	 * checks the whole list and finds a specific course with ID courseID. returns a pointer to that CourseNode
	 * @param courseID The ID to find
	 * @return a Pointer/CourseNode associated to the indicated courseID
	 */
	public CourseNode find(String courseID){
		CourseNode t = head;
		int ctr = 0;
		while(t != null){
			if(t.course.getCourseID().equals(courseID)){
				System.out.println("went through "+ctr+" iterations");
				return t;
			}	
			t = t.next;
			ctr++;
		}
		return null;
	}

//CONTAINS
	/**
	 * uses find() to see if course Node with relevant course ID exists in the list
	 * @param courseID
	 * @return true if pointer is not null
	 */
	public boolean contains(String courseID){
		CourseNode t = find(courseID);
		if(t == null)
			return false;
		return true;
	}
	
//EQUALS
	/**
	 * checks both lists to see if they are identical, except for the course IDs, which 
	 * can be different
	 * @param lst The list you are checking against
	 * @return true if the lists are the same
	 */
	public boolean equals(CourseList lst){
		if(this.size != lst.size){
			System.out.println("false");
			return false;
		}
		else{
			boolean same = true;
			CourseNode t1 = lst.head;
			CourseNode t2 = this.head;
			while(t1 != null && t2 != null){
				if(!t1.course.equals(t2.course)){
					same = false;
				}
				t1 = t1.next;
				t2 = t2.next;
			}
			return same;
		}
		
	}

}
