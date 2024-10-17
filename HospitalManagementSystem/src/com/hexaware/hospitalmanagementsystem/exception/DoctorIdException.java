package com.hexaware.hospitalmanagementsystem.exception;

public class DoctorIdException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public DoctorIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
