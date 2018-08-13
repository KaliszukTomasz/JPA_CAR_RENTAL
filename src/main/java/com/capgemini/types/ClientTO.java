package com.capgemini.types;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClientTO {

	private Long id;
	private String firstName;
	private String lastName;
	private AddressTO address;
	private Date dateOfBirth;
	private Integer phoneNumber;
	private String email;
	private Long creditCardNumber;
	private Set<CarLoanTO> carLoansSet = new HashSet<>();

	public ClientTO() {

	}

	public ClientTO(Long id, String firstName, String lastName, AddressTO address, Date dateOfBirth,
			Integer phoneNumber, String email, Long creditCardNumber, Set<CarLoanTO> carLoansSet) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.creditCardNumber = creditCardNumber;
		this.carLoansSet = carLoansSet;
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

	public AddressTO getAddress() {
		return address;
	}

	public void setAddress(AddressTO address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public Long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(Long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Set<CarLoanTO> getCarLoansSet() {
		return carLoansSet;
	}

	public void setCarLoansSet(Set<CarLoanTO> carLoansSet) {
		this.carLoansSet = carLoansSet;
	}

}
