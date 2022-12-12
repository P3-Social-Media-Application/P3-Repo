package com.revature.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Likes")
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ID;
	int postID;
	String likedBy;
	
	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Likes(int iD, int postID, String likedBy) {
		super();
		ID = iD;
		this.postID = postID;
		this.likedBy = likedBy;
	}

	@Override
	public String toString() {
		return "Likes [ID=" + ID + ", postID=" + postID + ", likedBy=" + likedBy + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, likedBy, postID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Likes other = (Likes) obj;
		return ID == other.ID && Objects.equals(likedBy, other.likedBy) && postID == other.postID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public String getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(String likedBy) {
		this.likedBy = likedBy;
	}
}
