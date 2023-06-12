package com.notebook.bean;

import java.util.Date;
import java.util.List;

public class Contact {
	private int id;
	private int userId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String relativeDegree;
	private Boolean isDeleted;
	private List<Phone> phoneList;
	private Date createdAt;
	private Date updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
//		DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
//		this.dateOfBirth = df.parse(dateOfBirth);
		this.dateOfBirth = dateOfBirth;
	}

	public String getRelativeDegree() {
		return relativeDegree;
	}

	public void setRelativeDegree(String relativeDegree) {
		this.relativeDegree = relativeDegree;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
