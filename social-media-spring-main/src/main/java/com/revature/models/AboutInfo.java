package com.revature.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AboutInfo")
public class AboutInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String about_Me;
	private int userID;

	public AboutInfo(String aboutMe) {
		super();
		this.about_Me = aboutMe;
	}

	public AboutInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AboutInfo(int id, String aboutMe, int userID) {
		super();
		this.id = id;
		this.about_Me = aboutMe;
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "AboutInfo [id=" + id + ", aboutMe=" + about_Me + ", userID=" + userID + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(about_Me, id, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AboutInfo other = (AboutInfo) obj;
		return Objects.equals(about_Me, other.about_Me) && id == other.id && userID == other.userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAboutMe() {
		return about_Me;
	}

	public void setAboutMe(String aboutMe) {
		this.about_Me = aboutMe;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
