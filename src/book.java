import java.io.Serializable;


@SuppressWarnings("serial")
//book renamed to Book
public class book implements Serializable {
	
	//T renamed to title
	private String T;
	//A renamed to author
	private String A;
	//C renamed to callNo
	private String C;
	//ID renamed to id
	private int ID;
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	//book renamed to Book
	public book(String author, String title, String callNo, int id) {
		//A renamed to author
		this.author = author;
		//T renamed to title
		this.title = title;
		//C renamed to callNo
		this.callNo = callNo;
		//ID renamed to id
		this.id = id;
		this.state = STATE.AVAILABLE;
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

	public Integer ID() {
		//ID renamed to id
		return id;
	}

	public String Title() {
		//T renamed to title
		return title;
	}


	
	public boolean Available() {
		return state == STATE.AVAILABLE;
	}

	
	public boolean On_loan() {
		return state == STATE.ON_LOAN;
	}

	
	public boolean Damaged() {
		return state == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}

	//DAMAGED renamed to isDamaged
	public void Return(boolean isDamaged) {
		if (state.equals(STATE.ON_LOAN)) {
			//DAMAGED renamed to isDamaged
			if (isDamaged) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void Repair() {
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
