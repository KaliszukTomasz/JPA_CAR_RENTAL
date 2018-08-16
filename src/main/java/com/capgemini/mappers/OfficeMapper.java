package com.capgemini.mappers;

import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.OfficeTO;

public class OfficeMapper {

	public static OfficeEntity map2OfficeEntity(OfficeTO oTO) {
		if (oTO == null) {
			return null;
		}
		OfficeEntity oE = new OfficeEntity();
		oE.setPhoneNumber(oTO.getPhoneNumber());
		oE.setEmail(oTO.getEmail());
		oE.setAddress(AddressMapper.map2AdressEntity(oTO.getAddress()));
		return oE;
	}

	public static OfficeTO map2OfficeTO(OfficeEntity oE) {

		if (oE == null) {
			return null;
		}
		OfficeTO officeTO = new OfficeTO();
		officeTO.setId(oE.getId());
		officeTO.setPhoneNumber(oE.getPhoneNumber());
		officeTO.setEmail(oE.getEmail());
		officeTO.setAddress(AddressMapper.map2AddressTO(oE.getAddress()));
		return officeTO;
	}
}
