package Model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import controller.observer;

public class VModel extends DefaultTableModel{
	private boolean isCrossed = false;

	private List<observer> observers = new ArrayList<>();
	 
	public VModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
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
}
