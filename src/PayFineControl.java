public class PayFineControl {
	
	private PayFineUI ui;
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; //CONTROL_STATE changed to ControlState
	private ControlState state;
	
	private Library library;	//library changed to Library as Class name has been renamed.
	private Member member;;		// member changed to Member as Class name has been renamed.


	public PayFineControl() {
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED; //CONTROL_STATE changed to ControlState
	}
	
	
	public void setUI(PayFineUI ui) {
		if (!state.equals(ControlState.INITIALISED)) { //CONTROL_STATE changed to ControlState
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(PayFineUI.UI_STATE.READY);
		state = ControlState.READY;		//CONTROL_STATE changed to ControlState
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(ControlState.READY)) { //CONTROL_STATE changed to ControlState
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId);
		
		if (member == null) {
			ui.display("Invalid Member Id");
			return;
		}
		ui.display(member.toString());
		ui.setState(PayFineUI.UI_STATE.PAYING);
		state = ControlState.PAYING; //CONTROL_STATE changed to ControlState
	}
	
	
	public void cancel() {
		ui.setState(PayFineUI.UI_STATE.CANCELLED);
		state = ControlState.CANCELLED; //CONTROL_STATE changed to ControlState
	}


	public double payFine(double amount) {
		if (!state.equals(ControlState.PAYING)) { //CONTROL_STATE changed to ControlState
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			ui.display(String.format("Change: $%.2f", change));
		}
		ui.display(member.toString());
		ui.setState(PayFineUI.UI_STATE.COMPLETED);
		state = ControlState.COMPLETED; //CONTROL_STATE changed to ControlState
		return change;
	}
	


}
