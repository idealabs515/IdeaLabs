import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//@author	Bikram Shrestha
//@reviewer	Vaishnav Reddy Chitty
//@mediator	Huseyin Caliskan.


@SuppressWarnings("serial")
// The class name was changed from loan to Loan
public class Loan implements Serializable {
	// enum name changed from LOAN_STATE to LoanState
	public static enum LoanState { //Formatting changed.
		CURRENT,
		OVER_DUE, 
		DISCHARGED 
	};
	
	private int loanId;	// ID changed to loanId
	// book is not the correct Object type so it was renamed to Book 
	private Book book; 	// Book object was renamed to book.
	// Member is not the correct Object type so it was renamed to Member 
	private Member member;	// M was changed to member for clearity. 
	private Date dueDate;	// D was renamed to dueDate for clearity.
	private LoanState state;

	// The name of the constructor was changed to reflect the change.
	// All the wrong parameter type was corrected to correct type.
	public Loan(int loanId, Book book, Member member, Date dueDate) {
		this.loanId = loanId;	// ID was changed to loanId.
		this.book = book;		// B was changed to book.
		this.member = member;	// M was changed to member
		this.dueDate = dueDate;	// D was changed to dueDate	
		this.state = LoanState.CURRENT;
	}

	// Variable name was changed from D to dueDate.
	public void checkOverDue() {
		if (state == LoanState.CURRENT &&
			Calendar.getInstance().Date().after(dueDate)) {
			this.state = LoanState.OVER_DUE;			
		}
	}
    // assigned sign was changed back to == comparision as suggested by @Vaishnav
    /*    
    //@Vaishnav(Review): The value should be reverted to the original.
	// Logical error was corrected as state was being compared
	// instead of assigning value tot it. 
	*/
	public boolean isOverDue() {
		return state == LoanState.OVER_DUE;
	}

	//Reverted back to Integer as suggested by @Vaishnav
	/*
	//@Vaishnav(Review): The getID method should be reverted to Integer type
	// getID() function was returning Integer instead of int.
	// So, it was corrected.
	*/
	public Integer getId() {
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

	// Datatype changed from member to Member.
	// Member() method was changed to getMember().
	public Member getMember() {
		return member;	// M was changed to member.
	}

	// Datatype changed from book to Book.
	// Book() method was changed to getBook().
	public Book getBook() {
		return book;	// return book instead of B.
	}

	// method Loan() was renamed to clearLoan() to reflect it property.
	public void clearLoan() {
		state = LoanState.DISCHARGED;		
	}

}
