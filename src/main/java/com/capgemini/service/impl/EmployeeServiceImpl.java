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
	public EmployeeTO addEmployeeTODatabase(EmployeeTO employeeTO) {
		EmployeeEntity newEmployeeEntity = employeeDao.save(EmployeeMapper.map2EmployeeEntity(employeeTO));
		employeeTO.setId(newEmployeeEntity.getId());
		return employeeTO;
	}
}
