package ch09Thread;

public class CorruptedData1 {
	protected static int DISPLAY = 1, CHANGE = 2;
	private WorkThread1 slowWorker = null;
	private WorkThread1 fastWorker = null;
	// private int number = 0;

	public CorruptedData1() { // number = (int)(10*Math.random());
		slowWorker = new WorkThread1(DISPLAY);
		fastWorker = new WorkThread1(CHANGE);
	}

	public static void main(String args[]) {
		CorruptedData1 cd = new CorruptedData1();
	}
}
//public 

class WorkThread1 extends Thread {
	protected static int DISPLAY = 1, CHANGE = 2;
// private CorruptedData data = null;
	static int number = 0;

	private int work = 0;

	public WorkThread1(int _work) { // data = _data;
		work = _work;
		start();
		number = (int) (10 * Math.random());
	}

	public void run() { // data.performWork(work);
		performWork(work);
	}

	public void performWork(int type)
	// public void performWork(int type)
	{
		if (type == DISPLAY) {
			System.out.println("Number before sleeping: " + number);
			try {
				sleep(2000);
			} catch (InterruptedException ie) {
				System.err.println("Error: " + ie);
			}
			System.out.println("Number after waking up: " + number);
		}
		if (type == CHANGE)
			number = -1;
	}
}
