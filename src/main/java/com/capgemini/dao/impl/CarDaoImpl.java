package com.capgemini.dao.impl;

import java.security.spec.EncodedKeySpec;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.BookEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

	@Override
	public List<CarEntity> findCarByBrandAndType(String brand, String type) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car where upper(car.brand) "
				+ "like concat(upper(:brand), '%') AND upper(car.type) "
				+ "like concat(upper(:type),'%')", CarEntity.class);
		query.setParameter("brand", brand);
		query.setParameter("type", type);
		return query.getResultList();
		
	}

	@Override
	public Set<CarEntity> findCarByOffice(Long officeId) {
		OfficeDao officeDao = new OfficeDaoImpl();
		OfficeEntity officeEntity = officeDao.findOne(officeId);
		return officeEntity.getCarEntitySet();
	}

	@Override
	public CarEntity addAttachedEmployee(Long carId, EmployeeEntity employeeEntity) {
		CarDao carDao = new CarDaoImpl();
		CarEntity carEntity = carDao.findOne(carId);
		carEntity.addEmployeeEntityToCarEntity(employeeEntity);
		employeeEntity.addCarEntity(carEntity);
		return carEntity;
	}

	@Override
	public Set<CarEntity> findCarByEmployee(Long employeeId) {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		EmployeeEntity employeeEntity = employeeDao.findOne(employeeId);
		return employeeEntity.getCarsSet();
	}

}
