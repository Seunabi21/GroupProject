package coursework;

public class ShareLight {
	private String state;
	private int move;
	private boolean waiting;
	private boolean done;
	private int qNo;
	
	public ShareLight() {
		state = "R";
		move = 0;
		qNo = 0;
		waiting = false;
		done = false;
	}

	// wait while no number
	// when waiting over, get number
	// set empty to true and notify waiting methods
	public synchronized String get() {
		System.out.println("Got: " + state);
		notifyAll();
		return state;
	}
	
	public synchronized boolean getWaiting() {
		while (qNo != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Got: " + waiting);
		waiting = false;
		notifyAll();
		return waiting;
	}
	
	public synchronized int getMove() {
		while (move == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Moving: " + move);
		notifyAll();
		return move;
	}

	// wait while number there
	// when waiting over, put number
	// set empty to false and notify waiting methods
	public synchronized void put(String state) {
		System.out.println("Put: " + state);
		notifyAll();
		this.state = state;
	}
	
	public synchronized void put(boolean waiting) {
		
		while (waiting) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Put: " + waiting);
		notifyAll();
		this.waiting = waiting;
	}
	
	public synchronized void putMove(int move, int qNo) {
		
		while (qNo != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Put: " + waiting);
		notifyAll();
		
		this.move = move;
		this.qNo = qNo;
	}
	
	public void setDone() {
		done = true;
	}

	public synchronized boolean getDone() {
		while (!done) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return done;
	}
}
