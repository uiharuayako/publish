package ch11Database;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//UpdateDB.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class UpdateDB {
	public static void main(String[] args) {
		JFrame myframe = new UpdateFrame();
		myframe.show();
	}
}

class UpdateFrame extends JFrame {
	public UpdateFrame() {
		setTitle("数据库更新");
		setSize(500, 100);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container contentpane = getContentPane();
		contentpane.add(new UpdatePanel());
	}
}

class UpdatePanel extends JPanel implements ActionListener {
	private Connection con = null;
	private Statement stmt = null;
	private JTextField sqlcommand;
	private JButton submit;
	private JTextArea resultarea;
	private ResultSet rs = null;

	public UpdatePanel() {
		sqlcommand = new JTextField(30);
		resultarea = new JTextArea(5, 30);
		resultarea.setEditable(false);
		submit = new JButton("提交");
		add(new JLabel("SQL更新语句"));
		add(sqlcommand);
		add(submit);
		add(new JLabel("当前数据表NameAge中的记录"));
		JScrollPane scrollpane = new JScrollPane(resultarea);
		add(scrollpane);
		submit.addActionListener(this);
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			// 与Mysql下的UpdateDB数据库建立连接
			con = DriverManager.getConnection("jdbc:mysql://localhost/UpdateDB?serverTimezone=UTC", "javacourse",
					"javacourse");
			System.out.println("已连接到数据库");
			stmt = con.createStatement();
			showTable();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return;
		}
	}

	public void showTable() { // 显示当前数据表中的记录
		try {
			rs = stmt.executeQuery("SELECT * FROM NameAge");
			resultarea.setText("姓名");
			for (int i = 1; i <= (30 - 2 * "姓名".length()); i++)
				resultarea.append("  ");
			resultarea.append("年龄" + "\n");
			while (rs.next()) {
				String sname = rs.getString("name"); // 取姓名
				resultarea.append(sname); // 把取出的姓名添加到文本区
				int length = sname.length();
				// 按照不同姓名的长度，输出相应个数的空格以使后面的"年龄"列能够对齐
				for (int i = 1; i <= (30 - 2 * length); i++)
					resultarea.append(" ");
				resultarea.append(rs.getString("age") + "\n"); // 输出年龄
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			String command = sqlcommand.getText();
			command = new String(command.getBytes());
			stmt.execute(command);
			showTable(); // 每次更新数据库后立即把数据表的最新状态显示出来
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}

/*
 * 演示用的语句
 * 
 * INSERT INTO nameage VALUES('遥11',20); INSERT INTO nameage VALUES('感12',18);
 * 
 * delete from nameage where name = '遥11';
 * 
 * update nameage set age =21 where age = 18;
 * 
 */
