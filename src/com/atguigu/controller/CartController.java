package com.atguigu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.service.BookService;
import com.atguigu.utils.WebUtils;

/**
 * 购物车
 * @author Administrator
 *
 */
@RequestMapping("/cart")
@Controller
public class CartController{
       
	@Autowired
	private BookService bookService;
	/**
	 * 加入购物车
	 */
	@RequestMapping("/add")
	public String add(Book book, @RequestHeader("referer")String referer, 
			HttpServletRequest request){
		// 从session中获取购物车
		Cart cart = WebUtils.getCart(request);
		cart.addBook2Cart(book);
		// 最后加购商品名称
		book = bookService.queryBook(book);
		request.getSession().setAttribute("lastAddCart", book.getTitle());
		// 跳回列表页面
		return "redirect:"+referer;
	}

	/**
	 * 从购物车中删除购物项
	 */
	@RequestMapping("/delete")
	public String delete(Book book, @RequestHeader("referer")String referer, 
			HttpServletRequest request){
		// 从session中获取购物车
		Cart cart = WebUtils.getCart(request);
		cart.deleteItem(book);
		// 跳回购物车页面
		return "redirect:"+referer;
	}
	
	/**
	 * 更改购物车中购物项数量
	 */
	@RequestMapping("/update")
	public String update(Book book, @RequestParam("count")int count,
			@RequestHeader("referer")String referer, HttpServletRequest request){
		// 从session中获取购物车
		Cart cart = WebUtils.getCart(request);
		cart.updateCount(book, count);
		// 跳回购物车页面
		return "redirect:"+referer;
	}
	
	/**
	 * 清空购物车
	 */
	@RequestMapping("/clear")
	public String clear(@RequestHeader("referer")String referer, HttpServletRequest request){
		// 从session中获取购物车
		Cart cart = WebUtils.getCart(request);
		cart.clear();
		request.getSession().removeAttribute("lastAddCart");
		// 跳回购物车页面
		return "redirect:"+referer;
	}
	
}
