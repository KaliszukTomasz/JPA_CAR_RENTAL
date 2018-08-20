package com.capgemini.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

	@Autowired
	OfficeDao officeDao;
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public List<CarEntity> findCarByBrandAndType(String brand, String type) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car where upper(car.brand) "
						+ "like concat(upper(:brand), '%') AND upper(car.carType) " + "like concat(upper(:type),'%')",
				CarEntity.class);
		query.setParameter("brand", brand);
		query.setParameter("type", type);
		return query.getResultList();

	}

	@Override
	public Set<CarEntity> findCarByOffice(Long officeId) {
		OfficeEntity officeEntity = officeDao.findOne(officeId);
		return officeEntity.getCarEntitySet();
	}

	@Override
	public CarEntity addAttachedEmployee(Long carId, EmployeeEntity employeeEntity) {
		CarEntity carEntity = findOne(carId);
		carEntity.addEmployeeEntityToCarEntity(employeeEntity);
		employeeEntity.addCarEntity(carEntity);

		return carEntity;
	}

	@Override
	public List<EmployeeEntity> findListOfEmployeesInOfficeAssignedToCar(Long officeId, Long carId) {

		TypedQuery<EmployeeEntity> query = entityManager
				.createQuery(
						"select employee from EmployeeEntity employee left join employee.carsSet as cars "
								+ "where (employee.office.id) = :officeId AND (cars.id) = :carId",
						EmployeeEntity.class);
		query.setParameter("officeId", officeId);
		query.setParameter("carId", carId);
		return query.getResultList();

	}

	@Override
	public List<CarEntity> findListOfCarsLoaned10TimesByDistinctClients(Long numberOfCarLoans) {

		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car "
						+ "where car.id IN (select carLoan.car.id from CarLoanEntity carLoan "
							+ "group by carLoan.car.id "
							+ "having count(distinct carLoan.client.id) >= :numberOfCarLoans)",
				CarEntity.class);

		query.setParameter("numberOfCarLoans", numberOfCarLoans);
		return query.getResultList();
	}

	@Override
	public Long findNumberOfCarsLoanedInTimeStartDateEndDate(Date startDate, Date endDate) {
		TypedQuery<Long> query = entityManager
				.createQuery(
						"select count(car) from CarEntity car join car.carLoans carLoans "
								+ "where carLoans.loanDate <= :startDate and carLoans.returnDate >= :endDate "
								+ "group by car",
						Long.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.getSingleResult();
	}
		
		
		@Override
	public List<CarEntity> findCarsLoanedInTimeStartDateEndDate(Date startDate, Date endDate) {
		TypedQuery<CarEntity> query = entityManager
				.createQuery(
						"select car from CarEntity car left join car.carLoans carLoans "
								+ "where carLoans.loanDate <= :startDate and carLoans.returnDate >= :endDate",
						CarEntity.class);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.getResultList();
	}

	
}
