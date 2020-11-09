package com.ssys.employee.entity;

import lombok.Data;

import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class SalaryReport {
	private Double lower;
	private Double higher;
	private Double average;
}
