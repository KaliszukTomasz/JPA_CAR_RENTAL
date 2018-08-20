package com.capgemini.types.builders;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.exceptions.IncorrectCarTOException;
import com.capgemini.types.CarTO;
import com.capgemini.types.OfficeTO;

/**
 * CarTO builder
 * 
 * @author TKALISZU
 *
 */
public class CarTOBuilder {
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

	public CarTOBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public CarTOBuilder setCarType(String carType) {
		this.carType = carType;
		return this;
	}

	public CarTOBuilder setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public CarTOBuilder setYearOfProduction(Year yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
		return this;
	}

	public CarTOBuilder setColor(String color) {
		this.color = color;
		return this;
	}

	public CarTOBuilder setEngineCapacity(Integer engineCapacity) {
		this.engineCapacity = engineCapacity;
		return this;
	}

	public CarTOBuilder setEnginePower(Integer enginePower) {
		this.enginePower = enginePower;
		return this;
	}

	public CarTOBuilder setMileage(Integer mileage) {
		this.mileage = mileage;
		return this;
	}

	public CarTOBuilder setCurrentLocation(OfficeTO officeTO) {
		this.currentLocation = officeTO;
		return this;
	}

	public CarTOBuilder setEmployeesSet(Set<EmployeeEntity> employeesSet) {
		this.employeesSet = employeesSet;
		return this;
	}

	public CarTO buildCarTO() {
		checkBeforeBuild(carType, brand, yearOfProduction, color, engineCapacity, enginePower, mileage);
		return new CarTO(id, carType, brand, yearOfProduction, color, engineCapacity, enginePower, mileage,
				currentLocation, employeesSet);
	}

	private void checkBeforeBuild(String carType, String brand, Year yearOfProduction, String color,
			Integer engineCapacity, Integer enginePower, Integer mileage) {
		if (carType == null || brand == null || yearOfProduction == null || color == null || engineCapacity == null
				|| enginePower == null || mileage == null ) {
			throw new IncorrectCarTOException("Incorrect carTO to be created");
		}
	}
}