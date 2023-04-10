package view;
import Model.UIShare;
import controller.VController;

public class VApp {
	public static void main(String[] args) {
		UIShare uiContent = new UIShare();
		Thread control = new Thread(new VController(uiContent));
		control.start();
//		VController control = ;
	}
}
