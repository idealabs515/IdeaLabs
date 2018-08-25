import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

// Author	Bikram Shrestha
// Reviewer	Vaishnav Reddy Chitty
// Mediator	Huseyin Caliskan.


@SuppressWarnings("serial")
// The class name was changed from loan to Loan
public class Loan implements Serializable {

	// enum name changed from LOAN_STATE to LoanState
	public static enum LoanState { 
		CURRENT,
		OVER_DUE, 
		DISCHARGED 
	};						// Formatting changed.
	
	private int id;			// ID changed to id
	// book is not the correct Object type so it was renamed to Book 
	private Book book;		// Book object was renamed to book.
	// Member is not the correct Object type so it was renamed to Member 
	private Member member;	// M was changed to member for clearity. 
	private Date dueDate;	// D was renamed to dueDate for clearity.
	private LoanState state;


	// The name of the constructor was changed to reflect the change.
	// All the wrong parameter type was corrected to correct type.
	public Loan(int id, Book book, Member member, Date dueDate) {
		this.id = id;			// ID was changed to id.
		this.book = book;		// B was changed to book.
		this.member = member;	// M was changed to member
		this.dueDate = dueDate;	// D was changed to dueDate	
		this.state = LoanState.CURRENT;
	}


	public void checkOverDue() {
		if (state == LoanState.CURRENT &&
			Calendar.getInstance().Date().after(dueDate)) {		// Variable name was changed from D to dueDate.
			this.state = LoanState.OVER_DUE;			
		}
	}


	public boolean isOverDue() {
		return state == LoanState.OVER_DUE;
	}


	public Integer getId() { 
		return id;				// ID was changed to id.
	}


	public Date getDueDate() {
		return dueDate;			// D was changed to dueDate.
	}
	
	
	public String toString() { 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	//sdf was changed to dateFormat

		StringBuilder stringBuilder = new StringBuilder();		// sb was changed to stringBuilder.
		stringBuilder.append("Loan:  ")				// sb was changed to stringBuilder.
					 .append(id)					//ID was changed to id.
					 .append("\n") 
		  			 .append("  Borrower ")
		  			 .append(member.getId())		// M changed to member and M.ID() changed to member.getId().
		  			 .append(" : ")	
		             .append(member.getLastName())	// M changed to member.
		             .append(", ")
		             .append(member.getFirstName())	// M changed to member.
		             .append("\n")
		             .append("  Book ")
		             .append(book.getId())			// B was changed to book and B.ID() changed to book.getId()
		             .append(" : " )	
		  			 .append(book.getTitle())		// B was changed to book and Title() was changed to getTitle()
		  			 .append("\n")	
		  			 .append("  DueDate: ")
		  			 .append(dateFormat.format(dueDate)) // sdf was changed to dateFormat 
		  			 .append("\n")	
		  			 .append("  State: ")
		  			 .append(state);		
		return stringBuilder.toString();			// sb was changed to stringBuilder.
	}


	// Datatype changed from member to Member.
	public Member getMember() {		// Member() method was changed to getMember().
		return member;				// M was changed to member.
	}


	// Datatype changed from book to Book.
	public Book getBook() {			// Book() method was changed to getBook().
		return book;				// return book instead of B.
	}


	// method Loan() was renamed to clearLoan() to reflect it property.
	public void clearLoan() {
		state = LoanState.DISCHARGED;		
	}
}
