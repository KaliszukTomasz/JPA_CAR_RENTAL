package com.capgemini.types.builders;

import com.capgemini.exceptions.IncorrectAddressException;
import com.capgemini.types.AddressTO;

/**
 * AddressTO builder
 * 
 * @author TKALISZU
 *
 */
public class AddressTOBuilder {
    private String city;
    private String zipCode;
    private String streetAddress;

    public AddressTOBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public AddressTOBuilder setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressTOBuilder setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public AddressTO buildAddressTO() {
    	checkBeforeBuild(city, zipCode, streetAddress);
        return new AddressTO(city, zipCode, streetAddress);
    }
    private void checkBeforeBuild(String city, String zipCode, String streetAddress) {
		if (city == null || zipCode == null || streetAddress == null ) {
			throw new IncorrectAddressException("Incorrect address to be created");
		}

	}
}