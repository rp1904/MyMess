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
@Table(name = "meal_coupens")
public class MealCoupen implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "coupen_id", unique = true)
	private String coupenId;

	@Column(name = "no_of_meals")
	private int noOfMeals;

	private int validity;

	private float amount;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "mess_id")
	private Mess mess;

	@Embedded
	private CreatedUpdated createdUpdated;

	public void setMess(Mess mess) {
		this.mess = mess;
	}

	public String getCoupenId() {
		return coupenId;
	}

	public void setCoupenId(String coupenId) {
		this.coupenId = coupenId;
	}

	public int getNoOfMeals() {
		return noOfMeals;
	}

	public void setNoOfMeals(int noOfMeals) {
		this.noOfMeals = noOfMeals;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "MealCoupen [coupenId=" + coupenId + ", noOfMeals=" + noOfMeals + ", validity=" + validity + ", amount="
				+ amount + ", createdUpdated=" + createdUpdated + "]";
	}

	public String toShortString() {
		return noOfMeals + ", " + validity + ", " + amount;
	}
}
