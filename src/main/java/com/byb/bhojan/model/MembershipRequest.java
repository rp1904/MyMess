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

import com.byb.bhojan.util.ProjectConstant;

@Entity
@Table(name = "membership_requests")
public class MembershipRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "membership_request_id_pk")
	private long membershipRequestIdPk;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requesting_member_id")
	private User member;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mess_id")
	private Mess mess;

	@Column(name = "request_status")
	private String requestStatus = ProjectConstant.MEMBERSHIP_REQUEST_PENDING;

	@Embedded
	private CreatedUpdated createdUpdated;

	public long getMembershipRequestIdPk() {
		return membershipRequestIdPk;
	}

	public void setMembershipRequestIdPk(long membershipRequestIdPk) {
		this.membershipRequestIdPk = membershipRequestIdPk;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Mess getMess() {
		return mess;
	}

	public void setMess(Mess mess) {
		this.mess = mess;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "MembershipRequest [membershipRequestIdPk=" + membershipRequestIdPk + ", member=" + member + ", mess="
				+ mess + ", requestStatus=" + requestStatus + ", createdUpdated=" + createdUpdated + "]";
	}

}
