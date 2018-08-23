import java.util.Scanner;


public class BorrowBookUI {
	//enum UI_STATE renamed to UiState
	public static enum UiState { 
		INITIALISED, 
		READY, 		
		RESTRICTED, 
		SCANNING, 
		IDENTIFIED, 
		FINALISING, 
		COMPLETED, 
		CANCELLED 
	};
								

	//control renamed to borrowBookControl
	private BorrowBookControl borrowBookControl;
	private Scanner input;
	//state renamed to uiState
	//enum UI_STATE renamed to UiState
	private UiState uiState;

	
	//parameter control renamed to borrowBookControl
	public BorrowBookUI(BorrowBookControl borrowBookControl) {
		this.borrowBookControl = borrowBookControl;
		input = new Scanner(System.in);
		//state renamed to uiState
		//enum UI_STATE renamed to UiState
		uiState = UiState.INITIALISED;
		//setUI changed to setBorrowBookUI
		borrowBookControl.setBorrowBookUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
	
	//method setState renamed to setUiState
	//parameter state renamed to uiState
	//enum UI_STATE renamed to UiState
	public void setUiState(UiState uiState) {
		//state renamed to uiState
		this.uiState = uiState;
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			//state renamed to uiState
			switch (uiState) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:	
				//memStr varibale renamed to memberInputString
				String memberInputString = input("Swipe member card (press <enter> to cancel): ");
				//memStr renamed to memberInputString
				if (memberInputString.length() == 0) {
					//control renamed to borrowBookControl
					borrowBookControl.cancel();
					break;
				}
				try {
					//memStr renamed to memberInputString
					int memberId = Integer.valueOf(memberInputString).intValue();
					//control renamed to borrowBookControl
					//Swiped renamed to swiped
					borrowBookControl.swiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				//control renamed to borrowBookControl
				borrowBookControl.cancel();
				break;
			
				
			case SCANNING:
				//bookStr variable renamed to bookInputString
				String bookInputString = input("Scan Book (<enter> completes): ");
				//bookStr renamed to bookInputString
				if (bookInputString.length() == 0) {
					//control renamed to borrowBookControl
					//Complete renamed to complete
					borrowBookControl.complete();
					break;
				}
				try {
					//bookStr renamed to bookInputString
					int bookId = Integer.valueOf(bookInputString).intValue();
					//control renamed to borrowBookControl
					//Scanned renamed to scannned
					borrowBookControl.scanned(bookId);
					
				} 
				catch (NumberFormatException e) { //indentation fix
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String ans = input("Commit loans? (Y/N): ");
				if (ans.toUpperCase().equals("N")) {
					//control renamed to borrowBookControl
					borrowBookControl.cancel();
					
				} 
				else {	//indentation fix
					//control renamed to borrowBookControl
					borrowBookControl.commitLoans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				//state renamed to uiState
				throw new RuntimeException("BorrowBookUI : unhandled state :" + uiState);			
			}
		}		
	}


	public void display(Object object) {
		output(object);		
	}


}
