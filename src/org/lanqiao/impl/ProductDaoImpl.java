package org.lanqiao.impl;

import java.util.List;

import org.lanqiao.dao.BaseDao;
import org.lanqiao.dao.ProductDao;
import org.lanqiao.entity.Product;


public class ProductDaoImpl extends BaseDao<Product> implements ProductDao  {
	
	public List<Product> select(String sql,Object[] param){
		return query(sql, param);
	}
	public List<Product> selectId(Product product) {
		return query("select * from productinfo where productId = ?",
				new Object[] { product.getProductId()});
	}
	public List<Product> selectType(Product product) {
		return query("select * from productinfo where productTypeId =(select productTypeId from productinfo where productId = ? ) ",
				new Object[] { product.getProductId()});
	}
	public Product selectHistory(int id2) {
		return (Product)query("select * from productinfo where productId = ?",
				new Object[] { id2 }).get(0);
	}
	public List<Product> selectFruit() {
		return query("select * from productinfo where productTypeId = 1",null);
	}
	public List<Product> selectFresh() {
		return query("select * from productinfo where productTypeId = 3",null);
	}
	public List<Product> selectVegetables() {
		return query("select * from productinfo where productTypeId = 2",null);
	}
	public List<Product> selectAll() {
		return query("select * from productinfo ORDER BY  RAND() LIMIT 8",null);
	}
	
	
	public boolean selectProduct(String sql,Object[] param){  //��ѯProduct�����Ƿ����ĳ����¼
		return queryIfExits(sql, param);
	}
	
	public List<Product> selectProductById(String sql,Object[] param){
		return query(sql,param);
	}
	public boolean insertProduct(String sql,Object[] param){   //��Product���в���ĳ����¼
		return update(sql,param);
	}
	
	public List<Product> selectProductByPage(int page) {  //��ѯProduct����������Ϣ
		return query("select * from productinfo limit ?,5",new Object[]{page});
	}
	
	public int selectCount(String sql){ //��ѯ������������
		return queryCount(sql);
	}
	
	public boolean deleteProduct(String sql,Object[] param){  //ɾ��Product���м�¼
		return update(sql, param);
	}
	
	public int selectProductTypeId(String sql,String param){
		return queryProductTypeId(sql,param);
	}
	
	public boolean selectProductIfExist(String sql,Object[] param){
		return queryIfExits(sql,param);
	}
	
	public boolean changeInventory(String sql,Object[] param){
		return update(sql, param);
	}
	public boolean changePrice(String sql,Object[] param){
		return update(sql, param);
	}
	
}
