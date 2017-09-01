package com.itheima.metadata.demo1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import com.itheima.jdbc.utils.JDBCUtils2;

/**
 * JDBC中的元数据的测试类
 * @author admin
 *
 */
public class MetaDataDemo1 {

	@Test
	/**
	 * 数据库元数据
	 */
	public void demo1(){
		Connection conn = null;
		conn = JDBCUtils2.getConnection();
		// 获得数据库元数据:
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			System.out.println("获得驱动名称:"+metaData.getDriverName());
			System.out.println("获得驱动URL:"+metaData.getURL());
			System.out.println("获得用户名:"+metaData.getUserName());
			
			// 获得表中的主键：
			ResultSet rs = metaData.getPrimaryKeys(null, null, "category");
			if(rs.next()){
				String name = rs.getString("COLUMN_NAME");
				System.out.println(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * 参数元数据：
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = JDBCUtils2.getConnection();
			String sql = "update category set cname = ? where cid = ?";
			stmt = conn.prepareStatement(sql);
			ParameterMetaData metaData = stmt.getParameterMetaData();
			int count = metaData.getParameterCount();
			System.out.println(count);
		}catch(Exception e){
			
		}
	}
	
	@Test
	/**
	 * 结果集元数据：
	 */
	public void demo3(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JDBCUtils2.getConnection();
			String sql = "select * from category";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			for(int i = 1;i<=count ;i++){
				String name = metaData.getColumnName(i);
				
				String type = metaData.getColumnTypeName(i);
				System.out.println(name+type);
			}
		}catch(Exception e){
			
		}
	}
}
