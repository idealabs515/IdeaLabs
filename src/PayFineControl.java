public class PayFineControl {
	//@author: Bikram Shrestha
	//reviewer: Huseyin Caliskan
	//Mediator: Muhammad Ahmed Shoaib
	
	private PayFineUI payFineUI; //ui changed to payFineUI
	private enum ControlState { //CONTROL_STATE changed to ControlState
		INITIALISED, 
		READY, 
		PAYING, 
		COMPLETED, 
		CANCELLED 
		}; 						//Code formatted for clearity.
	//Variable state renamed to controlState
	private ControlState controlState; //CONTROL_STATE changed to ControlState
	
	private Library library;	//library changed to Library as Class name has been renamed.
	//+HC there is two semicolumns at the end of the following line
	private Member member;;		// member changed to Member as Class name has been renamed.


	public PayFineControl() {
		this.library = library.INSTANCE();
		//Variable state renamed to controlState
		controlState = ControlState.INITIALISED; //CONTROL_STATE changed to ControlState
	}
	
	//Method setUI renamed setPayFineUI
	public void setPayFineUI(PayFineUI payFineUI) {
		//Variable state renamed to controlState
		if (!controlState.equals(ControlState.INITIALISED)) { //CONTROL_STATE changed to ControlState
			throw new RuntimeException("PayFineControl: cannot call setPayFineUI except in INITIALISED state");
		}	
		this.payFineUI = payFineUI;
		//UI_STATE changed to UserInterface to reflect change made in PayFineUI.java
		payFineUI.setState(PayFineUI.UserInterfaceState.READY); 
		//Variable state renamed to controlState
		controlState = ControlState.READY;		//CONTROL_STATE changed to ControlState
	}


	public void cardSwiped(int memberId) {
		//Variable state renamed to controlState
		if (!controlState.equals(ControlState.READY)) { //CONTROL_STATE changed to ControlState
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId);
		
		if (member == null) {
			payFineUI.display("Invalid Member Id");
			return;
		}
		payFineUI.display(member.toString());
		//UI_STATE changed to UserInterface to reflect change made in PayFineUI.java
		payFineUI.setState(PayFineUI.UserInterfaceState.PAYING);
		//Variable state renamed to controlState
		controlState = ControlState.PAYING; //CONTROL_STATE changed to ControlState
	}
	
	
	public void cancel() {
		//UI_STATE changed to UserInterface to reflect change made in PayFineUI.java
		payFineUI.setState(PayFineUI.UserInterfaceState.CANCELLED);
		//Variable state renamed to controlState
		controlState = ControlState.CANCELLED; //CONTROL_STATE changed to ControlState
	}


	public double payFine(double amount) {
		//Variable state renamed to controlState
		if (!controlState.equals(ControlState.PAYING)) { //CONTROL_STATE changed to ControlState
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			payFineUI.display(String.format("Change: $%.2f", change));
		}
		payFineUI.display(member.toString());
		//UI_STATE changed to UserInterface to reflect change made in PayFineUI.java
		payFineUI.setState(PayFineUI.UserInterfaceState.COMPLETED);
		//Variable state renamed to controlState
		controlState = ControlState.COMPLETED; //CONTROL_STATE changed to ControlState
		return change;
	}
	


}
