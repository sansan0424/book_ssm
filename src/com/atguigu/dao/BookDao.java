package com.atguigu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.Book;

public interface BookDao {
	
	/**
	 * 查询一本图书信息
	 * @return
	 */
	Book getBook(Book book);
	
	/**
	 * 插入图书
	 * @return
	 */
	int insertBook(Book book);
	
	/**
	 * 修改图书
	 * @return
	 */
	int updateBook(Book book);
	
	/**
	 * 删除图书信息
	 * @return
	 */
	int deleteBook(Book book);
	
	/**
	 * 查询分页列表
	 * 带价格区间条件
	 */
	List<Book> getBookList(@Param("min") Double minPrice, @Param("max") Double maxPrice);

	/**
	 * 修改图书的库存和销量
	 */
	int updateSalesAndStock(Book book);
	
}
