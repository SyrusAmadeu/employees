package com.ssys.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgeReport {
	private Employee younger;
	private Employee older;
	private Double Average;
}
