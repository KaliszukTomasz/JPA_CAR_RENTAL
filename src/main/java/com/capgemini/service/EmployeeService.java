package com.capgemini.service;

import java.util.List;

import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

public interface EmployeeService {

	EmployeeTO addEmployeeTODatabase(EmployeeTO employeeTO);

	Integer findNumberOfEmployeesInDatabase();
	
	List<EmployeeTO> findListOfEmployeesWorkingInOfficeQuery(OfficeTO officeTO);

	List<EmployeeTO> findListOfEmployeesTakeCareOnCarQuery(CarTO carTO);

}
