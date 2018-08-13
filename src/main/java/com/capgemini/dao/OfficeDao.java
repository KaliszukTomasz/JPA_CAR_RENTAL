package com.capgemini.dao;

import java.util.ArrayList;

import com.capgemini.domain.OfficeEntity;

public interface OfficeDao extends Dao<OfficeEntity, Long> {

	//TODO
	OfficeEntity changeDetailsOfOfficeEntity(Long officeId, OfficeEntity newDetailsOfficeEntity);
	OfficeEntity addEmployeeToOffice(Long officeId, Long employeeId);
	OfficeEntity eraseEmployeeFromOffice(Long officeId, Long employeeId);
	ArrayList<Long> findListOfEmployeesInOffice(Long officeId);
	
	
	
}
