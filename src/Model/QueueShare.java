package Model;

import java.util.LinkedList;
import java.util.Queue;

/**
* @author Ryan Spowart
* Share Object
* contains data transfer from the JunctionController to the individual traffic Queues
*/
public class QueueShare {
	private boolean LightState;			//
	private int timeUntil;		//time until completion
	private boolean done;		//notifys when complete
	private boolean calcDone;	//tracks when the calculations are complete
	private Queue<Vehicle> q;	//list of vehicles for passing back to controller when needed
	private Queue<Vehicle> vAdded; //list of vehcles to be added, allows for the transfer of meny vehicles at once.
	
	//Constructor
	public QueueShare() {
		timeUntil = 0;
		LightState = false;
		done = false;
		calcDone = false;
		q =new LinkedList<>();
		vAdded = new LinkedList<>();
	}

	// wait while no number
	// when waiting over, get number
	// set empty to true and notify waiting methods
	public synchronized boolean getWaitLightTrue() {
		while (!LightState) {
			System.out.println("Waiting on light");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		return LightState;
	}
	public synchronized boolean getWaitLightFalse() {
		while (LightState) {
			System.out.println("Waiting on light");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		return LightState;
	}
	//Adds vehicles to q
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

		System.out.println("putlightState: " + n);
		notifyAll();
		this.LightState = n;
	}
	
	//Adds time to the lights
	public synchronized void addTime(int t) {
		System.out.println("putAddedTime: " + t);
		notifyAll();
		this.timeUntil = t;
	}
	//returns the time stored
	public int getTime() {
		return timeUntil;
	}
	//sets the done condition to true
	public synchronized void setDone() {
		notifyAll();
		done = true;
	}
	//returns if the done condition is true
	public synchronized boolean getDone() {
		while(done) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}
	//sets if the calculations have been complete
	public synchronized void setCalcDone() {
		notifyAll();
		calcDone = true;
	}
	//gets if the calculations have been completed
	public boolean getCalcDone() {
		return calcDone;
	}
	//adds a new vehicle to the queue of vehicles to be added
	public synchronized void addVehicleNew(Vehicle v) {
		System.out.println(v.toString());
		vAdded.add(v);
		notifyAll();
	}
	//reuturns the queue of vehicles added.
	public Queue<Vehicle> getVehicleNew() {
		
		Queue<Vehicle> v = vAdded;
		vAdded.clear();
		return v;
	}
	//reuturns the queue of vehicles added.
		public synchronized Queue<Vehicle> WaitgetVehicleNew() {
			while(vAdded.size() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Adding new vehicles");
			Queue<Vehicle> v = vAdded;
			vAdded.clear();
			return v;
		}
}
