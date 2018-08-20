package com.capgemini.dao;

import java.util.List;
import java.util.Set;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;

public interface OfficeDao extends Dao<OfficeEntity, Long> {

	Set<EmployeeEntity> findListOfEmployeesInOffice(Long officeId);
	List<EmployeeEntity> findListOfEmployeesInOfficeAssignedToCar(Long officeId, Long carId);
	
	
	
}
