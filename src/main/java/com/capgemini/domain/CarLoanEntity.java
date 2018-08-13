package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.type.DateType;

public class CarLoanEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Long carId;
	@Column(nullable = false)
	private Long clientId;
	@Column(nullable = false)
	private DateType loanDate;
	@Column(nullable = false)
	private DateType returnDate;
	@Column(nullable = false)
	private Long loanOffice;
	@Column(nullable = false)
	private Long returnOffice;
	@Column(nullable = false)
	private Integer amountOfLoan;

	public CarLoanEntity() {
	}

	public CarLoanEntity(Long id, Long carId, Long clientId, DateType loanDate, DateType returnDate, Long loanOffice,
			Long returnOffice, Integer amountOfLoan) {
		super();
		this.id = id;
		this.carId = carId;
		this.clientId = clientId;
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

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	public Long getLoanOffice() {
		return loanOffice;
	}

	public void setLoanOffice(Long loanOffice) {
		this.loanOffice = loanOffice;
	}

	public Long getReturnOffice() {
		return returnOffice;
	}

	public void setReturnOffice(Long returnOffice) {
		this.returnOffice = returnOffice;
	}

	public Integer getAmountOfLoan() {
		return amountOfLoan;
	}

	public void setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}

}
