package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.EmployeeTO;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	OfficeDao officeDao;

	@Override
	public void addEmployeeTODatabase(EmployeeTO employeeTO) {
		EmployeeEntity employeeEntity = EmployeeMapper.map2EmployeeEntity(employeeTO);
		employeeDao.save(employeeEntity);
//		if (employeeTO.getOffice() != null) {
//			OfficeEntity officeEntity = officeDao.findOne(employeeTO.getOffice().getId());
//			officeEntity.addEmployeeEntity(employeeEntity);
//			officeDao.update(officeEntity);
//		}
	}
}
