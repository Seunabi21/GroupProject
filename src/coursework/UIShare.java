package coursework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class UIShare {
	private HashMap <String, Queue <Vehicle> > Phase; //Stores the phase and queue of vehicles
	private HashMap <String, Statistics> PhaseStats; //Stores the statistics attributed to each phase
	private ArrayList<Integer> PhaseTime;
	private boolean update;
	
	public UIShare(){
		Phase = new HashMap<>();
		PhaseStats = new HashMap<String, Statistics>();
		PhaseTime = new ArrayList<Integer>();
		update = false;
	}
	
	public void setUI(HashMap <String, Queue <Vehicle> > Phase,HashMap <String, Statistics> PhaseStats,ArrayList<Integer> PhaseTime) {
		this.Phase = Phase;
		this.PhaseStats = PhaseStats;
		this.PhaseTime = PhaseTime;
		update = true;
		notifyAll();
	}
	
	public synchronized boolean Updated() {
		while(!update) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		update = false;
		return true;
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
			Segments[s/2][1] = (Integer)Segments[s/2][1] + PhaseStats.get(""+s).WaitTime;
			Segments[s/2][2] = (Integer)Segments[s/2][2] + PhaseStats.get(""+s).WaitLength;
			Segments[s/2][3] = (Integer)Segments[s/2][3] + PhaseStats.get(""+s).CrossTime;

		}
		
		return Segments;
	}
	
	/*
	 * Calculates the total emissions
	 */
	public int CalcTotalEmissions() {
		int total = 0;
		for (int s = 0; s < PhaseStats.size(); s++) { //summing all emissions stored
			total += PhaseStats.get("" + s).Emissions;
		}
		
		return total;
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
			totalTme += PhaseStats.get("" + s).WaitTime;
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
					if(v.Direction.equals("Right") || v.Direction.equals("Straight")) { // Even -- Right & Strait
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
}
