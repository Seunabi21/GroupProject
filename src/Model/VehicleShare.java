package Model;
/**
* @author Ryan Spowart
* Share Object
* contains data transfer from Traffic queue to Vehicle threads
*/

public class VehicleShare {
	public String state; //state if the light- R | A | G
	private int move; // how far cars can move forward
	private boolean waiting; //whether a car is waiting for the junction to be clear
	private boolean done; //whether junction calculations are compelte for the moment
	private int qNo; //number of cars present in queue
	
	/*
	 * Constructor
	 */
	public VehicleShare(int qNo) {
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
	
	//Allows a car to wait on the light to turn green
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
	
	//returns if a car is waiting to cross the junction
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
		waiting = false;
		return true;
	}
	//gets how far car can move
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
		qNo -=1;
		return move;
	}

	//sets the state of the light
	public synchronized void put(String state) {
		System.out.println("Put Light: " + state);
		notifyAll();
		this.state = state;
	}
	//sets whether the next vehicle is waiting
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
	//resets the wait to false
	public void resetWait() {
		waiting = false;
	}
	
	//sets how far cars can move and the number of cars to anticipate receiving this.
	public synchronized void putMove(int move, int qNo) {
		
		while (qNo != 0) {	//ensures that cars cant be told to move further until they have all moved up
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
	//notifies of completion
	public void setDone() {
		done = true;
	}
	//returns whether the cycle is complete.
	public synchronized boolean getDone() {
		
		return done;
	}
}
