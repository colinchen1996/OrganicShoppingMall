package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.Product;

public interface ProductDao {
	public List<Product> selectId(Product product);
	public List<Product> selectType(Product product);
	public Product selectHistory(int id2);
	public List<Product> selectFruit();
	public List<Product> selectFresh();
	public List<Product> selectVegetables();
	public List<Product> selectAll();
}
