package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.type.DateType;

@Entity
@Table(name = "Car_Loans")
public class CarLoanEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private CarEntity car;
	@ManyToOne
	private ClientEntity client;
	@Column(nullable = false)
	private DateType loanDate;
	@Column(nullable = false)
	private DateType returnDate;
	@ManyToOne
	private OfficeEntity loanOffice;
	@ManyToOne
	private OfficeEntity returnOffice;
	@Column(nullable = false)
	private Integer amountOfLoan;

	public CarLoanEntity() {
	}

	public CarLoanEntity(CarEntity car, ClientEntity client, DateType loanDate, DateType returnDate,
			OfficeEntity loanOffice, OfficeEntity returnOffice, Integer amountOfLoan) {
		super();
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

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public DateType getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(DateType loanDate) {
		this.loanDate = loanDate;
	}

	public DateType getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(DateType returnDate) {
		this.returnDate = returnDate;
	}

	public OfficeEntity getLoanOffice() {
		return loanOffice;
	}

	public void setLoanOffice(OfficeEntity loanOffice) {
		this.loanOffice = loanOffice;
	}

	public OfficeEntity getReturnOffice() {
		return returnOffice;
	}

	public void setReturnOffice(OfficeEntity returnOffice) {
		this.returnOffice = returnOffice;
	}

	public Integer getAmountOfLoan() {
		return amountOfLoan;
	}

	public void setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}

}
