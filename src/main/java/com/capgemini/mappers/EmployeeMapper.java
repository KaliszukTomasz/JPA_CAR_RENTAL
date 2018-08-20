package com.capgemini.mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

/**
 * @author TKALISZU
 *
 *         Description: EmployeeMapper has 4 methods. Can map EmployeeTO to
 *         EmployeeEntity, EmployeeEntity to EmployeeTO and Set/List of
 *         EmployeeEntities to Set/List EmployeeTO.
 */
public class EmployeeMapper {

	private EmployeeMapper() {
	}

	public static EmployeeEntity map2EmployeeEntity(EmployeeTO eTO) {

		EmployeeEntity eE = new EmployeeEntity();
		eE.setId(eTO.getId());
		eE.setFirstName(eTO.getFirstName());
		eE.setLastName(eTO.getLastName());
		eE.setDateOfBirth(eTO.getDateOfBirth());
		eE.setEmployeePosition(eTO.getEmployeePosition());

		return eE;

	}

	public static EmployeeTO map2EmployeeTO(EmployeeEntity employeeEntity) {

		EmployeeTO eTO = new EmployeeTO();
		eTO.setId(employeeEntity.getId());
		eTO.setFirstName(employeeEntity.getFirstName());
		eTO.setLastName(employeeEntity.getLastName());
		eTO.setDateOfBirth(employeeEntity.getDateOfBirth());
		eTO.setEmployeePosition(employeeEntity.getEmployeePosition());

		return eTO;

	}

	public static Set<EmployeeTO> mapEmployeeEntitySet2EmployeeTOSet(Set<EmployeeEntity> emplEntitySet) {

		return emplEntitySet.stream().map(temp -> {
			EmployeeTO empTO = new EmployeeTO();
			empTO.setId(temp.getId());
			empTO.setFirstName(temp.getFirstName());
			empTO.setLastName(temp.getLastName());
			empTO.setEmployeePosition(temp.getEmployeePosition());
			return empTO;

		}).collect(Collectors.toSet());

	}

	public static List<EmployeeTO> mapEmployeeEntityList2EmployeeTOList(List<EmployeeEntity> emplEntityList) {

		return emplEntityList.stream().map(temp -> {
			EmployeeTO empTO = new EmployeeTO();
			empTO.setId(temp.getId());
			empTO.setFirstName(temp.getFirstName());
			empTO.setLastName(temp.getLastName());
			empTO.setEmployeePosition(temp.getEmployeePosition());
			return empTO;

		}).collect(Collectors.toList());

	}
}
