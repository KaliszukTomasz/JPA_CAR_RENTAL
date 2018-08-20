package com.capgemini.types.builders;

import java.util.Date;
import java.util.Set;

import com.capgemini.enums.EmployeePosition;
import com.capgemini.exceptions.IncorrectEmployeeTOException;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

/**
 *
 * EmployeeTO builder
 * 
 * @author TKALISZU
 *
 */
public class EmployeeTOBuilder {
	private Long id;
	private String firstName;
	private String lastName;
	private EmployeePosition employeePosition;
	private Date dateOfBirth;
	private OfficeTO office;
	private Set<CarTO> carsSet;

	public EmployeeTOBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public EmployeeTOBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public EmployeeTOBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public EmployeeTOBuilder setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public EmployeeTOBuilder setOffice(OfficeTO office) {
		this.office = office;
		return this;
	}

	public EmployeeTOBuilder setEmployeePosition(EmployeePosition employeePosition) {
		this.employeePosition = employeePosition;
		return this;
	}

	public EmployeeTOBuilder setCarsSet(Set<CarTO> carsSet) {
		this.carsSet = carsSet;
		return this;
	}

	public EmployeeTO buildEmployeeTO() {
		checkBeforeBuild(firstName, lastName, dateOfBirth, office);
		return new EmployeeTO(id, firstName, lastName, employeePosition, dateOfBirth, office, carsSet);
	}

	private void checkBeforeBuild(String firstName, String lastName, Date dateOfBirth, OfficeTO office) {
		if (firstName == null || lastName == null || dateOfBirth == null || office == null) {
			throw new IncorrectEmployeeTOException("Incorrect employeeTO to be created");
		}
	}
}