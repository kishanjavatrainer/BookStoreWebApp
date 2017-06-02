package com.infotech.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import com.infotech.bookstore.model.Book;

public interface BookDAO {

	boolean insertBook(Book book) throws SQLException;
	List<Book> listAllBooks() throws SQLException;
	boolean deleteBook(Book book) throws SQLException;
	boolean updateBook(Book book) throws SQLException;
	Book getBook(int id) throws SQLException;
}