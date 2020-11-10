package com.ssys.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssys.employee.entity.AgeReport;
import com.ssys.employee.entity.SalaryReport;
import com.ssys.employee.repository.AgeReportRepository;
import com.ssys.employee.repository.SalaryReportRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportService {

	@Autowired
	private AgeReportRepository ageRepository;

	@Autowired
	private SalaryReportRepository salaryRepository;

	public SalaryReport salaryReport() {
		SalaryReport salaryReport = SalaryReport.getInstance();
		salaryReport.setLower(salaryRepository.findEmployeeByLowerSalary(PageRequest.of(0,1)));
		salaryReport.setHigher(salaryRepository.findEmployeeByHigherSalary());
		salaryReport.setAverage(salaryRepository.findAverageSalary());
		log.info("Returning salary report");
		return salaryReport;
	}

	public AgeReport ageReport() {
		AgeReport ageReport = AgeReport.getInstance();
		ageReport.setYounger(ageRepository.findEmployeeByYounge());
		ageReport.setOlder(ageRepository.findEmployeeByOlder());
		ageReport.setAverage(ageRepository.findAverageAge());
		log.info("Returning age report");
		return ageReport;
	}

}
