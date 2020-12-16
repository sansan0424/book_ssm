package com.atguigu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.service.UserService;

@Controller
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao; 
	
	@Override
	public User login(User user) {
		// 根据用户名密码查询用户信息
		return userDao.getUser(user);
	}

	@Override
	public boolean regist(User user) {
		// 向用户表中插入用户
		int result = userDao.insertUser(user);
		return result>0;
	}

	@Override
	public boolean userIsExist(User user) {
		// 根据用户名查询用户信息
		user = userDao.getUser(user);
		return (user!=null);
	}

}
