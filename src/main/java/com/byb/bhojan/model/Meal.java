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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "meals")
public class Meal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "meal_id_pk", unique = true)
	private String mealId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "mess_id")
	private Mess mess;

	@Column(name = "meal_title")
	private String mealTitle;

	private String status;

	@Column(name = "veg_default_menu")
	private String vegDefaultMenu;

	@Column(name = "veg_items")
	private String vegItems;

	@Column(name = "veg_extra")
	private String vegExtra;

	@Column(name = "veg_rating")
	private String vegRating;

	private String sweet;

	private Boolean isNonVeg;

	@Column(name = "non_veg_default_menu")
	private String nonVegDefaultMenu;

	@Column(name = "non_veg_items")
	private String nonVegItems;

	@Column(name = "non_veg_extra")
	private String nonVegExtra;

	@Column(name = "non_veg_rating")
	private String nonVegRating;

	private String note;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getMealId() {
		return mealId;
	}

	public void setMealId(String mealId) {
		this.mealId = mealId;
	}

	public void setMess(Mess mess) {
		this.mess = mess;
	}

	public String getMealTitle() {
		return mealTitle;
	}

	public void setMealTitle(String mealTitle) {
		this.mealTitle = mealTitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVegDefaultMenu() {
		return vegDefaultMenu;
	}

	public void setVegDefaultMenu(String vegDefaultMenu) {
		this.vegDefaultMenu = vegDefaultMenu;
	}

	public String getVegItems() {
		return vegItems;
	}

	public void setVegItems(String vegItems) {
		this.vegItems = vegItems;
	}

	public String getVegExtra() {
		return vegExtra;
	}

	public void setVegExtra(String vegExtra) {
		this.vegExtra = vegExtra;
	}

	public String getSweet() {
		return sweet;
	}

	public void setSweet(String sweet) {
		this.sweet = sweet;
	}

	public Boolean getIsNonVeg() {
		return isNonVeg;
	}

	public void setIsNonVeg(Boolean isNonVeg) {
		this.isNonVeg = isNonVeg;
	}

	public String getNonVegDefaultMenu() {
		return nonVegDefaultMenu;
	}

	public void setNonVegDefaultMenu(String nonVegDefaultMenu) {
		this.nonVegDefaultMenu = nonVegDefaultMenu;
	}

	public String getNonVegItems() {
		return nonVegItems;
	}

	public void setNonVegItems(String nonVegItems) {
		this.nonVegItems = nonVegItems;
	}

	public String getNonVegExtra() {
		return nonVegExtra;
	}

	public void setNonVegExtra(String nonVegExtra) {
		this.nonVegExtra = nonVegExtra;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Meal [mealId=" + mealId + ", mess=" + mess + ", mealTitle=" + mealTitle + ", status=" + status
				+ ", vegDefaultMenu=" + vegDefaultMenu + ", vegItems=" + vegItems + ", vegExtra=" + vegExtra
				+ ", sweet=" + sweet + ", isNonVeg=" + isNonVeg + ", nonVegDefaultMenu=" + nonVegDefaultMenu
				+ ", nonVegItems=" + nonVegItems + ", nonVegExtra=" + nonVegExtra + ", note=" + note
				+ ", createdUpdated=" + createdUpdated + "]";
	}

}
