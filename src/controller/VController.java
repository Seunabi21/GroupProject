package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Model.TimeShare;
import Model.TrafficQueue;
import Model.UIShare;
import Model.Vehicle;
import Model.statistics;
import view.DuplicateIdException;
import view.VView;
import view.ValidationExeption;




public class VController implements Runnable {
	
	HashMap <String, Queue <Vehicle> > Phase; //Stores the phase and queue of vehicles
	HashMap <String, statistics> PhaseStats; //Stores the statistics attributed to each phase
	ArrayList<Integer> PhaseTime; //Stores the time for each phase
	ArrayList<TimeShare> timeShare;
	ArrayList<Thread> trafficqueues;
	UIShare uiContent;
	private VView v;
	
	
	public VController(UIShare uiContent){
		this.uiContent = uiContent;
		// Intialising Variables
		Phase = new HashMap<>();
		PhaseStats = new HashMap<String, statistics>();
		PhaseTime = new ArrayList<Integer>();
		timeShare = new ArrayList<TimeShare>();
		trafficqueues = new ArrayList<Thread>();

		importVehicles();//Importing data from csv
		importPhases();
		
		//Initialising PhaseStats
		for (int i = 0; i < PhaseTime.size(); i ++) {
			PhaseStats.put(""+i,new statistics());
			timeShare.add(new TimeShare());
		}
		
		randomVehicles();
		
		makeQueues();
		this.uiContent.setUI(Phase, PhaseStats, PhaseTime);
		
		v = new VView(vehToObj(), phaseToObj(), segToObj());
		v.addVehicle(new addVehicle());
		v.exitGui(new exitGui());
		v.startSim(new startSimulation());
		randomVehicles();
		
	}
	
	public void makeQueues() {
		System.out.println("Organising vehicles into lanes");
		int i =0;
		int seg = 1;
		boolean lane = true;
		for(i =0; i < 8; i ++) {
			trafficqueues.add(new Thread(new TrafficQueue(seperateLanes(lane,new ArrayList<Vehicle>(Phase.get(String.valueOf(seg)))),timeShare.get(i),PhaseStats.get(String.valueOf(i)))));
			System.out.println(i + " - " +seperateLanes(lane,new ArrayList<Vehicle>(Phase.get(String.valueOf(seg)))));
			lane = !lane;
			if (lane) {
				seg ++;
			}
			
		}
	}
	
	//left is true, right is false
		public ArrayList<Vehicle> seperateLanes(boolean lane, ArrayList<Vehicle> vlist ){
			ArrayList<Vehicle> v = new ArrayList<Vehicle>();
			for(Vehicle vehicle : vlist) {
				if(lane && vehicle.getDirection().equals("Left")) {
					v.add(vehicle);
				}else if (!lane && (vehicle.getDirection().equals("Right") || vehicle.getDirection().equals("Straight"))) {
					v.add(vehicle);
				}
			}
			return v;
		}
	
	/*
	 * Adds a new vehicle to the system
	 * Status not required as it will always initially be waiting.
	 * Validation not included
	 */
	public void AddVehicle(Object[] uiInput) throws DuplicateIdException {
		try {
			String vehID = ((String)uiInput[0]);
			System.out.println(vehID);
			Integer count = 0;
			for(Queue<Vehicle> i: Phase.values()) {
				for(Vehicle j : i){
					System.out.println(j.getID());
					if((j.getID()).equals(vehID)) {
						count += 1;
					}
				}
			}
			
			if(count == 0) {
				Phase.get((String)uiInput[6]).add(new Vehicle(uiInput));
			}else {
				throw new DuplicateIdException(((String)uiInput[0])+" already exist");
			}
			
		} catch (ValidationExeption e) {
			e.printStackTrace();
		}
	}
	
	private void importVehicles() {

		Scanner sc;
		Queue<Vehicle> q = new LinkedList<>();
		Vehicle tmpVeh;
		try {
			System.out.println(" Beginning Vehicle File Import.");
			sc = new Scanner(new File("./DataFiles/Vehicles.csv"));
			sc.useDelimiter(",");   //sets the delimiter pattern 
			
			while (sc.hasNextLine())  //returns a boolean value  
			{  
				//Splitting csv line into an array
				String[] csvRow = sc.nextLine().split(",");
				try {
					// creating a new vehicle object
					tmpVeh = new Vehicle(csvRow);
					q = new LinkedList<>();
					
					//Avoiding overwriting existing queues
					if(Phase.containsKey(csvRow[6])) {
						q = Phase.get(csvRow[6]);
						q.add(tmpVeh);
						Phase.put(csvRow[6], q);
					
					}else {
						q.add(tmpVeh); //Adding new vehicle to queue
						Phase.put(csvRow[6],q); // adding queue and to correct segment
					}
					
				}catch (ValidationExeption e) {
					e.printStackTrace();
				}  
			}   
			
			System.out.println(Phase);
			System.out.println(" File Import Complete.");
			sc.close();  //closes the scanner  
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	/*
	 * Imports Phases from a csv and adds them to the correct data structures.
	 * Data stored within Datafiles directory
	 * Also Validates the input data upon entry
	 */
	private void importPhases() {
		Scanner sc;
		try {
			System.out.println(" Beginning Phase File Import.");
			sc = new Scanner(new File("./DataFiles/Phases.csv"));
			sc.useDelimiter(",");   //sets the delimiter pattern 
			
			while (sc.hasNextLine())  //returns a boolean value  
			{  
				try { // Validating file input
					String nextLine = sc.nextLine();
					if(Integer.parseInt(nextLine) < 10 ) { //Minimum bounds
						PhaseTime.add(10);
						throw new ValidationExeption("Phase Below valid range...Adding Default");
					}else if(Integer.parseInt(nextLine) > 500){ //Maximum bounds
						PhaseTime.add(500);
						throw new ValidationExeption("Phase Above valid range...Adding Default");
					}else if(PhaseTime.size() > 8){ //Maximum amount of phases
						throw new ValidationExeption("too many phases in file");
					}else {
						PhaseTime.add(Integer.parseInt(nextLine));
					}
				}catch (ValidationExeption e) { //printing exception here to allow the file to continue being read
					e.printStackTrace();
				}  
				//Splitting csv line into an array
				
			}   
			//Validating for to few phases.
			if(PhaseTime.size() < 8) {
				while(PhaseTime.size() < 8) {
					PhaseTime.add(60);
				}
				throw new ValidationExeption("not enough phases in file...Adding default durations.");
			}
			
			System.out.println(PhaseTime);
			System.out.println(" File Import Complete.");
			sc.close();  //closes the scanner  
			//Printing error stacktraces
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ValidationExeption e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Generates a report from the statistics stores and saves to a .txt file
	 * Outputs the text file to the datafiles directory
	 * will save over previous file automatically.
	 * Also outputs the generated report to console.
	 */
	
	public String generateReport() {
		//Declarations
		int[] totalv = new int[PhaseTime.size()+1];
		int total = 0;
		int p = 1;
		//Initialisation
		for (int i : totalv) {
			totalv[i] = 0;
		}
		
		//Combining wait time
		int totalTme = 0;
		for (int s = 0; s < PhaseStats.size(); s++) {
			totalTme += PhaseStats.get("" + s).getWaitTime();
		}

	    try {
	      FileWriter myWriter = new FileWriter("./DataFiles/Report.txt");
	      
	      
	      //For Each Phase, works theoretically with any number of phases
			while ( p <  PhaseTime.size()) {
				//P is for each Phase , segment Right & straight and left turn lanes
				// converts to segment
				Queue <Vehicle> CurrentPhase = Phase.get(""+(p+2)/2);
				
				//for each vehicle in the current segment the number of vehicles passed is calculated
				for (Vehicle v : CurrentPhase) {
					if(v.getDirection().equals("Right") || v.getDirection().equals("Straight")) { // Even -- Right & Strait
						totalv[p] += 1;
						total ++;
					}else{
						totalv[p+1] += 1;
						total ++;
					}
				}
				//Formatting and outputting data
				if(totalv[p] != 0) {
					System.out.println("p" + p + " : " + totalv[p]);
					myWriter.write("p" + p + " : " + totalv[p]+"\n");
				}else {
					System.out.println("p" + p + " : " + 0);
					myWriter.write("p" + p + " : " + 0+"\n");
				}
				if(totalv[p+1] != 0) {
					System.out.println("p" + (p+1) + " : " + totalv[p+1]);
					myWriter.write("p" + (p+1) + " : " + totalv[p+1]+"\n");

				}
				else {
					System.out.println("p" + (p+1) + " : " + 0);
					myWriter.write("p" + (p+1) + " : " + 0+"\n");

				}
				p+=2;
			};
			//Outputting calculated totals
			System.out.println("Total Emissions : " + CalcTotalEmissions() + "g");
			System.out.println("Average Wait : " + totalTme/ total + "s" );
			myWriter.write("Total Emissions : " + CalcTotalEmissions() + "g"+"\n");
			myWriter.write("Average Wait : " + totalTme/ total + "s" +"\n");

	      
	      
	      
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
			  
		
		
		
		return "";
	}
	/*
	 * Phase Calculations for vehicle transition through junction
	 * also calculates statistics
	 */
	public void CalcPhases() {
		int count = 0;
		while(!isCrossed() && count < 1000) {
			int p = 0;
			int phaseTime = 0;
			while ( p <  PhaseTime.size()) {
				int curP = PhaseTime.get(p);
				int vTime = 0;
				phaseTime += curP;
				Queue <Vehicle> CurrentPhase = Phase.get("" + (p+2)/2);
				//P is for each Phase , segment Right & straight and left turn lanes
				// converts to segment
				for (Vehicle v : CurrentPhase) {
					if((curP - v.getCrossTime())>=0 &&(v.getDirection().equals("Right") || v.getDirection().equals("Straight")) &&! v.getStatus().equals("Crossed")) { // Even -- Right & Strait
						vTime += v.getCrossTime();
						UpdateStats( v, vTime, phaseTime,  p);
						
					}else if ((curP - v.getCrossTime())>=0 && !v.getStatus().equals("Crossed")){ //Odd -- Left
						vTime += v.getCrossTime();
						UpdateStats( v, vTime, phaseTime,  p);
					}
				}
				p++;
			};
			count ++;
		}
	}
	/*
	 * returns whether all vehicles have crossed or not
	 */
	private Boolean isCrossed() {
		int p = 0;
		while ( p <  PhaseTime.size()) {
			Queue <Vehicle> CurrentPhase = Phase.get("" + (p+2)/2);
			for (Vehicle v : CurrentPhase) {
				if(v.getStatus() != "Crossed") {
					System.out.println("Vehicle not crossed found");
					return false;
				}
			}
			p++;
		};
		return true;
	}
	/*
	 * Updates the statistics, only used within Calc Phases
	 * Reduces repeating code
	 */
	private void UpdateStats(Vehicle v, int vTime, int curP, int p) {
		v.setStatus("Crossed");
		PhaseStats.get("" + p).setWaitTime(PhaseStats.get("" + p).getWaitTime() + vTime + curP); 
		PhaseStats.get("" + p).setWaitLength(PhaseStats.get("" + p).getWaitLength() + v.getLength());
		PhaseStats.get("" + p).setCrossTime(PhaseStats.get("" + p).getCrossTime() + v.getCrossTime());
		PhaseStats.get(""+p).setEmissions((int) (PhaseStats.get(""+p).getEmissions() + (v.WaitEmissions(PhaseStats.get(""+p).getWaitTime()) + v.CrossEmisions())));
	}
	
	/*
	 * Calculates the total emissions
	 */
	public int CalcTotalEmissions() {
		int total = 0;
		for (int s = 0; s < PhaseStats.size(); s++) { //summing all emissions stored
			total += PhaseStats.get("" + s).getEmissions();
		}
		
		return total;
	}

	/*
	 * Converts the stored data structures to a generic format for us in gui elements
	 * for vehicles
	 */

	public Object[][] vehToObj() {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Queue<Vehicle> q : Phase.values()) {	//Converting vehicles hashmap to a list
			for(Vehicle v : q) {
				vehicles.add(v);
			}
		}
		
		Object[][] output = new Object[vehicles.size()][8];	//converting array list to generic format
		for(int i = 0; i < vehicles.size(); i++) {
			output[i] = vehicles.get(i).toObject();
		}
		return output;
	}
	
	/*
	 * Converts the stored data structures to a generic format for us in gui elements
	 * for Phases
	 */
	public Object[][] phaseToObj(){
		Object[][] output = new Object[PhaseTime.size()][2];
		for(int i = 0; i < PhaseTime.size(); i ++) {
			output[i][0] = "P " + (i + 1);
			output[i][1] = PhaseTime.get(i);
		}
		return output;
	}
	/*
	 * Converts the stored data structures to a generic format for us in gui elements
	 * for Segment Statistics
	 */
	public Object[][] segToObj(){
		
		Object[][] Segments = new Object [4][4];
		for(int x = 0; x < 4; x++) {	//initialising new generic object
			for(int y = 0; y < 4; y++) {
				Segments[x][y] = 0;
			}
		}
		//Assigning data to generic format
		for(int s =0; s < 8; s++) {
			Segments[s/2][0] = "S" + ((s/2)+1);
			Segments[s/2][1] = (Integer)Segments[s/2][1] + PhaseStats.get(""+s).getWaitTime();
			Segments[s/2][2] = (Integer)Segments[s/2][2] + PhaseStats.get(""+s).getWaitLength();
			Segments[s/2][3] = (Integer)Segments[s/2][3] + PhaseStats.get(""+s).getCrossTime();

		}
		
		return Segments;
	}
	
	//What happens when the juncntion controller Start or Run method is called.
		@Override
		public void run() {
			for(int i = 0; i < 8; i ++) {
				trafficqueues.get(i).start();
			}
			int segment = 1;
			//make an array of time share -- done
			//each traffic queue gets its own time share telling each one the time it can calculate.
			for(int i = 0; i < 8 ; i +=2) {
				System.out.println("------ Phases : " + (i+1) +" & " + (i+2) + "-------");
				timeShare.get(i).put(true);
				timeShare.get(i+1).put(true);
				try {
					Thread.sleep(100*PhaseTime.get(i));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				timeShare.get(i).put(false);
				timeShare.get(i+1).put(false);
				
				while(!timeShare.get(i).getCalcDone() && !timeShare.get(i+1).getCalcDone()) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				timeShare.get(i).setDone();
				timeShare.get(i+1).setDone();
				for(TimeShare t : timeShare) {
					t.addTime(PhaseTime.get(i));
				}
				System.out.println(Phase.keySet() +""+ (i+2/2));
				Phase.get("" + segment).clear();
				Phase.get("" + segment).addAll(timeShare.get(i).getVehicles());
				Phase.get("" + segment).addAll(timeShare.get(i+1).getVehicles());
				segment ++;
				
				uiContent.setUI(Phase, PhaseStats, PhaseTime);
			}
			
			for(int i = 0; i < 4; i ++) {
				timeShare.get(i).setDone();			
			}
			
			System.out.println("-------Simulation Complete-------");
			System.out.println(PhaseStats);
		}
	
	
	
	
	class addVehicle implements ActionListener{
		public void actionPerformed(ActionEvent ca) {
			Object[] newVehicle = new Object[] {
				v.getVehicleT().getText(), 
				v.getVehTypeT().getSelectedItem().toString(),
				v.getCrossTimeT().getText(),
				v.getDirectionT().getSelectedItem().toString(),
				v.getLengthT().getText(),
				v.getCarEmissionT().getText(),
				v.getSegmentT().getSelectedItem().toString()
			};
			
			try {
				AddVehicle(newVehicle);
			} catch (DuplicateIdException e) {
				e.printStackTrace();
			}
			
//			UPDATED CO VALUE
           
            
            v.updateView(vehToObj(), segToObj());
			
			System.out.println(CalcTotalEmissions());
        }
	}
	
	class exitGui implements ActionListener{
		public void actionPerformed(ActionEvent ca) {
			JFrame confirmex = new JFrame("EXIT");
		    if(JOptionPane.showConfirmDialog(confirmex, "Are you sure you want to exit","EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
		    	generateReport();
		    	System.exit(0);
		    }
        }
	}
	
	class startSimulation implements ActionListener{
		public void actionPerformed(ActionEvent ca) {
			CalcPhases();
        	v.updateView(vehToObj(), segToObj());
        	v.getCo().setText(CalcTotalEmissions()+"Kg");   
        	System.out.println(CalcTotalEmissions());
            v.getStartButton().setText("Continue");
        }
	}
	
	private void randomVehicles() {
		String vehicleID = "null";
		String vehicleType = "null";
		String crossTi = "0";
		String Direction = "null";
		String carLe = "0";
		String carEm = "0";
		String segmNo = "0";
		
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 9; i++) {
            if (i == 4) {
                sb.append("");
            } else if (i < 5) {
                sb.append((char) (random.nextInt(26) + 'A')); // Uppercase letter
            } else if (i < 7 ) {
                sb.append(random.nextInt(10)); // Digit
            } else {
                sb.append((char) (random.nextInt(26) + 'A')); // Lowercase letter
            }
        }
        vehicleID = sb.toString();
		
        int directNo = random.nextInt(3);
        int cartNo = random.nextInt(3);
        int segNo = random.nextInt(1,4);
		
		switch (directNo) {
	        case 0:
	            Direction = "Left";
	            break;
	        case 1:
	            Direction = "Straight";
	            break;
	        case 2:
	            Direction = "Right";
	            break;
		}
		
		switch (cartNo) {
	        case 0:
	            vehicleType = "BUS";
	            carLe = ""+random.nextInt(10,15);
	            carEm = "10";
	            crossTi = "8";
	            break;
	        case 1:
	            vehicleType = "CAR";
	            carLe = ""+random.nextInt(3,6);
	            carEm = "5";
	            crossTi = "3";
	            break;
	        case 2:
	            vehicleType = "TRUCK";
	            carLe = ""+random.nextInt(16,20);
	            carEm = "12";
	            crossTi = "8";
	            break;
		}
		
		switch (segNo) {
        	case 1:
	            segmNo = "1";
	            break;
	        case 2:
	            segmNo = "2";
	            break;
	        case 3:
	            segmNo = "3";
	            break;
	        case 4:
	            segmNo = "4";
	            break;
	        default:
	            segmNo = "1";
	            break;
		}
		
		Object[] newVehicle = new Object[] {
				vehicleID, 
				vehicleType,
				crossTi,
				Direction,
				carLe,
				carEm,
				segmNo
			};
			
			try {
				AddVehicle(newVehicle);
			} catch (DuplicateIdException e) {
				e.printStackTrace();
			}
		
	}

	
	
	
	
}
