package ch09Thread;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BankFX extends Application {
	protected final static int NUM_ACCOUNTS = 8;
	private final static int WASTE_TIME = 1;

	private int accounts[] = new int[NUM_ACCOUNTS];
	private CustomerFX customer[] = new CustomerFX[NUM_ACCOUNTS];
	private int counter = 0;

	private Label statusLabel = new Label("Transfers Completed: 0");
	private TextArea displayTextArea = new TextArea("");

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		Button showButton = new Button("Show Accounts");
		showButton.setOnAction(e -> showAccounts());

		Button startButton = new Button("Restart");
		startButton.setOnAction(e -> start());

		Button stopButton = new Button("Stop");
		startButton.setOnAction(e -> stopButtonClicked());

		// title pane
		FlowPane titlePane = new FlowPane();
		titlePane.getChildren().add(statusLabel);

		// button pane
		HBox buttonPane = new HBox();
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(showButton, startButton, stopButton);
		// main window
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(titlePane);
		displayTextArea.setEditable(false);
		mainPane.setCenter(displayTextArea);
		mainPane.setBottom(buttonPane);

		// closing win ...
		primaryStage.setOnCloseRequest(e -> System.exit(0));

		// Create a scene and place it in the stage
		Scene scene = new Scene(mainPane, 400, 400);
		primaryStage.setTitle("Mystery Money"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public BankFX() {

		for (int i = 0; i < accounts.length; i++)
			accounts[i] = 100000;
		for (int i = 0; i < accounts.length; i++)
			if (customer[i] != null)
				customer[i].halt();
		for (int i = 0; i < accounts.length; i++)
			customer[i] = new CustomerFX(i, this);

	}

//
	synchronized public void transfer(int from, int into, int amount) {
		if ((accounts[from] >= amount) && (from != into)) {
			int newAmountFrom = accounts[from] - amount;
			int newAmountTo = accounts[into] + amount;
			wasteSomeTime();
			accounts[from] = newAmountFrom;
			accounts[into] = newAmountTo;
		}
		statusLabel.setText("Transfers completed: " + counter++);
	}

	private void wasteSomeTime() {
		try {
			Thread.sleep(WASTE_TIME);
		} catch (InterruptedException ie) {
			System.err.println("Error: " + ie);
		}
	}

	private void showAccounts() {
		int sum = 0;
		for (int i = 0; i < accounts.length; i++) {
			sum += accounts[i];
			displayTextArea.appendText("\nAccount " + i + ": $" + accounts[i]);
		}
		displayTextArea.appendText("\nTotal Amount:    $" + sum);
		displayTextArea.appendText("\nTotal Transfers: " + counter + "\n");
	}

	private void start() {
		stopButtonClicked();
		for (int i = 0; i < accounts.length; i++)
			customer[i] = new CustomerFX(i, this);
	}

	private void stopButtonClicked() {
		for (int i = 0; i < accounts.length; i++)
			if (customer[i] != null)
				customer[i].halt();
	}

	public static void main(String args[]) {
		launch(args);

	}
}
