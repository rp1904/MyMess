package com.byb.bhojan.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mess_setting")
public class MessSetting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "mess_setting_id", unique = true)
	private String messSettingId;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "mess_id_fk")
	private Mess mess;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_id_fk")
	private Meal meal;

	@Column(name = "prepares_non_veg")
	private boolean preparesNonVeg = false;

	@Column(name = "opening_time_1")
	@Temporal(TemporalType.TIME)
	private Date openingTime1;

	@Column(name = "closeing_time_1")
	@Temporal(TemporalType.TIME)
	private Date closingTime1;

	@Column(name = "opening_time_2")
	@Temporal(TemporalType.TIME)
	private Date openingTime2;

	@Column(name = "closeing_time_2")
	@Temporal(TemporalType.TIME)
	private Date closingTime2;

	@Column(name = "weekly_off")
	private String weeklyOff;

	@Column(name = "off_session_1")
	private boolean offSession1 = true;

	@Column(name = "off_session_2")
	private boolean offSession2 = true;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getMessSettingId() {
		return messSettingId;
	}

	public void setMessSettingId(String messSettingId) {
		this.messSettingId = messSettingId;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public boolean isPreparesNonVeg() {
		return preparesNonVeg;
	}

	public void setPreparesNonVeg(boolean preparesNonVeg) {
		this.preparesNonVeg = preparesNonVeg;
	}

	public Date getOpeningTime1() {
		return openingTime1;
	}

	public void setOpeningTime1(Date openingTime1) {
		this.openingTime1 = openingTime1;
	}

	public Date getClosingTime1() {
		return closingTime1;
	}

	public void setClosingTime1(Date closingTime1) {
		this.closingTime1 = closingTime1;
	}

	public Date getOpeningTime2() {
		return openingTime2;
	}

	public void setOpeningTime2(Date openingTime2) {
		this.openingTime2 = openingTime2;
	}

	public Date getClosingTime2() {
		return closingTime2;
	}

	public void setClosingTime2(Date closingTime2) {
		this.closingTime2 = closingTime2;
	}

	public String getWeeklyOff() {
		return weeklyOff;
	}

	public void setWeeklyOff(String weeklyOff) {
		this.weeklyOff = weeklyOff;
	}

	public boolean isOffSession1() {
		return offSession1;
	}

	public void setOffSession1(boolean offSession1) {
		this.offSession1 = offSession1;
	}

	public boolean isOffSession2() {
		return offSession2;
	}

	public void setOffSession2(boolean offSession2) {
		this.offSession2 = offSession2;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	public void setMess(Mess mess) {
		this.mess = mess;
	}

	@Override
	public String toString() {
		return "MessSetting [messSettingId=" + messSettingId + ", meal=" + meal + ", preparesNonVeg=" + preparesNonVeg + ", openingTime1=" + openingTime1 + ", closingTime1=" + closingTime1 + ", openingTime2=" + openingTime2 + ", closingTime2=" + closingTime2
				+ ", weeklyOff=" + weeklyOff + ", offSession1=" + offSession1 + ", offSession2=" + offSession2 + ", createdUpdated=" + createdUpdated + "]";
	}

}
