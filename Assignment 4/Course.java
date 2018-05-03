import java.util.Scanner;
/**
 * Brianne O'Gallagher-Roy 40058629 
 * COMP249  Assignment # 4
 * Due Date Monday, April 16 2018
 * 
 * Course class. has course ID, name, credits, co requisite and pre requisite
 * @author Brianne
 */

public class Course implements DirectlyRelatable{
	private String courseID;
	private String courseName;
	private double credit;
	private String preReqID;
	private String coReqID;
	
	public Course(){
		
	}
	public Course(String courseID, String courseName, double credit, String preReqID, String coReqID){
		this.courseID = courseID;
		this.courseName = courseName;
		this.credit = credit;
		this.preReqID = preReqID;
		this.coReqID = coReqID;
	}
	public Course(Course c, String courseID){
		this.courseID = courseID;
		this.courseName = c.courseName;
		this.credit = c.credit;
		this.preReqID = c.preReqID;
		this.coReqID = c.coReqID;
	}

	public Course clone(Scanner lock){
		System.out.println("Enter a course ID");
		String ID = lock.next();
		return new Course(this, ID);
	}
	public String toString(){
		return "course ID: "+courseID+"\ncourse name: "+courseName+"\ncredit: "+credit+
				"\npre-req ID: "+preReqID+"\nco-req ID: "+coReqID+"\n";
	}
	public boolean equals(Object x){
		if(x==null||this.getClass() != x.getClass())
			return false;
		else{
			Course c = (Course)x;
			return(this.courseName == c.courseName && credit == c.credit
					&& this.preReqID == c.preReqID && this.coReqID == c.coReqID);
		}	
	}
	public boolean isDirectlyRelated(Course c) {
		if((preReqID != null && preReqID.equals(c.courseID)) || (coReqID != null && coReqID.equals(c.courseID)) || 
				(c.preReqID != null && c.preReqID.equals(this.courseID)) || (c.coReqID != null &&c.coReqID.equals(this.courseID)))
			return true;
		return false;
	}

	//accessor and mutator
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public String getPreReqID() {
		return preReqID;
	}
	public void setPreReqID(String preReqID) {
		this.preReqID = preReqID;
	}
	public String getCoReqID() {
		return coReqID;
	}
	public void setCoReqID(String coReqID) {
		this.coReqID = coReqID;
	}
		
}
