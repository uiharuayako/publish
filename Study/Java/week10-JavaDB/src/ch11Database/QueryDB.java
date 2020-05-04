package ch11Database;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//QueryDB.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class QueryDB {
	public static void main(String[] args) {
		JFrame myframe = new QueryFrame();
		myframe.show();
	}
}

class QueryFrame extends JFrame implements ActionListener {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private JLabel conditionlabel = new JLabel("请填写下列查询条件", SwingConstants.CENTER);
	private JLabel namelabel = new JLabel("姓名", SwingConstants.RIGHT);
	private JTextField name = new JTextField(5);
	private JLabel sexlabel = new JLabel("性别", SwingConstants.RIGHT);
	private JTextField sex = new JTextField(3);
	private JLabel agelabel = new JLabel("年龄", SwingConstants.RIGHT);
	private JTextField age = new JTextField(3);
	private JLabel majorlabel = new JLabel("专业", SwingConstants.RIGHT);
	private JTextField major = new JTextField(8);
	private JButton commit = new JButton("提交");
	private JLabel resultlabel = new JLabel("查询结果", SwingConstants.CENTER);
	private JTextArea resultarea = new JTextArea(10, 28);
	private String command = null;

	public QueryFrame() {
		setTitle("数据库查询");
		setSize(700, 500);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getContentPane().setLayout(new GridBagLayout());
		// 以下的代码功能是使用GridBagLayout布局编排Swing组件
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 100;
		gbc.weighty = 100;
		add(conditionlabel, gbc, 3, 0, 5, 1);
		add(name, gbc, 1, 1, 2, 1);
		add(sex, gbc, 4, 1, 1, 1);
		add(age, gbc, 6, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		add(major, gbc, 8, 1, 3, 1);
		gbc.anchor = GridBagConstraints.CENTER;
		add(commit, gbc, 5, 2, 1, 1);
		add(resultlabel, gbc, 4, 3, 3, 1);
		JScrollPane scrollpane = new JScrollPane(resultarea);
		add(scrollpane, gbc, 3, 4, 5, 3);
		resultarea.setEditable(false);
		resultarea.setLineWrap(true);
		gbc.anchor = GridBagConstraints.EAST;
		add(namelabel, gbc, 0, 1, 1, 1);
		add(sexlabel, gbc, 3, 1, 1, 1);
		add(agelabel, gbc, 5, 1, 1, 1);
		gbc.anchor = GridBagConstraints.CENTER;
		add(majorlabel, gbc, 7, 1, 1, 1);
		commit.addActionListener(this);
		commit.setNextFocusableComponent(name);

		try {
//			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//			con = DriverManager.getConnection("jdbc:oracle:thin:@211.11.166.219:1521:orcl", "scott", "tiger");

			// 本例改为 Mysql
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root",
					"MySQLpassword" +
                            "");

			stmt = con.createStatement();
		} catch (Exception ex) {
			resultarea.append(ex.getMessage() + "\n");
			return;
		}
	}

	public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		getContentPane().add(c, gbc);
	}

	// 处理提交按钮的点击事件
	public void actionPerformed(ActionEvent evt) {
		try {
			String namevalue = name.getText().trim();
			String sexvalue = sex.getText().trim();
			String agevalue = age.getText().trim();
			String majorvalue = major.getText().trim();
			String sname, ssex, sage, smajor;
			// 支持模糊搜索，并且当查询条件中某项输入为空时，
			// 认为此项取值不受限制
			sname = " LIKE '%" + namevalue + "%'";
			ssex = " LIKE'%" + sexvalue + "%'";
			if (agevalue.equals(""))
				sage = " BETWEEN 15 AND 35";
			else
				sage = "=" + agevalue;
			smajor = " LIKE'%" + majorvalue + "%'";
			command = "SELECT * FROM Student WHERE name" + sname + " AND sex" + ssex + " AND age" + sage + " AND major"
					+ smajor;

			// 先将含有查询条件的串进行编码转换，再把转换后的串送去执行，
			// 否则中文字段会出现乱码

			// command = new String(command.getBytes(), "ISO-8859-1");
			// 数据库是utf-8 编码， 直接用默认形式即可
			command = new String(command.getBytes());
			rs = stmt.executeQuery(command);
			resultarea.setText("");
			if (!rs.next())
				resultarea.setText("找不到符合此条件的记录");
			else {
				do {
					String rename = rs.getString("name").trim();
					String resex = rs.getString("sex").trim();
					String reage = rs.getString("age").trim();
					String remajor = rs.getString("major").trim();

					// 从数据库中取出后，必须经过编码转换才能正确显示汉字
					// 已设置为 utf8 ，不用转码
//					rename = new String(rename.getBytes("ISO-8859-1"), "GB2312");
//					resex = new String(resex.getBytes("ISO-8859-1"), "GB2312");
//					reage = new String(reage.getBytes("ISO-8859-1"), "GB2312");
//					remajor = new String(remajor.getBytes("ISO-8859-1"), "GB2312");

					resultarea.append(rename);

					// 计算姓名的长度，在姓名后输出相应的空格，以使后面的
					// "性别"等列对齐
					int length = rename.length();
					for (int i = 1; i <= (16 - 2 * length); i++)
						resultarea.append("  ");
					resultarea.append(resex + "              ");
					resultarea.append(reage + "                  ");
					resultarea.append(remajor + "\n");

				} while (rs.next());
			}
		} catch (Exception ex) {
			// 若有异常，则打印出异常信息
			resultarea.append(ex.getMessage() + "\n");
		}
	}
}
