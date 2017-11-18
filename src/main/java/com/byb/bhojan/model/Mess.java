package com.byb.bhojan.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "messes")
public class Mess implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "mess_id_pk", unique = true)
	private String messIdPk;

	@Column(name = "mess_id", unique = true)
	private String messId;

	@Column(name = "mess_name")
	private String messName;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mess_owner_id_fk")
	private User messOwner;

	private String address;

	private String latitude;

	private String longitude;

	@Column(name = "mess_tag_line")
	private String messTagLine;

	@Column(name = "mess_profile_image_url")
	private String messProfileImageUrl;

	@Column(name = "mess_profile_image_thumbnail_url")
	private String messProfileImageThumbnailUrl;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mess", cascade = CascadeType.MERGE)
	private List<User> messMembers;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mess", cascade = CascadeType.MERGE)
	private List<Meal> meals;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mess", cascade = CascadeType.MERGE)
	private List<MealCoupen> mealCoupens;

	@Column(name = "days_remaining")
	private int daysRemaining;

	@Column(name = "is_nonveg_prepare")
	private boolean isNonvegPrepare;

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getMessIdPk() {
		return messIdPk;
	}

	public void setMessIdPk(String messIdPk) {
		this.messIdPk = messIdPk;
	}

	public String getMessId() {
		return messId;
	}

	public void setMessId(String messId) {
		this.messId = messId;
	}

	public String getMessName() {
		return messName;
	}

	public void setMessName(String messName) {
		this.messName = messName;
	}

	public User getMessOwner() {
		return messOwner;
	}

	public void setMessOwner(User messOwner) {
		this.messOwner = messOwner;
	}

	public String getMessTagLine() {
		return messTagLine;
	}

	public void setMessTagLine(String messTagLine) {
		this.messTagLine = messTagLine;
	}

	public String getMessProfileImageUrl() {
		return messProfileImageUrl;
	}

	public void setMessProfileImageUrl(String messProfileImageUrl) {
		this.messProfileImageUrl = messProfileImageUrl;
	}

	public String getMessProfileImageThumbnailUrl() {
		return messProfileImageThumbnailUrl;
	}

	public void setMessProfileImageThumbnailUrl(String messProfileImageThumbnailUrl) {
		this.messProfileImageThumbnailUrl = messProfileImageThumbnailUrl;
	}

	public void setMessMembers(List<User> messMembers) {
		this.messMembers = messMembers;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public int getDaysRemaining() {
		return daysRemaining;
	}

	public void setDaysRemaining(int daysRemaining) {
		this.daysRemaining = daysRemaining;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public boolean isNonvegPrepare() {
		return isNonvegPrepare;
	}

	public void setNonvegPrepare(boolean isNonvegPrepare) {
		this.isNonvegPrepare = isNonvegPrepare;
	}

}
