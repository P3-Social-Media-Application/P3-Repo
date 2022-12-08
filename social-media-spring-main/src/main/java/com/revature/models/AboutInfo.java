package com.revature.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AboutInfo")
public class AboutInfo {

	@Id
	private int userID;
	private String about_Me;

	public AboutInfo(String aboutMe) {
		super();
		this.about_Me = aboutMe;
	}

	public AboutInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AboutInfo(int userID, String about_Me) {
		super();
		this.userID = userID;
		this.about_Me = about_Me;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getAbout_Me() {
		return about_Me;
	}

	public void setAbout_Me(String about_Me) {
		this.about_Me = about_Me;
	}

	@Override
	public int hashCode() {
		return Objects.hash(about_Me, userID);
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
		return Objects.equals(about_Me, other.about_Me) && userID == other.userID;
	}

	@Override
	public String toString() {
		return "AboutInfo [userID=" + userID + ", about_Me=" + about_Me + "]";
	}
}
