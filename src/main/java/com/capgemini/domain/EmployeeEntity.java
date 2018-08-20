package com.capgemini.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.capgemini.enums.EmployeePosition;

/**
 * @author TKALISZU Description: EmployeeEntity specify all information about
 *         employeeEntity - version, id, firstName, lastName, dateOfBirth,
 *         employeePosition, office and carSet. As every entity has information
 *         about create_date and modify_date.
 */

@Entity
@Table(name = "Employees")
public class EmployeeEntity {

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
	private Date dateOfBirth;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EmployeePosition employeePosition;

	@ManyToOne
	private OfficeEntity office;

	@ManyToMany(mappedBy = "employeesSet")
	private Set<CarEntity> carsSet = new HashSet<>();

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

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

	public EmployeePosition getEmployeePosition() {
		return employeePosition;
	}

	public void setEmployeePosition(EmployeePosition employeePosition) {
		this.employeePosition = employeePosition;
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
		carEntity.addEmployeeEntityToCarEntity(this);
	}

	public CarEntity removeCarEntity(CarEntity carEntity) {
		if (carsSet.remove(carEntity)) {
			carEntity.removeEmployeeEntityFromCarEntity(this);
			return carEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

	public Long getVersion() {
		return version;
	}
}
