package coursework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class reportgenerate {

  public static void generateReport(List<Vehicle> crossedVehicles, String fileName) throws IOException {
    int totalEmission = 0;
    int totalWaitingLength = 0;
    int totalWaitingTime = 0;

    for (Vehicle vehicle : crossedVehicles) {
      totalEmission += vehicle.getEmission();
      totalWaitingLength += vehicle.getWaitingLength();
      totalWaitingTime += vehicle.getWaitingTime();
    }

    int averageEmission = 0;
    if (!crossedVehicles.isEmpty()) {
      averageEmission = totalEmission / crossedVehicles.size();
    }

    int averageWaitingLength = 0;
    if (!crossedVehicles.isEmpty()) {
      averageWaitingLength = totalWaitingLength / crossedVehicles.size();
    }

    int averageWaitingTime = 0;
    if (!crossedVehicles.isEmpty()) {
      averageWaitingTime = totalWaitingTime / crossedVehicles.size();
    }

    String report = "Total Emission," + totalEmission + "\n" +
                    "Average Emission," + averageEmission + "\n" +
                    "Total Waiting Length," + totalWaitingLength + "\n" +
                    "Average Waiting Length," + averageWaitingLength + "\n" +
                    "Average Waiting Time," + averageWaitingTime + "\n";

    FileWriter writer = new FileWriter(report);
    writer.write(report);
    writer.close();
  }

}
