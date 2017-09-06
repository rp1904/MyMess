package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "instamojo_payment_request")
public class InstamojoPaymentRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "instamojo_payment_request_id")
	private String id;

	private String phone;

	private String email;

	private String buyer_name;

	private double amount;

	private String purpose;

	private String expires_at;

	private String status;

	private boolean send_sms;

	private boolean send_email;

	private String sms_status;

	private String email_status;

	private String shorturl;

	private String longurl;

	private String redirect_url;

	private String webhook;

	@Transient
	private String created_at;

	private String modified_at;

	private boolean allow_repeated_payments;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSend_sms() {
		return send_sms;
	}

	public void setSend_sms(boolean send_sms) {
		this.send_sms = send_sms;
	}

	public boolean isSend_email() {
		return send_email;
	}

	public void setSend_email(boolean send_email) {
		this.send_email = send_email;
	}

	public String getSms_status() {
		return sms_status;
	}

	public void setSms_status(String sms_status) {
		this.sms_status = sms_status;
	}

	public String getEmail_status() {
		return email_status;
	}

	public void setEmail_status(String email_status) {
		this.email_status = email_status;
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public String getLongurl() {
		return longurl;
	}

	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getWebhook() {
		return webhook;
	}

	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public boolean isAllow_repeated_payments() {
		return allow_repeated_payments;
	}

	public void setAllow_repeated_payments(boolean allow_repeated_payments) {
		this.allow_repeated_payments = allow_repeated_payments;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "InstamojoPaymentRequest [id=" + id + ", phone=" + phone + ", email=" + email + ", buyer_name="
				+ buyer_name + ", amount=" + amount + ", purpose=" + purpose + ", expires_at=" + expires_at
				+ ", status=" + status + ", send_sms=" + send_sms + ", send_email=" + send_email + ", sms_status="
				+ sms_status + ", email_status=" + email_status + ", shorturl=" + shorturl + ", longurl=" + longurl
				+ ", redirect_url=" + redirect_url + ", webhook=" + webhook + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + ", allow_repeated_payments=" + allow_repeated_payments
				+ ", createdUpdated=" + createdUpdated + "]";
	}

}
