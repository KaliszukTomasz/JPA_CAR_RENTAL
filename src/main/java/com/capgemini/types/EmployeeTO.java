package com.capgemini.types;

import java.util.Date;
import java.util.Set;

public class EmployeeTO {

	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private OfficeTO office;
	private Set<CarTO> carsSet;

	public EmployeeTO() {

	}

	public EmployeeTO(Long id, String firstName, String lastName, Date dateOfBirth, OfficeTO office,
			Set<CarTO> carsSet) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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

	

}
