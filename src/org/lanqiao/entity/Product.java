package org.lanqiao.entity;

public class Product {
	public int productId;
	public String productName;
	public float productPrice;
	public int productInventory;
	public String productDetail;
	public int productTypeId;
	public String defaultImg;
	public Product() {
		super();
	}

	public Product(int productId, String productName, float productPrice, int productInventory, String productDetail,
			int productTypeId, String defaultImg) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productInventory = productInventory;
		this.productDetail = productDetail;
		this.productTypeId = productTypeId;
		this.defaultImg = defaultImg;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public int getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(int productInventory) {
		this.productInventory = productInventory;
	}
	public String getDefaultImg() {
		return defaultImg;
	}
	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
	
}
