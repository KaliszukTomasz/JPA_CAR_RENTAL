package com.capgemini.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.CarLoanDao;
import com.capgemini.dao.ClientDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CarLoanEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class OfficeServiceTest extends AbstractTest {

	@Autowired
	OfficeService officeService;
	@Autowired
	OfficeDao officeDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	CarService carService;
	@Autowired
	CarLoanDao carLoanDao;
	@Autowired
	CarDao carDao;
	@Autowired
	ClientDao clientDao;
	@Autowired
	EmployeeService employeeService;

	@Test
	public void shouldAddOfficeToDatabaseTest() {
		// given
		int startSizeOfOfficeDao = officeDao.findAll().size();
		assertThat(officeDao.findAll().size() - startSizeOfOfficeDao, is(0));
		// when
		officeService.addOfficeToDatabase(buildOfficeTO());
		// then
		assertThat(officeDao.findAll().size() - startSizeOfOfficeDao, is(1));

	}

	@Test
	public void shouldRemoveOfficeFromDatabaseTest() {
		// given
		int size = officeDao.findAll().size();
		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());
		assertThat(officeDao.findAll().size() - size, is(1));

		// when
		officeService.removeOfficeFromDatabase(officeTO);

		// then
		assertThat(officeDao.findAll().size() - size, is(0));

	}

	@Test
	public void shouldChangeOfficeDetailsTest() {
		// given

		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());
		officeTO.setEmail("newEmail");

		// when
		officeService.changeOfficeDetails(officeTO);

		// then
		assertThat(officeDao.findOne(officeTO.getId()).getEmail(), is("newEmail"));
	}

	@Test
	public void shouldAddEmployeeToOfficeTest() {
		// given
		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());
		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(0));

		// when
		officeService.addEmployeeToOffice(officeTO, employeeTO);

		// then
		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(1));

	}

	@Test
	public void shouldRemoveEmployeeFromOfficeTest() {
		// given
		int numberOfEmployeesInDatabase = employeeService.findNumberOfEmployeesInDatabase();
		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());
		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		assertThat(employeeService.findNumberOfEmployeesInDatabase() - numberOfEmployeesInDatabase, is(1));

		officeService.addEmployeeToOffice(officeTO, employeeTO);

		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(1));
		assertThat(employeeDao.findOne(employeeTO.getId()).getOffice().getId(), is(officeTO.getId()));
		assertThat(employeeService.findNumberOfEmployeesInDatabase() - numberOfEmployeesInDatabase, is(1));
		// when
		officeService.removeEmployeeFromOffice(officeTO, employeeTO);

		// then
		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(0));
		assertThat(employeeService.findNumberOfEmployeesInDatabase() - numberOfEmployeesInDatabase, is(1));
	}

	@Test
	public void shouldFindAllEmployeesFromOfficeTest() {
		// given
		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());
		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		officeService.addEmployeeToOffice(officeTO, employeeTO);
		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(1));

		// when
		Set<EmployeeTO> employeeTOSet = officeService.findAllEmployeesFromOffice(officeTO);

		// then
		assertThat(employeeTOSet.size(), is(1));
	}

	@Test
	public void shouldFindAllEmployeesFromOfficeAssigendToCarTest() {
		// given
		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		CarTO carTO = carService.addCarToDatabase(buildCarTO());
		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());

		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(0));
		officeService.addEmployeeToOffice(officeTO, employeeTO);
		carService.addEmployeeToCar(carTO, employeeTO);

		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(1));

		// when
		List<EmployeeTO> employeeTOList = officeService.findAllEmployeesFromOfficeAssignedToCar(officeTO, carTO);

		// then
		assertThat(employeeTOList.size(), is(1));
	}

	@Test
	public void shouldDeleteAllInformationAboutCarTest() {
		// given
		int clientDaoStartSize = clientDao.findAll().size();
		int officeDaoStartSize = officeDao.findAll().size();
		int carLoanDaoStartSize = carLoanDao.findAll().size();
		int employeeDaoStartSize = employeeDao.findAll().size();
		int carDaoStartSize = carDao.findAll().size();

		EmployeeTO employeeTO = buildEmployeeTO();
		CarLoanEntity carLoanEntity = buildCarLoanEntity();
		CarEntity carEntity = buildCarEntity();
		carEntity.addCarLoan(carLoanEntity);
		carDao.save(carEntity);
		clientDao.save(buildClientEntity());
		employeeService.addEmployeeTODatabase(employeeTO);
		assertThat(officeDao.findAll().size() - officeDaoStartSize, is(1)); // 1
																			// office
		assertThat(clientDao.findAll().size() - clientDaoStartSize, is(1)); // 1
																			// client
		assertThat(carLoanDao.findAll().size() - carLoanDaoStartSize, is(1)); // 1
																				// carLoan
		assertThat(employeeDao.findAll().size() - employeeDaoStartSize, is(1)); // 1
																				// employee

		// when
		carEntity.removeCarLoan(carLoanEntity);
		carDao.delete(carEntity);

		// then
		assertThat(carDao.findAll().size() - carDaoStartSize, is(0)); // car
																		// removed
		assertThat(carLoanDao.findAll().size() - carLoanDaoStartSize, is(0)); // carLoan
																				// removed
		assertThat(employeeDao.findAll().size() - employeeDaoStartSize, is(1)); // employee
																				// not
																				// removed
		assertThat(officeDao.findAll().size() - officeDaoStartSize, is(1)); // office
																			// not
																			// removed
		assertThat(clientDao.findAll().size() - clientDaoStartSize, is(1)); // client
																			// not
																			// removed
	}

	private CarLoanEntity buildCarLoanEntity() {
		return new CarLoanEntity(null, clientDao.findOne(1L), new Date(2018, 12, 05, 18, 16),
				new Date(2018, 12, 05, 23, 05), officeDao.findOne(1L), officeDao.findOne(1L), 200);
	}
}
