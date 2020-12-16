package com.atguigu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.Book;
import com.atguigu.service.BookService;
import com.github.pagehelper.PageInfo;

/**
 * 图书前台
 * @author Administrator
 *
 */
@RequestMapping("/bookClient")
@Controller
public class BookClientController{
       
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/page")
	public String page(@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo, 
			@RequestParam(value="pageSize",required=false,defaultValue="4")Integer pageSize, 
			@RequestParam(value="min",required=false)String min, 
			@RequestParam(value="max",required=false)String max,
			Model model){
		// 查询图书分页信息
		PageInfo<Book> page = bookService.queryBookList(pageNo, pageSize, min, max);
		model.addAttribute("page", page);
		model.addAttribute("url", "bookClient/page?min="+(min==null?"":min)+"&max="+(max==null?"":max)+"&");
		// 跳转到首页图书列表
		return "forward:/book_list.jsp";
	}

}
