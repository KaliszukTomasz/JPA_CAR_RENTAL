package com.capgemini.service;

import java.util.List;
import java.util.Set;

import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

public interface OfficeService {

	OfficeTO addOfficeToDatabase(OfficeTO officeTO);
	OfficeTO removeOfficeFromDatabase(OfficeTO officeTO);
	OfficeTO changeOfficeDetails(OfficeTO officeTO);
	EmployeeTO addEmployeeToOffice(OfficeTO officeTO, EmployeeTO employeeTO);
	EmployeeTO removeEmployeeFromOffice(OfficeTO officeTO, EmployeeTO employeeTO);
	Set<EmployeeTO> findAllEmployeesFromOffice(OfficeTO officeTO);
	List<EmployeeTO> findAllEmployeesFromOfficeAssignedToCar(OfficeTO officeTO, CarTO carTO);
	
}

