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
	Class<T> clazz; // 声明一个泛型类的字节码文件对象
	protected Connection conn = null; // 声明一个数据库连接对象
	protected PreparedStatement stat = null; // 声明一个预编译对象
	protected ResultSet rs = null; // 声明一个结果集对象

	@SuppressWarnings("unchecked")
	public BaseDao() { // 获得该泛型类的字节码文件对象
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
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
			if (param != null) {
				for (int i = 0; i < param.length; i++) { // 循环给语句中的参数赋值
					stat.setObject(i + 1, param[i]);
				}
			}
			rs = stat.executeQuery(); // 执行查询语句

			ResultSetMetaData rsmd = rs.getMetaData(); // 获得表中的列名
			int columnCount = rsmd.getColumnCount(); // 获得表中的列数
			while (rs.next()) { // 循环遍历结果集
				T t = clazz.newInstance(); // 获得泛型类的实例化对象
				for (int i = 0; i < columnCount; i++) { // 循环向泛型类中的属性赋值
					Field field = clazz.getDeclaredField(rsmd.getColumnName(i + 1)); // 获得泛型类的属性名=数据库中的列名
					field.setAccessible(true); // 获得该属性的权限
					field.set(t, rs.getObject(i + 1)); // 向该属性赋值
				}
				list.add(t); // 将封装好数据的对象加入list
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
			closeAll(rs, stat, conn); // 关闭所有连接
		}
		return list;
	}

	public boolean update(String sql, Object[] param) {
		try {
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
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
	
	
	public boolean queryIfExits(String sql, Object[] param) { //查询是否存在
		try {
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
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
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
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
			conn = DBPool.getConnect(); // 获得连接
			stat = conn.prepareStatement(sql); // 预编译查询语句
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
