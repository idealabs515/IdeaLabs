import java.util.Scanner;
//Author: Muhammad Ahmed Shoaib
//Reviewer: Bikram
//Moderator: Vaishnav

public class FixBookUI {

	public static enum UISTATE { INITIALISED, READY, FIXING, COMPLETED }; //Changed UI_STATE to UISTATE

	private FixBookControl control;
	private Scanner input;
	private UISTATE uiState; //changed state to uiState

	//constructor
	public FixBookUI(FixBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		uiState = UISTATE.INITIALISED;
		control.setUI(this);
	}


	public void setState(UISTATE uiState) { //UISTATE
		this.uiState = uiState;
	}

	
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (uiState) {
			
			case READY:
				String bookString = input("Scan Book (<enter> completes): ");
				if (bookString.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookString).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException exception) { //changed e to exception 
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String choice = input("Fix Book? (Y/N) : ");
				boolean isFixed = false;
				if (choice.toUpperCase().equals("Y")) {
					isFixed = true;
				}
				control.fixBook(isFixed);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + uiState);			
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
