package com.itheima.datasource.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.itheima.jdbc.utils.JDBCUtils;

/**
 * 连接池的测试类:
 * @author admin
 *
 */
public class DataSourceDemo1 {

	@Test
	/**
	 * 自定义连接池的测试方法:
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource dataSource = new MyDataSource();
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
			JDBCUtils.release(rs, stmt, conn);
			/*try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
			// 归还连接:
			// dataSource.addBack(conn);
		}
	}
}
