package org.lanqiao.impl;

import java.util.List;

import org.lanqiao.dao.BaseDao;
import org.lanqiao.dao.CartDao;
import org.lanqiao.entity.Product;

public class CartDaoImpl extends BaseDao<Product> implements CartDao {
	public List<Product> getProductById(int productId){
		return super.query("SELECT * FROM productinfo WHERE productId =" + productId , null);
	}
}

