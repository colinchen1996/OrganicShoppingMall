package org.lanqiao.entity;

public class User {
	private int userId;
	private String userName; 
	private String userPass;
	private String email;
	private String phoneNumber;
	private String userStatus;
	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public User(int userId, String userName, String userPass, String email,
			String phoneNumber, String userStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userStatus = userStatus;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
}
