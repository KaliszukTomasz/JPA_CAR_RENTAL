package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

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

	@Override
	public List<EmployeeTO> findListOfEmployeesTakeCareOnCarQuery(CarTO carTO) {

		List<EmployeeEntity> list = employeeDao.findListOfEmployeesTakeCareOnCarQuery(carTO.getId());
		return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(list);

		// TypedQuery<EmployeeEntity> query = EntityManager
		// .createQuery("select employee from EmployeeEntity employee "
		// + "where :carEntity MEMBER OF (employee.carSet)",
		// EmployeeEntity.class);
		// query.setParameter("carEntity", carDao.findOne(carId));
		// return query.getResultList();

		// List<EmployeeEntity> list =
		// employeeDao.findListOfEmployeesTakeCareOnCarQuery(carTO.getId());
		// return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(list);

	}
}
