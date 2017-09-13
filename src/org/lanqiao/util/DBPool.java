package org.lanqiao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPool {
	private DBPool(){}  //˽�л����췽����ȷ�����಻�ܱ��ⲿʵ��������
	private static DataSource dataSource=null;  //����һ�����ݿ����ӳض���
	public static synchronized Connection getConnect() throws SQLException{
		if(dataSource==null){ //�ж����ݿ����ӳ��Ƿ�Ϊ��
			dataSource = new ComboPooledDataSource("mysqlConnection"); //Ϊ���򴴽�һ�����ݿ����ӳ�
		}
		return dataSource.getConnection(); //�������ӳ��е�һ������
	} 
	
}
