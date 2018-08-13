package com.capgemini.domain;

import java.io.Serializable;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

public class CarEntity {
	private static final long serialVersionUID = 1L;

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
	@Column(nullable = false)
	private Long currentLocation;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Car_attachment", joinColumns = {
			@JoinColumn(name = "Car_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Employee_ID", nullable = false, updatable = false) })
	private Set<EmployeeEntity> employeesSet = new HashSet<>();
	
	
	public CarEntity() {
	}

	public CarEntity(Long id, String carType, String brand, Year yearOfProduction, String color, Integer engineCapacity,
			Integer enginePower, Integer mileage, Long currentLocation) {
		super();
		this.id = id;
		this.carType = carType;
		this.brand = brand;
		this.yearOfProduction = yearOfProduction;
		this.color = color;
		this.engineCapacity = engineCapacity;
		this.enginePower = enginePower;
		this.mileage = mileage;
		this.currentLocation = currentLocation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Long currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Set<EmployeeEntity> getEmployeesSet() {
		return employeesSet;
	}

	public void setEmployeesSet(Set<EmployeeEntity> employeesSet) {
		this.employeesSet = employeesSet;
	}

}
