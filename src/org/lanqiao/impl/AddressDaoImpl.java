package org.lanqiao.impl;

import java.util.List;

import org.lanqiao.dao.AddressDao;
import org.lanqiao.dao.BaseDao;
import org.lanqiao.entity.Address;

public class AddressDaoImpl extends BaseDao<Address> implements AddressDao {
	public List<Address> selectAddress(String sql, Object[] param) {
		return query(sql, param);
	}

//	public List<Address> deleteAddress(String sql, Object[] param) {
//		return query(sql, param);
//	}

	public boolean deleteAddress(String sql, Object[] param) {

		return update(sql, param);
	}
	
	public boolean updateAddress(String sql, Object[] param) {

		return update(sql, param);
	}
}
