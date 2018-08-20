package com.capgemini.types;

import java.util.Date;
import java.util.Set;

import com.capgemini.enums.EmployeePosition;

/*
* @author TKALISZU Description: EmployeeTO specify all information about
*         employeeTO - id, firstName, lastName, dateOfBirth,
*         employeePosition, office and carSet. 
*/
public class EmployeeTO {

	private Long id;
	private String firstName;
	private String lastName;
	private EmployeePosition employeePosition;
	private Date dateOfBirth;
	private OfficeTO office;
	private Set<CarTO> carsSet;

	public EmployeeTO() {

	}

	public EmployeeTO(Long id, String firstName, String lastName, EmployeePosition employeePosition, Date dateOfBirth, OfficeTO office,
			Set<CarTO> carsSet) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeePosition = employeePosition;
		this.dateOfBirth = dateOfBirth;
		this.office = office;
		this.carsSet = carsSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public OfficeTO getOffice() {
		return office;
	}

	public void setOffice(OfficeTO office) {
		this.office = office;
	}

	public Set<CarTO> getCarsSet() {
		return carsSet;
	}

	public void setCarsSet(Set<CarTO> carsSet) {
		this.carsSet = carsSet;
	}

	
	public EmployeePosition getEmployeePosition() {
		return employeePosition;
	}

	public void setEmployeePosition(EmployeePosition employeePosition) {
		this.employeePosition = employeePosition;
	}

	

}
