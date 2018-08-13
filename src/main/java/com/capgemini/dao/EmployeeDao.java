package com.capgemini.dao;

import java.util.ArrayList;

import com.capgemini.domain.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
//TODO
	
	ArrayList<Long> findListOfEmployeesConnectedWithCar(Long carId);
	
	
}
