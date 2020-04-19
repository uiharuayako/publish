package ch09Thread;

public class Customer extends Thread {
	private Bank bank = null;
	private int id = -1;
	private boolean running = false;

	public Customer(int _id, Bank _bank) {
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
			int into = (int) (Bank.NUM_ACCOUNTS * Math.random());
			int amount = (int) (1000 * Math.random());
			bank.transfer(id, into, amount);
			yield();
		}
	}
}
