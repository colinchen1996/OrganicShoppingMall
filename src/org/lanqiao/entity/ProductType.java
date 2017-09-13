package org.lanqiao.entity;

public class ProductType {
	private int productTypeId; 
	private String productType;
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public ProductType(int productTypeId, String productType) {
		super();
		this.productTypeId = productTypeId;
		this.productType = productType;
	}
	public ProductType() {
		super();
	} 
}
