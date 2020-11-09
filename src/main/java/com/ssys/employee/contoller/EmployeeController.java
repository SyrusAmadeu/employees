package com.ssys.employee.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssys.employee.entity.Employee;
import com.ssys.employee.exceptions.NullFieldsException;
import com.ssys.employee.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ApiOperation("List all employees from database.")
	@ApiResponses({ @ApiResponse(code = 200, message = "It will return a list of employees."),
			@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in.") })
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> findAll = employeeService.findAll();
		return new ResponseEntity<List<Employee>>(findAll, HttpStatus.OK);
	}

	@ApiOperation("Insert a new employee in database.")
	@ApiResponses({ @ApiResponse(code = 200, message = "It will return a new employee."),
			@ApiResponse(code = 400, message = "It will return a BAD_REQUEST if the required fields are missing.") })
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) throws NullFieldsException {
		Employee addEmployee = employeeService.addEmployee(newEmployee);
		return new ResponseEntity<Employee>(addEmployee, HttpStatus.CREATED);
	}

	@ApiOperation("Get one employee from the database via id")
	@ApiResponses({ @ApiResponse(code = 200, message = "It will return an employee with specified id."),
			@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
			@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.") })
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findEmployee(@PathVariable Long id) {
		Employee findEmployee = employeeService.findEmployee(id);
		return new ResponseEntity<Employee>(findEmployee, HttpStatus.OK);
	}

	@ApiOperation("Update the employee with id")
	@ApiResponses({ @ApiResponse(code = 200, message = "It will return the updated employee."),
			@ApiResponse(code = 400, message = "It will return a BAD_REQUEST if the required fields are missing."),
			@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
			@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.") })
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		Employee updateEmployee = employeeService.updateEmployee(newEmployee, id);
		return new ResponseEntity<Employee>(updateEmployee, HttpStatus.CREATED);
	}

	@ApiOperation("Remove an employee by id")
	@ApiResponses({
			@ApiResponse(code = 204, message = "It will return a NO_CONTENT if the employee is removed from database"),
			@ApiResponse(code = 403, message = "It will return a FORBIDDEN if user is not logged in."),
			@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database.") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}