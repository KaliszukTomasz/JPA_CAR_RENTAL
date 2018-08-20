package com.capgemini.types;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author TKALISZU Description: OfficeTO specify all information about officeTO
 *         - id, phoneNumber, email, address, employees, carLoansFromOffice,
 *         carReturnsToOffice and carEntitySet.
 */

public class OfficeTO {

	private Long id;
	private Integer phoneNumber;
	private String email;
	private AddressTO address;
	private Set<EmployeeTO> employees = new HashSet<>();
	private Set<CarLoanTO> carLoansFromOffice = new HashSet<>();
	private Set<CarLoanTO> carReturnsToOffice = new HashSet<>();
	private Set<CarTO> carSet = new HashSet<>();

	public OfficeTO() {

	}

	public OfficeTO(Long id, Integer phoneNumber, String email, AddressTO address, Set<EmployeeTO> employees,
			Set<CarLoanTO> carLoansFromOffice, Set<CarLoanTO> carReturnsToOffice, Set<CarTO> carSet) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.employees = employees;
		this.carLoansFromOffice = carLoansFromOffice;
		this.carReturnsToOffice = carReturnsToOffice;
		this.carSet = carSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressTO getAddress() {
		return address;
	}

	public void setAddress(AddressTO address) {
		this.address = address;
	}

	public Set<EmployeeTO> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeTO> employees) {
		this.employees = employees;
	}

	public Set<CarLoanTO> getCarLoansFromOffice() {
		return carLoansFromOffice;
	}

	public void setCarLoansFromOffice(Set<CarLoanTO> carLoansFromOffice) {
		this.carLoansFromOffice = carLoansFromOffice;
	}

	public Set<CarLoanTO> getCarReturnsToOffice() {
		return carReturnsToOffice;
	}

	public void setCarReturnsToOffice(Set<CarLoanTO> carReturnsToOffice) {
		this.carReturnsToOffice = carReturnsToOffice;
	}

	public Set<CarTO> getCarSet() {
		return carSet;
	}

	public void setCarSet(Set<CarTO> carSet) {
		this.carSet = carSet;
	}

	public boolean checkIfEveryParamNotNullThenTrue() {
		return !Stream.of(phoneNumber, email, address).allMatch(Objects::isNull);

	}

}
