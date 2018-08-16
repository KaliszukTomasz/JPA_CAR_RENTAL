package com.capgemini.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;

public class CarMapper {

	public static CarEntity map2CarEntity(CarTO carTO) {
		CarEntity carE = new CarEntity();
		carE.setCarType(carTO.getCarType());
		carE.setBrand(carTO.getBrand());
		carE.setYearOfProduction(carTO.getYearOfProduction());
		carE.setColor(carTO.getColor());
		carE.setEngineCapacity(carTO.getEngineCapacity());
		carE.setEnginePower(carTO.getEnginePower());
		carE.setMileage(carTO.getMileage());
		carE.setCurrentLocation(OfficeMapper.map2OfficeEntity(carTO.getCurrentLocation()));
		//carE.setEmployeesSet(carTO.getEmployeesSet());

		return carE;
	}

	public static List<CarTO> mapListCarEntities2CarTOs(List<CarEntity> list) {

		List<CarTO> listCarTO = list.stream().map(temp -> {
			CarTO carTO = new CarTO();
			carTO.setId(temp.getId());
			carTO.setColor(temp.getColor());
			carTO.setYearOfProduction(temp.getYearOfProduction());
			carTO.setEnginePower(temp.getEnginePower());
			carTO.setEngineCapacity(temp.getEngineCapacity());
			carTO.setMileage(temp.getMileage());
			return carTO;
		}).collect(Collectors.toList());

		return listCarTO;
	}
	
	public static Set<CarTO> mapSetCarEntities2CarTOs(Set<CarEntity> carSet) {

		Set<CarTO> carTOSet = carSet.stream().map(temp -> {
			CarTO carTO = new CarTO();
			carTO.setId(temp.getId());
			carTO.setColor(temp.getColor());
			carTO.setYearOfProduction(temp.getYearOfProduction());
			carTO.setEnginePower(temp.getEnginePower());
			carTO.setEngineCapacity(temp.getEngineCapacity());
			carTO.setMileage(temp.getMileage());
			return carTO;
		}).collect(Collectors.toSet());

		return carTOSet;
	}

}
