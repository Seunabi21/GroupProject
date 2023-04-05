package coursework;

public class TimeShare {
	private int n;
	public int ID;
	private boolean empty;
	private boolean done;

	public TimeShare() {
		n = 0;
		empty = true;
		done = false;
	}

	// wait while no number
	// when waiting over, get number
	// set empty to true and notify waiting methods
	public synchronized int get() {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Got: " + n);
		empty = true;
		notifyAll();
		return n;
	}

	// wait while number there
	// when waiting over, put number
	// set empty to false and notify waiting methods
	public synchronized void put(int n) {

		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Put: " + n);
		empty = false;
		notifyAll();
		this.n = n;
	}

	public void setDone() {
		done = true;
	}

	public boolean getDone() {
		return done;
	}
}
