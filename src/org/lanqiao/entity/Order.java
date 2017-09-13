package org.lanqiao.entity;

public class Order {
	private int orderId;
	private String orderDate;
	private float orderPrice;
	private int orderStatusId;
	
	private int userId;
	private int addressId;
	private String orderStatus;
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public Order(int orderId, String orderDate, float orderPrice, int orderStatusId, int userId, int addressId,
			String orderStatus) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderPrice = orderPrice;
		this.orderStatusId = orderStatusId;
		this.userId = userId;
		this.addressId = addressId;
		this.orderStatus = orderStatus;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Order(int orderId, String orderDate, float orderPrice, int orderStatusId, int userId, int addressId) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderPrice = orderPrice;
		this.orderStatusId = orderStatusId;
		this.userId = userId;
		this.addressId = addressId;
	}
	public int getOrderStatusId() {
		return orderStatusId;
	}
	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public Order() {
		super();
	}

	
}
