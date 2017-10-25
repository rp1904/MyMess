package com.byb.bhojan.dto;

import java.util.Date;

public class MessSettingDto {

	public String messIdPk;

	public String trialMealId;

	private String trialMealVegDefaultMenu;

	private String trialMealVegItems;

	private String trialMealVegExtra;

	private String trialMealSweet;

	private String trialMealNonVegDefaultMenu;

	private String trialMealNonVegItems;

	private String trialMealNonVegExtra;

	private boolean preparesNonVeg;

	private Date openingTime1;

	private Date closingTime1;

	private Date openingTime2;

	private Date closingTime2;

	private String weeklyOff;

	private boolean offSession1;

	private boolean offSession2;

	private String createdBy;

	private Date createdAt;

	private String updatedBy;

	private Date updatedAt;

	public String getMessIdPk() {
		return messIdPk;
	}

	public void setMessIdPk(String messIdPk) {
		this.messIdPk = messIdPk;
	}

	public String getTrialMealId() {
		return trialMealId;
	}

	public void setTrialMealId(String trialMealId) {
		this.trialMealId = trialMealId;
	}

	public String getTrialMealVegDefaultMenu() {
		return trialMealVegDefaultMenu;
	}

	public void setTrialMealVegDefaultMenu(String trialMealVegDefaultMenu) {
		this.trialMealVegDefaultMenu = trialMealVegDefaultMenu;
	}

	public String getTrialMealVegItems() {
		return trialMealVegItems;
	}

	public void setTrialMealVegItems(String trialMealVegItems) {
		this.trialMealVegItems = trialMealVegItems;
	}

	public String getTrialMealVegExtra() {
		return trialMealVegExtra;
	}

	public void setTrialMealVegExtra(String trialMealVegExtra) {
		this.trialMealVegExtra = trialMealVegExtra;
	}

	public String getTrialMealSweet() {
		return trialMealSweet;
	}

	public void setTrialMealSweet(String trialMealSweet) {
		this.trialMealSweet = trialMealSweet;
	}

	public String getTrialMealNonVegDefaultMenu() {
		return trialMealNonVegDefaultMenu;
	}

	public void setTrialMealNonVegDefaultMenu(String trialMealNonVegDefaultMenu) {
		this.trialMealNonVegDefaultMenu = trialMealNonVegDefaultMenu;
	}

	public String getTrialMealNonVegItems() {
		return trialMealNonVegItems;
	}

	public void setTrialMealNonVegItems(String trialMealNonVegItems) {
		this.trialMealNonVegItems = trialMealNonVegItems;
	}

	public String getTrialMealNonVegExtra() {
		return trialMealNonVegExtra;
	}

	public void setTrialMealNonVegExtra(String trialMealNonVegExtra) {
		this.trialMealNonVegExtra = trialMealNonVegExtra;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "MessSettingDto [messIdPk=" + messIdPk + ", trialMealId=" + trialMealId + ", trialMealVegDefaultMenu=" + trialMealVegDefaultMenu + ", trialMealVegItems=" + trialMealVegItems + ", trialMealVegExtra=" + trialMealVegExtra + ", trialMealSweet=" + trialMealSweet
				+ ", trialMealNonVegDefaultMenu=" + trialMealNonVegDefaultMenu + ", trialMealNonVegItems=" + trialMealNonVegItems + ", trialMealNonVegExtra=" + trialMealNonVegExtra + ", preparesNonVeg=" + preparesNonVeg + ", openingTime1=" + openingTime1
				+ ", closingTime1=" + closingTime1 + ", openingTime2=" + openingTime2 + ", closingTime2=" + closingTime2 + ", weeklyOff=" + weeklyOff + ", offSession1=" + offSession1 + ", offSession2=" + offSession2 + ", createdBy=" + createdBy + ", createdAt="
				+ createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
	}

}
