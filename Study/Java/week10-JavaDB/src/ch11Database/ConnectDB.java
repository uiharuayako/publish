package ch11Database;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//ConnectDB.java
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class ConnectDB {
	public static void main(String[] args) {
		JFrame myframe = new ConnectFrame();
		myframe.show();
	}
}

class ConnectFrame extends JFrame implements ActionListener {
	private Connection con = null;
	private Statement stmt = null;
	private JTextField url = new JTextField(10);
	private JTextField driver = new JTextField(10);
	private JTextField username = new JTextField(10);
	private JPasswordField password = new JPasswordField(10);
	private JTextArea resultarea = new JTextArea(6, 30);
	private JButton submit = new JButton("连接");
	private JLabel statelabel = new JLabel("连接数据库的状态如下", SwingConstants.LEFT);
	private JLabel urllabel = new JLabel("数据库URL", SwingConstants.LEFT);
	private JLabel driverlabel = new JLabel("驱动程序", SwingConstants.LEFT);
	private JLabel userlabel = new JLabel("用户名", SwingConstants.LEFT);
	private JLabel pwdlabel = new JLabel("密码", SwingConstants.LEFT);

	public ConnectFrame() {
		setTitle("数据库连接");
		setSize(420, 300);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		resultarea.setEditable(false);
		resultarea.setLineWrap(true); // 打开自动换行功能
		Container c = getContentPane();
		c.setLayout(null);
		c.add(urllabel);
		urllabel.setBounds(10, 10, 60, 22);
		c.add(url);
		url.setBounds(80, 10, 240, 22);
		c.add(driverlabel);
		driverlabel.setBounds(10, 40, 60, 22);
		c.add(driver);
		driver.setBounds(80, 40, 240, 22);
		c.add(userlabel);
		userlabel.setBounds(10, 70, 60, 22);
		c.add(username);
		username.setBounds(80, 70, 240, 22);
		c.add(pwdlabel);
		pwdlabel.setBounds(10, 100, 60, 22);
		c.add(password);
		password.setBounds(80, 100, 240, 22);
		c.add(submit);
		submit.setBounds(335, 60, 60, 25);
		c.add(statelabel);
		statelabel.setBounds(140, 135, 150, 22);
		JScrollPane scrollpane = new JScrollPane(resultarea);
		c.add(scrollpane);
		scrollpane.setBounds(80, 160, 240, 100);
		submit.addActionListener(this);
		driver.setNextFocusableComponent(username);
		password.setNextFocusableComponent(submit);
		submit.setNextFocusableComponent(url);
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			resultarea.setText("");
			Class.forName(driver.getText().trim());
			resultarea.append("驱动程序已加载，即将连接数据库" + "\n");
			con = DriverManager.getConnection(url.getText().trim(), username.getText().trim(),
					password.getText().trim());
			DatabaseMetaData dmd = con.getMetaData();
			resultarea.append("已连接到数据库:" + dmd.getURL() + "\n");
			resultarea.append("所用的驱动程序:" + dmd.getDriverName() + "\n");
		} catch (Exception ex) {
			resultarea.append(ex.getMessage());
		}
	}
}
