package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.enums.EmployeePosition;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

/**
 * @author TKALISZU
 *
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	OfficeDao officeDao;

	/*
	 * addEmployeeTODatabase add new employeeEntity to database with employeeTO
	 * parameters
	 * 
	 * @param EmployeeTO employeeTO
	 * 
	 * @see
	 * com.capgemini.service.EmployeeService#addEmployeeTODatabase(com.capgemini
	 * .types.EmployeeTO)
	 */
	@Override
	public EmployeeTO addEmployeeTODatabase(EmployeeTO employeeTO) {
		EmployeeEntity newEmployeeEntity = employeeDao.save(EmployeeMapper.map2EmployeeEntity(employeeTO));
		employeeTO.setId(newEmployeeEntity.getId());
		return employeeTO;
	}

	/*
	 * find number of employees in database
	 * 
	 * @see
	 * com.capgemini.service.EmployeeService#findNumberOfEmployeesInDatabase()
	 */
	@Override
	public Integer findNumberOfEmployeesInDatabase() {
		return employeeDao.findAll().size();
	}

	/*
	 * findListOfEmployeesWorkingInOfficeQuery find list of employees working in
	 * office with id = officeTO.id
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @see com.capgemini.service.EmployeeService#
	 * findListOfEmployeesWorkingInOfficeQuery(com.capgemini.types.OfficeTO)
	 */
	@Override
	public List<EmployeeTO> findListOfEmployeesWorkingInOfficeQuery(OfficeTO officeTO) {
		List<EmployeeEntity> list = employeeDao.findListOfEmployeesWorkingInOfficeQuery(officeTO.getId());
		return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(list);
	}

	/*
	 * findListOfEmployeesTakeCareOnCarQuery find list of employees assigned to
	 * car with id = carTO.id
	 * 
	 * @param CarTO carTO
	 * 
	 * @see com.capgemini.service.EmployeeService#
	 * findListOfEmployeesTakeCareOnCarQuery(com.capgemini.types.CarTO)
	 */
	@Override
	public List<EmployeeTO> findListOfEmployeesTakeCareOnCarQuery(CarTO carTO) {

		List<EmployeeEntity> list = employeeDao.findListOfEmployeesTakeCareOnCarQuery(carTO.getId());
		return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(list);
	}

	/*
	 * findListOfEmployeesWorkingOnPositionQuery find list of employees on
	 * position from enum (MANAGER, ACCOUNTANT, DEALER
	 * 
	 * @param EmployeePosition employeePosition
	 * 
	 * @see com.capgemini.service.EmployeeService#
	 * findListOfEmployeesWorkingOnPositionQuery(com.capgemini.enums.
	 * EmployeePosition)
	 */
	@Override
	public List<EmployeeTO> findListOfEmployeesWorkingOnPositionQuery(EmployeePosition employeePosition) {
		List<EmployeeEntity> list = employeeDao.findListOfEmployeesWorkingOnPositionQuery(employeePosition);
		return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(list);
	}
}
