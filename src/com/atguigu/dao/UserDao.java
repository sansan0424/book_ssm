package com.atguigu.dao;

import com.atguigu.bean.User;

/**
 * 用户dao接口
 * @author Administrator
 *
 */
public interface UserDao {

	/**
	 * 根据用户名和密码查询用户信息
	 * @return
	 */
	User getUser(User user);
	
	/**
	 * 插入用户信息
	 * @return
	 */
	int insertUser(User user);

}
