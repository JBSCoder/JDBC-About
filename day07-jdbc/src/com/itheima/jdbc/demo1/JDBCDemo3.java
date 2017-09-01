package com.itheima.jdbc.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.itheima.jdbc.utils.JDBCUtils2;

/**
 * 使用JDBCUtils2工具类完成CURD的操作.C3P0的改造的工具类
 * @author admin
 *
 */
public class JDBCDemo3 {

	@Test
	public void demo1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JDBCUtils2.getConnection();
			String sql = "select * from category";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("cid")+"   "+rs.getString("cname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils2.release(rs, stmt, conn);
		}
	}
}
