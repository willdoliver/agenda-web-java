package com.notebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDAO {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java-jdbc", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
}
