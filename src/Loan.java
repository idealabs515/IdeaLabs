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

	
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(D)) {
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	
	public boolean isOverDue() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public Integer getId() {
		return ID;
	}


	public Date getDueDate() {
		return D;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.getId()).append(" : ")
		  .append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.Title()).append("\n")
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
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
