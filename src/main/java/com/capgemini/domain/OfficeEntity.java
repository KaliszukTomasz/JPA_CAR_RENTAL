package com.capgemini.domain;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Offices")
public class OfficeEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Integer phoneNumber;
	@Column(nullable = false, length = 30)
	private String email;
	@Column(nullable = false)
	@Embedded
	private AddressEntity address;

	@OneToMany(mappedBy = "office")
	private Set<EmployeeEntity> employees = new HashSet<>();

	@OneToMany(mappedBy = "loanOffice")
	private Set<CarLoanEntity> carLoansFromOffice = new HashSet<>();

	@OneToMany(mappedBy = "returnOffice")
	private Set<CarLoanEntity> carReturnsToOffice = new HashSet<>();

	@OneToMany(mappedBy = "currentLocation")
	private Set<CarEntity> carEntitySet = new HashSet<>();

	public OfficeEntity() {
	}

	public OfficeEntity(Integer phoneNumber, String email, AddressEntity address, Set<EmployeeEntity> employees,
			Set<CarLoanEntity> carLoansFromOffice, Set<CarLoanEntity> carReturnsToOffice, Set<CarEntity> carEntitySet) {
		super();
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.employees = employees;
		this.carLoansFromOffice = carLoansFromOffice;
		this.carReturnsToOffice = carReturnsToOffice;
		this.carEntitySet = carEntitySet;
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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public Set<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public Set<CarLoanEntity> getCarLoansFromOffice() {
		return carLoansFromOffice;
	}

	public void setCarLoansFromOffice(Set<CarLoanEntity> carLoansFromOffice) {
		this.carLoansFromOffice = carLoansFromOffice;
	}

	public Set<CarLoanEntity> getCarReturnsToOffice() {
		return carReturnsToOffice;
	}

	public void setCarReturnsToOffice(Set<CarLoanEntity> carReturnsToOffice) {
		this.carReturnsToOffice = carReturnsToOffice;
	}

	public Set<CarEntity> getCarEntitySet() {
		return carEntitySet;
	}

	public void setCarEntitySet(Set<CarEntity> carEntitySet) {
		this.carEntitySet = carEntitySet;
	}

	public void addCarEntityToCurrentLocation(CarEntity carEntity) {
		carEntitySet.add(carEntity);
	}

	public CarEntity removeCarEntityFromCurrentLocation(CarEntity carEntity) {
		if (carEntitySet.remove(carEntity)) {
			return carEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

	public void addCarLoanEntityFromOffice(CarLoanEntity carLoanEntity) {
		carLoansFromOffice.add(carLoanEntity);
	}

	public CarLoanEntity removeCarLoanToOffice(CarLoanEntity carLoanEntity) {
		if (carLoansFromOffice.remove(carLoanEntity)) {
			return carLoanEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

	public void addCarReturnLoanEntityToOffice(CarLoanEntity carLoanEntity) {
		carReturnsToOffice.add(carLoanEntity);
	}

	public CarLoanEntity removeReturnCarLoanToOffice(CarLoanEntity carLoanEntity) {
		if (carReturnsToOffice.remove(carLoanEntity)) {
			return carLoanEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

	public void addEmployeeEntity(EmployeeEntity employeeEntity) {
		employees.add(employeeEntity);
	}

	public EmployeeEntity removeEmployeeEntity(EmployeeEntity employeeEntity) {
		if (employees.remove(employeeEntity)) {
			return employeeEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

}
