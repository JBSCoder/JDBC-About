package com.itheima.datasource.demo1;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.itheima.jdbc.utils.JDBCUtils;

/**
 * 自定义连接池：
 * @author admin
 *
 */
public class MyDataSource implements DataSource{
	// 创建一个List集合用于存放多个连接对象.
	private List<Connection> list = new ArrayList<Connection>();
	// 在程序开始的时候，初始化几个连接,将连接存放到list中.
	public MyDataSource() {
		// 初始化3个连接:
		for(int i=1;i<=3;i++){
			Connection conn = JDBCUtils.getConnection();
			list.add(conn);
		}
	}
	
	@Override
	// 获得连接的方法：
	public Connection getConnection() throws SQLException {
		if(list.size() <= 0){
			for(int i=1;i<=3;i++){
				Connection conn = JDBCUtils.getConnection();
				list.add(conn);
			}	
		}
		Connection conn = list.remove(0);
		MyConnection myConn = new MyConnection(conn, list);
		return myConn;
	}
	
	// 归还连接的方法：
/*	public void addBack(Connection conn){
		list.add(conn);
	}*/
	
	
	
	
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
