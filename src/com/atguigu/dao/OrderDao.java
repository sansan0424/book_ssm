package com.atguigu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.Order;

public interface OrderDao {

	/**
	 * 插入订单
	 */
	int insertOrder(Order order);
	
	/**
	 * 修改订单状态
	 */
	int updateStatus(Order order);

	/**
	 * 查询所有订单列表<br/>
	 */
	List<Order> getOrderList(@Param("userId")Integer userId);

}
