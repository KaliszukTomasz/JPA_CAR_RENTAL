package com.capgemini.domain;

import java.io.Serializable;
import java.util.Date; //TODO czy uzyc daty sql?

import javax.persistence.*;

@Entity
@Table(name = "Employees")
public class EmployeeEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 30)
	private String firstName;
	@Column(nullable = false, length = 30)
	private String lastName;
	@Column(nullable = false)
	private Date dateOfBirth;
	// @Column
	// private enum positionInCompany {
	// MANAGER, ACCOUNTANT, DEALER ;
	// }

	public EmployeeEntity() {
	}

	public EmployeeEntity(Long id, String firstName, String lastName, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
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

}
