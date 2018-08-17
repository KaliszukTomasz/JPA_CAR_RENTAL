package com.capgemini.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
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

	@Override
	public EmployeeTO addEmployeeTODatabase(EmployeeTO employeeTO) {
		EmployeeEntity newEmployeeEntity = employeeDao.save(EmployeeMapper.map2EmployeeEntity(employeeTO));
		employeeTO.setId(newEmployeeEntity.getId());
		return employeeTO;
	}
	
	@Override
	public Integer findNumberOfEmployeesInDatabase(){
		return employeeDao.findAll().size();
	}

	@Override
	public List<EmployeeTO> findListOfEmployeesWorkingInOfficeQuery(OfficeTO officeTO) {
		List<EmployeeEntity> list = employeeDao.findListOfEmployeesWorkingInOfficeQuery(officeTO.getId());
		return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(list);
	}
	
	@Override
	public List<EmployeeTO> findListOfEmployeesTakeCareOnCarQuery(CarTO carTO) {
		
		List<EmployeeEntity> list = employeeDao.findListOfEmployeesTakeCareOnCarQuery(carTO.getId());
		return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(list);
		

		
//			TypedQuery<EmployeeEntity> query = EntityManager
//					.createQuery("select employee from EmployeeEntity employee "
//							+ "where :carEntity MEMBER OF (employee.carSet)", EmployeeEntity.class);
//			query.setParameter("carEntity", carDao.findOne(carId));
//			return query.getResultList();
		
//		List<EmployeeEntity> list = employeeDao.findListOfEmployeesTakeCareOnCarQuery(carTO.getId());
//		return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(list);
	
	}
}
