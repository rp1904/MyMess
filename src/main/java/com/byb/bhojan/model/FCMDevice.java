package com.byb.bhojan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fcm_devices")
public class FCMDevice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "device_id")
	private String deviceId;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "fcm_token")
	private String fcmToken;

	private String cordova; // Get the version of Cordova running on the device.

	private String model; // Returns the name of the devices model or product.
							// The value is set by the device manufacturer and
							// may be different across versions of the same
							// product.

	private String platform; // Get the devices operating system name.

	private String version; // Get the operating system version.

	private String uuid; // Get the devices Universally Unique Identifier
							// (UUID).

	private String manufacturer; // Get the devices manufacturer.

	@Column(name = "is_virtual")
	private String isVirtual; // Whether the device is running on a simulator.

	private String serial; // Get the device hardware serial number.

	@Embedded
	private CreatedUpdated createdUpdated;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public String getCordova() {
		return cordova;
	}

	public void setCordova(String cordova) {
		this.cordova = cordova;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public CreatedUpdated getCreatedUpdated() {
		return createdUpdated;
	}

	public void setCreatedUpdated(CreatedUpdated createdUpdated) {
		this.createdUpdated = createdUpdated;
	}

	@Override
	public String toString() {
		return "FCMDevice [deviceId=" + deviceId + ", userId=" + userId + ", fcmToken=" + fcmToken + ", cordova="
				+ cordova + ", model=" + model + ", platform=" + platform + ", version=" + version + ", uuid=" + uuid
				+ ", manufacturer=" + manufacturer + ", isVirtual=" + isVirtual + ", serial=" + serial
				+ ", createdUpdated=" + createdUpdated + "]";
	}

}
