/**
The below File is being edited by Chitty vaishnav Reddy
@Editor:Chitty Vaishnav Reddy
@Version-1.1
*/
//@Reviewer Bikram Shrestha
//@Moderater Muhammad Ahmed Shoaib
public class ReturnBookControl {

	private ReturnBookUI returnBookUI; //changed variable ui to returnBookUI
	//Enum name should be Camel case it was Captital
	private enum ControlState { INITIALISED, READY, INSPECTING };
	private ControlState state;  
	
	//private library library;--> version-0
	//After Editing Class names should be Capital
	private Library library; 	
	//After Editing Class names should be Capital
	private Loan currentLoan;
	
	public ReturnBookControl() {
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(ReturnBookUI returnBookUI) {
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.returnBookUI = returnBookUI;
		returnBookUI.setState(ReturnBookUI.UI_STATE.READY);
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			returnBookUI.display("Invalid Book Id");
			return;
		}
		if (!currentBook.onLoan()) { //bikram something is wrong with method name On_loan()//Edited
			returnBookUI.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		returnBookUI.display("Inspecting");
		returnBookUI.display(currentBook.toString());
		returnBookUI.display(currentLoan.toString());
		
		if (currentLoan.isOverDue()) {
			returnBookUI.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		returnBookUI.setState(ReturnBookUI.UI_STATE.INSPECTING);
		state = ControlState.INSPECTING;		
	}


	public void scanningComplete() {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		returnBookUI.setState(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(ControlState.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		returnBookUI.setState(ReturnBookUI.UI_STATE.READY);
		state = ControlState.READY;				
	}


}
