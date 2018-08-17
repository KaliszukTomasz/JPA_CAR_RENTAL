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
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
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
		CarTO carTO = buildCarTO();
		assertEquals(0, carService.findAllCarsInDatabase().size());
		// when
		carService.addCarToDatabase(carTO);
		carService.addCarToDatabase(carTO);
		// then
		assertEquals(2, carService.findAllCarsInDatabase().size());
		carTO.setId(1L);
		carService.removeCarFromDatabase(carTO);
		carTO.setId(2L);
		carService.removeCarFromDatabase(carTO);
	}

	@Test
	public void shouldChangeCarDetailsTest() {
		// given
		CarTO carTO = buildCarTO();
		carService.addCarToDatabase(carTO);
		assertEquals("BMW", carDao.findOne(1L).getBrand());

		// when
		CarTO newCarTO = buildSecoundCarTO();
		newCarTO.setId(1L);
		carService.changeCarDetails(newCarTO);

		// then
		assertEquals("Mercedes", carDao.findOne(1L).getBrand());
		assertEquals("kombi", carDao.findOne(1L).getCarType());
		assertEquals("blue", carDao.findOne(1L).getColor());
	}

	@Test
	@Transactional
	public void addEmployeeToCar() {
		// given
		CarTO carTO = buildCarTO();
		carService.addCarToDatabase(carTO);
		assertThat(carDao.findOne(1L).getEmployeesSet(), is(empty()));

		// when
		EmployeeTO employeeTO = buildEmployeeTO();
		employeeDao.save(buildEmployeeEntity());

		carTO.setId(1L);
		employeeTO.setId(1L);
		carService.addEmployeeToCar(carTO, employeeTO);
		// then
		assertThat(carDao.findOne(1L).getEmployeesSet(), is(not(empty())));
	}

	@Test
	public void shouldFindCarByBrandAndTypeTest() {
		// given
		CarTO carTO = new CarTO();
		carTO.setBrand("BMW");
		carTO.setCarType("sedan");
		carService.addCarToDatabase(buildCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());

		// when
		List<CarTO> carTOList = carService.findCarByBrandAndType(carTO);

		// then
		assertThat(carTOList.size(), is(1));

	}

	@Test
	public void shouldFindAllCarsInDatabaseTest() {
		// given
		carService.addCarToDatabase(buildCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());

		// when
		List<CarTO> carTOList = carService.findAllCarsInDatabase();

		// then
		assertThat(carTOList.size(), is(2));

	}

	@Test
	@Transactional
	public void shouldFindCarByAttachedEmployeeTest() {
		// given
		carService.addCarToDatabase(buildCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());

		employeeDao.save(buildEmployeeEntity());
		carDao.addAttachedEmployee(1L, employeeDao.findOne(1L));
		// carDao.addAttachedEmployee(2L, employeeDao.findOne(1L));
		EmployeeTO employeeTO = new EmployeeTO();
		employeeTO.setId(1L);

		// when
		Set<CarTO> carTOSet = carService.findCarByAttachedEmployee(employeeTO);

		// then
		assertThat(carTOSet.size(), is(1));

	}

	@Test
	public void shouldFindEmployeesByOfficeAndCarTest() {
		// given
		OfficeTO officeTO = buildOfficeTO();
		CarTO carTO = buildCarTO();
		carService.addCarToDatabase(carTO);
		carService.addCarToDatabase(buildSecoundCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());

		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		officeService.addOfficeToDatabase(officeTO);

		// employeeDao.save(buildEmployeeEntity());
		carDao.addAttachedEmployee(1L, employeeDao.findOne(1L));
		employeeDao.addEmployeeToOffice(1L, 1L);
		// carDao.addAttachedEmployee(2L, employeeDao.findOne(1L));

		EmployeeTO employeeTO = new EmployeeTO();
		employeeTO.setId(1L);
		officeTO.setId(1L);
		carTO.setId(1L);
		// when
		List<EmployeeTO> carTOList = carService.findEmployeeByOfficeAndCar(officeTO, carTO);

		// then
		assertThat(carTOList.size(), is(1));

	}

	private EmployeeEntity buildEmployeeEntity() {
		return new EmployeeEntity("Arek", "Konieczny", Date.from(Instant.now()), null, new HashSet<>());
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

		EmployeeTO employeeTO = new EmployeeTOBuilder().setDateOfBirth(new Date("10/11/1959")).setFirstName("Adam")
				.setLastName("Kowalski").setOffice(buildOfficeTO()).buildEmployeeTO();
		return employeeTO;
	}

	private OfficeTO buildOfficeTO() {
		OfficeTO officeTO = new OfficeTOBuilder().setAddress(new AddressTO("Poznan", "61-251", "Polna"))
				.setEmail("biuro@gmail.com").setPhoneNumber(745215321).buildOfficeTO();
		return officeTO;
	}

	private OfficeEntity buildOfficeEntity() {
		OfficeEntity officeEntity = new OfficeEntity(65656565, "qwe", new AddressEntity(), new HashSet<>(),
				new HashSet<>(), new HashSet<>(), new HashSet<>());
		return officeEntity;
	}
}
