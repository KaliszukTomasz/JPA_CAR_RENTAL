package com.capgemini.types.builders;

import java.util.Set;

import com.capgemini.types.AddressTO;
import com.capgemini.types.CarLoanTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

/**
 * OfficeTO builder
 * 
 * @author TKALISZU
 *
 */
public class OfficeTOBuilder {
	private Long id;
	private Integer phoneNumber;
	private String email;
	private AddressTO address;
	private Set<EmployeeTO> employees;
	private Set<CarLoanTO> carLoansFromOffice;
	private Set<CarLoanTO> carReturnsToOffice;
	private Set<CarTO> carSet;

	public OfficeTOBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public OfficeTOBuilder setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public OfficeTOBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public OfficeTOBuilder setAddress(AddressTO address) {
		this.address = address;
		return this;
	}

	public OfficeTOBuilder setEmployees(Set<EmployeeTO> employees) {
		this.employees = employees;
		return this;
	}

	public OfficeTOBuilder setCarLoansFromOffice(Set<CarLoanTO> carLoansFromOffice) {
		this.carLoansFromOffice = carLoansFromOffice;
		return this;
	}

	public OfficeTOBuilder setCarReturnsToOffice(Set<CarLoanTO> carReturnsToOffice) {
		this.carReturnsToOffice = carReturnsToOffice;
		return this;
	}

	public OfficeTOBuilder setCarSet(Set<CarTO> carSet) {
		this.carSet = carSet;
		return this;
	}

	public OfficeTO buildOfficeTO() {
		checkBeforeBuild(phoneNumber, email, address);
		return new OfficeTO(id, phoneNumber, email, address, employees, carLoansFromOffice, carReturnsToOffice, carSet);
	}

	private void checkBeforeBuild(Integer phoneNumber, String email, AddressTO address) {
		if (phoneNumber == null || email == null || address == null) {
			throw new RuntimeException("Incorrect officeTO to be created");
		}
	}
}