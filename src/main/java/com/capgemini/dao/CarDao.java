package com.capgemini.dao;

import java.util.List;
import java.util.Set;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;

public interface CarDao extends Dao<CarEntity, Long> {

	List<CarEntity> findCarByBrandAndType(String brand, String type);
	Set<CarEntity> findCarByOffice(Long officeId);
	CarEntity addAttachedEmployee(Long carId, EmployeeEntity employeeEntity);
	Set<CarEntity> findCarByEmployee(Long employeeId);
	

}
