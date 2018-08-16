package com.capgemini.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	
	@Autowired
	OfficeDao officeDao;
	
	
	@Override
	public void addEmployeeToOffice(Long officeId, Long employeeId) {
		EmployeeEntity employeeEntity = findOne(employeeId);
		OfficeEntity officeEntity = officeDao.findOne(officeId);
		officeEntity.addEmployeeEntity(employeeEntity);
		
	}
	
	@Override
	public void removeEmployeeFromOffice(Long officeId, Long employeeId){
		
		
	}

	
	

}
