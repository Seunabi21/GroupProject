package Model;
/* @author Ryan
 * Tracks statistical information for output
 * 
 */
public class statistics {
	private int WaitTime;
	private int WaitLength;
	private int CrossTime;
	private int Emissions;
	
	//Constructor
	public statistics() {
		setWaitTime(0);
		setWaitLength(0);
		setCrossTime(0);
		setEmissions(0);
		
	}
	
	public void addWait(int WaitTime) {
		this.setWaitTime(this.getWaitTime() + WaitTime);
	}
	public void addLength(int WaitLength) {
		this.setWaitLength(this.getWaitLength() + WaitLength);
	}
	public void addCrosstime(int CrossTime) {
		this.setCrossTime(this.getCrossTime() + CrossTime);
	}
	public void addEmissions(int Emissions) {
		this.setEmissions(this.getEmissions() + Emissions);
	}
	
	public String toString() {
		return " " + getWaitTime() + "s | " + getWaitLength() + "m | " +  getCrossTime() + "s | " + getEmissions() + "g \n";
		
	}
	public Object[] toObject() {
		Object[] output = {getWaitTime(),getWaitLength(),getCrossTime()};
		return output;
	}

	public int getWaitTime() {
		return WaitTime;
	}

	public void setWaitTime(int waitTime) {
		WaitTime = waitTime;
	}

	public int getWaitLength() {
		return WaitLength;
	}

	public void setWaitLength(int waitLength) {
		WaitLength = waitLength;
	}

	public int getCrossTime() {
		return CrossTime;
	}

	public void setCrossTime(int crossTime) {
		CrossTime = crossTime;
	}

	public int getEmissions() {
		return Emissions;
	}

	public void setEmissions(int d) {
		Emissions = d;
	}
}

