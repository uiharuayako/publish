package ch11Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//DataDefine.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataDefine {
	public static void main(String args[]) {

		// 表示连接的是本机上1433端口、名为DataDefine的数据库
		// 其中1433是SQL Server默认的端口
//		String url = " jdbc:microsoft:sqlserver://weblogic:1433 /DataDefine";

		// 本例使用 mysql 数据库
		String url = "jdbc:mysql://localhost:3306/sys?autoReconnect=true&useUnicode=true&serverTimezone=UTC";

		Connection con = null;
		Statement sm = null;
		String command = null;
		try {
//			DriverManager.registerDriver(new com.microsoft.jdbc.sqlserver.SQLServerDriver());

			// 本例使用 Mysql 驱动
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			System.out.println("驱动程序已加载");
			con = DriverManager.getConnection(url, "root", "uiharuayako2001");
			// javacourse是用户名，密码：javacourse
			System.out.println("OK: 已成功连接到数据库");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return;
		}
		try {
			// 建表的 SQL 语句
			command = "CREATE TABLE 个人资料(姓名 char(20),性别 char(2),年龄 int, 籍贯 char(30))";
			sm = con.createStatement();
			sm.executeUpdate(command);
			System.out.println("OK: 表已建立");

			// "个人资料.dat"是事先准备好的文件，用于往新建立的表中填充数据
			// 从读取本地文件数据，添加到数据库
			BufferedReader in = new BufferedReader(new FileReader("个人资料.dat"));

			String line = null;
			while ((line = in.readLine()) != null) {
				command = "INSERT INTO 个人资料 VALUES(" + line + ")";
				// 利用"个人资料.dat"文件中的内容填充基本表
				sm.executeUpdate(command);
			}
			System.out.println("表已填充好");
			command = "CREATE VIEW 个人资料视图(姓名,籍贯) AS SELECT 姓名,籍贯 FROM 个人资料"; // 建立视图
			sm.executeUpdate(command);
			System.out.println("视图已建立");
		} catch (SQLException ex) {
			System.out.println("SQLException:");
			while (ex != null) {
				System.err.println(ex.getMessage());
				ex = ex.getNextException();
			}
		} catch (IOException ex) {
			System.out.println("IOException:");
			System.err.println(ex.getMessage());
		}
	}
}
