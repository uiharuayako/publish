package homework;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.*;
import java.util.Collection;

public class Question4 extends Application{
    @Override // Override the start method in the Application class
    public void start(Stage exampleStage) {
        exampleStage.setTitle("演示多线程");
        VBox myPane = new VBox(2);
        Label thread1 = new Label("①");
        Label thread2 = new Label("②");
        myPane.getChildren().addAll(thread1, thread2);
        HBox buttons = new HBox(4);
        Button startDash1 = new Button("开始线程1");
        Button startDash2 = new Button("开始线程2");
        Button pause1 = new Button("暂停线程1");
        Button pause2 = new Button("暂停线程2");
        Button stop = new Button("结束");
        buttons.getChildren().addAll(startDash1, startDash2, pause1, pause2, stop);
        BorderPane thisPane = new BorderPane();
        thisPane.setTop(myPane);
        thisPane.setBottom(buttons);
        Scene myScene = new Scene(thisPane);
        exampleStage.setScene(myScene);
        exampleStage.show();

        Thread threadOne=new Thread(){
            public void run(){
                System.out.println("s");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                            thread1.setLayoutX(thread1.getLayoutX()+10);
                    }});
            }
        };

        startDash1.setOnAction(event -> {


            threadOne.start();
        });
    }

    public static void main(String args[]) {
        launch(args);
    }
}
