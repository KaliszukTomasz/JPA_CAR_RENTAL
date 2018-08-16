package com.capgemini.types.builders;

import java.time.Year;
import java.util.Date;
import java.util.Set;

import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.AddressTO;
import com.capgemini.types.CarLoanTO;
import com.capgemini.types.ClientTO;

public class ClientTOBuilder {
	private Long id;
	private String firstName;
	private String lastName;
	private AddressTO address;
	private Date dateOfBirth;
	private Integer phoneNumber;
	private String email;
	private Long creditCardNumber;
	private Set<CarLoanTO> carLoansSet;

	public ClientTOBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public ClientTOBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public ClientTOBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public ClientTOBuilder setAddress(AddressTO address) {
		this.address = address;
		return this;
	}

	public ClientTOBuilder setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public ClientTOBuilder setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public ClientTOBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public ClientTOBuilder setCreditCardNumber(Long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
		return this;
	}

	public ClientTOBuilder setCarLoansSet(Set<CarLoanTO> carLoansSet) {
		this.carLoansSet = carLoansSet;
		return this;
	}

	public ClientTO buildClientTO() {
		checkBeforeBuild(firstName, lastName, address, dateOfBirth, phoneNumber, email, creditCardNumber);
		return new ClientTO(id, firstName, lastName, address, dateOfBirth, phoneNumber, email, creditCardNumber,
				carLoansSet);
	}

	private void checkBeforeBuild(String firstName, String lastName, AddressTO address, Date dateOfBirth,
			Integer phoneNumber, String email, Long creditCardNumber) {
		if (firstName == null || lastName == null || address == null || dateOfBirth == null || phoneNumber == null
				|| email == null || creditCardNumber == null) {
			throw new RuntimeException("Incorrect officeTO to be created");
		}
	}

}