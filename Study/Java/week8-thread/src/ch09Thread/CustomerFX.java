package ch09Thread;

import javafx.application.Platform;

public class CustomerFX extends Thread {
	private BankFX bank = null;
	private int id = -1;
	private boolean running = false;

	public CustomerFX(int _id, BankFX _bank) {
		bank = _bank;
		id = _id;
		start();
	}

	public void start() {
		running = true;
		super.start();
	}

	public void halt() {
		running = false;
	}

	public void run() {

		while (running) {

			// 更新JavaFX组件的代码

			int into = (int) (Bank.NUM_ACCOUNTS * Math.random());
			int amount = (int) (1000 * Math.random());
			Platform.runLater(() -> {
				bank.transfer(id, into, amount);
			});
			yield();
		}
	}
}
