package com.capgemini.domain;

import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author TKALISZU Description: CarEntity specify all information about Car -
 *         Version, id, carType, brand, yearOfProduction, color, engineCapacity,
 *         enginePower, mileage, currentLocation, carLoans and employeesSet. As
 *         every entity has information about create_date and modify_date.
 */
@Entity
@Table(name = "Cars")
public class CarEntity {

	@Version
	private Long version;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 30)
	private String carType;
	@Column(nullable = false, length = 30)
	private String brand;
	@Column(nullable = false)
	private Year yearOfProduction;
	@Column(nullable = false, length = 30)
	private String color;
	@Column(nullable = false)
	private Integer engineCapacity;
	@Column(nullable = false)
	private Integer enginePower;
	@Column(nullable = false)
	private Integer mileage;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private OfficeEntity currentLocation;

	@OneToMany(mappedBy = "car", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	private Set<CarLoanEntity> carLoans = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Car_attachment", joinColumns = {
			@JoinColumn(name = "Car_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Employee_ID", nullable = false, updatable = false) })
	Set<EmployeeEntity> employeesSet = new HashSet<>();

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public CarEntity() {
	}

	public CarEntity(String carType, String brand, Year yearOfProduction, String color, Integer engineCapacity,
			Integer enginePower, Integer mileage, OfficeEntity currentLocation, Set<CarLoanEntity> carLoans,
			Set<EmployeeEntity> employeesSet) {
		super();
		this.carType = carType;
		this.brand = brand;
		this.yearOfProduction = yearOfProduction;
		this.color = color;
		this.engineCapacity = engineCapacity;
		this.enginePower = enginePower;
		this.mileage = mileage;
		this.currentLocation = currentLocation;
		this.carLoans = carLoans;
		this.employeesSet = employeesSet;
	}

	public Long getId() {
		return id;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Year getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Year yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(Integer engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public Integer getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(Integer enginePower) {
		this.enginePower = enginePower;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public OfficeEntity getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(OfficeEntity currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Set<CarLoanEntity> getCarLoans() {
		return carLoans;
	}

	public void setCarLoans(Set<CarLoanEntity> carLoans) {
		this.carLoans = carLoans;
	}

	public Set<EmployeeEntity> getEmployeesSet() {
		return employeesSet;
	}

	public void setEmployeesSet(Set<EmployeeEntity> employeesSet) {
		this.employeesSet = employeesSet;
	}

	public void addCarLoan(CarLoanEntity carLoanEntity) {
		carLoans.add(carLoanEntity);
		carLoanEntity.setCar(this);
	}

	public CarLoanEntity removeCarLoanEntity(CarLoanEntity carLoanEntity) {
		if (carLoans.remove(carLoanEntity)) {
			carLoanEntity.setCar(null);
			return carLoanEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

	public void addEmployeeEntityToCarEntity(EmployeeEntity employeeEntity) {
		employeesSet.add(employeeEntity);
	}

	public EmployeeEntity removeEmployeeEntityFromCarEntity(EmployeeEntity employeeEntity) {
		if (employeesSet.remove(employeeEntity)) {
			employeeEntity.removeCarEntity(this);
			return employeeEntity;
		} else {
			throw new NoSuchElementException();
		}
	}

	public void removeCarLoan(CarLoanEntity carLoanEntity) {
		carLoans.remove(carLoanEntity);
		carLoanEntity.setCar(null);

	}

	public Long getVersion() {
		return version;
	}
}
