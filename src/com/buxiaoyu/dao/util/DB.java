package com.buxiaoyu.dao.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB 
{
	private static Connection connection;
	private static Statement statement;
	private static final String DATABASE_NAME = "bookbase";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";

	static
	{
		String datanm = "com.mysql.jdbc.Driver";
		try
		{
			Class.forName(datanm);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException
	{
		if(connection == null || connection.isClosed() )
		{
			String url = "jdbc:mysql://10.13.144.141:10448/"+ DATABASE_NAME + "?autoReconnect=true&failOverReadOnly=false";
			//String url = "jdbc:mysql://localhost:3306/" + DATABASE_NAME+ "?autoReconnect=true&failOverReadOnly=false";
			try
			{
				connection = DriverManager.getConnection(url,USERNAME,PASSWORD);
			}
			catch(Exception e)
			{ 
				System.out.println("Hello!");
				e.printStackTrace();
			}
		}
		return connection;
	}


	public static Statement getStatement() throws SQLException
	{
		if(statement == null || statement.isClosed() ) statement = DB.getConnection().createStatement();
		return statement;
	}


	public static void release(ResultSet res,Statement stmt,Connection conn)
	{
		if(res != null)
		{
			try
			{
				res.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		

		if(conn != null)
		{
			try
			{
				conn.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if(stmt != null)
		{
			try
			{
				stmt.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void release(Object o)
	{
		try
		{
			if(o instanceof ResultSet)
			{
				((ResultSet)o).close();
			}else if (o instanceof Statement)
			{
				((Statement) o).close();
			} else if (o instanceof Connection)
			{
				((Connection) o).close();
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
