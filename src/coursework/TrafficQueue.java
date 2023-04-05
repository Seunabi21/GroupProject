package coursework;

import java.util.ArrayList;
import java.util.Queue;

public class TrafficQueue implements Runnable{

	ArrayList<Vehicle> Vehicles; //Stores the time for each phase
	TimeShare timeshare;
	TrafficQueue(ArrayList<Vehicle> Vehicles, TimeShare timeshare){
		this.timeshare = timeshare;
		this.Vehicles = Vehicles;
	}
	
	@Override
	public void run() {
		while (!timeshare.getDone()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			CalcPhase(timeshare.get());
			System.out.println(Vehicles);
		}
		System.out.println("DIE");
	}
	
	public void CalcPhase(int time) {
		int vTime = 0;
		//P is for each Phase , segment Right & straight and left turn lanes
		// converts to segment
		for (Vehicle v : Vehicles) {
			if((time - v.CrossTime)>=0 &&(v.Direction.equals("Right") || v.Direction.equals("Straight")) &&! v.Status.equals("Crossed")) { // Even -- Right & Strait
				vTime += v.CrossTime;
				
			}else if ((time - v.CrossTime)>=0 && !v.Status.equals("Crossed")){ //Odd -- Left
				vTime += v.CrossTime;
			}
		}
	}

}
