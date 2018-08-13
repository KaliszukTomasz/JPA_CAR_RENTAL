package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.CarEntity;

public interface CarDao extends Dao<CarEntity, Long> {

	CarEntity addCarEntity(CarEntity carEntity);
	CarEntity removeCarEntity(CarEntity carEntity);
	CarEntity removeCarEntityByCarId(Long carId);
	CarEntity changeDetailsOfCarEntity(Long carId, CarEntity newDetailsCarEntity);
	
	
	List<CarEntity> findCarByBrand(String brand);

	List<CarEntity> findCarByOffice(Long officeId);
	List<CarEntity> findCarByType(String type);
	

}
