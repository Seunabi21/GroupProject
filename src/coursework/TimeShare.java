package coursework;

public class TimeShare {
	private boolean n;
	private int timeUntil;
	private boolean empty;
	private boolean done;
	private boolean calcDone;
	
	public TimeShare() {
		timeUntil = 0;
		n = false;
		empty = true;
		done = false;
		calcDone = false;
	}

	// wait while no number
	// when waiting over, get number
	// set empty to true and notify waiting methods
	public synchronized boolean get() {
		while (calcDone) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		empty = true;
		return n;
	}

	// wait while number there
	// when waiting over, put number
	// set empty to false and notify waiting methods
	public synchronized void put(boolean n) {

		/*while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		System.out.println("putlightState: " + n);
		empty = false;
		notifyAll();
		this.n = n;
	}
	
	public synchronized void addTime(int t) {
		System.out.println("putAddedTime: " + t);
		empty = false;
		notifyAll();
		this.timeUntil = t;
	}
	
	public int getTime() {
		return timeUntil;
	}
	public void setDone() {
		done = true;
	}

	public boolean getDone() {
		return done;
	}
	
	public synchronized void setCalcDone() {
		notifyAll();
		done = true;
	}

	public boolean getCalcDone() {
		return done;
	}
}
