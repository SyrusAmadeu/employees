package com.ssys.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssys.employee.entity.Employee;

public interface SalaryReportRepository extends JpaRepository<Employee, Long>{
	@Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MIN(salary) FROM Employee)")
	List<Employee> findEmployeeByLowerSalary();
	
	@Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(salary) FROM Employee)")
	List<Employee> findEmployeeByHigherSalary();
	
	@Query("SELECT AVG(salary) FROM Employee ")
	Double findAverageSalary();
}
