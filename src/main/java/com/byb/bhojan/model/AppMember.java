package com.byb.bhojan.model;

import java.util.Date;

public class AppMember {

  private String memberId;
  private String fullName;
  private boolean isActiveMealRedeedmed;
  private Date createdAt;

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public boolean isActiveMealRedeedmed() {
    return isActiveMealRedeedmed;
  }

  public void setActiveMealRedeedmed(boolean isActiveMealRedeedmed) {
    this.isActiveMealRedeedmed = isActiveMealRedeedmed;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "AppMember [memberId=" + memberId + ", fullName=" + fullName + ", isActiveMealRedeedmed="
        + isActiveMealRedeedmed + ", createdAt=" + createdAt + "]";
  }


}
