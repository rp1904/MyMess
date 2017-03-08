package com.btechnoserve.mymess.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = "role"))
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_role_id", unique = true, nullable = false)
	private Integer userRoleId;

	private String role;

	public UserRole() {
	}

	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Column(name = "role", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}