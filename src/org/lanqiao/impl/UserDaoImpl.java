package org.lanqiao.impl;

import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.BaseDao;
import org.lanqiao.dao.UserDao;
import org.lanqiao.entity.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
   User user = new User();
   public List<User> select(String sql,Object[] param){
		return query(sql, param);
	}
	
	public boolean update2(String sql,Object[] param) {
		
		return update(sql, param);
	}
	@Override
	public boolean register(User user) {
		boolean ch=update("insert into userinfo(userName,userPass,email,phoneNumber) values (?,?,?,?)", new Object[]{user.getUserName(),user.getUserPass(),user.getEmail(),user.getPhoneNumber()});
		if(ch){
		return true;
		}
		else {
			return false;
		}
	}
	public List<User> login(User user){
		List<User> list=query("select * from userinfo where userName=? and userPass=?",new Object[]{user.getUserName(),user.getUserPass()});
		if(list.size()>0){
			return list;
			}
		return null;
     } 
	public boolean registerCheck(User user){
		List<User> list =new ArrayList<>();
		if(user.getUserName()!=null){
		    list=query("select * from userinfo where userName=?",new Object[]{user.getUserName()});
		}else if(user.getEmail()!=null){
			list=query("select * from userinfo where email=?",new Object[]{user.getEmail()});
		}else{
			list=query("select * from userinfo where phoneNumber=?",new Object[]{user.getPhoneNumber()});
		}
		if(list.size()>0){
			return true;
			}
		return false;
     }
	
	public List<User> selectAllUser() {  //查询Product表中所有信息
		return query("select * from userinfo where userName!='admin'",null);
	}
	
	public int selectCount(String sql){
		return queryCount(sql);
	}
	public boolean lockUser(String sql,Object[] param){
		return update(sql, param);
	}
}
