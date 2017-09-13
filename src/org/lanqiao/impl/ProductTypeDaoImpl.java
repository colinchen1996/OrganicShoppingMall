package org.lanqiao.impl;

import java.util.List;

import org.lanqiao.dao.BaseDao;
import org.lanqiao.entity.ProductType;

public class ProductTypeDaoImpl extends BaseDao<ProductType> {
	public boolean selectProductType(String sql,Object[] param){  //查询ProductType表中是否存在某条记录
		return queryIfExits(sql, param);
	}
	
	public boolean insertProductType(String sql,Object[] param){   //向ProductType表中插入某条记录
		return update(sql,param);
	}
	
	public List<ProductType> selectAllProductType() {  //查询ProductType表中所有信息
		return query("select * from productypeinfo",null);
	}
	
	public boolean deleteProductType(String sql,Object[] param){  //删除ProductType表中记录
		return update(sql, param);
	}
}
