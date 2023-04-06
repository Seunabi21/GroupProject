package coursework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TrafficQueue implements Runnable{
	private Queue<Vehicle> Crossed;
	private Queue<Vehicle> Waiting;
	private Queue<Thread> Vehicles; //Stores the time for each phase
	private TimeShare timeshare;
	private ShareLight light;
	private Statistics stats;
	
	TrafficQueue(ArrayList<Vehicle> Vehicles, TimeShare timeshare, Statistics stats){
		this.timeshare = timeshare;
		this.Vehicles =new LinkedList<>();
		this.Waiting = new LinkedList<>();
		Crossed = new LinkedList<>();
		light = new ShareLight(Vehicles.size());
		this.stats = stats;
		
		int distance = 0;
		for(Vehicle v : Vehicles) {
			this.Vehicles.add(new Thread(new Vehicle(v.ID,v.Type,v.CrossTime,v.Length,v.Direction,v.Emission,v.Segment,light,distance)));
			Waiting.add(new Vehicle(v.ID,v.Type,v.CrossTime,v.Length,v.Direction,v.Emission,v.Segment,light,distance));
			distance += v.Length;
		}
		System.out.println(distance);
		stats.addLength(distance);
	}
	
	@Override
	public void run() {
		System.out.println("Waiting " + Waiting);

		for (Thread t : this.Vehicles) {
			t.start();
		}
	
		while (!timeshare.getDone() && !timeshare.getCalcDone()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			if(timeshare.get()) {
				CalcPhase();
			}
		}
		light.setDone();
	}
	
	public synchronized void CalcPhase() {
		try {
			light.put("A");
			Thread.sleep(100);
			light.put("G");
			
			while(light.getWaiting() == true && timeshare.get() && Waiting.size() != 0) {
				Vehicle v = Waiting.remove();
				v.Status="Crossed";
				Thread.sleep(100*v.CrossTime);
				Crossed.add(v);
				
				stats.addWait((v.CrossTime + timeshare.getTime()));
				stats.addCrosstime(v.CrossTime);
				stats.addEmissions((v.Emission*(v.CrossTime + timeshare.getTime()))/60);
				
			}
			
			while(timeshare.getDone()) {
				wait();
			}
			timeshare.setCalcDone();
			light.put("A");
			Thread.sleep(100);
			light.put("R");
			System.out.println("Waiting :" + Waiting);
			System.out.println("Crossed :" + Crossed);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
