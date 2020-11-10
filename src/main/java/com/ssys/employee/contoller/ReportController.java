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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/reports/employees")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@ApiOperation("List all employees from database.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Salary report successfuly returned."),
			@ApiResponse(code = 403, message = "Access forbidden.") })
	@GetMapping(value = "/salary")

	public ResponseEntity<SalaryReport> salaryReport() {
		SalaryReport salaryReport = reportService.salaryReport();
		return new ResponseEntity<SalaryReport>(salaryReport, HttpStatus.OK);
	}

	@ApiOperation("List all employees from database.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Age report successfuly returned."),
			@ApiResponse(code = 403, message = "Access forbidden.") })
	@GetMapping(value = "/age")

	public ResponseEntity<AgeReport> ageReport() {
		AgeReport ageReport = reportService.ageReport();
		return new ResponseEntity<AgeReport>(ageReport, HttpStatus.OK);
	}
}
