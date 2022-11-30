package com.revature.models;

import java.util.Objects;

public class PwordModel {

	private String oldPass;
	private String newPass;
	public PwordModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PwordModel(String oldPass, String newPass) {
		super();
		this.oldPass = oldPass;
		this.newPass = newPass;
	}
	@Override
	public String toString() {
		return "PwordModel [oldPass=" + oldPass + ", newPass=" + newPass + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(newPass, oldPass);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PwordModel other = (PwordModel) obj;
		return Objects.equals(newPass, other.newPass) && Objects.equals(oldPass, other.oldPass);
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	
	
}