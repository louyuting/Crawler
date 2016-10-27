package com.crawler.Dao;

import com.crawler.config.Config;
import com.crawler.utils.LogUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB Connection管理
 */
public class JDBCUtil {
	private static Connection conn;
	/***
     * 获取数据库连接对象
	 * @return
     */
	public static Connection getConnection(){
		//获取数据库连接
		try {
			if(conn == null || conn.isClosed()){
                conn = createConnection();
            }
            else{
                return conn;
            }
		} catch (SQLException e) {
		}
		return conn;
	}

	/**
     * 关闭连接对象
	 */
	public static void close(){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
     * 内部调用的函数
	 * @return
     */
	private static Connection createConnection(){
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver") ;//加载驱动
            //建立mysql的连接
            conn = DriverManager.getConnection(Config.DB_Url,Config.DB_Username,Config.DB_Password);
		}
		catch(MySQLSyntaxErrorException e){
			LogUtil.log_debug("数据库不存在..请先手动创建创建数据库:");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e1){
            LogUtil.log_debug("ClassNotFoundException"+ e1.toString());
		}
		catch(SQLException e2){
            LogUtil.log_debug("SQLException"+e2.toString());
		}
		return conn;
	}

    /**
     * 测试
     * @param args
     * @throws Exception
     */
	@org.junit.Test
	public static void main(String [] args) throws Exception{
		getConnection();
		close();
	}
}