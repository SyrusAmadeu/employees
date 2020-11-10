package com.ssys.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssys.employee.entity.AgeReport;
import com.ssys.employee.entity.SalaryReport;
import com.ssys.employee.repository.AgeReportRepository;
import com.ssys.employee.repository.SalaryReportRepository;


@Service
public class ReportService {

	@Autowired
	private AgeReportRepository ageRepository;

	@Autowired
	private SalaryReportRepository salaryRepository;

	public SalaryReport salaryReport() {
		SalaryReport salaryReport = SalaryReport.getInstance();
		salaryReport.setLower(salaryRepository.findEmployeeByLowerSalary());
		salaryReport.setHigher(salaryRepository.findEmployeeByHigherSalary());
		salaryReport.setAverage(salaryRepository.findAverageSalary());
		return salaryReport;
	}

	public AgeReport ageReport() {
		AgeReport ageReport = AgeReport.getInstance();
		ageReport.setYounger(ageRepository.findEmployeeByYounge());
		ageReport.setOlder(ageRepository.findEmployeeByOlder());
		ageReport.setAverage(ageRepository.findAverageAge());
		return ageReport;
	}

}
