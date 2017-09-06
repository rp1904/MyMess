package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "instamojo_payment_req_response")
public class InstamojoPaymentReqResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "payment_req_response_id")
	private String paymentReqResponseId;

	@Column(name = "mess_id")
	private String messId;

	private boolean success;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_request_id_fk")
	private InstamojoPaymentRequest payment_request;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getPaymentReqResponseId() {
		return paymentReqResponseId;
	}

	public void setPaymentReqResponseId(String paymentReqResponseId) {
		this.paymentReqResponseId = paymentReqResponseId;
	}

	public String getMessId() {
		return messId;
	}

	public void setMessId(String messId) {
		this.messId = messId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public InstamojoPaymentRequest getPayment_request() {
		return payment_request;
	}

	public void setPayment_request(InstamojoPaymentRequest payment_request) {
		this.payment_request = payment_request;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "InstamojoPaymentReqResponse [paymentReqResponseId=" + paymentReqResponseId + ", messId=" + messId
				+ ", success=" + success + ", payment_request=" + payment_request + ", createdUpdated=" + createdUpdated
				+ "]";
	}

}
