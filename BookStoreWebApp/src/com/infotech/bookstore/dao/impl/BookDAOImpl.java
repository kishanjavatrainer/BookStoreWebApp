package com.infotech.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.infotech.bookstore.dao.BookDAO;
import com.infotech.bookstore.model.Book;
import com.infotech.bookstore.util.DBUtil;

/**
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author kishan
 */
public class BookDAOImpl implements BookDAO {
	
	@Override
	public boolean insertBook(Book book) throws SQLException {
		String sql = "INSERT INTO book_table (title, author, price) VALUES (?, ?, ?)";
		boolean rowInserted = false;
		
		try(Connection connection = DBUtil.getDataSource().getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setFloat(3, book.getPrice());
			
			rowInserted = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowInserted;
	}
	
	@Override
	public List<Book> listAllBooks() throws SQLException {
		List<Book> listBook = new ArrayList<>();
		
		String sql = "SELECT * FROM book_table";
		
		try(Connection connection = DBUtil.getDataSource().getConnection();
			Statement statement =connection .createStatement();
			ResultSet resultSet = statement.executeQuery(sql)) {
			
			while (resultSet.next()) {
				int id = resultSet.getInt("book_id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				float price = resultSet.getFloat("price");
				
				Book book = new Book(id, title, author, price);
				listBook.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBook;
	}
	
	@Override
	public boolean deleteBook(Book book) throws SQLException {
		String sql = "DELETE FROM book_table where book_id = ?";
		boolean rowDeleted = false;
		try(Connection connection = DBUtil.getDataSource().getConnection();
			PreparedStatement ps =connection.prepareStatement(sql)) {
			
			ps.setInt(1, book.getId());
			
			rowDeleted = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowDeleted;		
	}
	
	@Override
	public boolean updateBook(Book book) throws SQLException {
		String sql = "UPDATE book_table SET title = ?, author = ?, price = ? WHERE book_id = ?";
		boolean rowUpdated = false;
		try(Connection connection = DBUtil.getDataSource().getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setFloat(3, book.getPrice());
			ps.setInt(4, book.getId());
			
			rowUpdated = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;		
	}
	
	@Override
	public Book getBook(int id) throws SQLException {
		Book book = null;
		String sql = "SELECT * FROM book_table WHERE book_id = ?";
		try(Connection connection = DBUtil.getDataSource().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				float price = resultSet.getFloat("price");
				
				book = new Book(id, title, author, price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
}
