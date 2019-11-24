package com.socgen.employeeportal.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socgen.employeeportal.model.Employee;
import com.socgen.employeeportal.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	public EmployeeService(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	public Employee save(Employee employee) {
		if(employee.getId()!=null) {
			throw new EntityExistsException("Employee already Exists with ID: "+employee.getId());			
		}
		return empRepo.save(employee);
	}
	
	public Employee update(Employee employee) {
		if(employee.getId()==null) {
			throw new EntityNotFoundException("Employee Not Found");			
		}
		return empRepo.save(employee);
	}
	
	public List<Employee> findAll(){
		return empRepo.findAll();
	}
	
	public Employee findOne(Integer id) {
		
		Employee result = empRepo.findById(id).orElse(null);
		if(result == null)
			throw new EntityNotFoundException("Employee Not Found");
		return result;
		
	}
	
	public void delete(Integer id) {
		empRepo.deleteById(id);		 
	}
	
	
}
