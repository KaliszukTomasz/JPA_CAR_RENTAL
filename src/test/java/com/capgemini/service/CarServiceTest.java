package com.capgemini.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.CarLoanDao;
import com.capgemini.dao.ClientDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CarLoanEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class CarServiceTest extends AbstractTest {

	@Autowired
	private CarService carService;
	@Autowired
	private CarDao carDao;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private CarLoanDao carLoanDao;

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
		CarTO carTO = carService.addCarToDatabase(buildCarTO());
		assertThat(carService.findNumberOfEmployeesAssignedToThisCar(carTO), is(0));

		// when
		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		carService.addEmployeeToCar(carTO, employeeTO);

		// then
		assertThat(carService.findNumberOfEmployeesAssignedToThisCar(carTO), is(1));
	}

	@Test
	public void shouldFindCarByBrandAndTypeTest() {
		// given
		CarTO carTO = buildCarTO();
		int carTOListStartSize = carService.findCarByBrandAndType(carTO).size();
		carService.addCarToDatabase(buildCarTO());
		carService.addCarToDatabase(buildSecoundCarTO());

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

	@Test
	@Transactional
	public void shouldFindCarsWitchThreeOrMoreCarLoans() {
		// given

		ClientEntity clientEntity1 = clientDao.save(buildClientEntity());
		ClientEntity clientEntity2 = clientDao.save(buildClientEntity());
		ClientEntity clientEntity3 = clientDao.save(buildClientEntity());

		CarLoanEntity carLoanEntityTemp1 = buildCarLoanEntity();
		carLoanEntityTemp1.setClient(clientDao.findOne(clientEntity1.getId()));
		CarLoanEntity carLoanEntityTemp2 = buildCarLoanEntity();
		carLoanEntityTemp2.setClient(clientDao.findOne(clientEntity2.getId()));
		CarLoanEntity carLoanEntityTemp3 = buildCarLoanEntity();
		carLoanEntityTemp3.setClient(clientDao.findOne(clientEntity3.getId()));

		CarLoanEntity carLoanEntity1 = carLoanDao.save(carLoanEntityTemp1);
		CarLoanEntity carLoanEntity2 = carLoanDao.save(carLoanEntityTemp2);
		CarLoanEntity carLoanEntity3 = carLoanDao.save(carLoanEntityTemp3);

		CarEntity carEntity = buildCarEntity();
		carEntity.addCarLoan(carLoanEntity1);
		carEntity.addCarLoan(carLoanEntity2);
		carEntity.addCarLoan(carLoanEntity3);

		// when
		CarEntity carEntity2 = carDao.save(carEntity);

		// then
		assertThat(carDao.findOne(carEntity2.getId()).getCarLoans().size(), is(3));
		assertThat(carService.findListOfCarsLoanedXTimesByDistinctClients(3L).size(), is(1));

	}

	@Test
	public void shouldFindNumberOfCarsLoanedInTimeStartDateEndDate() {

		CarEntity carEntity = carDao.save(buildCarEntity());
		CarLoanEntity carLoanEntity = carLoanDao.save(buildCarLoanEntityWithDays());
		carEntity.addCarLoan(carLoanEntity);

		// when
		carEntity = carDao.update(carEntity);
		carLoanDao.update(carLoanEntity);

		// then

		assertThat(carService.findCarsLoanedInTimeStartDateEndDate(java.sql.Date.valueOf("2018-01-01"),
				java.sql.Date.valueOf("2018-01-02")).size(), is(1));

		assertThat(carService.findNumberOfCarsLoanedInTimeStartDateEndDate(java.sql.Date.valueOf("2018-01-01"),
				java.sql.Date.valueOf("2018-01-02")), is(1L));
	}

	@Test
	@Transactional
	public void optimisticLokingTest() {
		// given
		ClientEntity clientEntity = buildClientEntity();
		clientEntity = clientDao.save(clientEntity);
		clientDao.flush();
		clientDao.detach(clientEntity);
		clientEntity.setFirstName("Tomek");

		ClientEntity newClientEntity = clientDao.findOne(clientEntity.getId());

		newClientEntity.setFirstName("first name");
		clientDao.save(newClientEntity);
		clientDao.flush();

		// when
		try {
			ClientEntity clientEntity2 = clientDao.update(clientEntity);
			clientDao.save(clientEntity2);

			// then
			Assert.fail("Expected that optimistoc loking not working");

		} catch (ObjectOptimisticLockingFailureException e) {
			// Test pass. We expected optimistoc loking exception.
		}

	}

	@Test
	public void builderTest() {
		buildCarLoanTO();
		buildCarTO();
		buildClientTO();
		buildEmployeeTO();
		buildOfficeTO();
		buildSecoundCarTO();
		buildAddressTO();
	}

	private CarLoanEntity buildCarLoanEntityWithDays() {
		CarLoanEntity carLoanEntityTemp = buildCarLoanEntity();
		carLoanEntityTemp.setLoanDate(java.sql.Date.valueOf("2018-01-01"));
		carLoanEntityTemp.setReturnDate(java.sql.Date.valueOf("2018-01-03"));
		return carLoanEntityTemp;
	}

	private CarLoanEntity buildCarLoanEntity() {
		return new CarLoanEntity(null, clientDao.findOne(1L), java.sql.Date.valueOf("2018-05-05"),
				java.sql.Date.valueOf("2018-05-05"), officeDao.findOne(1L), officeDao.findOne(1L), 200);
	}

}
