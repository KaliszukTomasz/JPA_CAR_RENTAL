package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CarLoanEntity;
import com.capgemini.domain.ClientEntity;

public interface CarLoanDao extends Dao<CarLoanEntity, Long> {

	void addNewCarLoanToDatabase(CarLoanEntity carLoanEntity, ClientEntity clientEntity, CarEntity carEntity);

}
