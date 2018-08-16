package com.capgemini.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

public class EmployeeMapper {

	public static EmployeeEntity map2EmployeeEntity(EmployeeTO eTO) {

		EmployeeEntity eE = new EmployeeEntity();
		eE.setId(eTO.getId());
		eE.setFirstName(eTO.getFirstName());
		eE.setLastName(eTO.getLastName());
		eE.setDateOfBirth(eTO.getDateOfBirth());

		return eE;

	}

	public static EmployeeTO map2EmployeeTO(EmployeeEntity employeeEntity) {

		EmployeeTO eTO = new EmployeeTO();
		eTO.setId(employeeEntity.getId());
		eTO.setFirstName(employeeEntity.getFirstName());
		eTO.setLastName(employeeEntity.getLastName());
		eTO.setDateOfBirth(employeeEntity.getDateOfBirth());

		return eTO;

	}

	public static Set<EmployeeTO> mapEmployeeEntitySet2EmployeeTOSet(Set<EmployeeEntity> emplEntitySet) {

		Set<EmployeeTO> employeeTOSet = emplEntitySet.stream().map(temp -> {
			EmployeeTO empTO = new EmployeeTO();
			empTO.setId(temp.getId());
			empTO.setFirstName(temp.getFirstName());
			empTO.setLastName(temp.getLastName());
			return empTO;

		}).collect(Collectors.toSet());

		return employeeTOSet;
	}
	
	public static List<EmployeeTO> mapEmployeeEntityList2EmployeeTOList(List<EmployeeEntity> emplEntityList) {

		List<EmployeeTO> employeeTOList = emplEntityList.stream().map(temp -> {
			EmployeeTO empTO = new EmployeeTO();
			empTO.setId(temp.getId());
			empTO.setFirstName(temp.getFirstName());
			empTO.setLastName(temp.getLastName());
			return empTO;

		}).collect(Collectors.toList());

		return employeeTOList;
	}
}
