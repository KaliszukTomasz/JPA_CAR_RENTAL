package com.capgemini.types;

import java.time.Year;

public class CarTO {

    private Long id;
    private String carType;
    private String brand;
    private Year yearOfProduction;
    private String color;
    private Integer engineCapacity;
    private Integer enginePower;
    private Integer mileage;
    private Long currentLocation;

    public CarTO(){}


    public CarTO(Long id, String carType, String brand, Year yearOfProduction, String color, Integer engineCapacity,
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




}