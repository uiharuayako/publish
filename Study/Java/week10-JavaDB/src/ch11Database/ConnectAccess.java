package ch11Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectAccess {
	public static void main(String args[]) {
		String url = "jdbc:odbc:Connection";
		// Connection是已建立的ODBC数据源名称
		try {
			try {
				// 加载JDBC-ODBC Bridge驱动程序
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				// sun.jdbc.odbc.JdbcOdbcDriver是Jdbc-Odbc桥在JDK中的类名，
				// 要注意的是，在不同的开发环境中，驱动程序的类可能包含在不同的包里
			} catch (java.lang.ClassNotFoundException e) {
				System.out.println("Can not load Jdbc-Odbc Bridge  Driver");
				System.err.print("ClassNotFoundException: ");
				System.err.println(e.getMessage());
			}
			Connection con = DriverManager.getConnection(url);
			// 使用指定的url连接数据库
			DatabaseMetaData dmd = con.getMetaData();
			System.out.println("连接的数据库: " + dmd.getURL());
			System.out.println("驱动程序: " + dmd.getDriverName());
		} catch (SQLException ex) {
			System.out.println("SQLException: ");
			while (ex != null) {
				System.out.println("Message: " + ex.getMessage());
				// 打印出错信息
				ex = ex.getNextException();
			}
		}
	}
}
