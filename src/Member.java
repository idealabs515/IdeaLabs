/**
Version-1.1
@Editer:ChittyVaishnavReddy
@reviewer:Bikram Shreshta
@Mediater:Husayin
*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")//Classname starts with capital-case
public class Member implements Serializable {
    //Edited as per naming conventions 
	private String lastName;
	private String firstName;
	private String email;
	private int phoneNo;
	private int id;
	private double fines;
	
	//Type loan must start with capital case 
	private Map<Integer, Loan> loans;

	//Changed the constructer to Capital case
	public Member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.id = id;
		
		this.loans = new HashMap<>();
	}

	
	//Edited as per naming conventions 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(id).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(email).append("\n")
		  .append("  Phone: ").append(phoneNo)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines))
		  .append("\n");
		
		for (Loan loan : loans.values()) {
			sb.append(loan).append("\n");
		}		  
		return sb.toString();
	}

	
	//Edited as per naming conventions 
	public int getId() {
		return id;
	}

	
	//Edited as per naming conventions 
	//Type loan must start with capital case 
	public List<Loan> getLoans() {
		//Type loan must start with capital case 
		return new ArrayList<Loan>(loans.values());
	}

	
	//Edited as per naming conventions (LNS->loans)
	public int getNumberOfCurrentLoans() {
		return loans.size();
	}

	
	//Edited as per naming conventions (FINES->fines)
	public double getFinesOwed() {
		return fines;
	}

	
	//Edited as per naming conventions (loan->Loan)
	public void takeOutLoan(Loan loan) {
		if (!loans.containsKey(loan.getId())) {
			loans.put(loan.getId(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	//Edited as per naming conventions 
	public String getLastName() {
		return lastName;
	}

	//Edited as per naming conventions 
	public String getFirstName() {
		return firstName;
	}


	//Edited as per naming conventions 
	public void addFine(double fine) {
		fines += fine;
	}
	
	//Edited as per naming conventions 
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) {
			change = amount - fines;
			fines = 0;
		}
		else {
			fines -= amount;
		}
		return change;
	}

	
        //Edited as per naming conventions 
	public void dischargeLoan(Loan loan) {
		if (loans.containsKey(loan.getId())) {
			loans.remove(loan.getId());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
