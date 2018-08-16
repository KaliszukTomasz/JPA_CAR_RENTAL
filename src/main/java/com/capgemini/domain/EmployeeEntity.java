package com.capgemini.domain;

import java.util.Date; //TODO czy uzyc daty sql?
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private OfficeEntity office;

	@ManyToMany(mappedBy = "employeesSet", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<CarEntity> carsSet = new HashSet<>();


	
	public EmployeeEntity() {
	}

	public EmployeeEntity(String firstName, String lastName, Date dateOfBirth, OfficeEntity office,
			Set<CarEntity> carsSet) {
		super();
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

	public OfficeEntity getOffice() {
		return office;
	}

	public void setOffice(OfficeEntity office) {
		this.office = office;
	}

	public Set<CarEntity> getCarsSet() {
		return carsSet;
	}

	public void setCarsSet(Set<CarEntity> carsSet) {
		this.carsSet = carsSet;
	}

	public void addCarEntity(CarEntity carEntity) {
		carsSet.add(carEntity);
		//TODO czy tutaj trzeba dodawac z drugiej strony?
	}

	public CarEntity removeCarEntity(CarEntity carEntity) {
		if (carsSet.remove(carEntity)) {
			return carEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

}
