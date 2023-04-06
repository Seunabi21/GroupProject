package coursework;
/**
*
* @author Ryan Spowart
* Models the Statistics stored for each Phase
* These are combined externally to form Segment Statistics
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
	public void addWait(int WaitTime) {
		this.WaitTime += WaitTime;
	}
	public void addLength(int WaitLength) {
		this.WaitLength += WaitLength;
	}
	public void addCrosstime(int CrossTime) {
		this.CrossTime += CrossTime;
	}
	public void addEmissions(int Emissions) {
		this.Emissions += Emissions;
	}
	
	public String toString() {
		return " " + WaitTime + "s | " + WaitLength + "m | " +  CrossTime + "s | " + Emissions + "g \n";
		
	}
	public Object[] toObject() {
		Object[] output = {WaitTime,WaitLength,CrossTime};
		return output;
	}
}
