package com.capgemini.dao;

import java.util.ArrayList;

import com.capgemini.domain.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
//TODO
//	
//	ArrayList<Long> findListOfEmployeesConnectedWithCar(Long carId);
//	
//	
	

	void addEmployeeToOffice(Long officeId, Long employeeId);

	void removeEmployeeFromOffice(Long officeId, Long employeeId);
	
}
