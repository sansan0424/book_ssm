package com.atguigu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;

	@Override
	public Book queryBook(Book book) {
		return bookDao.getBook(book);
	}

	@Override
	public void addBook(Book book) {
		bookDao.insertBook(book);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	@Override
	public void deleteBook(Book book) {
		bookDao.deleteBook(book);
	}
	
	@Override
	public PageInfo<Book> queryBookList(Integer pageNo, Integer pageSize, String minPrice, String maxPrice){
		
		Double min = 0.0;
		try {
			min = Double.parseDouble(minPrice);
		} catch (Exception e) {
			
		}
		
		Double max = Double.MAX_VALUE;
		try {
			max = Double.parseDouble(maxPrice);
		} catch (Exception e) {
			
		}
		
		// 分页插件
		PageHelper.startPage(pageNo, pageSize);
		List<Book> list = bookDao.getBookList(min, max);
	
		//传入要连续显示多少页
		PageInfo<Book> info = new PageInfo<>(list, 5);
		
		return info;
	}

	@Override
	public void updateSalesAndStock(Book book) {
		bookDao.updateSalesAndStock(book);
	}

}
