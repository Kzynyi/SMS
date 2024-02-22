package com.sms.exception;

public class AccountExistsException extends Exception {

	private static final long serialVersionUID = 1177802877981097657L;
	private String message;
	
	public AccountExistsException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
