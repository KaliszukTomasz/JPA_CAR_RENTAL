package com.capgemini.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author TKALISZU Description: CarLoanEntity specify all information about
 *         carLoan - version, id, car, client, loanDate, returnDate, loanOffice,
 *         returnOffice amountOfLoan. As every entity has information about
 *         create_date and modify_date.
 */
@Entity
@Table(name = "Car_Loans")
public class CarLoanEntity {

	@Version
	private Long version;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private CarEntity car;
	@ManyToOne
	private ClientEntity client;
	@Column(nullable = false)
	private Date loanDate;
	@Column(nullable = false)
	private Date returnDate;
	@ManyToOne
	private OfficeEntity loanOffice;
	@ManyToOne
	private OfficeEntity returnOffice;
	@Column
	private Integer amountOfLoan;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public CarLoanEntity() {
	}

	public CarLoanEntity(CarEntity car, ClientEntity client, Date loanDate, Date returnDate, OfficeEntity loanOffice,
			OfficeEntity returnOffice, Integer amountOfLoan) {
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

	public Long getVersion() {
		return version;
	}

}
