package org.lanqiao.impl;

import java.util.List;

import org.lanqiao.dao.BaseDao;
import org.lanqiao.dao.ProductImgInfoDao;
import org.lanqiao.entity.ProductImgInfo;

public class ProductImgInfoDaoImpl extends BaseDao<ProductImgInfo> implements ProductImgInfoDao {

	
	
	public List<ProductImgInfo> selectImgInfo(ProductImgInfo productImgInfo) {
		return query("select * from productimginfo where productId = ? ",
				new Object[]{productImgInfo.getProductId()});
		
	}
}
