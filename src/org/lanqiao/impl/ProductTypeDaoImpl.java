package org.lanqiao.impl;

import java.util.List;

import org.lanqiao.dao.BaseDao;
import org.lanqiao.entity.ProductType;

public class ProductTypeDaoImpl extends BaseDao<ProductType> {
	public boolean selectProductType(String sql,Object[] param){  //��ѯProductType�����Ƿ����ĳ����¼
		return queryIfExits(sql, param);
	}
	
	public boolean insertProductType(String sql,Object[] param){   //��ProductType���в���ĳ����¼
		return update(sql,param);
	}
	
	public List<ProductType> selectAllProductType() {  //��ѯProductType����������Ϣ
		return query("select * from productypeinfo",null);
	}
	
	public boolean deleteProductType(String sql,Object[] param){  //ɾ��ProductType���м�¼
		return update(sql, param);
	}
}
