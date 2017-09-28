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
@Table(name = "mess_payment_voucher")
public class MessPaymentVoucher implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "mess_payment_coupen_id", unique = true)
	private String messPaymentVoucherId;

	private String name;

	private double amount;

	private int days;
	
	private double discount;
	
	@Embedded
	private CreatedUpdated createdUpdated;

	public String getMessPaymentVoucherId() {
		return messPaymentVoucherId;
	}

	public void setMessPaymentVoucherId(String messPaymentVoucherId) {
		this.messPaymentVoucherId = messPaymentVoucherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "MessPaymentVoucher [messPaymentVoucherId=" + messPaymentVoucherId + ", name=" + name + ", amount=" + amount + ", days=" + days + ", discount=" + discount + ", createdUpdated=" + createdUpdated + "]";
	}

}
