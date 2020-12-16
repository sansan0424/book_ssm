package com.atguigu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class OrderServlet
 */
@RequestMapping("/orderClient")
@Controller
public class OrderClientController{
       
	@Autowired
	private OrderService orderService;
	
	/**
	 * 结算，生成订单编号
	 */
	@RequestMapping("/checkout")
	public String checkout(HttpServletRequest request, Model model){
		Cart cart = WebUtils.getCart(request);
		User user = WebUtils.getUser(request);
		String orderId = orderService.saveOrder(cart, user.getId());
		model.addAttribute("orderId", orderId);
		// 清空购物车
		cart.clear();
		// 转发到结算成功页面
		return "cart/checkout";
	}

	/**
	 * 跳转到订单列表
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		User user = WebUtils.getUser(request);
		List<Order> list = orderService.queryOrderListByUserId(user.getId());
		model.addAttribute("list", list);
		// 转发到订单列表页面
		return "order/order";
	}
	
	/**
	 * 确认收货
	 */
	@RequestMapping("/receive")
	public String receive(@RequestParam("orderId")String orderId, @RequestHeader("referer")String referer){
		orderService.updateOrder(orderId, 2);
		// 跳回到列表页面
		return "redirect:"+referer;
	}
	
}
