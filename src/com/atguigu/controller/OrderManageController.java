package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.Order;
import com.atguigu.service.OrderService;

/**
 * Servlet implementation class OrderManageServlet
 */
@RequestMapping("/orderManage")
@Controller
public class OrderManageController{
       
	@Autowired
	private OrderService orderService;
	
	/**
	 * 跳转到订单列表页面
	 */
	@RequestMapping("/list")
	public String list(Model model){
		List<Order> list = orderService.queryOrderList();
		model.addAttribute("list", list);
		// 转发到订单列表页面
		return "order/order";
	}
	
	/**
	 * 发货
	 */
	@RequestMapping("/deliver")
	public String deliver(@RequestParam("orderId")String orderId, @RequestHeader("referer")String referer){
		orderService.updateOrder(orderId, 1);
		// 跳回到列表页面
		return "redirect:"+referer;
	}

}
