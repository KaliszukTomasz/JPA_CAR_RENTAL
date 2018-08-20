package com.capgemini.types.builders;

import java.util.Date;

import com.capgemini.types.CarLoanTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.OfficeTO;

/**
 * CarLoanTO builder
 * 
 * @author TKALISZU
 *
 */
public class CarLoanTOBuilder {
	private Long id;
	private CarTO car;
	private ClientTO client;
	private Date loanDate;
	private Date returnDate;
	private OfficeTO loanOffice;
	private OfficeTO returnOffice;
	private Integer amountOfLoan;

	public CarLoanTOBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public CarLoanTOBuilder setCar(CarTO car) {
		this.car = car;
		return this;
	}

	public CarLoanTOBuilder setClient(ClientTO client) {
		this.client = client;
		return this;
	}

	public CarLoanTOBuilder setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
		return this;
	}

	public CarLoanTOBuilder setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
		return this;
	}

	public CarLoanTOBuilder setLoanOffice(OfficeTO loanOffice) {
		this.loanOffice = loanOffice;
		return this;
	}

	public CarLoanTOBuilder setReturnOffice(OfficeTO returnOffice) {
		this.returnOffice = returnOffice;
		return this;
	}

	public CarLoanTOBuilder setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
		return this;
	}

	public CarLoanTO buildCarLoanTO() {
		checkBeforeBuild(car, client, loanDate, loanOffice);
		return new CarLoanTO(id, car, client, loanDate, returnDate, loanOffice, returnOffice, amountOfLoan);
	}

	private void checkBeforeBuild(CarTO car, ClientTO client, Date loanDate, OfficeTO loanOffice) {
		if (car == null || client == null || loanDate == null || loanOffice == null) {
			throw new RuntimeException("Incorrect officeTO to be created");
		}
	}
}