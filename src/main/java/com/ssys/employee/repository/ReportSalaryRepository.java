package com.ssys.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssys.employee.entity.Employee;

public interface ReportSalaryRepository extends JpaRepository<Employee, Long>{
//	@Query("select min(emp_salary) from employees")
	public Employee findEmployeeByLowerSalary();
}
