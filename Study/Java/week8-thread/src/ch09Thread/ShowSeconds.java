package ch09Thread;

import java.awt.Frame;
import java.awt.TextField;
import java.util.Date;

public class ShowSeconds extends Frame implements Runnable {
	private Thread clocker = null;
	TextField t = new TextField(20);
	private Date now; // = new Date()

	public ShowSeconds() {
		clocker = new Thread(this);
		clocker.start();
		add(t);
		setVisible(true);

	}

	public void run() {
		while (true) {
			now = new Date();
			t.setText(now.toString());
			System.out.println(now);
			try {
				clocker.sleep(1000);
			} catch (InterruptedException ie) {
				System.err.println("Thread error: " + ie);
			}
		}
	}

	public static void main(String args[]) {
		ShowSeconds time = new ShowSeconds();
	}
}
