package com.empmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empmanagement.entity.Employee;
import com.empmanagement.exceptions.ResourceNotFoundException;
import com.empmanagement.repository.EmployeeDao;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/employees")
	public List<Employee> getAllEmployee()
	{
		return employeeDao.findAll();
		
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return employeeDao.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employee= employeeDao.findById(id);
		if(employee.isPresent())
		{
			return employee.get();
		}
		else {
			throw new ResourceNotFoundException("Employee does not exit "+id);
			
		}
	}
	@PutMapping("/employees/{id}")
	public Employee updateEmployeeById(@PathVariable Long id,@RequestBody Employee employee2) {
		Optional<Employee> employee= employeeDao.findById(id);
		if(employee.isPresent())
		{
			employee.get().setFirstName(employee2.getFirstName());
			employee.get().setLastName(employee2.getLastName());
			employee.get().setEmailId(employee2.getEmailId());
			
			return employeeDao.save(employee.get());
			
		}
		else {
			throw new ResourceNotFoundException("Employee does not exit "+id);
			
		}
	}
	
	@DeleteMapping("/employees/{id}")
	public Employee updateEmployeeById(@PathVariable Long id) {
			Optional<Employee> employee= employeeDao.findById(id);
			if(employee.isPresent())
			{
				 employeeDao.delete(employee.get());
				 return employee.get();
				
			}
			else {
				throw new ResourceNotFoundException("Employee does not exit "+id);
				
			}
		}
	
	
}
