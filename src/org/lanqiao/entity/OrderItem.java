package org.lanqiao.entity;

public class OrderItem {
	private int orderId;
	private int productId;
	private int productCount;
	public OrderItem(int orderId, int productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}
	public OrderItem() {
		super();
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public OrderItem(int orderId, int productId, int productCount) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productCount = productCount;
	}
	
}
