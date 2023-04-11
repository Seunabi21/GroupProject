package view;

import controller.ReportLog;
import controller.VController;

public class VApp {
	public static void main(String[] args) {
		ReportLog report = ReportLog.getInstance();
		VController controller = new VController();
		Thread control = new Thread(controller);
		control.start();
		Thread randomThread = new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(6 * 1000);
					controller.randomVehicles();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		randomThread.start();
	}
}
