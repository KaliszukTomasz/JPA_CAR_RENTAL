package com.capgemini.service;

import java.util.List;
import java.util.Set;

import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

public interface CarService {

	public CarTO addCarToDatabase(CarTO carTO);

	CarTO removeCarFromDatabase(CarTO carTO);

	CarTO changeCarDetails(CarTO carTO);

	void addEmployeeToCar(CarTO carTO, EmployeeTO employeeTO);

	List<CarTO> findCarByBrandAndType(CarTO carTO);

	Set<CarTO> findCarByAttachedEmployee(EmployeeTO employeeTO);

	List<CarTO> findAllCarsInDatabase();

	List<EmployeeTO> findEmployeeByOfficeAndCar(OfficeTO officeTO, CarTO carTO);

	int findNumberOfEmployeesAssignedToThisCar(CarTO carTO);

}
