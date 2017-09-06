package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mess_payments")
public class MessPayment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "mess_payment_id")
	private String messPaymentId;

	@Column(name = "mess_id")
	private String mess_id;

	@Column(name = "payment_status")
	private String paymentStatus;

	private String amount;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getMessPaymentId() {
		return messPaymentId;
	}

	public void setMessPaymentId(String messPaymentId) {
		this.messPaymentId = messPaymentId;
	}

	public String getMess_id() {
		return mess_id;
	}

	public void setMess_id(String mess_id) {
		this.mess_id = mess_id;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

}
