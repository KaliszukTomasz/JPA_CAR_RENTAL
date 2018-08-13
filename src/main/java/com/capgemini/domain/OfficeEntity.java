package com.capgemini.domain;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "Offices")
public class OfficeEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Integer phoneNumber;
	@Column(nullable = false, length = 30)
	private String email;
	@Column(nullable = false, length = 30)
	private String city;
	@Column(nullable = false, length = 30)
	private String zipCode;
	@Column(nullable = false, length = 30)
	private String streetAdress;
	
	public OfficeEntity(){
	}

	public OfficeEntity(Long id, Integer phoneNumber, String email, String city, String zipCode, String streetAdress) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.city = city;
		this.zipCode = zipCode;
		this.streetAdress = streetAdress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return streetAdress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAdress = streetAdress;
	}
	
	
	
}
