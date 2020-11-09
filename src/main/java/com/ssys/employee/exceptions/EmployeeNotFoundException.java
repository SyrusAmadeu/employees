package com.ssys.employee.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3180028493350959291L;

	public EmployeeNotFoundException() {
		super("Could not find employee");
	}
}
