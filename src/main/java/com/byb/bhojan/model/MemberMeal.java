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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "members_meals")
public class MemberMeal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "member_meal_id_pk")
	private String memberMealId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "member_id_fk")
	private User member;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "meal_id_fk")
	private Meal meal;

	@Column(name = "meal_type")
	private String mealType;

	@Column(name = "remaining_meal_count")
	private int remainingMealCount;

	@Column(name = "read_by")
	private String readBy;

	@Column(name = "meal_for")
	private String mealFor;

	private float rate;

	private String comment;

	private String note;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getMemberMealId() {
		return memberMealId;
	}

	public void setMemberMealId(String memberMealId) {
		this.memberMealId = memberMealId;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public int getRemainingMealCount() {
		return remainingMealCount;
	}

	public void setRemainingMealCount(int remainingMealCount) {
		this.remainingMealCount = remainingMealCount;
	}

	public String getReadBy() {
		return readBy;
	}

	public void setReadBy(String readBy) {
		this.readBy = readBy;
	}

	public String getMealFor() {
		return mealFor;
	}

	public void setMealFor(String mealFor) {
		this.mealFor = mealFor;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

}
