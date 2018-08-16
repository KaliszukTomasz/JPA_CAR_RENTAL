package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class ClientEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 30)
	private String firstName;
	@Column(nullable = false, length = 30)
	private String lastName;
	@Column(nullable = false)
	@Embedded
	private AddressEntity address;
	@Column(nullable = false)
	private Date dateOfBirth;
	@Column(nullable = false)
	private Integer phoneNumber;
	@Column(nullable = false, length = 30)
	private String email;
	@Column(nullable = false)
	private Long creditCardNumber;

	@OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
	private Set<CarLoanEntity> carLoansSet = new HashSet<>();

	public ClientEntity() {
	}

	public ClientEntity(String firstName, String lastName, AddressEntity address, Date dateOfBirth, Integer phoneNumber,
			String email, Long creditCardNumber, Set<CarLoanEntity> carLoansSet) {
		super();
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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
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

	public Set<CarLoanEntity> getCarLoansSet() {
		return carLoansSet;
	}

	public void setCarLoansSet(Set<CarLoanEntity> carLoansSet) {
		this.carLoansSet = carLoansSet;
	}

	public void addCarLoanEntity(CarLoanEntity carLoanEntity) {
		carLoansSet.add(carLoanEntity);
		carLoanEntity.setClient(this);
	}

	public CarLoanEntity removeCarLoanEntity(CarLoanEntity carLoanEntity) {
		if (carLoansSet.remove(carLoanEntity)) {
			return carLoanEntity;
		} else {
			throw new NoSuchElementException();
		}
	}
}
