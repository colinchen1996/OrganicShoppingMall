package org.lanqiao.entity;

public class Cart {
	private int cartId;
	private String productName;
	private String productImg;
	private int userId;
	private int productQuantity;
	private int productPrice;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public Cart(int cartId, String productName, String productImg, int userId, int productQuantity, int productPrice) {
		super();
		this.cartId = cartId;
		this.productName = productName;
		this.productImg = productImg;
		this.userId = userId;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

}
