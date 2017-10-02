package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "instamojo_payment_logs")
public class InstamojoPaymentLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "instamojo_payment_log_id", unique = true)
	private String instamojoPaymentLogId;

	private String payment_id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mess_id_fk")
	private Mess mess;

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

	@Column(name = "voucher_name")
	private String voucherName;

	@Column(name = "voucher_amount")
	private double voucherAmount;

	@Column(name = "voucher_days")
	private int voucherDays;

	@Column(name = "voucher_discount")
	private double voucherDiscount;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getInstamojoPaymentLogId() {
		return instamojoPaymentLogId;
	}

	public void setInstamojoPaymentLogId(String instamojoPaymentLogId) {
		this.instamojoPaymentLogId = instamojoPaymentLogId;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public Mess getMess() {
		return mess;
	}

	public void setMess(Mess mess) {
		this.mess = mess;
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

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public double getVoucherAmount() {
		return voucherAmount;
	}

	public void setVoucherAmount(double voucherAmount) {
		this.voucherAmount = voucherAmount;
	}

	public int getVoucherDays() {
		return voucherDays;
	}

	public void setVoucherDays(int voucherDays) {
		this.voucherDays = voucherDays;
	}

	public double getVoucherDiscount() {
		return voucherDiscount;
	}

	public void setVoucherDiscount(double voucherDiscount) {
		this.voucherDiscount = voucherDiscount;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "InstamojoPaymentLog [instamojoPaymentLogId=" + instamojoPaymentLogId + ", payment_id=" + payment_id + ", payment_request_id=" + payment_request_id + ", amount=" + amount + ", buyer=" + buyer + ", buyer_name=" + buyer_name + ", buyer_phone=" + buyer_phone
				+ ", currency=" + currency + ", fees=" + fees + ", longurl=" + longurl + ", mac=" + mac + ", purpose=" + purpose + ", shorturl=" + shorturl + ", status=" + status + ", voucherName=" + voucherName + ", voucherAmount=" + voucherAmount
				+ ", voucherDays=" + voucherDays + ", voucherDiscount=" + voucherDiscount + ", createdUpdated=" + createdUpdated + "]";
	}

}
