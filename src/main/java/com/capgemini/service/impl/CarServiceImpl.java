package com.capgemini.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.OfficeMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	@Autowired
	CarDao carDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	OfficeDao officeDao;

	/*
	 * addCarTODatabase add new CarEntity to database with carTO parameters.
	 * 
	 * @param CarTO carTO
	 * 
	 * @see
	 * com.capgemini.service.CarService#addCarToDatabase(com.capgemini.types.
	 * CarTO)
	 */
	@Override
	public CarTO addCarToDatabase(CarTO carTO) {
		CarEntity carEntity = carDao.save(CarMapper.map2CarEntity(carTO));
		carTO.setId(carEntity.getId());
		return carTO;
	}

	/*
	 * removeCarFromDatabase remove carEntity from database with carTO.id
	 * 
	 * @param CarTO carTO
	 * 
	 * @see
	 * com.capgemini.service.CarService#removeCarFromDatabase(com.capgemini.
	 * types.CarTO)
	 */
	@Override
	public CarTO removeCarFromDatabase(CarTO carTO) {
		CarEntity carEntity = carDao.findOne(carTO.getId());
		carDao.delete(carEntity);
		return carTO;

	}

	/*
	 * changeCarDetails change parameters of carEntity witch carTO.id can
	 * change: carEntity.brand, carEntity.carType, carEntity.color,
	 * carEntity.location, carEntity.engineCapacity, carEntity.enginePower.
	 * 
	 * @param CarTO carTO
	 * 
	 * @see
	 * com.capgemini.service.CarService#changeCarDetails(com.capgemini.types.
	 * CarTO)
	 */
	@Override
	public CarTO changeCarDetails(CarTO carTO) {
		CarEntity carEntity = carDao.findOne(carTO.getId());
		if (carTO.getBrand() != null) {
			carEntity.setBrand(carTO.getBrand());
		}
		if (carTO.getCarType() != null) {
			carEntity.setCarType(carTO.getCarType());
		}
		if (carTO.getColor() != null) {
			carEntity.setColor(carTO.getColor());
		}
		if (carTO.getCurrentLocation() != null) {
			carEntity.setCurrentLocation(OfficeMapper.map2OfficeEntity(carTO.getCurrentLocation()));
		}
		if (carTO.getEngineCapacity() != null) {
			carEntity.setEngineCapacity(carTO.getEngineCapacity());
		}
		if (carTO.getEnginePower() != null) {
			carEntity.setEnginePower(carTO.getEnginePower());
		}
		carDao.update(carEntity);
		return carTO;
	}

	/*
	 * 
	 * addEmployeeTOCar attached carEntity with id = carTO.id to employeeEntity
	 * with id = employeeTO.id
	 * 
	 * @param CarTO carTO
	 * 
	 * @param EmployeeTO employeeTO
	 * 
	 * @see
	 * com.capgemini.service.CarService#addEmployeeToCar(com.capgemini.types.
	 * CarTO, com.capgemini.types.EmployeeTO)
	 */
	@Override
	public void addEmployeeToCar(CarTO carTO, EmployeeTO employeeTO) {
		carDao.addAttachedEmployee(carTO.getId(), employeeDao.findOne(employeeTO.getId()));
	}

	/*
	 * findCarByBrandAndType find cars from database which brand = carTO.brand,
	 * and carType = carTO.carType;
	 * 
	 * @param CarTO carTO
	 * 
	 * @see
	 * com.capgemini.service.CarService#findCarByBrandAndType(com.capgemini.
	 * types.CarTO)
	 */
	@Override
	public List<CarTO> findCarByBrandAndType(CarTO carTO) {
		if (carTO.getBrand() != null && carTO.getCarType() != null) {
			List<CarEntity> carEntityList = carDao.findCarByBrandAndType(carTO.getBrand(), carTO.getCarType());
			return CarMapper.mapListCarEntities2CarTOs(carEntityList);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/*
	 * findCarByAttachedEmployee find cars which are to employeeEntity with
	 * employeeTO.id.
	 * 
	 * @param EmployeeTO employeeTO
	 * 
	 * @see
	 * com.capgemini.service.CarService#findCarByAttachedEmployee(com.capgemini.
	 * types.EmployeeTO)
	 */
	@Override
	public Set<CarTO> findCarByAttachedEmployee(EmployeeTO employeeTO) {
		if (employeeTO != null) {
			EmployeeEntity employeeEntity = employeeDao.findOne(employeeTO.getId());
			Set<CarEntity> carEntitySet = employeeEntity.getCarsSet();
			return CarMapper.mapSetCarEntities2CarTOs(carEntitySet);

		} else {
			throw new IllegalArgumentException();
		}
	}

	/*
	 * findEmplyeeByOfficeAndCar find all employees which are connected with
	 * office with id = officeTO.id and car with id = catTO.id
	 * 
	 * @param CarTO carTO
	 * 
	 * @param OfficeTO officeTO
	 * 
	 * @see
	 * com.capgemini.service.CarService#findEmployeeByOfficeAndCar(com.capgemini
	 * .types.OfficeTO, com.capgemini.types.CarTO)
	 */
	@Override
	public List<EmployeeTO> findEmployeeByOfficeAndCar(OfficeTO officeTO, CarTO carTO) {
		if (officeTO.getId() == null || carTO.getId() == null) {
			throw new IllegalArgumentException();
		} else {
			List<EmployeeEntity> listEmployeeEntities = carDao
					.findListOfEmployeesInOfficeAssignedToCar(officeTO.getId(), carTO.getId());
			return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(listEmployeeEntities);
		}

	}

	/*
	 * findListOfCarsLoanedXTimesByDistinctClients find list of cars which are
	 * loaned X times by distinct clients
	 * 
	 * @param Long numberOfLoans
	 * 
	 * @see com.capgemini.service.CarService#
	 * findListOfCarsLoanedXTimesByDistinctClients(java.lang.Long)
	 */
	@Override
	public List<CarTO> findListOfCarsLoanedXTimesByDistinctClients(Long numberOfLoans) {
		List<CarEntity> carEntityList = carDao.findListOfCarsLoaned10TimesByDistinctClients(numberOfLoans);
		return CarMapper.mapListCarEntities2CarTOs(carEntityList);
	}

	/*
	 * findNumberOfCarsLoanedInTimeStartDateEndDate find how many cars was
	 * loaned in period of time (startDate, endDate)
	 * 
	 * @param Date startDate
	 * 
	 * @param Date endDate
	 * 
	 * @see com.capgemini.service.CarService#
	 * findNumberOfCarsLoanedInTimeStartDateEndDate(java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Long findNumberOfCarsLoanedInTimeStartDateEndDate(Date startDate, Date endDate) {
		return carDao.findNumberOfCarsLoanedInTimeStartDateEndDate(startDate, endDate);

	}

	/*
	 * findCarsLoanedInTimeStartDateEndDate find list of cars which was loaned
	 * in period of time (startDate, endDate)
	 * 
	 * @see
	 * com.capgemini.service.CarService#findCarsLoanedInTimeStartDateEndDate(
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<CarTO> findCarsLoanedInTimeStartDateEndDate(Date startDate, Date endDate) {
		List<CarEntity> carEntityList = carDao.findCarsLoanedInTimeStartDateEndDate(startDate, endDate);
		return CarMapper.mapListCarEntities2CarTOs(carEntityList);

	}

	@Override
	public List<CarTO> findAllCarsInDatabase() {
		return CarMapper.mapListCarEntities2CarTOs(carDao.findAll());
	}

	@Override
	public int findNumberOfEmployeesAssignedToThisCar(CarTO carTO) {
		return carDao.findOne(carTO.getId()).getEmployeesSet().size();

	}
}
