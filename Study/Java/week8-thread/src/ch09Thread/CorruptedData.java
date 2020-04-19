package ch09Thread;

public class CorruptedData {
	protected static int DISPLAY = 1, CHANGE = 2;
	private WorkThread slowWorker = null;
	private WorkThread fastWorker = null;
	private int number = 0;

	public CorruptedData() {
		number = (int) (10 * Math.random());
		slowWorker = new WorkThread(this, DISPLAY);
		fastWorker = new WorkThread(this, CHANGE);
	}

	public synchronized void performWork(int type)
	// public void performWork(int type)
	{
		if (type == DISPLAY) {
			System.out.println("Number before sleeping: " + number);
			try {
				slowWorker.sleep(1);
			} catch (InterruptedException ie) {
				System.err.println("Error: " + ie);
			}
			System.out.println("Number after waking up: " + number);
		}
		if (type == CHANGE)
			number = -1;
	}

	public static void main(String args[]) {
		CorruptedData cd = new CorruptedData();
	}
}

//public 
class WorkThread extends Thread {
	private CorruptedData data = null;
	private int work = 0;

	public WorkThread(CorruptedData _data, int _work) {
		data = _data;
		work = _work;
		start();
	}

	public void run() {
		data.performWork(work);
	}
}
