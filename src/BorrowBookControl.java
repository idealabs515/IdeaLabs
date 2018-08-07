import java.util.ArrayList;
import java.util.List;
//@author	Huseyin Caliskan
//@reviewer	Huhammad Ahmed Shoaib
//@mediator	Bikram Shrestha 
public class BorrowBookControl {
	//enum definition has been carried to top of the class
	private enum ControlState { //CONTROL_STATE renamed to ControlState
		INITIALISED, 
		READY, 
		RESTRICTED, 
		SCANNING, 
		IDENTIFIED, 
		FINALISING, 
		COMPLETED, 
		CANCELLED 
	}; //Indentation fix
	
	
	//instance variable declarations
	private BorrowBookUI borrowBookUI; //ui renamed to borrowBookUI
	private Library library; //L renemad to library
	private Member member; //M renamed to member
	private ControlState controlState; //state renamed to controlState
	private List<Book> pendingBookList; //PENDING renamed to pendingBookList
	private List<Loan> completedLoanList; //COMPLETED renamed to completedLoanList
	private Book book; //B renamed to book
	
	
	//Default Constructor
	public BorrowBookControl() {
		this.library = library.INSTANCE();
		controlState = ControlState.INITIALISED;
	}
	
	
	
	//setter method for borrowBookUI variable
	public void setBorrowBookUI(BorrowBookUI borrowBookUI) {
		if (!controlState.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("BorrowBookControl: cannot call setBorrowBookUI except in INITIALISED state");
		}
		
		this.borrowBookUI = borrowBookUI;
		borrowBookUI.setState(BorrowBookUI.UI_STATE.READY);
		controlState = ControlState.READY;		
	}

	
	//operational public methods
	public void cardSwiped(int memberId) {
		if (!controlState.equals(ControlState.READY)) {
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		}
		
		member = library.getMember(memberId);
		if (member == null) {
			borrowBookUI.display("Invalid memberId");
			return;
		}
		if (library.memberCanBorrow(member)) {
			pendingBookList = new ArrayList<>();
			borrowBookUI.setState(BorrowBookUI.UI_STATE.SCANNING);
			controlState = ControlState.SCANNING; 
		}
		else 
		{
			borrowBookUI.display("Member cannot borrow at this time");
			borrowBookUI.setState(BorrowBookUI.UI_STATE.RESTRICTED); 
		}
	}
	
	
	public void bookScanned(int bookId) {
		book = null;
		if (!controlState.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.Book(bookId);
		if (book == null) {
			borrowBookUI.display("Invalid bookId");
			return;
		}
		if (!book.Available()) {
			borrowBookUI.display("Book cannot be borrowed");
			return;
		}
		pendingBookList.add(book);
		for (Book tempBook : pendingBookList) {
			borrowBookUI.display(tempBook.toString());
		}
		if (library.loansRemainingForMember(member) - pendingBookList.size() == 0) {
			borrowBookUI.display("Loan limit reached");
			complete();
		}
	}
	
	
	public void complete() {
		if (pendingBookList.size() == 0) {
			cancel();
		}
		else {
			borrowBookUI.display("\nFinal Borrowing List");
			for (Book tempBook : pendingBookList) {
				borrowBookUI.display(tempBook.toString());
			}
			completedLoanList = new ArrayList<loan>();
			borrowBookUI.setState(BorrowBookUI.UI_STATE.FINALISING);
			controlState = ControlState.FINALISING;
		}
	}


	public void commitLoans() {
		if (!controlState.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (Book tempBook : pendingBookList) {
			Loan loan = library.issueLoan(tempBook, member);
			completedLoanList.add(loan);			
		}
		borrowBookUI.display("Completed Loan Slip");
		for (Loan loan : completedLoanList) {
			borrowBookUI.display(loan.toString());
		}
		borrowBookUI.setState(BorrowBookUI.UI_STATE.COMPLETED);
		controlState = ControlState.COMPLETED;
	}

	
	public void cancel() {
		borrowBookUI.setState(BorrowBookUI.UI_STATE.CANCELLED);
		controlState = ControlState.CANCELLED;
	}
	
	
}
