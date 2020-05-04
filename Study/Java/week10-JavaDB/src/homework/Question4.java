package homework;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question4 extends Application {
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String command = null;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //整体来看，是个vbox
        VBox root=new VBox();
        Label lab1=new Label("请填写下列查询条件");
        lab1.setFont(new Font(20));
        HBox inputHB=new HBox();
        TextField name=new TextField();
        TextField sex=new TextField();
        TextField age=new TextField();
        TextField major=new TextField();
        inputHB.getChildren().addAll(Question3.titleAndText("姓名",name),Question3.titleAndText("性别",sex),Question3.titleAndText("年龄",age),Question3.titleAndText("专业",major));
        Button commit=new Button("提交");
        Label lab2=new Label("查询结果");
        lab2.setFont(new Font(20));
        TextArea resultarea=new TextArea();
        root.getChildren().addAll(lab1,inputHB,commit,lab2,resultarea);
        Scene myScene=new Scene(root);
        primaryStage.setScene(myScene);
        primaryStage.setTitle("数据库查询");
        primaryStage.show();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root","MySQLpassword");
            stmt = con.createStatement();
        } catch (Exception ex) {
            resultarea.appendText(ex.getMessage() + "\n");
        }
        commit.setOnAction(event -> {
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

                        resultarea.appendText(rename);

                        // 计算姓名的长度，在姓名后输出相应的空格，以使后面的
                        // "性别"等列对齐
                        int length = rename.length();
                        for (int i = 1; i <= (16 - 2 * length); i++)
                            resultarea.appendText("  ");
                        resultarea.appendText(resex + "              ");
                        resultarea.appendText(reage + "                  ");
                        resultarea.appendText(remajor + "\n");

                    } while (rs.next());
                }
            } catch (Exception ex) {
                // 若有异常，则打印出异常信息
                resultarea.appendText(ex.getMessage() + "\n");
            }
        });
    }
}
