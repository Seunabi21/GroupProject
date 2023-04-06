package coursework;

public class ShareLight {
	private String state;
	private int move;
	private boolean waiting;
	private boolean done;
	private int qNo;
	
	public ShareLight(int qNo) {
		state = "R";
		move = 0;
		this.qNo = qNo;
		waiting = false;
		done = false;
	}

	// wait while no number
	// when waiting over, get number
	// set empty to true and notify waiting methods
	public synchronized String get() {
		while (state.equals("R")) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Got State: " + state);
		notifyAll();
		return state;
	}
	public synchronized String getWaitG() {
		while (!state.equals("G")) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Got State: " + state);
		notifyAll();
		return state;
	}
	
	
	public synchronized boolean getWaiting() {
		while (!waiting && qNo >0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Got waiting: " + waiting);
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
		System.out.println("Put Light: " + state);
		notifyAll();
		this.state = state;
	}
	
	public synchronized void putWait(boolean waiting) {
		while (this.waiting) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Car Waiting: " + waiting);
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
		
		System.out.println("Put move: " + move + " "+ qNo);
		notifyAll();
		
		this.move = move;
		this.qNo = qNo;
	}
	
	public void setDone() {
		done = true;
	}

	public synchronized boolean getDone() {
		
		return done;
	}
}
