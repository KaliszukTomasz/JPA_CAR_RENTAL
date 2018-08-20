package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.enums.EmployeePosition;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

	void addEmployeeToOffice(Long officeId, Long employeeId);

	void removeEmployeeFromOffice(Long officeId, Long employeeId);

	List<EmployeeEntity> findListOfEmployeesWorkingInOfficeQuery(Long id);

	List<EmployeeEntity> findListOfEmployeesTakeCareOnCarQuery(Long carId);

	List<EmployeeEntity> findListOfEmployeesWorkingOnPositionQuery(EmployeePosition employeePosition);
	
	
	
}
