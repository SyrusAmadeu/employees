package com.ssys.employee.exceptions;

public class InvalidEmailException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1812874142428215553L;

	public InvalidEmailException(String message) {
		super(message);
	}
}
