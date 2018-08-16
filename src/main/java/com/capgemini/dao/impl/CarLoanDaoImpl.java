package com.capgemini.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarLoanDao;
import com.capgemini.domain.CarLoanEntity;

@Repository
public class CarLoanDaoImpl extends AbstractDao<CarLoanEntity, Long> implements CarLoanDao {

	//TODO
}
