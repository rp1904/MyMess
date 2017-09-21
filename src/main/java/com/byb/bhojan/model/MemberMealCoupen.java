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
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "members_mealcoupens")
public class MemberMealCoupen implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = "member_meal_coupen_id_pk")
  private String memberMealCoupenId;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "member_id_fk")
  private User member;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "meal_coupen_id_fk")
  private MealCoupen mealCoupen;

  @Temporal(javax.persistence.TemporalType.DATE)
  @Column(name = "expiry_date")
  private Date expiryDate;

  @Column(name = "no_of_meals")
  private int noOfMeals;

  @Column(name = "remaining_meal_count")
  private int remainingMealCount;

  private String status;

  @Embedded
  private CreatedUpdated createdUpdated;

  public String getMemberMealCoupenId() {
    return memberMealCoupenId;
  }

  public void setMemberMealCoupenId(String memberMealCoupenId) {
    this.memberMealCoupenId = memberMealCoupenId;
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

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public int getNoOfMeals() {
    return noOfMeals;
  }

  public void setNoOfMeals(int noOfMeals) {
    this.noOfMeals = noOfMeals;
  }

  public int getRemainingMealCount() {
    return remainingMealCount;
  }

  public void setRemainingMealCount(int remainingMealCount) {
    this.remainingMealCount = remainingMealCount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CreatedUpdated getCreatedUpdated() {
    return createdUpdated;
  }

  public void setCreatedUpdated(CreatedUpdated createdUpdated) {
    this.createdUpdated = createdUpdated;
  }

  @Override
  public String toString() {
    return "MemberMealCoupen [memberMealCoupenId=" + memberMealCoupenId + ", member=" + member
        + ", mealCoupen=" + mealCoupen + ", expiryDate=" + expiryDate + ", noOfMeals=" + noOfMeals
        + ", remainingMealCount=" + remainingMealCount + ", status=" + status + ", createdUpdated="
        + createdUpdated + "]";
  }

}
