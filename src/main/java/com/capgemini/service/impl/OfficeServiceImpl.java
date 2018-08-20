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

	/*
	 * addOfficeToDatabase add new officeTO to database.
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @see
	 * com.capgemini.service.OfficeService#addOfficeToDatabase(com.capgemini.
	 * types.OfficeTO)
	 */
	@Override
	public OfficeTO addOfficeToDatabase(OfficeTO officeTO) {
		if (officeTO.checkIfEveryParamNotNullThenTrue()) {
			OfficeEntity officeEntity = officeDao.save(OfficeMapper.map2OfficeEntity(officeTO));
			officeTO.setId(officeEntity.getId());
			return officeTO;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/*
	 * removeOfficeFromDatabase remove officeEntity with officeTO.id from
	 * database
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @see com.capgemini.service.OfficeService#removeOfficeFromDatabase(com.
	 * capgemini.types.OfficeTO)
	 */
	@Override
	public OfficeTO removeOfficeFromDatabase(OfficeTO officeTO) {
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		officeDao.delete(officeTO.getId());
		return OfficeMapper.map2OfficeTO(officeEntity);
	}

	/*
	 * changeOfficeDatails change details of office in database. Dates come from
	 * officeTO update to entity with officeTO.id
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @see
	 * com.capgemini.service.OfficeService#changeOfficeDetails(com.capgemini.
	 * types.OfficeTO)
	 */
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
		if (saveInBase) {
			officeDao.update(officeEntity);
		}
		return officeTO;
	}

	/*
	 * addEmployeetoOffce add attachement to officeEntity from database witch
	 * officeTO.id and attached employeeEntity with employeeTO.id
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @see
	 * com.capgemini.service.OfficeService#addEmployeeToOffice(com.capgemini.
	 * types.OfficeTO, com.capgemini.types.EmployeeTO)
	 */
	@Override
	public EmployeeTO addEmployeeToOffice(OfficeTO officeTO, EmployeeTO employeeTO) {
		EmployeeEntity employeeEntity = employeeDao.findOne(employeeTO.getId());
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		officeEntity.addEmployeeEntity(employeeEntity);
		officeDao.update(officeEntity);
		return EmployeeMapper.map2EmployeeTO(employeeEntity);
	}

	/*
	 * removeEmployeeFromOffice remove attachement officeEntity - employeeEntity
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @param EmployeeTO EmployeeTO
	 * 
	 * @see com.capgemini.service.OfficeService#removeEmployeeFromOffice(com.
	 * capgemini.types.OfficeTO, com.capgemini.types.EmployeeTO)
	 */
	@Override
	public EmployeeTO removeEmployeeFromOffice(OfficeTO officeTO, EmployeeTO employeeTO) {
		EmployeeEntity employeeEntity = employeeDao.findOne(employeeTO.getId());
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		officeEntity.removeEmployeeEntity(employeeEntity);
		return EmployeeMapper.map2EmployeeTO(employeeEntity);
	}

	/*
	 * findAllEmployeesFromOffice take set of employeeTO which are connected to
	 * the office
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @see Set<EmployeeTO>
	 */
	@Override
	public Set<EmployeeTO> findAllEmployeesFromOffice(OfficeTO officeTO) {
		OfficeEntity officeEntity = officeDao.findOne(officeTO.getId());
		Set<EmployeeEntity> employeeEntitySet = officeEntity.getEmployees();
		return EmployeeMapper.mapEmployeeEntitySet2EmployeeTOSet(employeeEntitySet);
	}

	/*
	 * findAllEmployeesFromOfficeAssignedToCar find all employees from office
	 * which are assigned to car witch carTO.id.
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @param CarTO carTO
	 * 
	 * @see List<EmployeeTO>
	 */
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

	/*
	 * findSizeOfCollectionOfEmployeesInOffice find number of employees assigned
	 * to officeentity with officeTO.id
	 * 
	 * @see com.capgemini.service.OfficeService#
	 * findSizeOfCollectionOfEmployeesInOffice(com.capgemini.types.OfficeTO)
	 */
	@Override
	public Integer findSizeOfCollectionOfEmployeesInOffice(OfficeTO officeTO) {
		return officeDao.findOne(officeTO.getId()).getEmployees().size();
	}

}
