package com.capgemini.service;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;
import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.CarLoanDao;
import com.capgemini.dao.ClientDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CarLoanEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.enums.EmployeePosition;
import com.capgemini.types.AddressTO;
import com.capgemini.types.CarLoanTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.builders.CarLoanTOBuilder;
import com.capgemini.types.builders.CarTOBuilder;
import com.capgemini.types.builders.ClientTOBuilder;
import com.capgemini.types.builders.EmployeeTOBuilder;
import com.capgemini.types.builders.OfficeTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class OfficeServiceTest {

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
		assertThat(officeDao.findAll(), is(empty()));
		// when
		officeService.addOfficeToDatabase(buildOfficeTO());
		// then
		assertThat(officeDao.findAll().size(), is(1));

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
		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());
		EmployeeTO employeeTO = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		officeService.addEmployeeToOffice(officeTO, employeeTO);
		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(1));

		// when
		officeService.removeEmployeeFromOffice(officeTO, employeeTO);

		// then
		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(0));

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

	// @Test
	// public void shouldDeleteAllInformationAboutCarTest() {
	// // given
	// OfficeTO officeTO = buildOfficeTO();
	// EmployeeTO employeeTO = buildEmployeeTO();
	// CarTO carTO = buildCarTO();
	// carService.addCarToDatabase(carTO);
	// // CarLoanTO carLoanTO = buildCarLoanTO();
	//
	// assertThat(clientDao.findAll().size(), is(0));
	// clientDao.save(buildClientEntity());
	// assertThat(clientDao.findAll().size(), is(1));
	// // officeService.addOfficeToDatabase(buildOfficeTO());
	// // employeeService.addEmployeeTODatabase(employeeTO);
	// // employeeDao.save(buildEmployeeEntity());
	// assertThat(officeDao.findAll().size(), is(1));
	// CarLoanEntity carLoanEntity = carLoanDao.save(buildCarLoanEntity());
	//
	// assertThat(carLoanEntity.getId(), is(1L));
	// assertThat(officeDao.findAll().size(), is(1));
	//
	// assertThat(clientDao.findAll().size(), is(1));
	// assertThat(carLoanDao.findAll().size(), is(1));
	// // assertThat(carDao.findOne(1L).getCarLoans().size(), is(1));
	// assertThat(clientDao.findOne(1L).getCarLoansSet().size(), is(1));
	// //
	//
	// // officeTO.setId(1L);
	// // employeeTO.setId(1L);
	// // carTO.setId(1L);
	// // carTO.setEmployeesIdSet(null);
	// // carTO.setCurrentLocation(null);
	// CarEntity newCarEntity = carDao.findOne(1L);
	//
	// // newCarEntity.removeCarLoanEntity(carLoanDao.findOne(1L));
	//
	// // assertThat(officeDao.findOne(1L).getEmployees().size(), is(1));
	// // assertThat(carDao.findOne(1L).getCarLoans().size(), is(1));
	//
	// // when
	// carTO.setId(1L);
	// carService.removeCarFromDatabase(carTO);
	//
	// // then
	//
	// assertThat(employeeDao.findAll().size(), is(1));
	//
	// }

	@Test
	public void shouldNewTESTDeleteAllInformationAboutCarTest() {

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
		assertThat(clientDao.findAll().size() - clientDaoStartSize, is(0));
		clientDao.save(buildClientEntity());
		employeeService.addEmployeeTODatabase(employeeTO);
		assertThat(officeDao.findAll().size() - officeDaoStartSize, is(1));
		assertThat(clientDao.findAll().size() - clientDaoStartSize, is(1));
		assertThat(carLoanDao.findAll().size() - carLoanDaoStartSize, is(1));
		assertThat(employeeDao.findAll().size() - employeeDaoStartSize, is(1));

		// when
		carEntity.removeCarLoan(carLoanEntity);
		carDao.delete(carEntity);

		// then
		assertThat(carDao.findAll().size() - carDaoStartSize, is(0));
		assertThat(carLoanDao.findAll().size() - carLoanDaoStartSize, is(0));
		assertThat(employeeDao.findAll().size() - employeeDaoStartSize, is(1));
		assertThat(officeDao.findAll().size() - officeDaoStartSize, is(1));
		assertThat(clientDao.findAll().size() - clientDaoStartSize, is(1));
	}

	private CarLoanEntity buildCarLoanEntity() {
		return new CarLoanEntity(null, clientDao.findOne(1L), new Date(2018, 12, 05, 18, 16),
				new Date(2018, 12, 05, 23, 05), officeDao.findOne(1L), officeDao.findOne(1L), 200);
	}

	private CarEntity buildCarEntity() {
		CarEntity carEntity = new CarEntity();
		carEntity.setCarType("sedan");
		carEntity.setCurrentLocation(buildOfficeEntity());
		carEntity.setBrand("BMW");
		carEntity.setYearOfProduction(Year.of(1995));
		carEntity.setColor("red");
		carEntity.setEngineCapacity(1600);
		carEntity.setEnginePower(100);
		carEntity.setMileage(100000);
		return carEntity;
	}
	// @Transactional
	// private CarLoanEntity buildCarLoanEntity() {
	// CarTO carTO = buildCarTO();
	// carTO.setId(1L);
	// ClientTO clientTO = buildClientTO();
	// clientTO.setId(1L);
	// OfficeTO officeTO = buildOfficeTO();
	// officeTO.setId(1L);
	//
	// CarEntity carEntity = new CarEntity("sedan", "CS", Year.of(2000),
	// "black", 123, 123, 123, null, null, null);
	// ClientEntity clientEntity = new ClientEntity("jan", "kowal",
	// buildAddressEntity(), new Date(1992, 12, 12),
	// 9521566, "newEmail", 56123156598L, null);
	//
	// OfficeEntity officeEntity = buildOfficeEntity();
	// CarEntity carEnt = carDao.findOne(1L);
	//
	// return new CarLoanEntity(carDao.findOne(1L), clientDao.findOne(1L), new
	// Date(2018, 12, 05, 18, 16),
	// new Date(2018, 12, 05, 23, 05), officeDao.findOne(1L),
	// officeDao.findOne(1L), 200);
	//
	// }

	private CarLoanTO buildCarLoanTO() {
		CarTO carTO = buildCarTO();
		carTO.setId(1L);
		ClientTO clientTO = buildClientTO();
		clientTO.setId(1L);
		OfficeTO officeTO = buildOfficeTO();
		officeTO.setId(1L);
		return new CarLoanTOBuilder().setAmountOfLoan(200).setCar(carTO).setClient(clientTO)
				.setLoanDate(new Date(2018, 12, 05, 18, 16)).setReturnDate(new Date(2018, 12, 05, 23, 05))
				.setLoanOffice(officeTO).setReturnOffice(officeTO).buildCarLoanTO();

	}

	private OfficeTO buildOfficeTO() {

		return new OfficeTOBuilder().setAddress(new AddressTO("Berlin", "21353", "Lisia")).setCarSet(null)
				.setEmail("email").setPhoneNumber(1356235).setEmployees(null).buildOfficeTO();

	}

	private EmployeeTO buildEmployeeTO() {

		EmployeeTO employeeTO = new EmployeeTOBuilder().setDateOfBirth(new Date("10/11/1959")).setFirstName("Adam")
				.setEmployeePosition(EmployeePosition.DEALER).setLastName("Kowalski").setOffice(buildOfficeTO())
				.buildEmployeeTO();
		return employeeTO;
	}

	// private OfficeTO buildOfficeTO() {
	// OfficeTO officeTO = new OfficeTOBuilder().setAddress(new
	// AddressTO("Poznan", "61-251", "Polna"))
	// .setEmail("biuro@gmail.com").setPhoneNumber(745215321).buildOfficeTO();
	// return officeTO;
	// }

	private EmployeeEntity buildEmployeeEntity() {
		return new EmployeeEntity("Arek", "Konieczny", Date.from(Instant.now()), null, null);
	}

	private OfficeEntity buildOfficeEntity() {
		OfficeEntity officeEntity = new OfficeEntity(65656565, "qwe", buildAddressEntity(), new HashSet<>(),
				new HashSet<>(), new HashSet<>(), new HashSet<>());
		return officeEntity;
	}

	private CarTO buildCarTO() {
		CarTO carTO = new CarTOBuilder().setCarType("sedan").setCurrentLocation(buildOfficeTO()).setBrand("BMW")
				.setYearOfProduction(Year.of(1995)).setColor("red").setEngineCapacity(1600).setEnginePower(100)
				.setMileage(100000).buildCarTO();
		return carTO;
	}

	private ClientTO buildClientTO() {
		ClientTO clientTO = new ClientTOBuilder().setAddress(new AddressTO("Warszawa", "41457", "Warszawska"))
				.setCreditCardNumber(987439871345L).setDateOfBirth(new Date("10/11/1959")).setEmail("newEmail@wp.pl")
				.setFirstName("Artur").setLastName("Lewandowski").setPhoneNumber(841532154).buildClientTO();
		return clientTO;
	}

	private ClientEntity buildClientEntity() {
		return new ClientEntity("Artur", "Kras", buildAddressEntity(), new Date("10/11/1959"), 56641235, "emiala@wp.pl",
				5615612123168L, new HashSet<>());
	}

	private AddressEntity buildAddressEntity() {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity("Miasto");
		addressEntity.setStreetAdress("ulica");
		addressEntity.setZipCode("zip");
		return addressEntity;
	}
}
