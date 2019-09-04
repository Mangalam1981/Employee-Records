package com.employeerecord.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeerecord.model.Employee;
import com.employeerecord.repository.EmployeeRepository;

@Service
public class EmployeeDAO {

	@Autowired
	EmployeeRepository employeeRepository;

	/*save an employee*/
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}

	/*get an employees*/
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}

	/*get an employee id*/
	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id);
	}

	/*delete an employee*/
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}

}
