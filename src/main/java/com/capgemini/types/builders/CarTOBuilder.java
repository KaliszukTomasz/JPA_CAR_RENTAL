package com.capgemini.types.builders;

import java.time.Year;

import com.capgemini.types.CarTO;

public class CarTOBuilder {
    private Long id;
    private String carType;
    private String brand;
    private Year yearOfProduction;
    private String color;
    private Integer engineCapacity;
    private Integer enginePower;
    private Integer mileage;
    private Long currentLocation;

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

    public CarTOBuilder setCurrentLocation(Long currentLocation) {
        this.currentLocation = currentLocation;
        return this;
    }

    public CarTO buildCarTO() {
        return new CarTO(id, carType, brand, yearOfProduction, color, engineCapacity, enginePower, mileage, currentLocation);
    }
}