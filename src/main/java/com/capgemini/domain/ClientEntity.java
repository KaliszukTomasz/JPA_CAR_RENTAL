package com.capgemini.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author TKALISZU Description: ClientEntity specify all information about
 *         clientEntity - version, id, firstName, lastName, address, dateOfBirth
 *         phoneNumber, email, creditCardNumber and carLoansSet. As every entity
 *         has information about create_date and modify_date.
 */
@Entity
@Table(name = "Clients")
public class ClientEntity {

	@Version
	private Long version;
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

	@OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<CarLoanEntity> carLoansSet = new HashSet<>();

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

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
			carLoanEntity.setClient(null);
			return carLoanEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

	public Long getVersion() {
		return version;
	}
}
