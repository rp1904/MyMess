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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "user_id_pk", unique = true)
	private String userIdPk;

	@Column(name = "user_id", unique = true)
	private String userId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id_fk")
	private UserProfile userProfile;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_role_id_fk")
	private UserRole userRole;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "mess_id_fk")
	private Mess mess;

	@Column(name = "email", unique = true)
	@Email
	private String email;

	@Column(name = "mobile_number", unique = true)
	private String mobileNumber;

	private String password;

	@Transient
	private String confirmPassword;

	@Column(name = "is_enable")
	private boolean isEnable = Boolean.TRUE;

	@Column(name = "accout_non_expired")
	private boolean accoutNonExpired = Boolean.TRUE;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired = Boolean.TRUE;

	@Column(name = "accout_non_locker")
	private boolean accoutNonLocker = Boolean.TRUE;

	@Embedded
	private CreatedUpdated createdUpdated;

	public User() {
	}

	public User(String email, String mobileNumber, String password) {
		super();
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}

	public String getUserIdPk() {
		return userIdPk;
	}

	public void setUserIdPk(String userIdPk) {
		this.userIdPk = userIdPk;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public void setMess(Mess mess) {
		this.mess = mess;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean isAccoutNonExpired() {
		return accoutNonExpired;
	}

	public void setAccoutNonExpired(boolean accoutNonExpired) {
		this.accoutNonExpired = accoutNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccoutNonLocker() {
		return accoutNonLocker;
	}

	public void setAccoutNonLocker(boolean accoutNonLocker) {
		this.accoutNonLocker = accoutNonLocker;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "User [userIdPk=" + userIdPk + ", userId=" + userId + ", userProfile=" + userProfile + ", userRole="
				+ userRole + ", mess=" + mess + ", email=" + email + ", mobileNumber=" + mobileNumber + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", isEnable=" + isEnable + ", accoutNonExpired="
				+ accoutNonExpired + ", credentialsNonExpired=" + credentialsNonExpired + ", accoutNonLocker="
				+ accoutNonLocker + ", createdUpdated=" + createdUpdated + "]";
	}

}
