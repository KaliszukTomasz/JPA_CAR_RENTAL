package com.capgemini.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;

@Repository
public class OfficeDaoImpl extends AbstractDao<OfficeEntity, Long> implements OfficeDao {

	@Override
	public Set<EmployeeEntity> findListOfEmployeesInOffice(Long officeId) {
		return findOne(officeId).getEmployees();

	}

	@Override
	public List<EmployeeEntity> findListOfEmployeesInOfficeAssignedToCar(Long officeId, Long carId) {
		TypedQuery<EmployeeEntity> query = entityManager
				.createQuery("select employee from EmployeeEntity employee inner join employee.carsSet as cars "
						+ "where (employee.office.id) = :officeId and cars.id = :carId", EmployeeEntity.class);
		query.setParameter("officeId", officeId);
		query.setParameter("carId", carId);
		return query.getResultList();
	}

}
