package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao{

	@Override
	public CarEntity changeDetailsOfCarEntity(Long cadId, CarEntity newDetailsCarEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarEntity> findCarByBrand(String brand) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car where upper(car.brand) like concat(upper(:brand), '%'", CarEntity.class);
		query.setParameter("brand", brand);
		return query.getResultList();
	}
	
	@Override
	public List<CarEntity> findCarByType(String type) {
		TypedQuery<CarEntity> query = entityManager.createQuery(
				"select car from CarEntity car where upper(car.type) like concat(upper(:type), '%'", CarEntity.class);
		query.setParameter("type", type);
		return query.getResultList();
	}

	@Override
	public List<CarEntity> findCarByOffice(Long officeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
