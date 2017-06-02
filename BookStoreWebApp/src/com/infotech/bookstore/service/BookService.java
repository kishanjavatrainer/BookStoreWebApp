package com.infotech.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.infotech.bookstore.model.Book;

public interface BookService {

	boolean createBook(Book book) throws SQLException;
	List<Book> getAllBooksInfo() throws SQLException;
	boolean deleteBookInfo(Book book) throws SQLException;
	boolean updateBookInfo(Book book) throws SQLException;
	Book getBookById(int id) throws SQLException;
}
