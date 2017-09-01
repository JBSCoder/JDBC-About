package com.itheima.c3p0.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import com.itheima.jdbc.utils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0连接池的使用：
 * @author admin
 *
 */
public class C3P0Demo1 {

	/**
	 * 手动设置参数
	 */
	@Test
	public void demo1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// System.err.println("Hello");
		try{
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql:///web_07");
			dataSource.setUser("root");
			dataSource.setPassword("123");
			
			conn = dataSource.getConnection();
			String sql ="select * from category";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("cid")+"   "+rs.getString("cname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs,stmt, conn);
		}
	}
	
	/**
	 * 配置文件设置参数
	 */
	@Test
	public void demo2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// System.err.println("Hello");
		try{
			ComboPooledDataSource dataSource = new ComboPooledDataSource("oracle1111");
			
			conn = dataSource.getConnection();
			String sql ="select * from category";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("cid")+"   "+rs.getString("cname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs,stmt, conn);
		}
	}
}
