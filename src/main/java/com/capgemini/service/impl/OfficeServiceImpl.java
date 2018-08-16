package com.capgemini.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.OfficeMapper;
import com.capgemini.service.OfficeService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	OfficeDao officeDao;
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public OfficeTO addOfficeToDatabase(OfficeTO officeTO) {
		if (officeTO.checkIfEveryParamNotNullThenTrue()) {
			officeDao.save(OfficeMapper.map2OfficeEntity(officeTO));
			return officeTO;
		} else
			throw new IllegalArgumentException();
	}

	@Override
	public OfficeTO removeOfficeFromDatabase(OfficeTO officeTO) {
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		officeDao.delete(officeTO.getId());
		return OfficeMapper.map2OfficeTO(officeEntity);
	}

	@Override
	public OfficeTO changeOfficeDetails(OfficeTO officeTO) {
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		boolean saveInBase = false;
		if (officeTO.getEmail() != null) {
			officeEntity.setEmail(officeTO.getEmail());
			saveInBase = true;
		}
		if (officeTO.getPhoneNumber() != null) {
			officeEntity.setPhoneNumber(officeTO.getPhoneNumber());
			saveInBase = true;
		}
		if(saveInBase){
			officeDao.update(officeEntity);
		}
		return officeTO;
	}

	@Override
	public EmployeeTO addEmployeeToOffice(OfficeTO officeTO, EmployeeTO employeeTO) {
		EmployeeEntity employeeEntity = employeeDao.findOne(employeeTO.getId());
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		officeEntity.addEmployeeEntity(employeeEntity);
		officeDao.update(officeEntity);
		return EmployeeMapper.map2EmployeeTO(employeeEntity);
	}

	@Override
	public EmployeeTO removeEmployeeFromOffice(OfficeTO officeTO, EmployeeTO employeeTO) {
		EmployeeEntity employeeEntity = employeeDao.findOne(employeeTO.getId());
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		officeEntity.removeEmployeeEntity(employeeEntity);
		officeDao.update(officeEntity);
		return EmployeeMapper.map2EmployeeTO(employeeEntity);
	}

	@Override
	public Set<EmployeeTO> findAllEmployeesFromOffice(OfficeTO officeTO) {
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		Set<EmployeeEntity> employeeEntitySet = officeEntity.getEmployees();
		return EmployeeMapper.mapEmployeeEntitySet2EmployeeTOSet(employeeEntitySet);
	}

	@Override
	public List<EmployeeTO> findAllEmployeesFromOfficeAssignedToCar(OfficeTO officeTO, CarTO carTO) {
		if (officeTO.getId() == null || carTO.getId() == null) {
			throw new IllegalArgumentException();
		} else {
			List<EmployeeEntity> listEmplEntities = officeDao.findListOfEmployeesInOfficeAssignedToCar(officeTO.getId(),
					carTO.getId());
			return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(listEmplEntities);
		}
	}



}
