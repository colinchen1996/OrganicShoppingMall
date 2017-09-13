package org.lanqiao.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.util.DBPool;

public class BaseDao<T> {
	Class<T> clazz; // ����һ����������ֽ����ļ�����
	protected Connection conn = null; // ����һ�����ݿ����Ӷ���
	protected PreparedStatement stat = null; // ����һ��Ԥ�������
	protected ResultSet rs = null; // ����һ�����������

	@SuppressWarnings("unchecked")
	public BaseDao() { // ��ø÷�������ֽ����ļ�����
		clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void closeAll(ResultSet rs, PreparedStatement stat, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<T> query(String sql, Object[] param) {
		List<T> list = new ArrayList<T>();
		try {
			conn = DBPool.getConnect(); // �������
			stat = conn.prepareStatement(sql); // Ԥ�����ѯ���
			if (param != null) {
				for (int i = 0; i < param.length; i++) { // ѭ��������еĲ�����ֵ
					stat.setObject(i + 1, param[i]);
				}
			}
			rs = stat.executeQuery(); // ִ�в�ѯ���

			ResultSetMetaData rsmd = rs.getMetaData(); // ��ñ��е�����
			int columnCount = rsmd.getColumnCount(); // ��ñ��е�����
			while (rs.next()) { // ѭ�����������
				T t = clazz.newInstance(); // ��÷������ʵ��������
				for (int i = 0; i < columnCount; i++) { // ѭ���������е����Ը�ֵ
					Field field = clazz.getDeclaredField(rsmd.getColumnName(i + 1)); // ��÷������������=���ݿ��е�����
					field.setAccessible(true); // ��ø����Ե�Ȩ��
					field.set(t, rs.getObject(i + 1)); // ������Ը�ֵ
				}
				list.add(t); // ����װ�����ݵĶ������list
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn); // �ر���������
		}
		return list;
	}

	public boolean update(String sql, Object[] param) {
		try {
			conn = DBPool.getConnect(); // �������
			stat = conn.prepareStatement(sql); // Ԥ�����ѯ���
			for (int i = 0; i < param.length; i++) {
				if (param[i] instanceof Integer) {
					stat.setInt(i + 1, (Integer) param[i]);
				} else {
					stat.setString(i + 1, (String) param[i]);
				}
			}
			stat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn);
		}
		return false;
	}
	
	
	public boolean queryIfExits(String sql, Object[] param) { //��ѯ�Ƿ����
		try {
			conn = DBPool.getConnect(); // �������
			stat = conn.prepareStatement(sql); // Ԥ�����ѯ���
			for (int i = 0; i < param.length; i++) {
				if (param[i] instanceof Integer) {
					stat.setInt(i + 1, (Integer) param[i]);
				} else {
					stat.setString(i + 1, (String) param[i]);
				}
			}
			rs = stat.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn);
		}
		return true;
	}

	public int queryProductTypeId(String sql, String param) {
		try {
			conn = DBPool.getConnect(); // �������
			stat = conn.prepareStatement(sql); // Ԥ�����ѯ���
			stat.setString(1, param);
			rs = stat.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn);
		}
		return -1;
	}
	
	public int queryCount(String sql){
		try {
			conn = DBPool.getConnect(); // �������
			stat = conn.prepareStatement(sql); // Ԥ�����ѯ���
			rs = stat.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, stat, conn);
		}
		return -1;
	}

}
