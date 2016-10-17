package com.buxiaoyu.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class test_database {
	public static void main(String args[]) {
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
			Connection connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Bookbase","root","123456");
			 

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book");
			 
			while (rs.next()) {
				System.out.println(rs.getString("ISBN"));
			}
		}
		catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
}

