package coursework;

public class MainExperimental {

	public static void main(String[] args) {
		Thread j = new Thread (new JunctionControler());
		j.start();
	}

}
