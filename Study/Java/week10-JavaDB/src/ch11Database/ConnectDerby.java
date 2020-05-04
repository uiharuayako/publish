package ch11Database;

//ConnectDerby.java
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDerby {
	public static void main(String args[]) {
		String url = "jdbc:derby:MyDbTest;create=true";
		// 连接的是Derby下的MyDbTest数据库
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			// 加载JDBC驱动，其中org.apache.derby.jdbc.EmbeddedDriver
//是这个驱动的类名
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			System.out.println("驱动程序已装载");
			System.out.println("即将连接数据库");
		} catch (Exception ex) {
			// 如果无法加载驱动，则给出错误信息
			System.out.println("Can not load Jdbc Driver:" + ex.getMessage());
			return;
		}
		try {
			con = DriverManager.getConnection(url);
			DatabaseMetaData dmd = con.getMetaData();
			System.out.println("已连接到数据库:" + dmd.getURL());
			// 显示连接的数据库
			System.out.println("所用的驱动程序是:" + dmd.getDriverName());
			// 显示所用驱动程序
		} catch (SQLException ex) {
			System.out.println("failed to Connect");
			// 若无法连接，给出错误信息
			System.out.println(ex.getMessage());
		}
	}
}
