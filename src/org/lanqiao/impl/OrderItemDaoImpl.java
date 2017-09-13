package org.lanqiao.impl;

import java.util.List;

import org.lanqiao.dao.BaseDao;
import org.lanqiao.dao.OrderItemDao;
import org.lanqiao.entity.OrderItem;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
	public List<OrderItem> select(String sql, Object[] param) {

		return query(sql, param);
	}
}
