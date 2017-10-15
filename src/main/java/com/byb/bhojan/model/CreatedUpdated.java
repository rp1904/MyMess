package com.byb.bhojan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CreatedUpdated {

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_at")
	private Date updatedAt;

	public CreatedUpdated() {
	}

	public CreatedUpdated(String createdBy) {
		super();
		Date currDate = new Date();
		this.createdBy = createdBy;
		this.createdAt = currDate;
		this.updatedBy = createdBy;
		this.updatedAt = currDate;
	}

	public CreatedUpdated(Date date, String createdBy) {
		super();
		this.createdBy = createdBy;
		this.createdAt = date;
		this.updatedBy = createdBy;
		this.updatedAt = date;
	}

	public CreatedUpdated(CreatedUpdated old_created_updated, String updatedBy) {
		super();
		Date currDate = new Date();
		this.createdBy = old_created_updated.getCreatedBy();
		this.createdAt = old_created_updated.getCreatedAt();
		this.updatedBy = updatedBy;
		this.updatedAt = currDate;
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
		return "CreatedUpdated [createdBy=" + createdBy + ", createdAt=" + createdAt + ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + "]";
	}

}
