package homework;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Question4 extends Application{
    @Override // Override the start method in the Application class
    public void start(Stage exampleStage) {
        exampleStage.setTitle("演示多线程");
        Pane myPane = new Pane();
        Text thread1 = new Text("①");
        thread1.setY(15);
        thread1.setFill(Color.RED);
        Text thread2 = new Text("②");
        thread2.setY(35);
        thread2.setFill(Color.RED);
        Text sign=new Text("尚未开始");
        sign.setY(55);
        myPane.getChildren().addAll(thread1, thread2,sign);
        HBox buttons = new HBox(2);
        ToggleButton startTB = new ToggleButton("开始");
        startTB.setOnAction(event -> {
            if (startTB.isSelected()) {
                startTB.setText("暂停");
                thread1.setFill(Color.GREEN);
                thread2.setFill(Color.GREEN);
                sign.setText("赛跑中");
            } else {
                startTB.setText("继续");
                thread1.setFill(Color.RED);
                thread2.setFill(Color.RED);
                sign.setText(thread1.getX()>=thread2.getX()?"1领先":"2领先");
            }
        });
        Button resetButton = new Button("复位");
        resetButton.setOnAction(event -> {
            thread1.setX(0);
            thread2.setX(0);
            startTB.setSelected(false);
            startTB.setText("开始");
            thread1.setFill(Color.RED);
            thread2.setFill(Color.RED);
            sign.setText("尚未开始");
        });
        buttons.getChildren().addAll(startTB, resetButton);
        BorderPane thisPane = new BorderPane();
        thisPane.setTop(myPane);
        thisPane.setBottom(buttons);
        Scene myScene = new Scene(thisPane,500,100);
        exampleStage.setScene(myScene);
        exampleStage.show();
        new Thread(() -> {
            try {
                while (thread1.getX() < myScene.getWidth()-30) {
                    if (startTB.isSelected()) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                thread1.setX(thread1.getX() + Math.random() * 10);
                            }
                        });
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            } finally {
                thread1.setFill(Color.BLACK);
                thread2.setFill(Color.BLACK);
                startTB.setSelected(false);
                startTB.setDisable(true);
                resetButton.setDisable(true);
                sign.setText("1获胜！");
            }
        }).start();
        new Thread(() -> {
            try {
                while (thread2.getX() < myScene.getWidth()-30) {
                    if (startTB.isSelected()) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                thread2.setX(thread2.getX() + Math.random() * 10);
                            }
                        });
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            } finally {
                thread1.setFill(Color.BLACK);
                thread2.setFill(Color.BLACK);
                startTB.setSelected(false);
                startTB.setDisable(true);
                resetButton.setDisable(true);
                sign.setText("2获胜！");
            }
        }).start();
    }
    public static void main(String args[]) {
        launch(args);
    }
}
