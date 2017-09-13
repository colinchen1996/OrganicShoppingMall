package org.lanqiao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.BaseDao;
import org.lanqiao.dao.OrderDao;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.Product;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBPool;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
	
	public List<Order> select(String sql,Object[] param) {
		
		return query(sql, param);
	}
	
	public List<Order> selectOrders(String sql) {
		return query(sql, null);
	}

	public List<Product> selectOrderProducts(String sql, int id) {
		List<Product> productList = new ArrayList<Product>();
		try {
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
			stat.setInt(1, id);
			rs = stat.executeQuery();
			
			while(rs.next()){
				Product product = new Product();
				product.setProductName(rs.getString(1));
				product.setDefaultImg(rs.getString(2));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn);
		}
		return productList;
	}
	public User selectOrderUser(String sql, int id) {
		User user = new User();
		try {
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
			stat.setInt(1, id);
			rs = stat.executeQuery();
			
			while(rs.next()){
				user.setUserName(rs.getString(1));
				user.setPhoneNumber(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn);
		}
		return user;
	}

	public String selectOrderAddress(String sql, int id) {
		String address="";
		try {
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
			stat.setInt(1, id);
			rs = stat.executeQuery();
			
			if(rs.next()){
				address=rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn);
		}
		return address;
	}
	
	public String selectOrderStatus(String sql, int id) {
		String orderStatus="";
		try {
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
			stat.setInt(1, id);
			rs = stat.executeQuery();
			
			if(rs.next()){
				orderStatus=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn);
		}
		return orderStatus;
	}
}
