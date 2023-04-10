package Model;

import java.util.LinkedList;
import java.util.Queue;

/**
* @author Ryan Spowart
* Share Object
* contains data transfer from the JunctionController to the individual traffic Queues
*/
public class TimeShare {
	private boolean n;
	private int timeUntil;
	private boolean empty;
	private boolean done;
	private boolean calcDone;
	private Queue<Vehicle> q;
	
	public TimeShare() {
		timeUntil = 0;
		n = false;
		empty = true;
		done = false;
		calcDone = false;
		q =new LinkedList<>();
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
	public synchronized void putVehicles(Queue<Vehicle> q) {
		this.q = q;
	}
	public synchronized Queue<Vehicle> getVehicles() {
		return q;
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
