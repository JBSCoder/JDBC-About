package com.itheima.jdbc.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC的工具类：
 * @author admin
 *
 */
public class JDBCUtils {
	private static final String driverClass;
	private static final String url;
	private static final String username;
	private static final String password;
	

	static {
		Properties properties = null;
		// 读取属性文件：使用Java中Properties的对象.
		try{
			InputStream is = new FileInputStream("src/jdbc.properties");
			properties = new Properties();
			properties.load(is);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		driverClass = properties.getProperty("driverClass");
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
	}
	
	/**
	 * 注册驱动的方法
	 */
	public static void loadDriver(){
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得连接的方法
	 */
	public static Connection getConnection(){
		
		Connection conn = null;
		try {
			loadDriver();
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 释放资源的方法
	 */
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        // 垃圾回收尽快回收对象.
	        rs = null;
	    }
		if (stmt != null) {
	        try {
	        	stmt.close();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        // 垃圾回收尽快回收对象.
	        stmt = null;
	    }
		if (conn != null) {
	        try {
	        	conn.close();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        // 垃圾回收尽快回收对象.
	        conn = null;
	    }
	}
	
	public static void release(Statement stmt,Connection conn){
		if (stmt != null) {
	        try {
	        	stmt.close();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        // 垃圾回收尽快回收对象.
	        stmt = null;
	    }
		if (conn != null) {
	        try {
	        	conn.close();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        // 垃圾回收尽快回收对象.
	        conn = null;
	    }
	}
}
