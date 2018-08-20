package com.capgemini.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.enums.EmployeePosition;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class EmployeeServiceTest extends AbstractTest {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private CarService carService;
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
		// given
		int startSize = employeeService.findListOfEmployeesWorkingOnPositionQuery(EmployeePosition.DEALER).size();
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		EmployeeTO employeeTO2 = employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildEmployeeTO());
		employeeService.addEmployeeTODatabase(buildManagerEmployeeTO());

		// when
		// then
		assertThat(employeeTO2.getEmployeePosition(), is(EmployeePosition.DEALER));
		assertThat(
				employeeService.findListOfEmployeesWorkingOnPositionQuery(EmployeePosition.DEALER).size() - startSize,
				is(6));

	}

}
