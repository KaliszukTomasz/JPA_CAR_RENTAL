package com.capgemini.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Year;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.enums.EmployeePosition;
import com.capgemini.types.AddressTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.builders.CarTOBuilder;
import com.capgemini.types.builders.EmployeeTOBuilder;
import com.capgemini.types.builders.OfficeTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private CarService carService;
	@Autowired
	private CarDao carDao;
	@Autowired
	private EmployeeDao employeeDao;

	@Test
	public void shouldFindListOfEmployeesFromOfficeTest() {
		// given
		EmployeeTO employeeTO1 = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		EmployeeTO employeeTO2 = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		EmployeeTO employeeTO4 = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		OfficeTO officeTO = officeService.addOfficeToDatabase(buildOfficeTO());

		assertThat(officeService.findSizeOfCollectionOfEmployeesInOffice(officeTO), is(0));
		officeService.addEmployeeToOffice(officeTO, employeeTO1);
		officeService.addEmployeeToOffice(officeTO, employeeTO2);
		officeService.addEmployeeToOffice(officeTO, employeeTO4);

		// when
		List<EmployeeTO> employeeTOList = employeeService.findListOfEmployeesWorkingInOfficeQuery(officeTO);
		// then
		assertThat(employeeTOList.size(), is(3));

	}

	@Test

	public void shouldFindListOfEmployeesTakeCareOnCarQueryTest() {
		// given
		EmployeeTO employeeTO1 = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		EmployeeTO employeeTO4 = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		CarTO carTO1 = carService.addCarToDatabase(buildCarTO());

		carService.addEmployeeToCar(carTO1, employeeTO1);
		carService.addEmployeeToCar(carTO1, employeeTO4);

		assertThat(carService.findAllCarsInDatabase().size(), is(1));

		// when
		int numberOfEmployees = employeeService.findListOfEmployeesTakeCareOnCarQuery(carTO1).size();

		// then
		assertThat(numberOfEmployees, is(2));
	}

	@Test
	public void shouldFindListOfEmployeesOnEmployeePositionQueryTest() {
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		EmployeeTO employeeTO2 = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		assertThat(employeeTO2.getEmployeePosition(), is(EmployeePosition.DEALER));
		assertThat(employeeDao.findListOfEmployeesWorkingOnPositionQuery(EmployeePosition.DEALER), is(6));

	}

	private EmployeeTO buildEmployeeTO() {

		return new EmployeeTOBuilder().setDateOfBirth(new Date("10/11/1959")).setFirstName("Adam")
				.setEmployeePosition(EmployeePosition.DEALER).setLastName("Kowalski").setOffice(buildOfficeTO())
				.buildEmployeeTO();
	}

	private OfficeTO buildOfficeTO() {
		return new OfficeTOBuilder().setAddress(new AddressTO("Berlin", "21353", "Lisia")).setCarSet(null)
				.setEmail("email").setPhoneNumber(1356235).setEmployees(null).buildOfficeTO();

	}

	private CarTO buildCarTO() {
		CarTO carTO = new CarTOBuilder().setCarType("sedan").setCurrentLocation(buildOfficeTO()).setBrand("BMW")
				.setYearOfProduction(Year.of(1995)).setColor("red").setEngineCapacity(1600).setEnginePower(100)
				.setMileage(100000).buildCarTO();
		return carTO;

	}
}
