package com.ssys.employee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryReport {
	private Employee lower;
	private Employee higher;
	private Double average;
	
	private static SalaryReport instance = new SalaryReport();
	
	public static SalaryReport getInstance() {
		return instance;
	}
}
