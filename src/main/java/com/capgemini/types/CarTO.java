package com.capgemini.types;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import com.capgemini.domain.EmployeeEntity;

/**
 * @author TKALISZU Description: CarTO specify all information about Car - id,
 *         carType, brand, yearOfProduction, color, engineCapacity, enginePower,
 *         mileage, currentLocation, carLoans and employeesSet.
 */
public class CarTO {

	private Long id;
	private String carType;
	private String brand;
	private Year yearOfProduction;
	private String color;
	private Integer engineCapacity;
	private Integer enginePower;
	private Integer mileage;
	private OfficeTO currentLocation;
	private Set<EmployeeEntity> employeesSet = new HashSet<>();

	public CarTO() {
	}

	public CarTO(Long id, String carType, String brand, Year yearOfProduction, String color, Integer engineCapacity,
			Integer enginePower, Integer mileage, OfficeTO currentLocation, Set<EmployeeEntity> employeesSet) {
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
		this.employeesSet = employeesSet;
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

	public OfficeTO getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(OfficeTO currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Set<EmployeeEntity> getEmployeesSet() {
		return employeesSet;
	}

	public void setEmployeesIdSet(Set<EmployeeEntity> employeesSet) {
		this.employeesSet = employeesSet;
	}

}