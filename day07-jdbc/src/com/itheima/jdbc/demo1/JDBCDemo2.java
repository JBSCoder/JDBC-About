package com.itheima.jdbc.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.itheima.jdbc.utils.JDBCUtils;

/**
 * 使用工具类完成分类的CRUD的操作
 * @author admin
 *
 */
public class JDBCDemo2 {

	@Test
	/**
	 * 查询分类的操作
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL：
			String sql = "select * from category";
			// 创建执行SQL的对象:
			stmt = conn.prepareStatement(sql);
			// 执行SQL:
			rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("cid")+"    "+rs.getString("cname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, stmt, conn);
		}
	}
	
	/**
	 * 完成添加分类的操作
	 */
	@Test
	public void demo2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL：
			String sql = "insert into category values (null,?)";
			// 获得执行SQL的对象：
			stmt = conn.prepareStatement(sql);
			// 设置参数：
			stmt.setString(1, "鞋靴箱包");
			// 执行SQL:
			int i = stmt.executeUpdate();
			if(i>0){
				System.out.println("添加成功！！！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(stmt, conn);
		}
	}
	
	/**
	 * 修改分类
	 */
	@Test
	public void demo3(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL：
			String sql = "update category set cname = ? where cid = ?";
			// 创建执行SQL语句对象:
			stmt = conn.prepareStatement(sql);
			// 设置参数:
			stmt.setString(1, "烟酒糖茶");
			stmt.setInt(2, 4);
			// 执行sql:
			int i = stmt.executeUpdate();
			if(i>0){
				System.out.println("修改成功！！！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(stmt, conn);
		}
	}
	
	/**
	 * 删除分类
	 */
	@Test
	public void demo4(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL：
			String sql = "delete from category where cid = ?";
			// 创建执行SQL语句对象:
			stmt = conn.prepareStatement(sql);
			// 设置参数:
			stmt.setInt(1, 4);
			// 执行sql:
			int i = stmt.executeUpdate();
			if(i>0){
				System.out.println("删除成功！！！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(stmt, conn);
		}
	}
	
}
