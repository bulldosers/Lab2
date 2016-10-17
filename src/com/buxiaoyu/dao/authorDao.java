package com.buxiaoyu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.buxiaoyu.dao.util.DB;
import com.buxiaoyu.model.Author;

public class authorDao
{
	private static authorDao authorDao = null;
	private static final String AUTHOR_TABLE_NAME = "author";
	private int rp;
	private String fff;
	private double esp;
	private Statement stmt;

	private authorDao(){}

	public static authorDao getauthorDao()
	{
		if(authorDao == null)
		{
			authorDao = new authorDao();
		}
		return authorDao;
	}

	private static Author ORM(ResultSet res) throws SQLException
	{
		return new Author(res.getInt("authorID"),res.getString("name"),res.getInt("age"),res.getString("country"));
	}


	public ArrayList<Author> getAuthorsByName(String name) throws SQLException
	{
		String sql = "SELECT * FROM " + AUTHOR_TABLE_NAME + " WHERE name = '" + name +"'";
		System.out.println("update!");
		stmt = DB.getStatement();
		ResultSet res = stmt.executeQuery(sql);
		ArrayList<Author> authors = new ArrayList<Author>();    
		while(res.next())
		{
			Author author = ORM(res);
			authors.add(author);
		}

		System.out.println("update!");
		return authors;
	}

	public void addAuthor(Author author) throws SQLException
	{
		stmt = DB.getStatement();
		System.out.println("update!");
		String sql = "INSERT INTO " + AUTHOR_TABLE_NAME 
				+ " values(" + author.getAuthorID() +",'" + author.getName() 
				+ "'," + author.getAge() + ",'" + author.getCountry()
				+ "')"; 
		stmt.executeUpdate(sql);
	}
	public double getEsp() {
		return esp;
	}

	public void setEsp(double esp) {
		this.esp = esp;
	}

	public String getFff() {
		return fff;
	}

	public void setFff(String fff) {
		this.fff = fff;
	}
	public Author getAuthorByAuthorID(int authorID) throws SQLException
	{
		stmt = DB.getStatement();
		String sql = "SELECT * FROM " + AUTHOR_TABLE_NAME + " WHERE authorID = " + authorID + "";
		System.out.println("update!");
		ResultSet res = stmt.executeQuery(sql);
		if(res.next())
			return ORM(res);
		return null;
	}


	public void updateAuthor(Author newAuthor) throws SQLException 
	{
		stmt = DB.getStatement();
		String sql = "UPDATE " + AUTHOR_TABLE_NAME  + " SET "
				+ "name = '" + newAuthor.getName() + "',"
				+ "age = " + newAuthor.getAge() + ","
				+ "country = '" + newAuthor.getCountry() + "' WHERE authorID = " + newAuthor.getAuthorID();
		System.out.println("更新作者语句" + sql);
		stmt.executeUpdate(sql);
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}


}
