package com.capgemini.types;

import java.util.Date;

public class CarLoanTO {

	private Long id;
	private CarTO car;
	private ClientTO client;
	private Date loanDate;
	private Date returnDate;
	private OfficeTO loanOffice;
	private OfficeTO returnOffice;
	private Integer amountOfLoan;
	
	public CarLoanTO(){}

	public CarLoanTO(Long id, CarTO car, ClientTO client, Date loanDate, Date returnDate, OfficeTO loanOffice,
			OfficeTO returnOffice, Integer amountOfLoan) {
		super();
		this.id = id;
		this.car = car;
		this.client = client;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
		this.loanOffice = loanOffice;
		this.returnOffice = returnOffice;
		this.amountOfLoan = amountOfLoan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CarTO getCar() {
		return car;
	}

	public void setCar(CarTO car) {
		this.car = car;
	}

	public ClientTO getClient() {
		return client;
	}

	public void setClient(ClientTO client) {
		this.client = client;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public OfficeTO getLoanOffice() {
		return loanOffice;
	}

	public void setLoanOffice(OfficeTO loanOffice) {
		this.loanOffice = loanOffice;
	}

	public OfficeTO getReturnOffice() {
		return returnOffice;
	}

	public void setReturnOffice(OfficeTO returnOffice) {
		this.returnOffice = returnOffice;
	}

	public Integer getAmountOfLoan() {
		return amountOfLoan;
	}

	public void setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}
	
	
	
}
