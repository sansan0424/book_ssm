package com.atguigu.service;

import com.atguigu.bean.Book;
import com.github.pagehelper.PageInfo;

public interface BookService {

	/**
	 * 查询图书信息
	 * @return
	 */
	Book queryBook(Book book);

	/**
	 *	新增图书 
	 */
	void addBook(Book book);
	
	/**
	 * 修改图书
	 * @param book
	 */
	void updateBook(Book book);
	
	/**
	 * 删除图书
	 * @param book
	 */
	void deleteBook(Book book);
	
	/**
	 * 查询图书分页信息
	 */
	PageInfo<Book> queryBookList(Integer pageNo, Integer pageSize, String minPrice, String maxPrice);
	
	/**
	 * 修改图书的库存和销量
	 */
	void updateSalesAndStock(Book book);
}
