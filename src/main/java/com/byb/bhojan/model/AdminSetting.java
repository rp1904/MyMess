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

	@Column(name = "free_trial_days")
	private int freeTrialDays;

	@Column(name = "notify_before_days")
	private int notifyBeforeDays;

	@Embedded
	private CreatedUpdated createdUpdated;

	public int getAdminSettingId() {
		return adminSettingId;
	}

	public void setAdminSettingId(int adminSettingId) {
		this.adminSettingId = adminSettingId;
	}

	public int getFreeTrialDays() {
		return freeTrialDays;
	}

	public void setFreeTrialDays(int freeTrialDays) {
		this.freeTrialDays = freeTrialDays;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	public int getNotifyBeforeDays() {
		return notifyBeforeDays;
	}

	public void setNotifyBeforeDays(int notifyBeforeDays) {
		this.notifyBeforeDays = notifyBeforeDays;
	}

	@Override
	public String toString() {
		return "AdminSetting [adminSettingId=" + adminSettingId + ", freeTrialDays=" + freeTrialDays + ", notifyBeforeDays=" + notifyBeforeDays + ", createdUpdated=" + createdUpdated + "]";
	}


}
