package coursework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TrafficQueue implements Runnable{
	Queue<Vehicle> Crossed;
	Queue<Vehicle> Waiting;
	Queue<Thread> Vehicles; //Stores the time for each phase
	TimeShare timeshare;
	ShareLight light;
	
	TrafficQueue(ArrayList<Vehicle> Vehicles, TimeShare timeshare){
		this.timeshare = timeshare;
		this.Vehicles =new LinkedList<>();
		this.Waiting = new LinkedList<>();
		Crossed = new LinkedList<>();
		light = new ShareLight();
		int distance = 0;
		for(Vehicle v : Vehicles) {
			v.distance = distance;
			distance = v.Length;
			v.light = light;
			this.Vehicles.add(new Thread(v));
			Waiting.add(v);
		}
		
		for (Thread t : this.Vehicles) {
			t.start();
		}
	}
	
	@Override
	public void run() {
		while (!timeshare.getDone()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			if(timeshare.get()) {
				CalcPhase();
			}
		}
	}
	
	public void CalcPhase() {
		try {
			light.put("A");
			Thread.sleep(100);
			light.put("G");
			
			while(timeshare.get()) {
				System.out.println("IRAN");
				if (light.getWaiting() == true) {
					Vehicles.remove();
					Vehicle v = Waiting.remove();
					light.putMove(v.Length, Vehicles.size());
					Thread.sleep(10*v.CrossTime);
					Crossed.add(v);
				}
				Thread.sleep(100);
				
			}
			
			light.put("A");
			Thread.sleep(100);
			light.put("R");
			timeshare.setCalcDone();
			System.out.println(Waiting);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
