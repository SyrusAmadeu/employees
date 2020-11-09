package com.ssys.employee.exceptions;

public class InvalidBirthDateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4656887216929146657L;

	public InvalidBirthDateException(String message) {
		super(message);
	}
}
