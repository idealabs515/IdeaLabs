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
	private ControlState state; //CONTROL_STATE changed to ControlState
	
	private Library library;	//library changed to Library as Class name has been renamed.
	private Member member;;		// member changed to Member as Class name has been renamed.


	public PayFineControl() {
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED; //CONTROL_STATE changed to ControlState
	}
	
	
	public void setUI(PayFineUI payFineUI) {
		if (!state.equals(ControlState.INITIALISED)) { //CONTROL_STATE changed to ControlState
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.payFineUI = payFineUI;
		//UI_STATE changed to UserInterface to reflect change made in PayFineUI.java
		payFineUI.setState(PayFineUI.UserInterfaceState.READY); 
		state = ControlState.READY;		//CONTROL_STATE changed to ControlState
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(ControlState.READY)) { //CONTROL_STATE changed to ControlState
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
		state = ControlState.PAYING; //CONTROL_STATE changed to ControlState
	}
	
	
	public void cancel() {
		//UI_STATE changed to UserInterface to reflect change made in PayFineUI.java
		payFineUI.setState(PayFineUI.UserInterfaceState.CANCELLED);
		state = ControlState.CANCELLED; //CONTROL_STATE changed to ControlState
	}


	public double payFine(double amount) {
		if (!state.equals(ControlState.PAYING)) { //CONTROL_STATE changed to ControlState
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			payFineUI.display(String.format("Change: $%.2f", change));
		}
		payFineUI.display(member.toString());
		//UI_STATE changed to UserInterface to reflect change made in PayFineUI.java
		payFineUI.setState(PayFineUI.UserInterfaceState.COMPLETED);
		state = ControlState.COMPLETED; //CONTROL_STATE changed to ControlState
		return change;
	}
	


}
