package com.capgemini.service;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.time.Instant;
import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.border.EmptyBorder;
import javax.transaction.Transactional;

import org.aspectj.weaver.tools.cache.CachedClassEntry;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.enums.EmployeePosition;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.types.AddressTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.builders.CarTOBuilder;
import com.capgemini.types.builders.EmployeeTOBuilder;
import com.capgemini.types.builders.OfficeTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class CarServiceTest {

	@Autowired
	private CarService carService;
	@Autowired
	private CarDao carDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private OfficeDao officeDao;

	@Test
	public void shouldAddAndRemoveCarToFromDatabaseTest() {
		// given
		int carDaoStartSize = carService.findAllCarsInDatabase().size();

		assertEquals(0, carService.findAllCarsInDatabase().size() - carDaoStartSize);
		// when
		CarTO carTO1 = carService.addCarToDatabase(buildCarTO());
		CarTO carTO2 = carService.addCarToDatabase(buildCarTO());
		// then
		assertEquals(2, carService.findAllCarsInDatabase().size() - carDaoStartSize);

		carService.removeCarFromDatabase(carTO1);
		carService.removeCarFromDatabase(carTO2);
		assertEquals(0, carService.findAllCarsInDatabase().size() - carDaoStartSize);
	}

	@Test
	public void shouldRemoveCarFromDatabaseTest() {

		// given
		int carDaoStartSize = carService.findAllCarsInDatabase().size();
		CarTO carTO1 = carService.addCarToDatabase(buildCarTO());
		CarTO carTO2 = carService.addCarToDatabase(buildCarTO());
		assertEquals(2, carService.findAllCarsInDatabase().size() - carDaoStartSize);

		// when
		carService.removeCarFromDatabase(carTO1);
		carService.removeCarFromDatabase(carTO2);

		// then
		assertEquals(0, carService.findAllCarsInDatabase().size() - carDaoStartSize);

	}

	@Test
	public void shouldChangeCarDetailsTest() {
		// given
		CarTO carTO = carService.addCarToDatabase(buildCarTO());
		assertEquals("BMW", carDao.findOne(carTO.getId()).getBrand());

		// when
		carTO.setBrand("Mercedes");
		carTO.setCarType("kombi");
		carTO.setColor("blue");
		carService.changeCarDetails(carTO);

		// then
		assertEquals("Mercedes", carDao.findOne(carTO.getId()).getBrand());
		assertEquals("kombi", carDao.findOne(carTO.getId()).getCarType());
		assertEquals("blue", carDao.findOne(carTO.getId()).getColor());
	}

	@Test
	public void addEmployeeToCar() {
		// given
		int carDaoStartSize = carDao.findAll().size();
		CarTO carTO = carService.addCarToDatabase(buildCarTO());
		assertThat(carService.findNumberOfEmployeesAssignedToThisCar(carTO) - carDaoStartSize, is(0));

		// when
		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		carService.addEmployeeToCar(carTO, employeeTO);

		// then
		assertThat(carService.findNumberOfEmployeesAssignedToThisCar(carTO) - carDaoStartSize, is(1));
	}

	@Test
	public void shouldFindCarByBrandAndTypeTest() {
		// given
		CarTO carTO = buildCarTO();
		int carTOListStartSize = carService.findCarByBrandAndType(carTO).size();
		CarTO carTO2 = carService.addCarToDatabase(buildCarTO());
		CarTO carTO3 = carService.addCarToDatabase(buildSecoundCarTO());

		// when
		List<CarTO> carTOList = carService.findCarByBrandAndType(carTO);

		// then
		assertThat(carTOList.size() - carTOListStartSize, is(1));

	}

	@Test
	public void shouldFindAllCarsInDatabaseTest() {
		// given
		int carDaoStartSize = carService.findAllCarsInDatabase().size();
		carService.addCarToDatabase(buildCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());

		// when
		List<CarTO> carTOList = carService.findAllCarsInDatabase();

		// then
		assertThat(carTOList.size() - carDaoStartSize, is(2));

	}

	@Test
	public void shouldFindCarByAttachedEmployeeTest() {
		// given
		CarTO carTO = carService.addCarToDatabase(buildCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());

		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		carService.addEmployeeToCar(carTO, employeeTO);

		// when
		Set<CarTO> carTOSet = carService.findCarByAttachedEmployee(employeeTO);

		// then
		assertThat(carTOSet.size(), is(1));

	}

	@Test
	public void shouldFindEmployeesByOfficeAndCarTest() {
		// given
		int carDaoStartSize = carService.findAllCarsInDatabase().size();

		CarTO carTO = carService.addCarToDatabase(buildCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());

		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());

		carService.addEmployeeToCar(carTO, employeeTO);
		officeService.addEmployeeToOffice(officeTO, employeeTO);

		// when
		List<EmployeeTO> carTOList = carService.findEmployeeByOfficeAndCar(officeTO, carTO);

		// then
		assertThat(carService.findAllCarsInDatabase().size() - carDaoStartSize, is(4));
		assertThat(carTOList.size(), is(1));

	}

	@Test
	public void shouldIncrementVersionOfCarObject() {

		// given
		CarTO carTO = carService.addCarToDatabase(buildCarTO());
		assertThat(carDao.findOne(carTO.getId()).getVersion(), is(0L));

		// when
		carTO.setBrand("Audi");
		carTO = carService.changeCarDetails(carTO);

		// then
		assertThat(carDao.findOne(carTO.getId()).getVersion(), is(1L));

	}

	private CarTO buildCarTO() {
		CarTO carTO = new CarTOBuilder().setCarType("sedan").setCurrentLocation(buildOfficeTO()).setBrand("BMW")
				.setYearOfProduction(Year.of(1995)).setColor("red").setEngineCapacity(1600).setEnginePower(100)
				.setMileage(100000).buildCarTO();
		return carTO;

	}

	private CarTO buildSecoundCarTO() {

		CarTO carTO = new CarTOBuilder().setCarType("kombi").setCurrentLocation(buildOfficeTO()).setBrand("Mercedes")
				.setYearOfProduction(Year.of(2000)).setColor("blue").setEngineCapacity(2000).setEnginePower(200)
				.setMileage(200000).buildCarTO();
		return carTO;

	}

	private EmployeeTO buildEmployeeTO() {

		return new EmployeeTOBuilder().setDateOfBirth(new Date("10/11/1959")).setFirstName("Adam").setEmployeePosition(EmployeePosition.DEALER)
				.setLastName("Kowalski").setOffice(buildOfficeTO()).buildEmployeeTO();
	}

	private OfficeTO buildOfficeTO() {
		OfficeTO officeTO = new OfficeTOBuilder().setAddress(new AddressTO("Poznan", "61-251", "Polna"))
				.setEmail("biuro@gmail.com").setPhoneNumber(745215321).buildOfficeTO();
		return officeTO;
	}

}
