package coursework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VehicleTest {
	
	Vehicle testVehicle = new Vehicle("SA11LLY","CAR",10,10,"Left",20,"4");
	//Testing cross emissions calculations
	@Test
	void TestCrossEmissions() {
		int expected1 = 10;
		testVehicle.Emission = 10;
		testVehicle.CrossTime = 60;
		String Error1 = "Cross Emission Calculation Failed";
		assertEquals(expected1,testVehicle.CrossEmisions(),Error1);
		
		double expected2 = 1.67;
		testVehicle.Emission = 10;
		testVehicle.CrossTime = 5;
		String Error2 = "Cross Emission Variant Calculation Failed";
		assertEquals(expected2,testVehicle.WaitEmissions(10),Error2);
	}
	//Testing wait meission calculations
	@Test
	void TestWaitEmissions() {
		int expected1 = 10;
		testVehicle.Emission = 10;
		String Error1 = "Wait Emission Calculation Failed";
		assertEquals(expected1,testVehicle.WaitEmissions(60),Error1);
		
		double expected2 = 0.83;
		testVehicle.Emission = 5;
		String Error2 = "Wait Emission Variant Calculation Failed";
		assertEquals(expected2,testVehicle.WaitEmissions(10),Error2);
		
	}

}
