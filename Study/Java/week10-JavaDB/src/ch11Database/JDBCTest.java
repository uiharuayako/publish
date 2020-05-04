package ch11Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement prepstmt = null;

//		final String DB_DRIVER = "com.mysql.jdbc.Driver";   //  老驱动过时废弃
		final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		// 修改示例程序，添加时区
		final String DB_URL = "jdbc:mysql://localhost:3306/javacourse?autoReconnect=true&useUnicode=true&serverTimezone=UTC";
		final String DB_USER = "javacourse";
		final String DB_PASSWORD = "javacourse";

		try {
			// 加载驱动程序类
			Class.forName(DB_DRIVER);
			// 建立数据库连接
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// 要执行的sql语句
			String sql = "select book_id,book_name,author from books limit 1000";
			// 创建PreparedStatement对象
			prepstmt = conn.prepareStatement(sql);
			/// 执行查询
			ResultSet result = prepstmt.executeQuery();
			// 处理查询结果
			while (result.next()) {
				int book_id = result.getInt(1);
				String book_name = result.getString(2);
				String author = result.getString(3);
				System.out.println("book_id=" + book_id + ";book_name=" + book_name + ";author=" + author);
			}
			// 关闭连接
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
