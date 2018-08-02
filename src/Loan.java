import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
// The class name was changed from loan to Loan
public class Loan implements Serializable {
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int loanId;	// ID changed to loanId
	// book is not the correct Object type so it was renamed to Book 
	private Book book; 	// Book object was renamed to book.
	// Member is not the correct Object type so it was renamed to Member 
	private Member member;	// M was changed to member for clearity. 
	private Date dueDate;	// D was renamed to dueDate for clearity.
	private LOAN_STATE state;

	// The name of the constructor was changed to reflect the change.
	// All the wrong parameter type was corrected to correct type.
	public Loan(int loanId, Book book, Member member, Date dueDate) {
		this.loanId = loanId;	// ID was changed to loanId.
		this.book = book;		// B was changed to book.
		this.member = member;	// M was changed to member
		this.dueDate = dueDate;	// D was changed to dueDate	
		this.state = LOAN_STATE.CURRENT;
	}

	// Variable name was changed from D to dueDate.
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(dueDate)) {
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	// Logical error was corrected as state was being compared
	// instead of assigning value tot it.
	public boolean isOverDue() {
		return state = LOAN_STATE.OVER_DUE;
	}

	// getID() function was returning Integer instead of int.
	// So, it was corrected.
	public int getId() {
		return loanId;	// ID was changed to loanId.
	}


	public Date getDueDate() {
		return dueDate;	// D was changed to dueDate.
	}
	
	
	public String toString() {
		//sdf was changed to dateFormat 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder stringBuilder = new StringBuilder();
		//ID was changed to loanId.
		stringBuilder.append("Loan:  ")	// sb was changed to stringBuilder.
					 .append(loanId)
					 .append("\n") 
		  			 .append("  Borrower ")
		  			 .append(member.getId())	// M changed to member.
		  			 .append(" : ")	
		             .append(member.getLastName())	// M changed to member.
		             .append(", ")
		             .append(member.getFirstName())	// M changed to member.
		             .append("\n")
		             .append("  Book ")
		             .append(book.loanId())		// B was changed to book.
		             .append(" : " )	
		  			 .append(book.Title())	//B was changed to book.
		  			 .append("\n")	
		  			 .append("  DueDate: ")
		  			 .append(dateFormat.format(dueDate)) //sdf was changed to dateFormat 
		  			 .append("\n")	
		  			 .append("  State: ")
		  			 .append(state);		
		return stringBuilder.toString();	// sb was changed to stringBuilder.
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void Loan() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
