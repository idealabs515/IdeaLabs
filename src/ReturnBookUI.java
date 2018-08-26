/**
@Author:ChittyVaishnavReddy
@Reviewer:M.Ahmed
@Mediater: Bikram Shrestha
@version:0.2
*/
import java.util.Scanner;


public class ReturnBookUI {

	public static enum uiState { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl control;
	private Scanner input;
	private uiState state;

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = uiState.INITIALISED;
		control.setUI(this);
	}


	public void run() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY://changed bookStr to bookName
				String bookName = input("Scan Book (<enter> completes): ");
				if (bookName.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int returnBookId = Integer.valueOf(bookStr).intValue();
						control.bookScanned(returnBookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String answer = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (answer.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				control.dischargeLoan(isDamaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
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
	
	
	public void setState(uiState state) {	
		this.state = state;
	}

	
}
