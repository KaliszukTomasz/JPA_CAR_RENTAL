package com.capgemini.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
public class AddressEntity {

	@Column(nullable = false, length = 30)
	private String city;
	@Column(nullable = false, length = 30)
	private String zipCode;
	@Column(nullable = false, length = 30)
	private String streetAddress;

	public AddressEntity() {
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

	public String getStreetAdress() {
		return streetAddress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAddress = streetAdress;
	}

}
