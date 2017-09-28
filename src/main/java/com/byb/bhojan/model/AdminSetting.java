package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_settings")
public class AdminSetting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "admin_setting_id", unique = true)
	private int adminSettingId;

	@Column(name = "default_payable_amount")
	private int defaultPayableAmount;

	@Embedded
	private CreatedUpdated createdUpdated;

	public int getAdminSettingId() {
		return adminSettingId;
	}

	public void setAdminSettingId(int adminSettingId) {
		this.adminSettingId = adminSettingId;
	}

	public int getDefaultPayableAmount() {
		return defaultPayableAmount;
	}

	public void setDefaultPayableAmount(int defaultPayableAmount) {
		this.defaultPayableAmount = defaultPayableAmount;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "AdminSetting [adminSettingId=" + adminSettingId + ", defaultPayableAmount=" + defaultPayableAmount + ", createdUpdated=" + createdUpdated + "]";
	}

}
