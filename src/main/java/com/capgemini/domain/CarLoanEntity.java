package com.capgemini.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Car_Loans")
public class CarLoanEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private CarEntity car;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private ClientEntity client;
	@Column(nullable = false)
	private Date loanDate;
	@Column(nullable = false)
	private Date returnDate;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private OfficeEntity loanOffice;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private OfficeEntity returnOffice;
	@Column(nullable = false)
	private Integer amountOfLoan;


	public CarLoanEntity() {
	}

	public CarLoanEntity(CarEntity car, ClientEntity client, Date loanDate, Date returnDate,
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
