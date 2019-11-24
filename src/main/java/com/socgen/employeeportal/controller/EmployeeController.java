package com.socgen.employeeportal.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.employeeportal.model.Employee;
import com.socgen.employeeportal.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService empService;
	
	public EmployeeController(EmployeeService empService) {
		this.empService = empService;
	}
	
	@RequestMapping(value = "/employee", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> findAll(){
		List<Employee> employeeList = empService.findAll();
		// To Return the list of employees in sorted order of firtname
	
		Collections.sort(employeeList, new Comparator<Employee>() {
			
			@Override
			public int compare(Employee emp1, Employee emp2) {
				return emp1.getFirstname().compareTo(emp2.getFirstname());
			}
		});
		
		if(employeeList.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) throws URISyntaxException{
		logger.info("Creating an Employee : {}", employee);
		try {
			Employee result = empService.save(employee);
			System.out.println("Employee added with ID:"+result.getId());
			return ResponseEntity.created(new URI("/api/employee/"+result.getId())).body(result);
		}catch(EntityExistsException ex){
			return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/employee", 
			method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) throws URISyntaxException{
		logger.info("Updating an Employee with id {}", employee.getId());
		if(employee.getId() == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}		
		try {
			Employee result = empService.update(employee);
			return ResponseEntity.created(new URI("/api/employee/"+result.getId())).body(result);
		}catch(Exception ex){
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/employee/{id}", 
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id){
		logger.info("Deleting an Employee with id: {}", id);
		empService.delete(id);
		return ResponseEntity.ok().build();
	}
}
