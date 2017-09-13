package org.lanqiao.entity;

public class ProductItem {
	private int productId;
	private String productType;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public ProductItem(int productId, String productType) {
		super();
		this.productId = productId;
		this.productType = productType;
	}
	public ProductItem() {
		super();
		// TODO Auto-generated constructor stub
	}
}
