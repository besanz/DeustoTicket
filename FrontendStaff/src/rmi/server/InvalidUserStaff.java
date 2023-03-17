package rmi.server;

public class InvalidUserStaff extends Exception 
{

	private String message;
	
	
	public InvalidUserStaff() {
		super();
		this.message = "";		
	}
	
	public InvalidUserStaff(String exceptionMessage) {
		super();
		this.message = exceptionMessage;
	}
	
	
	public String getErrorMessage() {
		return this.message;
	}

}
