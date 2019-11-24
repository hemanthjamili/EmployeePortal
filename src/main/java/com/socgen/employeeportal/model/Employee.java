package com.socgen.employeeportal.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@NotEmpty
	@Column(name = "firstname", nullable = false)
	private String firstname;
	
	@NotEmpty
	@Column(name = "lastname", nullable = false)
	private String lastname;
	
	@NotEmpty
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "dob", nullable = false)
	private Date dob;
	
	@NotEmpty
	@Column(name = "department", nullable = false)
	private String department;
	
	public Employee() {
		
	}
	
	public Employee(Integer id, @NotEmpty String firstname, @NotEmpty String lastname, @NotEmpty String gender,
			Date dob, @NotEmpty String department) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.dob = dob;
		this.department = department;
	}
	
	public Employee(Integer id, @NotEmpty String firstname, @NotEmpty String lastname, @NotEmpty String gender, @NotEmpty String department) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.dob = new Date(12,11,1995);
		this.department = department;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstname + ", lastName=" + lastname + ", gender=" + gender
				+ ", dob=" + dob
				+ ", department=" + department + "]";
	}

}