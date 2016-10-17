package com.buxiaoyu.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class build_Author {
	public static void main(String args[])
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");     
			//Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!");
		}
		catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection( "jdbc:mysql://localhost:3306/Bookbase","root","123456");

			int num = 20;
			PreparedStatement Statement=connect.prepareStatement("INSERT INTO Author VALUES(?,?,?,?)");
			for(int i=0;i<num;i++)        
			{
				Statement.setString(1,""+i);
				Statement.setString(2,"测试"+i);
				Statement.setString(3,"20");
				Statement.setString(4,"中国"); 
				Statement.executeUpdate();
			}
			System.out.println("Success insert data into database!");
			// } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// System.out.println("An error has occurred:"+e.toString());
			//  e.printStackTrace();
		}catch(SQLException e)
		{
		}
	}
}