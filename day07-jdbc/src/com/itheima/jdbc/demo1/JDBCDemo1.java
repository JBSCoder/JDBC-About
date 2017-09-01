package com.itheima.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * JDBC的测试类：
 * @author admin
 *
 */
public class JDBCDemo1 {
	
	/**
	 * 使用JDBC完成查询的功能：
	 */
	@Test
	public void demo1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获得连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_07", "root", "123");
			// 3.创建执行SQL的对象.
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("cid")+"    "+rs.getString("cname"));
			}
			// 4.释放资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// rs.close();
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
		        // 垃圾回收尽快回收对象.
		        rs = null;
		    }
			if (pstmt != null) {
		        try {
		        	pstmt.close();
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
		        // 垃圾回收尽快回收对象.
		        pstmt = null;
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
}
