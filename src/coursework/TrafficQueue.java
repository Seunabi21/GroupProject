package coursework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
* @author Ryan Spowart
* Threaded Object
* Processes a single queue of traffic.
*/
public class TrafficQueue implements Runnable{
	private Queue<Vehicle> Crossed;
	private Queue<Vehicle> Waiting;
	private Queue<Thread> Vehicles; //Stores the time for each phase
	//Share Objects
	private TimeShare timeshare; //Data transfer from junction controller
	private ShareLight light; //Data transfer to stored vehicle threads
	private Statistics stats; //tracks statistics
	
	/*
	 * Constructor
	 */
	TrafficQueue(ArrayList<Vehicle> Vehicles, TimeShare timeshare, Statistics stats){
		this.timeshare = timeshare;//passing in share objects
		this.stats = stats;
		this.Vehicles =new LinkedList<>(); // making new queues
		this.Waiting = new LinkedList<>();
		Crossed = new LinkedList<>();
		light = new ShareLight(Vehicles.size()); // Creating new share object
		
		//Generating new instances of vehicles to avoid interference
		/*slightly jank should find a better solution*/
		int distance = 0;
		for(Vehicle v : Vehicles) {
			this.Vehicles.add(new Thread(new Vehicle(v.ID,v.Type,v.CrossTime,v.Length,v.Direction,v.Emission,v.Segment,light,distance)));
			Waiting.add(new Vehicle(v.ID,v.Type,v.CrossTime,v.Length,v.Direction,v.Emission,v.Segment,light,distance));
			distance += v.Length;
		}
		
		System.out.println(distance);
		stats.addLength(distance);
	}
	//Returns the state of the vehicles contained
	public Queue<Vehicle> getVehicles(){
		Queue<Vehicle> tmp = new LinkedList<>();
		tmp.addAll(Waiting);
		tmp.addAll(Crossed);
		return tmp;
	}
	/*
	 * What happens when the queue is ran
	 */
	@Override
	public void run() {
		System.out.println("Waiting " + Waiting);

		for (Thread t : this.Vehicles) {	//starting each of the individual vehicle threads no point starting them before they are needed
			t.start();
		}
		//while the light is set to green and the calculations haven't been completed
		while (!timeshare.getDone() && !timeshare.getCalcDone()) {
			try { //slight time delay to avoid spam if repeated checks occour.
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			
			if(timeshare.get()) {
				CalcPhase();
			}
		}
		//notifying the vehicles that this round is now complete.
		light.setDone();
	}
	/*
	 * Calculates a single cycle of the lights
	 */
	private synchronized void CalcPhase() {
		try {
			light.put("A");//changing lights to green
			Thread.sleep(100);
			light.put("G");
			//waiting until a car is waiting
			while(light.getWaiting() == true && timeshare.get() && Waiting.size() != 0) {
				Vehicle v = Waiting.remove();
				v.Status="Crossed";
				Thread.sleep(100*v.CrossTime);
				Crossed.add(v);
				
				stats.addWait((v.CrossTime + timeshare.getTime()));
				stats.addCrosstime(v.CrossTime);
				stats.addEmissions((v.Emission*(v.CrossTime + timeshare.getTime()))/60);
				
			}
			//waiting unitl the controller says to change lights red.
			while(timeshare.getDone()) {
				wait();
			}
			timeshare.setCalcDone();//stopping repeats
			light.put("A"); //changing lights red
			Thread.sleep(100);
			light.put("R");
			System.out.println("Waiting :" + Waiting);
			System.out.println("Crossed :" + Crossed);
			timeshare.putVehicles(getVehicles());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
