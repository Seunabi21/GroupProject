package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
* @author Ryan Spowart
* Threaded Object
* Processes a single queue of traffic.
*/
public class TrafficQueue implements Runnable{
	private Queue<Vehicle> Crossed;	//Tracking data for crossed vehicles
	private Queue<Vehicle> Waiting; //tracking data for waiting vehicles
	private Queue<Thread> Vehicles; //Stores the time for each phase
	//Share Objects
	private QueueShare qShare; //Data transfer from junction controller
	private VehicleShare light; //Data transfer to stored vehicle threads
	private statistics stats; //tracks statistics
	
	/*
	 * Constructor
	 */
	public TrafficQueue(ArrayList<Vehicle> Vehicles, QueueShare timeshare, statistics stats){
		this.qShare = timeshare;//passing in share objects
		this.stats = stats;
		this.Vehicles =new LinkedList<>(); // making new queues
		this.Waiting = new LinkedList<>();
		Crossed = new LinkedList<>();
		light = new VehicleShare(Vehicles.size()); // Creating new share object
		
		//Generating new instances of vehicles to avoid interference
		/*slightly jank should find a better solution*/
		int distance = 0;
		for(Vehicle v : Vehicles) {
			this.Vehicles.add(new Thread(new Vehicle(v.getID(),v.Type,v.getCrossTime(),v.getLength(),v.getDirection(),v.Emission,v.Segment,light,distance)));
			System.out.println(v.getDirection());
			Waiting.add(new Vehicle(v.getID(),v.Type,v.getCrossTime(),v.getLength(),v.getDirection(),v.Emission,v.Segment,light,distance));
			distance += v.getLength();
		}
		
		System.out.println(distance);
		stats.addLength(distance);
	}
	/*
	 * Methods
	 */
	// Adds new vehicles pased in to  
	public void addNewVehicles(Queue<Vehicle> Vehicles) {
		int distance = 0;

		for(Vehicle v : Waiting) {
			distance += v.getLength();
		}
		
		for(Vehicle v : Vehicles) {
			this.Vehicles.add(new Thread(new Vehicle(v.getID(),v.Type,v.getCrossTime(),v.getLength(),v.getDirection(),v.Emission,v.Segment,light,distance)));
			Waiting.add(new Vehicle(v.getID(),v.Type,v.getCrossTime(),v.getLength(),v.getDirection(),v.Emission,v.Segment,light,distance));
			distance += v.getLength();
		}
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
		while (!qShare.getDone()) {
			if(qShare.getWaitLightTrue()) {
				if(Waiting.size() == 0) {
					qShare.getVehicleNew();
				}else {
					addNewVehicles(qShare.getVehicleNew());
					CalcPhase();
				}
			}
			if(qShare.getWaitLightFalse()) {
				
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
			while(light.getWaiting() == true && Waiting.size() != 0) {
				Vehicle v = Waiting.remove();
				v.setStatus("Crossed");
				Thread.sleep(100*v.getCrossTime());
				Crossed.add(v);
				
				stats.addWait((v.getCrossTime() + qShare.getTime()));
				stats.addCrosstime(v.getCrossTime());
				stats.addEmissions((v.Emission*(v.getCrossTime() + qShare.getTime()))/60);
				
			}
			//waiting unitl the controller says to change lights red.
			while(qShare.getDone()) {
				wait();
			}
			qShare.setCalcDone();//stopping repeats
			light.put("A"); //changing lights red
			Thread.sleep(100);
			light.put("R");
			System.out.println("Waiting :" + Waiting);
			System.out.println("Crossed :" + Crossed);
			qShare.putVehicles(getVehicles());
			qShare.put(false);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
