package com.capgemini.dao.impl;

import java.util.EnumSet;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.enums.EmployeePosition;

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
	public void removeEmployeeFromOffice(Long officeId, Long employeeId) {

	}

	@Override
	public List<EmployeeEntity> findListOfEmployeesWorkingInOfficeQuery(Long officeId) {

		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select employee from EmployeeEntity employee " + "where (employee.office.id) = :officeId",
				EmployeeEntity.class);
		query.setParameter("officeId", officeId);
		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findListOfEmployeesTakeCareOnCarQuery(Long carId) {
		TypedQuery<EmployeeEntity> query = entityManager
				.createQuery("select employee from EmployeeEntity employee left join employee.carsSet as cars "
						+ "where (cars.id) = :carId", EmployeeEntity.class);
		query.setParameter("carId", carId);
		return query.getResultList();
	}

	@Override
	public List<EmployeeEntity> findListOfEmployeesWorkingOnPositionQuery(EmployeePosition employeePosition) {
		
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"select employee from EmployeeEntity employee "
				+ "where (employee.employeePosition) IN (:employeePosition)", EmployeeEntity.class);
			query.setParameter("employeePosition", employeePosition);
			return query.getResultList();
		
	}

//	select employee from EmployeeEntity as employee "
//	+ "where (employee.employeePosition) = (:employeePosition)
	/*TypedQuery<EmployeeEntity> query = entityManager
				.createQuery("select employee from EmployeeEntity employee left join employee.carsSet as cars "
						+ "where (employee.office.id) = :officeId AND (cars.id) = :carId", EmployeeEntity.class);
		query.setParameter("officeId", officeId);
		query.setParameter("carId", carId);
		return query.getResultList();*/
	

}
