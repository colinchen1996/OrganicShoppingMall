package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.entity.User;

public interface UserDao {
public boolean register(User user);
public List login(User user);
public boolean registerCheck(User user);
}
