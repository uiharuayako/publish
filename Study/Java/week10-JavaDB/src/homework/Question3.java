package homework;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Question3 extends Application {
    private Connection con=null;
    private Statement stm=null;
        @Override // Override the start method in the Application class
        public void start(Stage primaryStage) {
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC",
                        "root",
                        "MySQLpassword");
                System.out.println("连接成功");
                stm = con.createStatement();
            } catch (Exception ex) {
                System.err.println(ex);
            }
            primaryStage.setTitle("书籍信息管理 by Uiharu");
            BorderPane root=new BorderPane();
            //主体部分
            HBox allHB=new HBox();
            //添加部分
            VBox addVBox=new VBox();
            Label title1=new Label("添加一本书籍");
            title1.setFont(new Font(25));
            TextField nameTF=new TextField();
            TextField authorTF=new TextField();
            TextField publishTF=new TextField();
            TextField timeTF=new TextField();
            timeTF.setText("xxxx-xx-xx");
            TextField ISBNTF=new TextField();
            Button addBT=new Button("添加");
            addBT.setOnAction(event -> {
                try {
                    stm.executeUpdate("USE BookInfo");
                    stm.executeUpdate("INSERT INTO book VALUES('" + nameTF.getText() + "','" + authorTF.getText() + "','" + publishTF.getText() + "','" + timeTF.getText() + "','" + ISBNTF.getText() + "')");
                }catch(Exception e){
                    System.out.println("添加失败");
                }
            });
            addVBox.getChildren().addAll(title1,
                    new Separator(),
                    titleAndText("书名",nameTF),
                    titleAndText("作者",authorTF),
                    titleAndText("出版者",publishTF),
                    titleAndText("出版时间",timeTF),
                    titleAndText("ISBN",ISBNTF),
                    addBT);
            //修改部分
            VBox changeVBox=new VBox();
            Label title2=new Label("依照书名修改信息，书名不能改");
            title2.setFont(new Font(20));
            TextField cnameTF=new TextField();
            TextField cauthorTF=new TextField();
            TextField cpublishTF=new TextField();
            TextField ctimeTF=new TextField();
            ctimeTF.setText("xxxx-xx-xx");
            TextField cISBNTF=new TextField();
            Button changeBT=new Button("修改");
            changeBT.setOnAction(event -> {
                try {
                stm.executeUpdate("USE BookInfo");
                if(!cauthorTF.getText().equals("")){
                    stm.executeUpdate("UPDATE book SET 作者 = '"+cauthorTF.getText()+"' WHERE 书名 = '"+cnameTF.getText()+"'");
                }
                if(!cpublishTF.getText().equals("")){
                    stm.executeUpdate("UPDATE book SET 出版者 = '"+cpublishTF.getText()+"' WHERE 书名 = '"+cnameTF.getText()+"'");
                }
                if(!ctimeTF.getText().equals("xxxx-xx-xx")){
                    stm.executeUpdate("UPDATE book SET 出版时间 = '"+ctimeTF.getText()+"' WHERE 书名 = '"+cnameTF.getText()+"'");
                }
                if(!cISBNTF.getText().equals("")){
                    stm.executeUpdate("UPDATE book SET ISBN = '"+cISBNTF.getText()+"' WHERE 书名 = '"+cnameTF.getText()+"'");
                }
            }catch(Exception e){
                System.out.println("修改失败");
            }
            });
            changeVBox.getChildren().addAll(title2,
                    new Separator(),
                    titleAndText("书名",cnameTF),
                    titleAndText("作者",cauthorTF),
                    titleAndText("出版者",cpublishTF),
                    titleAndText("出版时间",ctimeTF),
                    titleAndText("ISBN",cISBNTF),
                    changeBT);
            //删除部分
            VBox deleteVBox=new VBox();
            Label title3=new Label("删除一本书籍");
            title3.setFont(new Font(25));
            TextField dnameTF=new TextField();
            Button deleteBT=new Button("删除");
            deleteBT.setOnAction(event -> {
                try {
                    stm.executeUpdate("USE BookInfo");
                    stm.executeUpdate("DELETE FROM book WHERE 书名 = '"+ dnameTF.getText() +"'");
                }catch(Exception e){
                    System.out.println("删除失败");
                }
            });
            deleteVBox.getChildren().addAll(title3,
                    new Separator(),
                    titleAndText("书名",dnameTF),
                    deleteBT);
            //添加三个部分
            allHB.getChildren().addAll(addVBox,new Separator(), changeVBox,new Separator(),deleteVBox);
            //下方
            HBox buttonHB=new HBox();
            Button exitBT=new Button("退出");
            exitBT.setOnAction(event -> {
                try{
                con.close();
                }catch(Exception e){
                    System.out.println("哇，关闭连接都能出错？？？？");
                }
                Platform.exit();
            });
            Button createBT=new Button("创建");
            createBT.setOnAction(event -> {
                try {
                    stm.executeUpdate("CREATE DATABASE IF NOT EXISTS BookInfo");
                    stm.executeUpdate("USE BookInfo");
                    stm.executeUpdate("CREATE TABLE book(书名 char(50),作者 char(20),出版者 char(20), 出版时间 char(10),ISBN char(20))");
                    stm.executeUpdate("CREATE VIEW bookview(ISBN,书名,作者) AS SELECT ISBN,书名,作者 FROM book");
                    System.out.println("创建数据库和表完成");
                    createBT.setDisable(true);
                }catch (Exception e){
                    System.out.println("相关数据库已存在");
                    createBT.setDisable(true);
                }
            });
            buttonHB.getChildren().addAll(createBT,exitBT);
            //总体添加
            root.setCenter(allHB);
            root.setTop(buttonHB);
            Scene myScene=new Scene(root);
            primaryStage.setScene(myScene);
            primaryStage.show();


        }
        public static HBox titleAndText(String title, TextField newTF){
            HBox tmp=new HBox();
            Label tmpLabel=new Label(title);
            tmp.getChildren().addAll(tmpLabel,newTF);
            return tmp;
        }

        public static void main(String[] args) {
            launch(args);
        }

}
