package com.employeerecord.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employeerecord.dao.EmployeeDAO;
import com.employeerecord.exception.EmployeeRecordsNotFoundException;
import com.employeerecord.model.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/company")
@Api(value ="Employee Records Management", description = "Employee Records Management System")
public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO;

	/**get all employees*/
	@ApiOperation(value = "List of employees", response = EmployeeController.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 400, message = "Please Provide Valid Input"),
			@ApiResponse(code = 404, message = "Employee Recoeds are not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@RequestMapping(method = RequestMethod.GET, path = "/getAllEmployees")
	public List<Employee> getAllEmployees(){
		return employeeDAO.findAll();
	}

	/**get an employee id*/
	@ApiOperation(value = "Get an employee Id")
	@RequestMapping(method = RequestMethod.GET, path = "/getEmployeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable(value="id") Long id) throws EmployeeRecordsNotFoundException {

		Employee employee = employeeDAO.findById(id).orElseThrow(() -> new EmployeeRecordsNotFoundException(id));
		/*
		 * if(employee==null) { return ResponseEntity.notFound().build(); }
		 */
		return ResponseEntity.ok().body(employee);		
	}

	/**save an employee*/
	@ApiOperation(value = "Add an employee")
	@RequestMapping(method = RequestMethod.POST, path = "/createEmployee")
	public Employee createEmployee(@ApiParam(value = "Employee object store in database table", required = true) @Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}

	/**update an employee*/
	@ApiOperation(value = "Update an employee")
	@RequestMapping(method = RequestMethod.PUT, path = "/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long id, @Valid @RequestBody Employee empDetails){

		Employee employee = employeeDAO.findById(id).orElseThrow(() -> new EmployeeRecordsNotFoundException(id)) ;
		/*
		 * if(employee==null) { return ResponseEntity.notFound().build(); }
		 */

		//employee.setName(empDetails.getName());
		employee.setDesignation(empDetails.getDesignation());
		employee.setEmail(empDetails.getEmail());
		//employee.setDateOfJoin(empDetails.getDateOfJoin());

		Employee updateEmployee = employeeDAO.save(employee);		
		return ResponseEntity.ok().body(updateEmployee);
	}

	/**delete an employee*/
	@ApiOperation(value = "Delete an employee")
	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteEmployee/{id}")
	public ResponseEntity<Employee> deleteEmployee(@ApiParam(value = "Employee Id from which employee object will delete from database table", required = true) @PathVariable(value="id") Long id){

		Employee employee = employeeDAO.findById(id).orElseThrow(() -> new EmployeeRecordsNotFoundException(id));
		/*
		 * if(employee==null) { return ResponseEntity.notFound().build(); }
		 */

		employeeDAO.delete(employee);
		return ResponseEntity.ok().build();		
	}	

}
