package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_profiles")
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "user_profile_id_pk", unique = true)
	private String userProfileIdPk;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "profile_image_url")
	private String profileImageUrl;

	@Column(name = "profile_image_thumbnail_url")
	private String profileImageThumbnailUrl;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id_fk")
	private Address address;

	public String getUserProfileIdPk() {
		return userProfileIdPk;
	}

	public void setUserProfileIdPk(String userProfileIdPk) {
		this.userProfileIdPk = userProfileIdPk;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getProfileImageThumbnailUrl() {
		return profileImageThumbnailUrl;
	}

	public void setProfileImageThumbnailUrl(String profileImageThumbnailUrl) {
		this.profileImageThumbnailUrl = profileImageThumbnailUrl;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserProfile [userProfileIdPk=" + userProfileIdPk + ", firstName=" + firstName + ", lastName=" + lastName + ", fullName=" + fullName + ", profileImageUrl=" + profileImageUrl + ", profileImageThumbnailUrl=" + profileImageThumbnailUrl + ", address=" + address
				+ "]";
	}

}
