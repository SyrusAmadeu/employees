package com.ssys.employee.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssys.employee.entity.Employee;

public interface SalaryReportRepository extends JpaRepository<Employee, Long>{
	@Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MIN(salary) FROM Employee)")
	Employee findEmployeeByLowerSalary(Pageable pageable);
	
	@Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(salary) FROM Employee)")
	Employee findEmployeeByHigherSalary();
	
	@Query("SELECT AVG(salary) FROM Employee ")
	Double findAverageSalary();
}
