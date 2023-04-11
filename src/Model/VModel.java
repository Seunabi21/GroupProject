package Model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import controller.observer;

public class VModel extends DefaultTableModel{
	private DefaultTableModel vehicleModel;
   	private DefaultTableModel phaseModel;
   	private DefaultTableModel statModel;
    
	
	private boolean isCrossed = false;

	private List<observer> observers = new ArrayList<>();
	 
	public VModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
//		vehicleModel = new DefaultTableModel(data, columnNames);
//	    phaseModel = new DefaultTableModel(data, columnNames);
//	    statModel = new DefaultTableModel(data, columnNames);
//		
	}

	public void addObserver(observer observer) {
        observers.add(observer);
    }

    public void removeObserver(observer observer) {
        observers.remove(observer);
    }

    public void setPressed(boolean isCrossed) {
        this.isCrossed = isCrossed;
        notifyObservers();
    }

    private void notifyObservers() {
        for (observer observer : observers) {
            observer.update(this);
        }
    }
    
    public DefaultTableModel getVehicleModel() {
		return vehicleModel;
	}

	public DefaultTableModel getPhaseModel() {
		return phaseModel;
	}
	
	public DefaultTableModel getStatModel() {
		return statModel;
	}

}
