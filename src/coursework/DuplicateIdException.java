package coursework;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DuplicateIdException extends Exception{
	public DuplicateIdException(String d)
    {
    	super(d);
        JFrame error = new JFrame("Error Message");
		JOptionPane.showMessageDialog(error,d);
    }
}
