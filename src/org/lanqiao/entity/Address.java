package org.lanqiao.entity;

public class Address {
	private int addressId;
	private int userId;
	private String province;
	private String city;
	private String area;
	private String street;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Address(int addressId, int userId, String province, String city, String area, String street) {
		super();
		this.addressId = addressId;
		this.userId = userId;
		this.province = province;
		this.city = city;
		this.area = area;
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
}
