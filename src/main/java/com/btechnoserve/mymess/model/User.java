package com.btechnoserve.mymess.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "user_id", unique = true)
	private String userId;

	@Column(name = "email", unique = true, nullable = false, length = 60)
	@Email
	private String email;

	private String mobileNumber;

	private String password;

	private boolean enabled;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_role_id_fk")
	private UserRole userRole;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_info_id_fk")
	private UserInfo userInfo;

	public User() {
	}

	public User(String email, String mobileNumber, String password) {
		super();
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}

	public User(String email, String mobileNumber, String password, boolean enabled, UserRole userRole) {
		super();
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", mobileNumber=" + mobileNumber + ", password="
				+ password + ", enabled=" + enabled + ", userRole=" + userRole + ", userInfo=" + userInfo + "]";
	}

}
