package Model;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import view.ValidationExeption;



/**
* @author Ryan Spowart
* Models the vehicles used within the application
*/
public class Vehicle implements Runnable {
	
	private String ID;		  // Registration plate equivalent
	String Type;	  // Car - Truck - Bus
	int CrossTime;    // Seconds
	String Direction; // Left - Right - Straight
	int Length;		  // Meters
	int Emission;	  // Grams of CO2 emitted
	String Status;	  // Waiting - driving?
	String Segment;
	VehicleShare vShare;
	int distance;
	
	/*
	 * Constructors
	 */
	public Vehicle(String ID, String Type, int CrossTime, int Length, String Direction, int Emission, String Segment) {
		this.setID(ID);
		this.Type = Type;
		this.setCrossTime(CrossTime);
		this.setLength(Length);
		this.Emission = Emission;
		this.setStatus("Waiting");
		this.Segment = Segment;
		this.Direction = Direction;
	}
	
	public Vehicle(String ID, String Type, int CrossTime, int Length, String Direction, int Emission, String Segment,VehicleShare light,int Distance) {
		this.setID(ID);
		this.Type = Type;
		this.setCrossTime(CrossTime);
		this.setLength(Length);
		this.Emission = Emission;
		this.setStatus("Waiting");
		this.Segment = Segment;
		this.vShare = light;
		this.distance = Distance;
		this.Direction = Direction;

	}
	
	/*
	 * Generates a vehicle from a generic object array
	 * throws a custom error message if the vehicle is incorrect
	 */
	public Vehicle(Object[] array) throws ValidationExeption {

		ValidateVehicle(array); // Validating here but allowing the call to process the alternate path if validation fails.
		this.setID((String) array[0]);
		this.Type = (String) array[1];
		this.setCrossTime(Integer.parseInt((String) array[2]));
		this.setDirection((String) array[3]);
		this.setLength(Integer.parseInt((String) array[4]));
		this.Emission = Integer.parseInt((String) array[5]);
		this.setStatus("Waiting");
		this.Segment = (String) array[6];

	}
	//Methods
	/*
	 * Calculates the emissions a car generates to cross the junction
	 */
	public double CrossEmisions() {
		return Math.round((Emission * (getCrossTime() / 60)) * 100.0) / 100.0;
	}
	/*
	 * Calculates the emissions a vehicle generates while waiting an amount of time
	 */
	public double WaitEmissions(double wait) {
		return Math.round((Emission * (wait / 60)) * 100.0) / 100.0;
	}
	/*
	 * Overwrites original to String with a custom version
	 */
	public String toString() {
		return getID() + " , " + getStatus() + " , " +  Type + " , " + getCrossTime() + " , " + getLength() + " , " + getDirection() + " , " + Emission + "}";
	}
	/*
	 * returns the Vehicle in the form of an Object array of info.
	 */
	public Object[] toObject() {
		Object[] output = {getID(),Type,getCrossTime(),getDirection(),getLength(),Emission,getStatus(),Segment};
		return output;
	}
	/*
	 * Throws an exception if the vehicle input is incorrect.
	 */
	private void ValidateVehicle(Object[] Vehicle) throws ValidationExeption {
	    try {
			//Regex comparison for number plate
			Pattern pattern = Pattern.compile("/\\b[a-z]{2}([1-9]|0[2-9]|6[0-9]|1[0-9])[a-z]{3}\\b/i", Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher((String)Vehicle[0]);
		    boolean matchFound = matcher.find();
		    //Checking regex match
	    	if(matchFound || ((String)Vehicle[0]).isEmpty() ) {
				throw new ValidationExeption("Number Plate Incorrect");
			//Type Validation
			}else if(!((String)Vehicle[1]).equals("CAR") && !((String)Vehicle[1]).equals("TRUCK") && !((String)Vehicle[1]).equals("BUS") && ((String)Vehicle[1]).isEmpty()  ) {
				throw new ValidationExeption("Car Type Incorrect");
			//Cross Time Validation
			}else if((Integer.parseInt((String) Vehicle[2]) < 1) || (Integer.parseInt((String) Vehicle[2]) > 60)) {
				throw new ValidationExeption("Invalid Cross Time");
			//Direction Validation
			}else if(!((String)Vehicle[3]).equals("Left") && !((String)Vehicle[3]).equals("Right") && !((String)Vehicle[3]).equals("Straight")) {
				throw new ValidationExeption("Invalid Direction");
			//Length Validation
			}else if((Integer.parseInt((String) Vehicle[4]) < 1) || (Integer.parseInt((String) Vehicle[4]) > 25)) {
				throw new ValidationExeption("Invalid Car Length");
			//Emissions
			}else if((Integer.parseInt((String) Vehicle[5]) < 1) || (Integer.parseInt((String) Vehicle[5]) > 50)) {
				throw new ValidationExeption("Invalid Car Emission");
			//Phase
			}else if((Integer.parseInt((String) Vehicle[6]) < 1) || (Integer.parseInt((String) Vehicle[6]) > 4)) {
				throw new ValidationExeption("Invalid Phase");
			}
	    //Other Possible Exceptions
	    }catch(NumberFormatException e) {
	    	throw new ValidationExeption("Invalid Data Format");
	    }catch(NullPointerException e) {
	    	throw new ValidationExeption("Missing Field");
	    }
		
	}
	
	@Override
	public void run() {
		while(!vShare.getDone()) {
			if(vShare.getWaitG().equals("G")) {
				System.out.println(distance);
				if(distance == 0) {
					vShare.putWait(true);
				}else{
					distance -= vShare.getMove();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}

	public int getCrossTime() {
		return CrossTime;
	}

	public void setCrossTime(int crossTime) {
		CrossTime = crossTime;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getLength() {
		return Length;
	}

	public void setLength(int length) {
		Length = length;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
