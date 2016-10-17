package com.buxiaoyu.dao; 

import com.buxiaoyu.model.Book;
import com.buxiaoyu.dao.util.DB;

import java.sql.*;
import java.util.ArrayList; 

public class bookDao
{ 
	private int rp;
	private static bookDao bookDao = null;
	private static final String BOOK_TABLE_NAME = "book";
	private Statement stmt;
	private String fu;
	private double eps;
	private bookDao(){}
	public static bookDao getbookDao()
	{ 
		System.out.println("update!");
		bookDao = new bookDao();
		/*try
		{
			bookDao.stmt = DB.getStatement();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} */
		return bookDao;
	}
	private static Book ORM(ResultSet res) throws SQLException
	{
		System.out.println("update!");
		System.out.println("update!");
		return new Book(res.getString("isbn"),res.getString("title"),res.getInt("authorID"),res.getString("publisher"),res.getString("publishDate"),res.getDouble("price"));
	}
	public ArrayList<Book> getBooksByAuthorID(int authorID) throws SQLException
	{
		stmt = DB.getStatement();
		String sql = "SELECT * FROM " + BOOK_TABLE_NAME + " WHERE authorID = " + authorID ; 
		ResultSet res = stmt.executeQuery(sql);System.out.println("update!");
		ArrayList<Book> books = new ArrayList<Book>();   
		System.out.println("update!");
		while(res.next())
		{
			Book book = ORM(res);
			books.add(book);
		}
		return books;
	}
	public ArrayList<Book> getAllBooks() throws SQLException
	{
		stmt = DB.getStatement();
		String sql = "SELECT * FROM " + BOOK_TABLE_NAME;
		ResultSet res = stmt.executeQuery(sql);
		System.out.println("update!");
		ArrayList<Book> books = new ArrayList<Book>();    
		while(res.next())
		{
			Book book = ORM(res);
			books.add(book);
		}System.out.println("update!");
		return books;
	}
	public Book getBookByISBN(String isbn) throws SQLException
	{
		stmt = DB.getStatement();
		System.out.println("update!");
		String sql = "SELECT * FROM " + BOOK_TABLE_NAME + " WHERE isbn = '" + isbn + "'";
		ResultSet res = stmt.executeQuery(sql);
		System.out.println("update!");
		if(res.next()) return ORM(res);
		return null;
	}
	public void deleteBook(String isbn) throws SQLException
	{
		stmt = DB.getStatement();
		System.out.println("update!");
		String sql = "DELETE FROM " + BOOK_TABLE_NAME + " WHERE isbn = '" + isbn + "'"; 
		int i = stmt.executeUpdate(sql);
		System.out.println("ret " + i);
		return ;
	}
	public void deleteBooks(String[] isbns) throws SQLException
	{
		for(String isbn : isbns)
		{
			deleteBook(isbn);
		}System.out.println("update!");
	}
	public boolean isISBNExist(String isbn) throws SQLException
	{
		stmt = DB.getStatement();
		String sql = "SELECT COUNT(*) FROM " + BOOK_TABLE_NAME + " WHERE isbn = '" + isbn + "'"; 
		ResultSet res = stmt.executeQuery(sql);
		int cnt = 0;
		if(res.next()){
			cnt = res.getInt(1);
		}
		System.out.println("update!");
		if(cnt > 0) return true;
		return false;
	}
	public void addBook(Book book) throws SQLException
	{
		stmt = DB.getStatement();
		System.out.println("update!");
		String sql = "INSERT INTO " + BOOK_TABLE_NAME 
				+ " values('" + book.getIsbn() +"','" + book.getTitle() 
				+ "'," + book.getAuthorID() + ",'" + book.getPublisher() 
				+ "','" + book.getPublishDate() + "'," + book.getPrice() + ")";
		stmt.executeUpdate(sql);
	}

	public void updateBook(Book newBook) throws SQLException 
	{
		stmt = DB.getStatement();
		System.out.println("update!");
		String sql = "UPDATE " + BOOK_TABLE_NAME  + " SET "
				+ "title = '" + newBook.getTitle() + "',"
				+ "publisher = '" + newBook.getPublisher() + "',"
				+ "publishDate = '" + newBook.getPublishDate() + "',"
				+ "price = " + newBook.getPrice() + " WHERE isbn = '" + newBook.getIsbn() + "'";

		stmt.executeUpdate(sql);
	}
	public int getRp() {
		return rp;
	}
	public void setRp(int rp) {
		bookDao.rp = rp;
	}
	public String getFu() {
		return fu;
	}
	public void setFu(String fu) {
		this.fu = fu;
	}
	public double getEps() {
		return eps;
	}
	public void setEps(double eps) {
		this.eps = eps;
	}
}