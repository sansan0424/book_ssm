package com.atguigu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public List<OrderItem> queryOrderItemList(String orderId) {
		return orderItemDao.getOrderItemList(orderId);
	}

	@Override
	public void saveOrderItemBatch(List<OrderItem> list) {
		orderItemDao.insertBatch(list);
	}

}
