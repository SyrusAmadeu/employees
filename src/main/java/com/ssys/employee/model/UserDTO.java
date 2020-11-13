package com.ssys.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {
	private String username;
	private String password;
}