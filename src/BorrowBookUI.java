import java.util.Scanner;


public class BorrowBookUI {
	//enum UI_STATE renamed to UiState
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };

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
		borrowBookControl.setUI(this);
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
				String memStr = input("Swipe member card (press <enter> to cancel): ");
				if (memStr.length() == 0) {
					//control renamed to borrowBookControl
					borrowBookControl.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					//control renamed to borrowBookControl
					borrowBookControl.Swiped(memberId);
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
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					//control renamed to borrowBookControl
					borrowBookControl.Complete();
					break;
				}
				try {
					int bookId = Integer.valueOf(bookStr).intValue();
					//control renamed to borrowBookControl
					borrowBookControl.Scanned(bookId);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String ans = input("Commit loans? (Y/N): ");
				if (ans.toUpperCase().equals("N")) {
					//control renamed to borrowBookControl
					borrowBookControl.cancel();
					
				} else {
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
