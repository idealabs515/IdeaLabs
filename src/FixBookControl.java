public class FixBookControl {
	//+HC -Please add comments where you made changes
	
	
	private enum ControlState { INITIALISED, READY, FIXING };
	//+HC -Must be changed according to naming conventions
	private ControlState state;
	private FixBookUI fixBookUI;
	private Library library;
	private Book currentBook;


	public FixBookControl() {
		//+HC -Must be changed according to naming conventions
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED;
	}
	
	
	//+HC -Must be changed according to naming conventions
	public void setUI(FixBookUI fixBookUI) {
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.fixBookUI = fixBookUI;
		fixBookUI.setState(FixBookUI.UI_STATE.READY);
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		//+HC -Must be changed according to naming conventions
		currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			fixBookUI.display("Invalid bookId");
			return;
		}
		//+HC -Must be changed according to naming conventions
		if (!currentBook.Damaged()) {
			fixBookUI.display("\"Book has not been damaged");
			return;
		}
		fixBookUI.display(currentBook.toString());
		fixBookUI.setState(FixBookUI.UI_STATE.FIXING);
		state = ControlState.FIXING;		
	}


	public void fixBook(boolean fix) {
		if (!state.equals(ControlState.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		fixBookUI.setState(FixBookUI.UI_STATE.READY);
		state = ControlState.READY;		
	}

	
	public void scanningComplete() {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		fixBookUI.setState(FixBookUI.UI_STATE.COMPLETED);		
	}


	//+HC -There should be only 2 space lines



}
