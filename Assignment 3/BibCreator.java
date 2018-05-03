/**
* Brianne O'Gallagher-Roy 40058629
* COMP249  Assignment #3
* Due Date: March 16, 2018 
*
* The purpose of this program is to take an input of ten JSON files, from Latex1.bib to Latex10.bib, read them,
* create corresponding files to copy certain information, then ask the user for a file then print that info.
* we do this by using scanner to read line by line. we must go through every file, and every article in that file. 
* for every article, it's tags are rearranged to be able to use it in a source of an article, for example. There are 
* three formats for the final product: IEEE, ACM and NJ. the program will go through a file, then create three other
* files, named IEEE#.json, ACM#.json and NJ#.json, to store the three different formats for all the articles from the
* original file.
* The program will also determine if they are valid. in this case valid means that no field is left empty (for example,
* title={}). if this happens, all the output files for this Latex file will be deleted, the console will print an error
* message, and the program will continue.
* when all the appropriate files have been created, the program asks the user which file they would like to display.
* the user has 2 chances to enter an appropriate file name otherwise the system exits. if a valid file name is entered,
* using BufferedReader, that file is outputted to the console. program ends.
 * 
 * @author Brianne
 *
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;


public class BibCreator {
	
	//THE METHOD OPEN
	/**
	 * This method checks every file for the word "article" then looks through (until the last line is "}")
	 * and stores all the necessary information in Strings. If one of the tags are empty, the method throws
	 * an exception and deletes the ieee, acm and nj files, then continues. Then the methods takes all the 
	 * strings and formats them appropriately, then stores them in the corresponding files. then the method 
	 * closes the scanners 
	 * 
	 * @param sc The Scanner, to read
	 * @param pwIEEE The PrintWriter for IEEE format, to write
	 * @param pwACM The PrintWriter for ACM format, to write
	 * @param pwNJ The PrintWriter for NJ format, to write
	 * @param index To know what index we are, to be able to use appropriate names and Scanners/PrintWriters
	 * @param num To count how many times an exception is thrown
	 * @return Return false if the file is invalid
	 */
	public static boolean processFilesForValidation(Scanner[] sc, PrintWriter[] pwIEEE, PrintWriter[] pwACM, PrintWriter[] pwNJ, int index, int num){
		while(sc[index].hasNextLine()){	
			try{
				String s = sc[index].nextLine(); //read the first line. if it contains "article", start a loop to read through it to find keywords
				
				if(s.contains("ARTICLE")){
					String authors = null; //make all the Strings so they exist
					String title = null;
					String journal = null;
					String volume = null;
					String number = null;
					String pages = null;
					String month = null;
					String year = null;
					String doi = null;
					String line = null;
					while(true){
						line = sc[index].nextLine(); //make line a string of the current line
						if(line.contains("={}")){ // check for empty files, throw exception if yes
							num++;//to keep track
							int indexOfEqual = line.indexOf('=');
							String keyWord = line.substring(0,indexOfEqual); //find word
							throw new FileInvalidException("Error: Detected Empty Field!\n"//throw
								+ "============================\n\nProblem detected with input file: Latex"+(index+1)+".bib\n"
										+ "File is invalid: Field \""+keyWord+"\" is empty. Processing stopped at this point. Other empty fields may exist.\n");	
						}
						
						if(line.equals("}")){ //this looks for end of "@ARTICLE{" and leaves once reached
							break;
						}//store all the appropriate values
						else if(line.contains("author={")){
							authors = line.substring(8,line.indexOf('}'));
						}else if(line.contains("title={")){
							title = line.substring(7,line.indexOf('}'));
						}else if(line.contains("journal={")){
							journal = line.substring(9,line.indexOf('}'));
						}else if(line.contains("volume={")){
							volume = line.substring(8,line.indexOf('}'));
						}else if(line.contains("number={")){
							number = line.substring(8,line.indexOf('}'));
						}else if(line.contains("pages={")){
							pages = line.substring(7,line.indexOf('}'));
						}else if(line.contains("month={")){
							month = line.substring(7,line.indexOf('}'));
						}else if(line.contains("year={")){
							year = line.substring(6,line.indexOf('}'));
						}else if(line.contains("doi={")){
							doi = line.substring(5,line.indexOf('}'));
						}
					}
					//change the authors appropriately
					String ieeeAuthor = authors.replace(" and",",");
					
					String acmAuthor = null;
					if(authors.indexOf("and") == -1){ //if there is only one author
						acmAuthor = authors;
					}else
						acmAuthor = authors.substring(0,authors.indexOf("and"));
					
					String njAuthor = authors.replace("and", "&");
					
					//print to files with appropriate formatting
					pwIEEE[index].println(ieeeAuthor+". \""+title+"\", "+journal+", vol. "+volume+", no. "+number+", p. "+pages+", "+month+" "+year+".\r\n");
					pwACM[index].println(acmAuthor+" et al. "+year+". "+title+". "+journal+". "+volume+", "+number+" ("+year+"), "+pages+". DOI: "+doi+".\r\n");
					pwNJ[index].println(njAuthor+". "+title+". "+journal+". "+volume+", "+pages+" ("+year+").\r\n");
						
				}
					
				
			}catch(FileInvalidException e){
				//catch the exception of an empty field. delete all the output files with the same number
				String message = e.getMessage();
				System.out.println(message);
				String ieee = "IEEE"+(index+1)+".json";
				String acmi = "ACM"+(index+1)+".json";
				String nj = "NJ"+(index+1)+".json";
				pwIEEE[index].close(); //make sure to close before you delete!
				pwACM[index].close();
				pwNJ[index].close();
				File a = new File(ieee); //use file class to delete!!
				a.delete();
				File b = new File(acmi);
				b.delete();
				File c = new File(nj);
				c.delete();
				return false;
			}
		}
		pwIEEE[index].close(); //make sure to close!!
		pwACM[index].close();
		pwNJ[index].close();
		return true;
	}
	//THE METHOD CLOSE
	
	
	public static void main (String[] args){
		int num = 0;//this is to count how many times a file is invalid
		System.out.println("Welcome to Bib Creator!\n");
		Scanner lock = new Scanner(System.in); //to get user input
		Scanner[] sc = new Scanner[10];//array of scanner objects, one to read each file
		
		PrintWriter[] pwIEEE = new PrintWriter[10];//there are three arrays here because we use a for loop that
		PrintWriter[] pwACM = new PrintWriter[10];   //has 10 iterations
		PrintWriter[] pwNJ = new PrintWriter[10];
		
		String[] fileNames = new String[30]; //to store the file names and delete them if necessary
		int count = 0; // keeps the place of the array filenames
		
		for(int i = 0; i < 10; i++){
			String s = "Latex"+(i+1)+".bib"; // as we iterate through, the latex number will increase, from 1 to 10
												//add +1 because the for loop starts at 0
			try{
				sc[i] = new Scanner(new FileInputStream(s)); //create an input stream for all the latex files
			}catch(FileNotFoundException e){
				System.out.println("Could not open input file "+s+" for "
				+ "reading.\n\nPlease check if file exists! Program "
				+ "will terminate after closing any opened files.\n");
				if(sc[i] != null) // if a scanner object is never created we cannot close it
					sc[i].close();
				System.exit(0);
			}
			
			
			//try opening the files=======================
			String ieee = "IEEE"+(i+1)+".json"; //another iteration through file names
			fileNames[count] = ieee; //storing all file names in fileNames
			count++; //then increase count
			String acm = "ACM"+(i+1)+".json";
			fileNames[count] = ieee;
			count++;
			String nj = "NJ"+(i+1)+".json";
			fileNames[count] = ieee;
			count++;
				
			try{
				pwIEEE[i] = new PrintWriter(new FileOutputStream(ieee)); //try to open the output stream.
			}catch(FileNotFoundException e){
				System.out.println("The file named \""+ieee+"\" could not be created/opened\nEverything will be deleted.");
				for(int j = 0; j < fileNames.length; j++){
					if(fileNames[j] != null){ //if stream cant be opened, delete everything in the array, if it exists
						File f = new File(fileNames[j]);
						f.delete();
					}
				}
				System.exit(0);
				//then exit. repeat with other format types
			}
			try{
				pwACM[i] = new PrintWriter(new FileOutputStream(acm));
			}catch(FileNotFoundException ee){
				System.out.println("The file named \""+acm+"\" could not be created/opened\nEverything will be deleted.");
				for(int j = 0; j < fileNames.length; j++){
					if(fileNames[j] != null){
						File f = new File(fileNames[j]);
						f.delete();
					}
				}
				System.exit(0);
			}
			try{
				pwNJ[i] = new PrintWriter(new FileOutputStream(nj));
			}catch(FileNotFoundException eee){
				System.out.println("The file named \""+nj+"\" could not be created/opened\nEverything will be deleted.");
				for(int j = 0; j < fileNames.length; j++){
					if(fileNames[j] != null){
						File f = new File(fileNames[j]);
						f.delete();
					}
				}
				System.exit(0);
			}
		
			//DONE trying to open files, still inside ===for=== loop
			
			if(!BibCreator.processFilesForValidation(sc, pwIEEE, pwACM, pwNJ, i, num)){ // validate the files and store 
																							//appropriate values
				num++; // if processFiles returns false it means a file was invalid, increase num to keep track
			}
		sc[i].close();	
		}
		//out of "for" loop!!!!!!!!!
		
		
		//print how many times a file was found invalid
		System.out.println("A total of "+num+" files were invalid, and could not be processed. "
				+ "All other "+(10-num)+" \"Valid\" files have been created.");

		System.out.print("which file would you like to see?");
		String userInput = lock.next(); //user writes file name they want to access
		
		BufferedReader br = null;//declare bufferedReader
		try{
			br = new BufferedReader(new FileReader(userInput));//initialize bufferedReader. throw exception
		}catch(FileNotFoundException e){
			System.out.println("Could not open input file. File does not exist; possibly it could not be created!\n\n"
					+ "However, you will be allowed another chance to enter another file name.\n");
			System.out.print("which file would you like to see?");
			userInput = lock.next();//one more chance!
			try{
				br = new BufferedReader(new FileReader(userInput));
			}catch(FileNotFoundException ee){
				System.out.println("Could not open input file again! Either file does not exist or could not be created.\n"
						+ "Sorry! I am unable to display your desired files! program will exit!");
				System.exit(0);//exhausted 2 attempts, system exits
			}
		}
		System.out.println("Here are the contents of the successfully created JSON file: "+userInput);//reach here if bufferedReader initialization has 
																										//been successful
		
		try{
			String s = br.readLine(); //get first line
			while(br.readLine()!=null){// if it isn't null
				System.out.println("\n"+s);//print it
				s = br.readLine();//IOException might be thrown here
			}
			br.close(); //close. IOException might be thrown here
		}catch(IOException e){
			String m = e.getMessage();
			System.out.println(m+" The Stsyem will now exit. bye");
			System.exit(0);
		}
		System.out.println("\nGOODBYE I HOPE YOU HAD FUN :)");//closing statement
	}
}

