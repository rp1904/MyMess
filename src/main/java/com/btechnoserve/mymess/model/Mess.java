package com.btechnoserve.mymess.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "mess")
public class Mess implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "mess_id", unique = true)
	private String messId;

	private String messDisplayId;

	private String messName;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_user_id_fk")
	private User messOwner;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id_fk")
	private Address messAddress;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mess", cascade = CascadeType.ALL)
	private List<User> members;

	private Date messStartedDate;

	private Date messRegistrationDate;

	public String getMessId() {
		return messId;
	}

	public void setMessId(String messId) {
		this.messId = messId;
	}

	public String getMessDisplayId() {
		return messDisplayId;
	}

	public void setMessDisplayId(String messDisplayId) {
		this.messDisplayId = messDisplayId;
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

	public Address getMessAddress() {
		return messAddress;
	}

	public void setMessAddress(Address messAddress) {
		this.messAddress = messAddress;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public Date getMessStartedDate() {
		return messStartedDate;
	}

	public void setMessStartedDate(Date messStartedDate) {
		this.messStartedDate = messStartedDate;
	}

	public Date getMessRegistrationDate() {
		return messRegistrationDate;
	}

	public void setMessRegistrationDate(Date messRegistrationDate) {
		this.messRegistrationDate = messRegistrationDate;
	}

}
