import java.util.Scanner;
//@author Bikram Shrestha
//@Reviewer: Muhammad Ahmed Shoaib

public class PayFineUI {

    //enum name changed from UI_STATE to UserInterfaceState.	
	public static enum UserInterfaceState { 
		INITIALISED,
		READY, 
		PAYING, 
		COMPLETED, 
		CANCELLED 
	}; 				//Formatting edited.

	private PayFineControl control;
	private Scanner userInput; //Changed input to userInput
	private UserInterfaceState state; //MAS: Change variable state to interfaceState or uiState it makes more sense.

	// Constructor for PayFineUI
	public PayFineUI(PayFineControl control) {
		this.control = control;
		userInput = new Scanner(System.in);
		state = UserInterfaceState.INITIALISED;
		control.setUI(this);
	}
	
	
	public void setState(UserInterfaceState state) { //MAS: Change variable state to interfaceState or uiState it makes more sense.
		this.state = state;
	}


	public void run() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			// Changed memStr to memberInputForId for code clearity.
			case READY:
				String memberInputForId = input("Swipe member card (press <enter> to cancel): ");
				if (memberInputForId.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memberInputForId).intValue();
					control.cardSwiped(memberId);
				}
				catch (NumberFormatException e) { //MAS: Change variable e to exception 
					output("Invalid memberId");
				}
				break;
			// Changed amtStr to amountInputForPay	
			case PAYING:
				double amount = 0;
				String amountInputForPay = input("Enter amount (<Enter> cancels) : ");
				if (amountInputForPay.length() == 0) {
					control.cancel();
					break;
				}
				try {
					amount = Double.valueOf(amountInputForPay).doubleValue();
				}
				catch (NumberFormatException e) {} //MAS: Change variable e to exception
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.payFine(amount);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return userInput.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void display(Object object) {
		output(object);
	}


}
