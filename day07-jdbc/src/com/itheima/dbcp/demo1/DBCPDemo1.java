package com.itheima.dbcp.demo1;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import com.itheima.jdbc.utils.JDBCUtils;

/**
 * DBCP连接池的使用：
 * @author admin
 *
 */
public class DBCPDemo1 {

	@Test
	/**
	 * 手动方式:
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///web_07");
		dataSource.setUsername("root");
		dataSource.setPassword("123");
		try{
			// 获得连接:
			conn = dataSource.getConnection();
			// 编写SQL:
			String sql = "select * from category";
			// 预编译SQL:
			stmt = conn.prepareStatement(sql);
			// 执行SQL:
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
	
	@Test
	/**
	 * 配置文件方式：
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Properties properties = new Properties();
		
		try{
			properties.load(new FileInputStream("src/dbcpconfig.properties"));
			DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
			// 获得连接:
			conn = dataSource.getConnection();
			// 编写SQL:
			String sql = "select * from category";
			// 预编译SQL:
			stmt = conn.prepareStatement(sql);
			// 执行SQL:
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
