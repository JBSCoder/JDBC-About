package com.itheima.jdbc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC的工具类：
 * @author admin
 *
 */
public class JDBCUtils2 {
	private static final ComboPooledDataSource DATA_SOURCE =new ComboPooledDataSource();
	/**
	 * 获得连接的方法
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DATA_SOURCE.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static DataSource getDataSource(){
		return DATA_SOURCE;
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
