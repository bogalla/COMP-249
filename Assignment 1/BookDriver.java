import java.util.Scanner;
public class BookDriver {

	public static void main(String[] args) {
		Scanner lock = new Scanner(System.in);
		
		//opening message
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("    Welcome to Nook's Book Site!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//ask for max number of books
		System.out.println("What is the maximum number of books your bookstore can contain?");
		final int MAX_BOOKS = lock.nextInt();
		
		//create book array to store all the books user wants to enter
		Book[] inventory = new Book[MAX_BOOKS];
		int choice1; //for main screen
		int choice2;
		int choice3;
		int passwordFail1 = 0;

	do{	//outside do-while encompasses entire program and shows main screen until user enters 5
		do{//middle do while is useful in case user gets password wrong
			do{//inside do-while makes sure the user enters valid input (1-5)
				System.out.println("What do you want to do?");
				System.out.println("\t1.\tEnter new books (password required)");
				System.out.println("\t2.\tChange information of a book (password required)");
				System.out.println("\t3.\tDisplay all books by a a specific author");
				System.out.println("\t4.\tDisplay all books under a certain price");
				System.out.println("\t5.\tQuit");
				System.out.println("Please enter your choice>");
				choice1 = lock.nextInt();
			}while(choice1 < 1 || choice1 > 5); //close inside do-while
			
			String userPassword = null; //create string to hold the password the user enters
			
			//password check (choice 1)
			if (choice1 == 1)
			{
				do{
					System.out.println("please enter password:");
					userPassword = lock.next();
					if (userPassword.compareTo("password") != 0){//if userPassword and "password" are not identical, increment the password failures
						passwordFail1++;
					}
					if (passwordFail1 == 3|| passwordFail1 == 6 || passwordFail1 == 9)
						break;//every third time user gets it wrong, go back to main screen
					if (passwordFail1 == 12){
						System.out.println("Program detected suspicous activities and will terminate immediately!");
						System.exit(0);//if user gets it wrong 12 times, terminate program
					}	
				}while(userPassword.compareTo("password") != 0);
				
				if (userPassword.compareTo("password") == 0)
					break;//if user gets it right, break out of middle do-while to the rest of the program
			}	
			
			//password check (choice 2)
			else if(choice1 == 2)
			{
				int passwordFail2 = 0;//a second counter for the password fails, re-starts every iteration endlessly until user gets password rigth
				do{
					System.out.println("please enter password:");
					userPassword = lock.next();
					if (userPassword.compareTo("password") != 0){//if userPassword and "password" are not identical, increment the password failures
						passwordFail2++;
					}
					if (passwordFail2 == 3)//every third attempt makes user go back to main screen, but no termination here
						break;
				}while(userPassword.compareTo("password") != 0);
				
				if (userPassword.compareTo("password") == 0)
					break;//if password is correct, break out of middle do-while to rest of program
			}	
			
			//if user enters 3,4,5 they get access to the rest of the program, no password required
			else
				break;
		}while(true); //close middle do-while
		
		//access was granted-------------------------------
		switch (choice1){
		case 1: 
			int newBooks; // will store number of new books owner wants to add
			do{
				System.out.println("How many books would you like to add?");
				newBooks = lock.nextInt();
				
				//if the number of books user wants to add surpasses the room available, print warning message and say how much room is left
				if(MAX_BOOKS - newBooks - Book.findNumberOfCreatedBooks() < 0 && MAX_BOOKS - Book.findNumberOfCreatedBooks() != 0)
					System.out.println("not enough space! you only have room for " +(MAX_BOOKS - Book.findNumberOfCreatedBooks()) + " books");
				
				//if there is absolutely no room for any more books, go back to main menu, there is nothing to do here
				if(MAX_BOOKS - Book.findNumberOfCreatedBooks() == 0){
					System.out.println("no more space to add new books, sorry! try updating existing books or re-starting the program");
					break;
				}
			}while(MAX_BOOKS - newBooks - Book.findNumberOfCreatedBooks() < 0);//while the number of books you want to add would surpass room in array
						
			if(MAX_BOOKS - Book.findNumberOfCreatedBooks() == 0)//re-start if there is no room
				continue;
			
			int goesTo = (Book.findNumberOfCreatedBooks()+newBooks);
			for(int i = Book.findNumberOfCreatedBooks(); i < (goesTo); i++){//start at the number of created books, this will be an empty space
				System.out.println("enter title, author, ISBN and price");       //and go to the end of the new books
				String title = lock.next(); //store all user choices into a book and store that in the array
				String author = lock.next();
				int isbn = lock.nextInt();
				double price = lock.nextDouble();
				inventory[i] = new Book(title, author, isbn, price);
			}
			continue; //go back to main screen
			
		case 2:
			int updateBook; //the book index you want to update
			do{
				System.out.println("which book would you like to update? (index value)");
				updateBook = lock.nextInt();
				
				if(inventory[updateBook] == null){//check to see if there exists a book in this spot already. if not, display warning
					System.out.println("there is no book here! would you like to \n1. re-enter a book"
						+ " or \n2. go back to main menu?");
					choice2 = lock.nextInt();
					if(choice2 ==1)
						continue;//re-start do while, ask question again
					else
						break;//back to main menu
				}
				else 
					break; 
			}while(true);
			
			if(inventory[updateBook] == null)
				continue;//back to main menu if no book was present
			
			do{
				System.out.println(inventory[updateBook].toString(updateBook)+"\n");//display info of chosen book
			
				do{
					System.out.println("What information would you like to change?\n\t1.\tauthor\n\t2.\ttitle"
							+ "\n\t3.\tISBN\n\t4.\tprice\n\t5.\tQuit\nEnter your choice>");
					choice3 = lock.nextInt();
				}while(choice3 < 1 || choice3 > 5);//make sure choice is an allowed number
			
				switch(choice3){//a switch to determine which attribute to change. use the setters to update the book with the user's choice
				case 1:
					System.out.println("change author to: ");
					String newAuthor = lock.next();
					inventory[updateBook].setAuthor(newAuthor);//change book's author to user's choice 
					break;
				case 2:
					System.out.println("change title to: ");
					String newTitle = lock.next();
					inventory[updateBook].setTitle(newTitle);//change book's title to user's choice 
					break;
				case 3:
					System.out.println("change ISBN to: ");
					int newIsbn = lock.nextInt();
					inventory[updateBook].setIsbn(newIsbn);//change book's ISBN to user's choice 
					break;
				case 4:
					System.out.println("change price to: ");
					double newPrice = lock.nextDouble();
					inventory[updateBook].setPrice(newPrice);//change book's price to user's choice 
				case 5:
					break;
				}
				if(choice3 == 5)
					break;//back to main menu, "Quit" option
			}while(true);
			continue;
		case 3:
			//go to and use method findBooksBy() to list all books by a specified author
			System.out.println("find books by who?");
			String author = lock.next();
			Book.findBooksBy(inventory, author);
			continue;//back to main menu
		case 4:
			//go to and use method findCheaperThan() to list all books by under a certain price
			System.out.println("find books cheaper than: ");
			double price = lock.nextDouble();
			Book.findCheaperThan(inventory, price);
			continue;//back to main menu
		case 5:
			//closing message and terminate program
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(  "Thanks for Using Nook's Book Site!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.exit(0);
		}
		
	}while(true); //close outside do-while
	
	}

}
