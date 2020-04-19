package ch09Thread;

import java.time.LocalTime;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowSecondsFX extends Application implements Runnable {
	private Thread clocker = null;
	Text text = new Text(70, 50, "");

	public ShowSecondsFX() {
		clocker = new Thread(this);
		clocker.start();

	}

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane to hold the texts
		Pane pane = new Pane();
		pane.setPadding(new Insets(5, 5, 5, 5));
		text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
		pane.getChildren().add(text);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 200, 120);
		primaryStage.setTitle("ShowText"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public void run() {
		while (true) {
			LocalTime now = LocalTime.now();
			String nowText = now.getHour() + ":" + now.getMinute() + ":" + now.getSecond();
			text.setText(nowText);
			System.out.println(now);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				System.err.println("Thread error: " + ie);
			}
		}
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
//		ShowSecondsFX time = new ShowSecondsFX();
		launch(args);
	}
}
