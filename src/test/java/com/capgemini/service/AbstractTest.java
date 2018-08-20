package com.capgemini.service;

import java.time.Instant;
import java.time.Year;
import java.util.Date;
import java.util.HashSet;

import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.enums.EmployeePosition;
import com.capgemini.types.AddressTO;
import com.capgemini.types.CarLoanTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.builders.AddressTOBuilder;
import com.capgemini.types.builders.CarLoanTOBuilder;
import com.capgemini.types.builders.CarTOBuilder;
import com.capgemini.types.builders.ClientTOBuilder;
import com.capgemini.types.builders.EmployeeTOBuilder;
import com.capgemini.types.builders.OfficeTOBuilder;

public abstract class AbstractTest {

	public OfficeEntity buildOfficeEntity() {
		OfficeEntity officeEntity = new OfficeEntity(65656565, "qwe", buildAddressEntity(), new HashSet<>(),
				new HashSet<>(), new HashSet<>(), new HashSet<>());
		return officeEntity;
	}

	public AddressEntity buildAddressEntity() {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity("Miasto");
		addressEntity.setStreetAdress("ulica");
		addressEntity.setZipCode("zip");
		return addressEntity;
	}
	
	public AddressTO buildAddressTO(){
		return new AddressTOBuilder().setCity("City").setStreetAddress("address").setZipCode("zip").buildAddressTO();
	}

	public CarTO buildCarTO() {
		CarTO carTO = new CarTOBuilder().setCarType("sedan").setCurrentLocation(buildOfficeTO()).setBrand("BMW")
				.setYearOfProduction(Year.of(1995)).setColor("red").setEngineCapacity(1600).setEnginePower(100)
				.setMileage(100000).buildCarTO();
		return carTO;

	}

	public CarTO buildSecoundCarTO() {

		CarTO carTO = new CarTOBuilder().setCarType("kombi").setCurrentLocation(buildOfficeTO()).setBrand("Mercedes")
				.setYearOfProduction(Year.of(2000)).setColor("blue").setEngineCapacity(2000).setEnginePower(200)
				.setMileage(200000).buildCarTO();
		return carTO;

	}

	public EmployeeTO buildEmployeeTO() {

		return new EmployeeTOBuilder().setDateOfBirth(java.sql.Date.valueOf("1988-05-05")).setFirstName("Adam")
				.setEmployeePosition(EmployeePosition.DEALER).setLastName("Kowalski").setOffice(buildOfficeTO())
				.buildEmployeeTO();
	}

	public OfficeTO buildOfficeTO() {
		OfficeTO officeTO = new OfficeTOBuilder().setAddress(new AddressTO("Poznan", "61-251", "Polna"))
				.setEmail("biuro@gmail.com").setPhoneNumber(745215321).buildOfficeTO();
		return officeTO;
	}

	@SuppressWarnings("deprecation")
	public EmployeeTO buildManagerEmployeeTO() {

		return new EmployeeTOBuilder().setDateOfBirth(new Date("10/11/1959")).setFirstName("Adam")
				.setEmployeePosition(EmployeePosition.MANAGER).setLastName("Kowalski").setOffice(buildOfficeTO())
				.buildEmployeeTO();
	}

	public CarEntity buildCarEntity() {
		CarEntity carEntity = new CarEntity();
		carEntity.setCarType("sedan");
		carEntity.setCurrentLocation(buildOfficeEntity());
		carEntity.setBrand("BMW");
		carEntity.setYearOfProduction(Year.of(1995));
		carEntity.setColor("red");
		carEntity.setEngineCapacity(1600);
		carEntity.setEnginePower(100);
		carEntity.setMileage(100000);
		return carEntity;
	}

	public CarLoanTO buildCarLoanTO() {
		CarTO carTO = buildCarTO();
		carTO.setId(1L);
		ClientTO clientTO = buildClientTO();
		clientTO.setId(1L);
		OfficeTO officeTO = buildOfficeTO();
		officeTO.setId(1L);
		return new CarLoanTOBuilder().setAmountOfLoan(200).setCar(carTO).setClient(clientTO)
				.setLoanDate(new Date(2018, 12, 05, 18, 16)).setReturnDate(new Date(2018, 12, 05, 23, 05))
				.setLoanOffice(officeTO).setReturnOffice(officeTO).buildCarLoanTO();

	}

	public EmployeeEntity buildEmployeeEntity() {
		return new EmployeeEntity("Arek", "Konieczny", Date.from(Instant.now()), null, null);
	}

	public ClientTO buildClientTO() {
		@SuppressWarnings("deprecation")
		ClientTO clientTO = new ClientTOBuilder().setAddress(new AddressTO("Warszawa", "41457", "Warszawska"))
				.setCreditCardNumber(987439871345L).setDateOfBirth(new Date("10/11/1959")).setEmail("newEmail@wp.pl")
				.setFirstName("Artur").setLastName("Lewandowski").setPhoneNumber(841532154).buildClientTO();
		return clientTO;
	}

	public ClientEntity buildClientEntity() {
		return new ClientEntity("Artur", "Kras", buildAddressEntity(), new Date("10/11/1959"), 56641235, "emiala@wp.pl",
				5615612123168L, new HashSet<>());
	}
	
	
}
