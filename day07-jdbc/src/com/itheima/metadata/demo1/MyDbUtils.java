package com.itheima.metadata.demo1;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

import com.itheima.jdbc.utils.JDBCUtils2;

public class MyDbUtils {
	public static void update(String sql,Object... objects){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			// 获得连接:
			conn = JDBCUtils2.getConnection();
			// 编写SQL：
			// 获得执行SQL的对象：
			stmt = conn.prepareStatement(sql);
			// 设置参数：
			// 获得参数元数据:
			ParameterMetaData metaData = stmt.getParameterMetaData();
			int count = metaData.getParameterCount();
			for(int i=1;i<=count ;i++){
				stmt.setObject(i, objects[i-1]);
			}
			// 执行SQL:
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils2.release(stmt, conn);
		}
	}
	
	public static void main(String[] args) {
		// 添加记录:
		// MyDbUtils.update("insert into category values (null,?)", "烟酒糖茶");
		// MyDbUtils.update("update category set cname = ? where cid = ?", "家居饰品",5);
		MyDbUtils.update("delete from category where cid = ?", 5);
	}
}
