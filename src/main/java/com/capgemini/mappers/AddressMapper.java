package com.capgemini.mappers;

import com.capgemini.domain.AddressEntity;
import com.capgemini.types.AddressTO;

public class AddressMapper {
	
	public static AddressEntity map2AdressEntity(AddressTO addressTO){
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity(addressTO.getCity());
		addressEntity.setStreetAdress(addressTO.getStreetAddress());
		addressEntity.setZipCode(addressTO.getZipCode());
		
		return addressEntity;
		
	}
	public static AddressTO map2AddressTO(AddressEntity addressEntity){
		AddressTO addressTO = new AddressTO();
		addressTO.setCity(addressEntity.getCity());
		addressTO.setStreetAddress(addressEntity.getStreetAdress());
		addressTO.setZipCode(addressEntity.getZipCode());
		
		return addressTO;
		
	}

}
