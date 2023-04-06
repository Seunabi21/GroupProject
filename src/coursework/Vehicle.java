package coursework;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
* @author Ryan Spowart
* Models the vehicles used within the application
*/
public class Vehicle implements Runnable{
	
	String ID;		  // Registration plate equivalent
	String Type;	  // Car - Truck - Bus
	int CrossTime;    // Seconds
	String Direction; // Left - Right - Straight
	int Length;		  // Meters
	int Emission;	  // Grams of CO2 emitted
	String Status;	  // Waiting - driving?
	String Segment;
	public ShareLight light;
	int distance;
	/*
	 * Constructors
	 */
	public Vehicle(String ID, String Type, int CrossTime, int Length, String Direction, int Emission, String Segment) {
		this.ID = ID;
		this.Type = Type;
		this.CrossTime = CrossTime;
		this.Length = Length;
		this.Emission = Emission;
		this.Status = "Waiting";
		this.Segment = Segment;
	}
	/*
	 * Generates a vehicle from a generic object array
	 * throws a custom error message if the vehicle is incorrect
	 */
	public Vehicle(Object[] array) throws ValidationExeption {

		ValidateVehicle(array); // Validating here but allowing the call to process the alternate path if validation fails.
		this.ID = (String) array[0];
		this.Type = (String) array[1];
		this.CrossTime = Integer.parseInt((String) array[2]);
		this.Direction = (String) array[3];
		this.Length = Integer.parseInt((String) array[4]);
		this.Emission = Integer.parseInt((String) array[5]);
		this.Status = "Waiting";
		this.Segment = (String) array[6];

	}
	//Methods
	/*
	 * Calculates the emissions a car generates to cross the junction
	 */
	public double CrossEmisions() {
		return Math.round((Emission * (CrossTime / 60)) * 100.0) / 100.0;
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
		return distance + "," + ID + " , " + Status + " , " +  Type + " , " + CrossTime + " , " + Length + " , " + Direction + " , " + Emission + "}";
	}
	/*
	 * returns the Vehicle in the form of an Object array of info.
	 */
	public Object[] toObject() {
		Object[] output = {ID,Type,CrossTime,Direction,Length,Emission,Status,Segment};
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
	    	if(matchFound) {
				throw new ValidationExeption("Number Plate Incorrect");
			//Type Validation
			}else if(!((String)Vehicle[1]).equals("CAR") && !((String)Vehicle[1]).equals("TRUCK") && !((String)Vehicle[1]).equals("BUS")) {
				throw new ValidationExeption("Type Incorrect");
			//Cross Time Validation
			}else if(Integer.parseInt((String) Vehicle[2]) < 0 && Integer.parseInt((String) Vehicle[2]) > 60) {
				throw new ValidationExeption("Invalid Time");
			//Length Validation
			}else if(Integer.parseInt((String) Vehicle[4]) < 0 && Integer.parseInt((String) Vehicle[4]) > 25) {
				throw new ValidationExeption("Invalid Length");
			//Direction Validation
			}else if(!((String)Vehicle[3]).equals("Left") && !((String)Vehicle[3]).equals("Right") && !((String)Vehicle[3]).equals("Straight")) {
				throw new ValidationExeption("Invalid Direction");
			//Emissions
			}else if(Integer.parseInt((String) Vehicle[5]) < 0 && Integer.parseInt((String) Vehicle[5]) > 50) {
				throw new ValidationExeption("Invalid Emission");
			//Phase
			}else if(Integer.parseInt((String) Vehicle[6]) < 0 && Integer.parseInt((String) Vehicle[6]) > 4) {
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
		while(!light.getDone()) {
			if(distance == 0) {
				light.put(true);
			}else{
				distance -= light.getMove();
			}
		}
	}
}
