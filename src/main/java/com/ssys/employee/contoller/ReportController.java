package com.ssys.employee.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssys.employee.entity.AgeReport;
import com.ssys.employee.entity.SalaryReport;
import com.ssys.employee.service.ReportService;

@RestController
@RequestMapping(value = "/reports/employees")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping(value = "/salary")
	public ResponseEntity<SalaryReport> salaryReport() {
		SalaryReport salaryReport = reportService.salaryReport();
		return new ResponseEntity<SalaryReport>(salaryReport, HttpStatus.OK);
	}
	
	@GetMapping(value = "/age")
	public ResponseEntity<AgeReport> ageReport() {
		AgeReport ageReport = reportService.ageReport();
		return new ResponseEntity<AgeReport>(ageReport, HttpStatus.OK);
	}
}
