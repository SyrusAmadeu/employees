package com.ssys.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgeReport {
	private Employee younger;
	private Employee older;
	private String average;
	
	private static AgeReport instance = new AgeReport();
	
	public static AgeReport getInstance () {
		return instance;
	}
}
