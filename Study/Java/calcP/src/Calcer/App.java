package Calcer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class App extends Application {
    double getOne=0.13;
    double notGet=1 - getOne;
    @Override
    public void start(Stage myStage){
        myStage.setTitle("偶像歌手星元概率简单计算 -by 初春绫子");
        HBox line1HBox=new HBox(2);
        Label label1=new Label("总共抽的次数:");
        TextField textField1=new TextField();
        line1HBox.getChildren().addAll(label1,textField1);
        HBox line2HBox=new HBox(2);
        Label label2=new Label("星元及空箱个数:");
        TextField textField2=new TextField();
        line2HBox.getChildren().addAll(label2,textField2);
        HBox line3HBox=new HBox(2);
        Label label3=new Label("抽这么多次出4个星元的概率:");
        TextField textField3=new TextField();
        textField3.setEditable(false);
        line3HBox.getChildren().addAll(label3,textField3);
        HBox line4HBox=new HBox(2);
        Label label4=new Label("发生当前情况的概率:");
        TextField textField4=new TextField();
        textField4.setEditable(false);
        line4HBox.getChildren().addAll(label4,textField4);
        HBox buttonBox=new HBox(3);
        Button calcButton=new Button("计算");
        calcButton.setOnAction(event -> {
           int totelNum= Integer.parseInt(textField1.getText().trim());
           int yourNum= Integer.parseInt(textField2.getText().trim());
           double p1=NcR(totelNum,yourNum)*Math.pow(getOne,yourNum)*Math.pow(notGet,totelNum-yourNum);
           double p2=0;
           for(int i=4;i<=totelNum;i++){
               p2+=NcR(totelNum,i)*Math.pow(getOne,i)*Math.pow(notGet,totelNum-i);
           }
           textField3.setText(p2+"");
           textField4.setText(p1+"");
        });
        Button resetButton=new Button("清空");
        resetButton.setOnAction(event -> {
            textField1.clear();
            textField2.clear();
            textField3.clear();
            textField4.clear();
        });
        Button exitButton=new Button("退出");
        exitButton.setOnAction(event -> {
            myStage.hide();
        });
        buttonBox.getChildren().addAll(calcButton,resetButton,exitButton);
        VBox mainV=new VBox(5);
        mainV.getChildren().addAll(line1HBox,line2HBox,line3HBox,line4HBox,buttonBox);
        Scene myScene=new Scene(mainV);
        myStage.setScene(myScene);
        myStage.show();
    }
    public static double factorial(double number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }
    //排列，n个数选r个排列
    public static double NcR(double n,double r) {
        return factorial(n)/( factorial(n-r) * factorial(r));
    }
    public static void main(String[] args) {
        launch(args);
    }
}
