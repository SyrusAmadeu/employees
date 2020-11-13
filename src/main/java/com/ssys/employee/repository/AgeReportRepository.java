package com.ssys.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssys.employee.entity.Employee;

public interface AgeReportRepository extends JpaRepository<Employee, Long> {
	@Query("SELECT e FROM Employee e WHERE e.birthDate = (SELECT MIN(birthDate) FROM Employee)")
	List<Employee> findEmployeeByYounge();
	
	@Query("SELECT e FROM Employee e WHERE e.birthDate = (SELECT MAX(birthDate) FROM Employee)")
	List<Employee> findEmployeeByOlder();
	
	@Query("SELECT  year(CURRENT_DATE) - AVG(year(birthDate)) FROM Employee")
	String findAverageAge();
	
}
 	