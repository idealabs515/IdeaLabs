import java.io.Serializable;


@SuppressWarnings("serial")
//book renamed to Book
public class Book implements Serializable {
	
	
	//STATE renamed to State
	private enum State { 
		AVAILABLE, 
		ON_LOAN, 
		DAMAGED, 
		RESERVED 
	};
	
	//T renamed to title
	private String title;
	//A renamed to author
	private String author;
	//C renamed to callNo
	private String callNo;
	//ID renamed to id
	private int id;
	

	//STATE type renamed to State
	private State state;
	
	
	//book renamed to Book
	public Book(String author, String title, String callNo, int id) {
		//A renamed to author
		this.author = author;
		//T renamed to title
		this.title = title;
		//C renamed to callNo
		this.callNo = callNo;
		//ID renamed to id
		this.id = id;
		//STATE renamed to State
		this.state = State.AVAILABLE;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//ID renamed to id
		sb.append("Book: ").append(id).append("\n")
			//T renamed to title
			.append("  Title:  ").append(title).append("\n")
			//A renamed to author
			.append("  Author: ").append(author).append("\n")
			//C renamed to callNo
			.append("  CallNo: ").append(callNo).append("\n")
			.append("  State:  ").append(state);
		
		return sb.toString();
	}
	

	//ID method renamed to getId()
	public Integer getId() {
		//ID renamed to id
		return id;
	}
	

	//Title method renamed to getTitle()
	public String getTitle() {
		//T renamed to title
		return title;
	}


	
	//Available method renamed to isAvailable()
	public boolean isAvailable() {
		//STATE renamed to State
		return state == State.AVAILABLE;
	}

	
	//On_loan method renamed to isOnLoan()
	public boolean isOnLoan() {
		//STATE renamed to State
		return state == State.ON_LOAN;
	}

	
	//Damaged method renamed to isDamaged()
	public boolean isDamaged() {
		//STATE renamed to State
		return state == State.DAMAGED;
	}

	
	//Borrow method renamed to borrow()
	public void borrow() {
		//STATE renamed to State
		if (state.equals(State.AVAILABLE)) {
			//STATE renamed to State
			state = State.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}
	

	//Return method renamed to return()
	//DAMAGED renamed to isDamaged
	public void return(boolean isDamaged) {
		//STATE renamed to State
		if (state.equals(State.ON_LOAN)) {
			//DAMAGED renamed to isDamaged
			if (isDamaged) {
				//STATE renamed to State
				state = State.DAMAGED;
			}
			else {
				//STATE renamed to State
				state = State.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	//Repair method renamed to repair()
	public void repair() {
		//STATE renamed to State
		if (state.equals(State.DAMAGED)) {
			//STATE renamed to State
			state = State.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
