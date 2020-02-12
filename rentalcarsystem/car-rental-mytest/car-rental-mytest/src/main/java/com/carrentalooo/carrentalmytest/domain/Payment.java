package com.carrentalooo.carrentalmytest.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class Payment {
	@Id
	@GeneratedValue
	private int paymentId;
	private Date paymentDateTime;

	private String paymentType;
	private String isConfirm;

	private double amount;
	@NotBlank
	@Pattern(regexp = "\\d{3}", message = "Invalid  CVV number")
	private String cvvNumber;
	@NotBlank
	@Pattern(regexp = "\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4}", message = "Invalid  card number")
	private String cardNumber;
	@NotBlank
	@Pattern(regexp = "\\d{2}[/]\\d{4}", message = "Invalid  card expiry day")
	private String expiryDate;

	@OneToOne
	private Reservation reservation;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Date getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(Date paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCvvNumber() {
		return cvvNumber;
	}

	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
