package com.buxiaoyu.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class build_data {
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
			PreparedStatement Statement=connect.prepareStatement("INSERT INTO Book VALUES(?,?,?,?,?,?)");
			for(int i=0;i<num;i++)        
			{
				Statement.setString(1,""+i);
				Statement.setString(2,"软工练习第"+i+"册");
				Statement.setString(3,""+i);
				Statement.setString(4,"哈尔滨工业大学"); 
				Statement.setString(5,"2016-10-1");
				Statement.setString(6,"88"); 
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