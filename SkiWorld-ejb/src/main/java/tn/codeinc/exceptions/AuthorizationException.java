package tn.codeinc.exceptions;

public class AuthorizationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6551539658696621799L;

	public AuthorizationException(String message){
		super(message);
	}

	public AuthorizationException() {
		// TODO Auto-generated constructor stub
	}
}
