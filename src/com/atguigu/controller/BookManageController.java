package com.atguigu.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.Book;
import com.atguigu.service.BookService;
import com.github.pagehelper.PageInfo;

/**
 * 图书管理
 * @author Administrator
 *
 */
@RequestMapping("/bookManage")
@Controller
public class BookManageController{
       
	@Autowired
	private BookService bookService;	
	
	/**
	 * 添加或修改图书提交
	 */
	@RequestMapping("/save")
	public String save(Book book) throws ServletException, IOException {
		// 根据参数中是否有id判断是添加保存还是修改保存
		if (book.getId() != null && book.getId() != 0) {
			// 修改
			bookService.updateBook(book);
		}else {
			// 添加
			bookService.addBook(book);
		}
		// 跳转到图书列表
		return "redirect:/bookManage/page";
	}
	
	/**
	 * 删除图书
	 */
	@RequestMapping("/delete")
	public String delete(Book book, @RequestHeader("referer")String referer) throws ServletException, IOException {
		bookService.deleteBook(book);
		// 跳回图书列表
		return "redirect:"+referer;
	}

	/**
	 * 跳转修改图书页面
	 */
	@RequestMapping("/edit")
	public String edit(Book book, Model model) throws ServletException, IOException {
		// 查询图书信息
		book = bookService.queryBook(book);
		model.addAttribute("book", book);
		// 跳转修改页面
		return "manager/book_edit";
	}
	
	/**
	 * 跳转图书列表
	 * 带分页
	 */
	@RequestMapping("/page")
	public String page(@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="4")Integer pageSize,
			Model model){
		// 查询图书分页信息
		PageInfo<Book> page = bookService.queryBookList(pageNo, pageSize, null, null);
		model.addAttribute("page", page);
		model.addAttribute("url", "bookManage/page?");
		// 跳转到图书列表
		return "manager/book_manager";
	}
	
}
