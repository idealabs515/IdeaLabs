public class FixBookControl {
	
	//@author Muhammad Ahmed Shoaib
	//@reviewer Huseyin Caliskan
	//@mediator Bikram Shrestha

	private enum ControlState { INITIALISED, READY, FIXING }; //the enum named changed from CONTROL_STATE to ControlState
	private ControlState controlState; //changed variable name from state to controlState
	private FixBookUI fixBookUI; //changed variable name from ui to fixBookUI
	private Library library; //changed from library to Library
	private Book currentBook; //changed from book to Book


	public FixBookControl() {
		
		this.library = library.getInstance(); // changed from library.INSTANCE() to library.getInstance()
		controlState = ControlState.INITIALISED;
	}
	
	
	
	public void setFixBookUI(FixBookUI fixBookUI) { //changed from setUI to setFixBookUI
		if (!controlState.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUI = fixBookUI;
		fixBookUI.setState(FixBookUI.UI_STATE.READY);
		controlState = ControlState.READY;		
	}

	//method bookScanned changed to getBook // There is getBook() method in Library so may be bookScanned was better.
	public void getBook(int bookId) {
		if (!controlState.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.setBook(bookId); //There is no setBook in library So change it ot getBook().
		
		if (currentBook == null) {
			fixBookUI.display("Invalid bookId");
			return;
		}
		//changed from currentBook.Damaged to currentBook.isDamaged()
		if (!currentBook.isDamaged()) {
			fixBookUI.display("\"Book has not been damaged");
			return;
		}
		fixBookUI.display(currentBook.toString());
		fixBookUI.setState(FixBookUI.UI_STATE.FIXING);
		controlState = ControlState.FIXING;		
	}


	public void fixBook(boolean fix) {
		if (!controlState.equals(ControlState.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		fixBookUI.setState(FixBookUI.UI_STATE.READY);
		controlState = ControlState.READY;		
	}

	
	public void scanningComplete() {
		if (!controlState.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUI.setState(FixBookUI.UI_STATE.COMPLETED);		
	}

	
}
