package com.balian.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtills {
	
	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");// 注册驱动

			// 建立连接的语句，注意用的test数据库，用户为root，密码为空，这个根据实际调整
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crunii", "root", "guan");

			return conn;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
}
