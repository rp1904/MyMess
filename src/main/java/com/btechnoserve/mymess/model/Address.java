package com.btechnoserve.mymess.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btechnoserve.mymess.util.ProjectConstant;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "address_id_pk")
	private long addressIdPk;

	private String country = ProjectConstant.DEFAULT_COUNTRY;

	private String state;

	private String city;

	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	@Column(name = "pin_code")
	private String pinCode;

	public long getAddressIdPk() {
		return addressIdPk;
	}

	public void setAddressIdPk(long addressIdPk) {
		this.addressIdPk = addressIdPk;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Address [addressIdPk=" + addressIdPk + ", country=" + country + ", state=" + state + ", city=" + city
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", pinCode=" + pinCode + "]";
	}

}
