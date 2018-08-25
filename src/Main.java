//Author: Muhammad Ahmed Shoaib
//Reviewer: Bikram Shrestha
//Mediator: Chitty Vaishnav Reddy
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner input; //changed IN to INPUT //Bikram Please change all the variable name to small INPUT, LIBRARY, MENU, CALENDER and DATEFORMAT
	private static Library library; //changed library to Library
	private static String menu;
	private static Calendar calender; //changed CAL to CALENDER
	private static SimpleDateFormat dateFormat; // changed SDF to DATEFORMAT
	
	
	private static String getMenu() { //changed method name from Get_Menu to getMenu
 		StringBuilder stringBuilder = new StringBuilder(); //changed sb to stringBuilder
		
		stringBuilder.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return stringBuilder.toString();
	}


	public static void main(String[] args) {		
		try {			
			input = new Scanner(System.in);
			library = Library.instance();
			calender = Calendar.getInstance();
			dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	
			for (Member member : library.Members()) { //changed variable m to member
				output(member);
			}
			output(" ");
			for (Book book : libaray.Books()) { //changed variable b to book
				output(book);
			}
						
			MENU = getMenu();
			
			boolean exit = false; //changed e to exit
			
			while (!exit) {
				
				output("\n" + dateFormat.format(calender.Date()));
				String choice = input(MENU); //changed variable c to choice
				
				switch (choice.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFine();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					exit = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.save();
			}			
		} catch (RuntimeException exception) { //changed variable name e to exception
			output(exception);
		}		
		output("\nEnded\n");
	}	

	
	private static void payFine() {
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		output("");
		for (Loan loan : library.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		for (Book book : library.Books()) {
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (Member member : library.Members()) { //capitalized Member
			output(member + "\n");
		}		
	}



	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() {
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calender.incrementDate(days);
			library.checkCurrentLoans();
			output(dateFormat.format(calender.Date()));
			
		} catch (NumberFormatException exception) { //changed variable name exception
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		Book book = library.addBook(author, title, callNo); //changed method name Add_Book to addBook()
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			Member member = library.addMember(lastName, firstName, email, phoneNo);
			output("\n" + member + "\n");
			
		} catch (NumberFormatException exception) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
