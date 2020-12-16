package com.atguigu.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;

/**
 * 封装参数工具类
 * @author Administrator
 *
 */
public class WebUtils {

	/**
	 * 从session中获取购物车
	 * 如果没有的话创建购物车放到session中
	 */
	public static Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		return cart;
	}
	
	/**
	 * 从session中获取用户信息
	 * @return
	 */
	public static User getUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute("user");
	}
	
}
