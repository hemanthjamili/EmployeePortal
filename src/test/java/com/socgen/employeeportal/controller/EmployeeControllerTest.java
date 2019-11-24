package com.socgen.employeeportal.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.socgen.employeeportal.model.Employee;
import com.socgen.employeeportal.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	List<Employee> mockEmployeesList;
	
	Employee mockEmployee = new Employee(1,"Hemanth", "Jamili", "Male", "Software Engineering");

	String exampleEmployeeJson = "{\"id\":\"1\",\"firstname\":\"Hemanth\",\"lastname\":\"Jamili\",\"gender\":\"Male\",\"dob\":\"12-11-1995\",\"department\":\"Software Engineering\"}";

	@Test
	public void fetchEmployeeDetails() throws Exception {

		Mockito.when(employeeService.findAll()).thenReturn(mockEmployeesList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/employee").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		//String expected = "{id:1,firstname:Hemanth,lastname:Jamili,gender:Male,dob:12-11-1995,department:Software Engineering}";

		
		
		JSONAssert.assertEquals(mockEmployeesList.toString(), result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void createEmployee() throws Exception {
		
		Employee mockEmployee = new Employee(1,"Hemanth", "Jamili", "Male", "Software Engineering");
		

		Mockito.when(employeeService.save(Mockito.any(Employee.class))).thenReturn(mockEmployee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/employee")
				.accept(MediaType.APPLICATION_JSON).content(exampleEmployeeJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}