package view;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ValidationExeption extends Exception {

    public ValidationExeption(String s)
    {
    	super(s);
        JFrame error = new JFrame("Error Message");
		JOptionPane.showMessageDialog(error,s);
    }
	
}
