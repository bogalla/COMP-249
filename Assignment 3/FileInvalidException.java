
/**
 * A class FileInvalidException that extends Exception, has a defalut error message
 * used for when a file has an empty tag
 * 
 * @author Brianne
 */
public class FileInvalidException extends Exception{
	
	/**
	 * Constructor - builds a FileInvalidException object, has a default message
	 */
	public FileInvalidException(){
		super("Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)");
	}
	
	
	/**
	 * Constructor - builds a FileInvalidException object, takes a string that will become
	 * error message
	 * 
	 * @param s The error message the user wants
	 */
	public FileInvalidException(String s){
		super(s);
	}
	
	/**
	 * a method that returns the error message
	 * 
	 * @return String The error message
	 */
	public String getMessage(){
		return super.getMessage();
	}

}
