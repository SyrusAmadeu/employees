package com.ssys.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssys.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
