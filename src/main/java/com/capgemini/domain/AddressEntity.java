package com.capgemini.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author TKALISZU Description: AddressEntity contains information about city,
 *         zipCode and streetAddress of ClientEntity or OfficeEntity
 */
@Embeddable
public class AddressEntity {

	@Column(nullable = false, length = 30)
	private String city;
	@Column(nullable = false, length = 30)
	private String zipCode;
	@Column(nullable = false, length = 30)
	private String streetAddress;

	

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

	public String getStreetAdress() {
		return streetAddress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAddress = streetAdress;
	}

}
