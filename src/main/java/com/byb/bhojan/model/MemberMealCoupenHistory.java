package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "members_mealcoupens_history")
public class MemberMealCoupenHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_meal_coupen_history_id_pk")
	private long memberMealCoupenHistoryId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "member_id_fk")
	private User member;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "meal_coupen_id_fk")
	private MealCoupen mealCoupen;

	@Embedded
	private CreatedUpdated createdUpdated;

	public long getMemberMealCoupenHistoryId() {
		return memberMealCoupenHistoryId;
	}

	public void setMemberMealCoupenHistoryId(long memberMealCoupenHistoryId) {
		this.memberMealCoupenHistoryId = memberMealCoupenHistoryId;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public MealCoupen getMealCoupen() {
		return mealCoupen;
	}

	public void setMealCoupen(MealCoupen mealCoupen) {
		this.mealCoupen = mealCoupen;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

}
