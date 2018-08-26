/**
Canlendar.java
@editer : ChittyVaishnavReddy
@Reviewer: Muhammad Ahmed Shoaib
@version:2.0
*/
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self;
	//changing from cal to customCalendar
	private static java.util.Calendar customCalendar;
	
	
	private Calendar() {
		customCalendar = java.util.Calendar.getInstance();
	}
	
	public static Calendar getInstance() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		customCalendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) {
		try {
			customCalendar.setTime(date);
	                customCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	                customCalendar.set(java.util.Calendar.MINUTE, 0);  
	                customCalendar.set(java.util.Calendar.SECOND, 0);  
	                customCalendar.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception exception) { // Changed variable e to exception 
			throw new RuntimeException(exception);
		}	
	}
	
	//changed date() to getDate() and worked on indentaion 
	public synchronized Date getDate() {
		try {
	                customCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	                customCalendar.set(java.util.Calendar.MINUTE, 0);  
	                customCalendar.set(java.util.Calendar.SECOND, 0);  
	                customCalendar.set(java.util.Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
		catch (Exception exception) { // Changed variable e to exception 
			throw new RuntimeException(exception);
		}	
	}

	//changed date() to getDate()
	public synchronized Date getDueDate(int loanPeriod) {
		Date now = getDate();
		customCalendar.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = customCalendar.getTime();
		customCalendar.setTime(now);
		return dueDate;
	}
	
	//changed date() to getDate()
	public synchronized long getDaysDifference(Date targetDate) {
		long diffMillis = getDate().getTime() - targetDate.getTime();
	        long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
	        return diffDays;
	}

}
