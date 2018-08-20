package com.capgemini.dao.impl;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarLoanDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CarLoanEntity;
import com.capgemini.domain.ClientEntity;

@Repository
public class CarLoanDaoImpl extends AbstractDao<CarLoanEntity, Long> implements CarLoanDao {

	
	
	@Override
	public void addNewCarLoanToDatabase(CarLoanEntity carLoanEntity, ClientEntity clientEntity, CarEntity carEntity){
		
		CarLoanEntity carLoanEntity2 = save(carLoanEntity);
		carLoanEntity2.setCar(carEntity);
		carLoanEntity2.setClient(clientEntity);
	
		
		
	}
}
