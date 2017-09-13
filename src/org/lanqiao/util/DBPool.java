package org.lanqiao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPool {
	private DBPool(){}  //私有化构造方法，确保该类不能被外部实例化对象
	private static DataSource dataSource=null;  //声明一个数据库连接池对象
	public static synchronized Connection getConnect() throws SQLException{
		if(dataSource==null){ //判断数据库连接池是否为空
			dataSource = new ComboPooledDataSource("mysqlConnection"); //为空则创建一个数据库连接池
		}
		return dataSource.getConnection(); //返回连接池中的一个连接
	} 
	
}
