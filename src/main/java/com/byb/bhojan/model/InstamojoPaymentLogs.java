package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instamojo_payment_logs")
public class InstamojoPaymentLogs implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String payment_id;

	@Column(name = "mess_id")
	private String messId;

	private String payment_request_id;

	private String amount;

	private String buyer;

	private String buyer_name;

	private String buyer_phone;

	private String currency;

	private String fees;

	private String longurl;

	private String mac;

	private String purpose;

	private String shorturl;

	private String status;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getMessId() {
		return messId;
	}

	public void setMessId(String messId) {
		this.messId = messId;
	}

	public String getPayment_request_id() {
		return payment_request_id;
	}

	public void setPayment_request_id(String payment_request_id) {
		this.payment_request_id = payment_request_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getBuyer_phone() {
		return buyer_phone;
	}

	public void setBuyer_phone(String buyer_phone) {
		this.buyer_phone = buyer_phone;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getLongurl() {
		return longurl;
	}

	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "InstamojoPaymentLogs [payment_id=" + payment_id + ", messId=" + messId + ", payment_request_id="
				+ payment_request_id + ", amount=" + amount + ", buyer=" + buyer + ", buyer_name=" + buyer_name
				+ ", buyer_phone=" + buyer_phone + ", currency=" + currency + ", fees=" + fees + ", longurl=" + longurl
				+ ", mac=" + mac + ", purpose=" + purpose + ", shorturl=" + shorturl + ", status=" + status
				+ ", createdUpdated=" + createdUpdated + "]";
	}

}
