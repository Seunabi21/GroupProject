package view;

import controller.VController;

public class VApp {
	public static void main(String[] args) {
		Thread control = new Thread(new VController());
		control.start();
//		VController control = ;
	}
}
