package com.ssys.employee.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ssys.employee.entity.Employee;
import com.ssys.employee.exceptions.EmployeeNotFoundException;
import com.ssys.employee.exceptions.InvalidBirthDateException;
import com.ssys.employee.exceptions.InvalidEmailException;
import com.ssys.employee.exceptions.InvalidLengthException;
import com.ssys.employee.exceptions.NullFieldsException;
import com.ssys.employee.repository.EmployeeRepository;
import com.ssys.employee.util.EmailUtils;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public List<Employee> findAll() {
		log.info("Listing all employees");
		return repository.findAll();
	}

	public Employee addEmployee(Employee newEmployee) {

		if (newEmployee.getDepartament().length() > 100 || newEmployee.getName().length() > 100
				|| newEmployee.getEmail().length() > 100) {
			log.error("All fields must be less than 100 characters");
			throw new InvalidLengthException("All fields must be less than 100 characters");

		} else if (!newEmployee.getEmail().matches(EmailUtils.EMAIL_PATTERN)) {
			log.error("Invalid email format");
			throw new InvalidEmailException("Invalid email format");

		} else if (newEmployee.getDepartament() == null || newEmployee.getDepartament().length() == 0
				|| newEmployee.getName() == null || newEmployee.getName().length() == 0
				|| newEmployee.getSalary() == null || newEmployee.getSalary() <= 0 || newEmployee.getBirthDate() == null
				|| newEmployee.getEmail() == "") {
			log.error("The values can not be negative or null");
			throw new NullFieldsException("The values can't be null or negative");

		} else if (newEmployee.getBirthDate().after(new Date())) {
			log.error("You can not born in the future, son");
			log.error("The date must be lesser than today");
			throw new InvalidBirthDateException("The date must be lesser than today");

		}
		log.info("New employee added\n Name: " + newEmployee.getName() + "\n Email: " + newEmployee.getEmail()
				+ "\n Departament: " + newEmployee.getDepartament() + "\n Salary: " + newEmployee.getSalary()
				+ "\n Birth Date: " + newEmployee.getBirthDate());
		return repository.save(newEmployee);
	}

	public Employee findEmployee(Long id) {
		log.info("Returning the employee number: " + id);
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());
	}

	public Employee updateEmployee(Employee newEmployee, Long id) throws EmployeeNotFoundException {
		return repository.findById(id).map(employee -> {
			if (newEmployee.getName() != null || newEmployee.getName() != "") {
				employee.setName(newEmployee.getName());
				log.info("Updated name: " + newEmployee.getName());
			}

			if (!newEmployee.getEmail().matches(EmailUtils.EMAIL_PATTERN) || newEmployee.getEmail() == "") {
				log.error("Invalid email format");
				throw new InvalidEmailException("Invalid email format");
			} else if (newEmployee.getEmail() != null) {
				employee.setEmail(newEmployee.getEmail());
				log.info("Updated email: " + newEmployee.getEmail());
			}
			if (newEmployee.getDepartament() != null) {
				employee.setDepartament(newEmployee.getDepartament());
				log.info("Updated departament: " + newEmployee.getDepartament());
			}
			if (newEmployee.getSalary() != null || newEmployee.getSalary() > 0) {
				employee.setSalary(newEmployee.getSalary());
				log.info("Updated salary: " + newEmployee.getSalary());
			}
			if (newEmployee.getBirthDate() != null) {
				employee.setBirthDate(newEmployee.getBirthDate());
				log.info("Updated birth date: " + newEmployee.getBirthDate());
			}
			return repository.save(employee);
		}).orElseThrow(() -> new EmployeeNotFoundException());
	}

	public void deleteEmployee(Long id) throws EmployeeNotFoundException {
		if (this.findEmployee(id) == null) {
			log.error("Could not find employee " + id);
			throw new EmployeeNotFoundException();
		} else {
			repository.deleteById(id);
			log.info("Employee " + id + " successful deleted");
		}
	}
}

@ControllerAdvice
@Slf4j
class ExceptionHelper1 {
	@ExceptionHandler(value = { InvalidBirthDateException.class })
	public ResponseEntity<Object> handleInvalidBirthDateException(InvalidBirthDateException ex) {
		log.error("Invalid Birth Date Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidEmailException.class })
	public ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException ex) {
		log.error("Invalid Length Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { EmployeeNotFoundException.class })
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		log.error("Invalid Length Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { NullFieldsException.class })
	public ResponseEntity<Object> handleNullFieldsException(NullFieldsException ex) {
		log.error("Invalid Length Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidLengthException.class })
	public ResponseEntity<Object> handleInvalidLengthException(InvalidLengthException ex) {
		log.error("Invalid Length Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}