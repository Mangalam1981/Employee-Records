package com.employeerecord.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeRecordsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmployeeRecordsNotFoundException(Long id) {
		super(String.format("Employee Records is not found with id : '%s'", id));
	}

}
