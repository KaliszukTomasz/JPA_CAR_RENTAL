package com.capgemini.types;
/**
 * @author TKALISZU Description: AddressTO contains information about city,
 *         zipCode and streetAddress of ClientTO or OfficeTO
 */
public class AddressTO {

	private String city;
	private String zipCode;
	private String streetAddress;

	public AddressTO() {

	}

	public AddressTO(String city, String zipCode, String streetAddress) {
		super();
		this.city = city;
		this.zipCode = zipCode;
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}



}
