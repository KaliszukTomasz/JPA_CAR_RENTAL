package com.capgemini.service.impl;

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

	@Override
	public CarTO addCarToDatabase(CarTO carTO) {
		CarEntity carEntity = CarMapper.map2CarEntity(carTO);
		carDao.save(carEntity);
		return carTO;
	}

	@Override 
	public CarTO removeCarFromDatabase(CarTO carTO) {
		CarEntity carEntity = carDao.findOne(carTO.getId());
		carDao.delete(carEntity);
		return carTO;

	}

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

	@Override
	public void addEmployeeToCar(CarTO carTO, EmployeeTO employeeTO) {
		carDao.addAttachedEmployee(carTO.getId(), employeeDao.findOne(employeeTO.getId()));
	}

	@Override
	public List<CarTO> findCarByBrandAndType(CarTO carTO) {
		if (carTO.getBrand() != null && carTO.getCarType() != null) {
			List<CarEntity> carEntityList = carDao.findCarByBrandAndType(carTO.getBrand(), carTO.getCarType());
			return CarMapper.mapListCarEntities2CarTOs(carEntityList);
		} else
			throw new IllegalArgumentException();
	}
	
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
	
	@Override
	public List<EmployeeTO> findEmployeeByOfficeAndCar(OfficeTO officeTO, CarTO carTO){
		if (officeTO.getId() == null || carTO.getId() == null) {
			throw new IllegalArgumentException();
		} else {
			List<EmployeeEntity> listEmployeeEntities = carDao.findListOfEmployeesInOfficeAssignedToCar(officeTO.getId(), carTO.getId());
			return EmployeeMapper.mapEmployeeEntityList2EmployeeTOList(listEmployeeEntities);
		}
		
		
	}

	@Override
	public List<CarTO> findAllCarsInDatabase() {
		return CarMapper.mapListCarEntities2CarTOs(carDao.findAll());
	}

}
