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
	private String aboutMe;
	private int userID;
	public AboutInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AboutInfo(int id, String aboutMe, int userID) {
		super();
		this.id = id;
		this.aboutMe = aboutMe;
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "AboutInfo [id=" + id + ", aboutMe=" + aboutMe + ", userID=" + userID + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(aboutMe, id, userID);
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
		return Objects.equals(aboutMe, other.aboutMe) && id == other.id && userID == other.userID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
}
