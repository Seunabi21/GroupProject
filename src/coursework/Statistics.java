package coursework;
/**
*
* @author Ryan
*/
public class Statistics {
	int WaitTime;
	int WaitLength;
	int CrossTime;
	int Emissions;
	
	//Constructor
	public Statistics() {
		WaitTime = 0;
		WaitLength = 0;
		CrossTime = 0;
		Emissions = 0;
		
	}
	public String toString() {
		return " " + WaitTime + "s | " + WaitLength + "m | " +  CrossTime + "s | " + Emissions + "g \n";
		
	}
	public Object[] toObject() {
		Object[] output = {WaitTime,WaitLength,CrossTime};
		return output;
	}
}
