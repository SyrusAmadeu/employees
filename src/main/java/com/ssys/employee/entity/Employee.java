package com.ssys.employee.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "emp_name")
	private String name;

	@Column(name = "emp_email")
	private String email;

	@Column(name = "emp_departament")
	private String departament;

	@Column(name = "emp_salary")
	private Double salary;
	
	@JsonFormat(pattern="dd-MM-yyyy", timezone = "Brazil/East")
	@Temporal(TemporalType.DATE)
	@Column(name = "emp_birth_date", updatable = false)
	private Date birthDate;

	public Employee(String name, String email, String departament, double salary, Date birthDate) {
		super();
		this.name = name;
		this.email = email;
		this.departament = departament;
		this.salary = salary;
		this.birthDate = birthDate;
	}

}
