package view;

/* @author Abiodun Oluwaseun
 * Display the Gui for the program
 * 
 */

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Model.VModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;


public class VView extends JFrame{
	private VModel VM;
	private JTable vehicleTable, phaseTable, statisticsTable, upstatisticsTable, upvehicleTable;
    private VModel vehicleModel, phaseModel, statisticsModel, upvehicleModel;
    private DefaultTableModel vehUp, statUp;
    JScrollPane vehPane = new JScrollPane();
    JScrollPane upPane = new JScrollPane();
    JScrollPane phPane = new JScrollPane();
    JScrollPane statPane = new JScrollPane();
    
    JTextField vehicleT = new JTextField();
    JTextField crossTimeT = new JTextField("0");
    JTextField lengthT = new JTextField("0");
    JTextField carEmissionT = new JTextField("0");
    JTextField carStatusT = new JTextField("Waiting");
    JComboBox<String> vehTypeT, directionT, segmentT;
    
    JButton addButton , exitButton;
	private JButton startButton;
    
    Font fontstyle = new Font("SansSerif", 0, 10);
	Font fontstyleBold = new Font("SansSerif", Font.BOLD, 10);
	Font fontstyleSmall = new Font("SansSerif", Font.BOLD, 8);
	Font fontstyleBig = new Font("SansSerif", 0, 14);
	Color primaryColor = new Color(25, 7, 60);
	Color secondaryColor = new Color(29, 112, 60);
	DefaultTableCellRenderer centerAlign = new DefaultTableCellRenderer();
    DefaultTableCellRenderer bgColor = new DefaultTableCellRenderer();
    JLabel co;
    
       
    public VView(Object[][] veh, Object[][] ph, Object [][] stat) {
    	
    	setTitle("Road Intersection");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(2,0,36));
        setSize(1250,700);
        setLocationRelativeTo(null);  
        setLayout(null);
        centerAlign.setHorizontalAlignment(JLabel.CENTER);
        bgColor.setBackground(new Color(29,112,60));
//        VM.addObserver(this);
        
        displayView(veh,ph,stat);
	        
     	//	LABELS
	        Icon vehIcon = new ImageIcon(getClass().getResource("/images/vehicles.png"));
	        Icon statIcon = new ImageIcon(getClass().getResource("/images/statistics.png"));
	        Icon phIcon = new ImageIcon(getClass().getResource("/images/phases.png"));
	        JLabel Topheader = new JLabel("VEHICLES", SwingConstants.CENTER );
	        Topheader.setIcon(vehIcon);
	        JLabel Topheader1 = new JLabel("PHASES", SwingConstants.CENTER);
	        Topheader1.setIcon(phIcon);
	        JLabel Topheader2 = new JLabel("STATISTICS", SwingConstants.CENTER);
	        Topheader2.setIcon(statIcon);
	        JLabel Topheader3 = new JLabel("CO2", SwingConstants.CENTER);
	        JLabel header = new JLabel("ADD VEHICLE", SwingConstants.CENTER);
	        JLabel vehicle = new JLabel("VEHICLE", SwingConstants.CENTER);
	        JLabel vehType = new JLabel("VEHICLE TYPE", SwingConstants.CENTER);
	        JLabel crossTime = new JLabel("CROSS TIME", SwingConstants.CENTER);
	        JLabel direction = new JLabel("DIRECTION", SwingConstants.CENTER);
	        JLabel length = new JLabel("CAR LENGTH", SwingConstants.CENTER);
	        JLabel carEmission = new JLabel("EMISSION", SwingConstants.CENTER);
	        JLabel carStatus = new JLabel("STATUS", SwingConstants.CENTER);
	        JLabel segment = new JLabel("SEGMENT", SwingConstants.CENTER);
        //END OF LABELS

        //	TEXTFIELDS
	        vehicleT.setMargin(new Insets(5,10,5,5));
	        
	        crossTimeT.setMargin(new Insets(5,10,5,5));
	        crossTimeT.addKeyListener(new KeyAdapter() {
	            public void keyPressed(KeyEvent ke) {
	               if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ) {
	                  crossTimeT.setEditable(true);
	               } else {
	                  crossTimeT.setEditable(false);
	               }
	            }
	         });
	        
	        lengthT.setMargin(new Insets(5,10,5,5));
	        lengthT.addKeyListener(new KeyAdapter() {
	            public void keyPressed(KeyEvent ke) {
	               if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ) {
	                  lengthT.setEditable(true);
	               } else {
	                  lengthT.setEditable(false);
	               }
	            }
	         });
	        
	        carEmissionT.setMargin(new Insets(5,10,5,5));
	        carEmissionT.addKeyListener(new KeyAdapter() {
	            public void keyPressed(KeyEvent ke) {
	               if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ) {
	                  carEmissionT.setEditable(true);
	               } else {
	                  carEmissionT.setEditable(false);
	               }
	            }
	         });
	        
	        carStatusT.setMargin(new Insets(5,10,5,5));
	        carStatusT.setEditable(false);
	        
	        String v1[] = { "","BUS","CAR","TRUCK" };
	        String d1[] = { "","Left", "Right", "Straight" };
	        String s1[] = { "1","2","3","4" };
	        
	        vehTypeT =  new JComboBox<String>(v1);
	        directionT =  new JComboBox<String>(d1);
	        segmentT =  new JComboBox<String>(s1);
	        
        //	END OF TEXTBOX
	        
	        
        //	BUTTONS
	        Icon addIcon = new ImageIcon(getClass().getResource("/images/add.png"));
	        Icon exitIcon = new ImageIcon(getClass().getResource("/images/exit.png"));
	        Icon cancelIcon = new ImageIcon(getClass().getResource("/images/start.png"));
	        
	        addButton = new JButton("Add Vehicle",addIcon);
	        addButton.setBackground(new Color(3, 192, 60));
	        addButton.setForeground(new Color(255, 255, 255));
	        
	        exitButton = new JButton("Exit", exitIcon);
	        exitButton.setBackground(new Color(128, 0, 32));
	        exitButton.setForeground(new Color(255, 255, 255));
	        
	        setStartButton(new JButton ("Start", cancelIcon));
	        getStartButton().setBackground(new Color(31, 117, 254));
	        getStartButton().setForeground(new Color(255, 255, 255));
        //	END OF BUTTONS
	        
	        
	        
	    //	POSITIONS 
	        // HEADER POSITIONS
	        	co = new JLabel(" kg",SwingConstants.CENTER);
		        co.setBounds(940,330,130,60);
		        co.setFont(new Font("SansSerif", Font.BOLD, 18));
		        co.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        co.setBackground(Color.WHITE);
		        co.setForeground(Color.WHITE);
		        add(co);
		        
		        header.setBounds(335, 410,150,60);
		        header.setFont(fontstyle);
		        header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        header.setBackground(primaryColor);
		        header.setForeground(Color.WHITE);
		        add(header);
		        
		        Topheader.setBounds(250,15,150,40);
		        Topheader.setFont(new Font("SansSerif", Font.BOLD, 18));
		        Topheader.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        Topheader.setBackground(Color.WHITE);
		        Topheader.setForeground(Color.WHITE);
		        add(Topheader);
		        
		        Topheader1.setBounds(570,15,150,40);
		        Topheader1.setFont(new Font("SansSerif", Font.BOLD, 18));
		        Topheader1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        Topheader1.setBackground(Color.white);
		        Topheader1.setForeground(Color.WHITE);
		        add(Topheader1);
		        
		        Topheader2.setBounds(950,15,150,40);
		        Topheader2.setFont(new Font("SansSerif", Font.BOLD, 18));
		        Topheader2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        Topheader2.setBackground(Color.white);
		        Topheader2.setForeground(Color.WHITE);
		        add(Topheader2);
		        
		        Topheader3.setBounds(930,300,150,40);
		        Topheader3.setFont(new Font("SansSerif", Font.BOLD, 18));
		        Topheader3.setForeground(Color.WHITE);
		        add(Topheader3);
	        //	END OF HEADER POSITIONS
	        
	        
	        //	ADD VEHICLES DETAILS POSITIONS
			    vehicle.setBounds(20, 500,100,40);
			    vehicle.setFont(fontstyleBold);
			    vehicle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			    vehicle.setBackground(primaryColor);
			    vehicle.setForeground(Color.WHITE);
			    add(vehicle);
			    vehicleT.setBounds(20,560,100,30);
			    add(vehicleT);
			    
		        vehType.setBounds(130, 500,100,40);
		        vehType.setFont(fontstyleBold);
		        vehType.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        vehType.setBackground(primaryColor);
		        vehType.setForeground(Color.WHITE);
		        add(vehType);
		        vehTypeT.setBounds(130,560,100,30);
		        add(vehTypeT);
		        
		        crossTime.setBounds(240, 500,100,40);
		        crossTime.setFont(fontstyleBold);
		        crossTime.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        crossTime.setBackground(primaryColor);
		        crossTime.setForeground(Color.WHITE);
		        add(crossTime);
		        crossTimeT.setBounds(240,560,100,30);
		        add(crossTimeT);
		        
		        direction.setBounds(350, 500,100,40);
		        direction.setFont(fontstyleBold);
		        direction.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        direction.setBackground(primaryColor);
		        direction.setForeground(Color.WHITE);
		        add(direction);
		        directionT.setBounds(350,560,100,30);
		        add(directionT);
		        
		        length.setBounds(460, 500,100,40);
		        length.setFont(fontstyleBold);
		        length.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		        length.setBackground(primaryColor);
		        length.setForeground(Color.WHITE);
		        add(length);
		        lengthT.setBounds(460,560,100,30);
		        add(lengthT);
		        
			    carEmission.setBounds(570, 500,100,40);
			    carEmission.setFont(fontstyleBold);
			    carEmission.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			    carEmission.setBackground(primaryColor);
			    carEmission.setForeground(Color.WHITE);
			    add(carEmission);
			    carEmissionT.setBounds(570,560,100,30);
			    add(carEmissionT);
			    
			    carStatus.setBounds(680, 500,100,40);
			    carStatus.setFont(fontstyleBold);
			    carStatus.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			    carStatus.setBackground(primaryColor);
			    carStatus.setForeground(Color.WHITE);
			    add(carStatus);
			    carStatusT.setBounds(680,560,100,30);
			    add(carStatusT);
			    
			    segment.setBounds(790, 500,100,40);
			    segment.setFont(fontstyleBold);
			    segment.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			    segment.setBackground(primaryColor);
			    segment.setForeground(Color.WHITE);
			    add(segment);
			    segmentT.setBounds(790,560,100,30);
			    add(segmentT);
			    
		    // END OF ADD VEHICLE DETAILS POSITION
		    
			    
		    // BUTTON POSITIONS
			    addButton.setBounds(20,610,150,30);
			    add(addButton);
			    
			    getStartButton().setBounds(250,610,150,30);
			    add(getStartButton());
			    
			    exitButton.setBounds(1000,610,150,30);
			    add(exitButton);
		    //	END OF BUTTON POSITION
    	
        setVisible(true);
    }
    
    
    public void vehicleTable(Object[][] data1) {
    	String [] vehicleColumnNames = {
			"VEHICLE", "TYPE", "CROSSING TIME", "DIRECTION", "LENGTH", "EMISSION", "STATUS", "SEGMENT"
    	};
    	vehicleModel = new VModel(data1, vehicleColumnNames);
        vehicleTable = new JTable(vehicleModel);
        
    	//	VEHICLE SETTING
		vehicleTable.setAutoCreateRowSorter(true);
	    vehicleTable.setBackground(primaryColor);
        vehicleTable.setFont(fontstyle);
        vehicleTable.setForeground(new Color(255, 255, 255));
        vehicleTable.setGridColor(new Color(0, 0, 0));
        vehicleTable.setRowHeight(30);
        
        //	VEHICLES TABLE HEADER 
        JTableHeader vehiclesTH = vehicleTable.getTableHeader();
        vehiclesTH.setPreferredSize(new Dimension(10,50));
        vehiclesTH.setFont(fontstyle);
        vehiclesTH.setAlignmentY(CENTER_ALIGNMENT);
        vehiclesTH.setBackground(secondaryColor);
        vehiclesTH.setForeground(new Color(255,255,255));
        
    	// VEHICLES TABLE COLUMN
        TableColumnModel vehicleMod = vehicleTable.getColumnModel();
        vehicleMod.getColumn(2).setPreferredWidth(120);
        vehicleMod.getColumn(3).setPreferredWidth(80);
        vehicleMod.getColumn(7).setPreferredWidth(80);
        int colCount = vehicleMod.getColumnCount();
        for(int i=0; i<colCount; i++){vehicleMod.getColumn(i).setCellRenderer(centerAlign);}
        
        vehPane = new JScrollPane(vehicleTable);
        vehPane.setBounds(20, 60,530,230);
	    add(vehPane);
    }
    
    public void phaseTable(Object[][] data2) {
    	String [] phColumnNames = {
	            "PHASE", "PHASE DURATION"
        };
    	phaseModel = new VModel(data2, phColumnNames);
        phaseTable = new JTable(phaseModel);
        
    	//	PHASES SETTINGS
	    phaseTable.setAutoCreateRowSorter(true);
	    phaseTable.setBackground(primaryColor);
        phaseTable.setFont(fontstyle);
        phaseTable.setForeground(new Color(255, 255, 255));
        phaseTable.setGridColor(new Color(0, 0, 0));
        phaseTable.setRowHeight(30);
        
        //	PHASES TABLE HEADER 
        JTableHeader phaseTH = phaseTable.getTableHeader();
        phaseTH.setPreferredSize(new Dimension(10,50));
        phaseTH.setFont(fontstyle);
        phaseTH.setAlignmentY(CENTER_ALIGNMENT);
        phaseTH.setBackground(secondaryColor);
        phaseTH.setForeground(new Color(255,255,255));
        
        //	PHASE TABLE COLUMN
	    TableColumnModel phaseMod = phaseTable.getColumnModel();
	    int colCount1 = phaseMod.getColumnCount();
	    for(int i=0; i<colCount1; i++){phaseMod.getColumn(i).setCellRenderer(centerAlign);}
	    
        phPane = new JScrollPane(phaseTable);
        phPane.setBounds(570, 60,200,295);
        add(phPane);
    }
    
    public void statisticsTable(Object[][] data3) {
    	String [] statColumnNames = {
        	"SEGMENT", "WAITING TIME", "WAITING LENGTH", "CROSS TIME"
    	};
    	statisticsModel = new VModel(data3, statColumnNames);
        statisticsTable = new JTable(statisticsModel);
        
    	//	STATISTICS SETTINGS
	    statisticsTable.setAutoCreateRowSorter(true);
	    statisticsTable.setBackground(primaryColor);
        statisticsTable.setFont(fontstyle);
        statisticsTable.setForeground(new Color(255, 255, 255));
        statisticsTable.setGridColor(new Color(0, 0, 0));
        statisticsTable.setRowHeight(30);
        
        //	STATISTICS TABLE HEADER 
        JTableHeader statTH = statisticsTable.getTableHeader();
        statTH.setPreferredSize(new Dimension(10,50));
        statTH.setFont(fontstyle);
        statTH.setAlignmentY(CENTER_ALIGNMENT);
        statTH.setBackground(secondaryColor);
        statTH.setForeground(new Color(255,255,255));
        
        //	STATISTICS TABLE COLUMN
        TableColumnModel statMod = statisticsTable.getColumnModel();
        int statcolCount = statMod.getColumnCount();
        for(int i=0; i<statcolCount; i++){statMod.getColumn(i).setCellRenderer(centerAlign);}
        
        statPane = new JScrollPane(statisticsTable);
        statPane.setBounds(790, 60,400,175);
        add(statPane);
	        
	        
    }
    
    public void vehTabUP(Object[][] data1) {
    	String [] vehicleColumnNames = {
    			"VEHICLE", "TYPE", "CROSSING TIME", "DIRECTION", "LENGTH", "EMISSION", "STATUS", "SEGMENT"
        	};
    	vehUp = new DefaultTableModel(data1, vehicleColumnNames);
    	vehicleTable.setModel(vehUp);
    }
    
    public void statTabUP(Object[][] data1) {
    	String [] statColumnNames = {
            	"SEGMENT", "WAITING TIME", "WAITING LENGTH", "CROSS TIME"
        	};
    	statUp = new DefaultTableModel(data1, statColumnNames);
    	statisticsTable.setModel(statUp);
    }
    
    public void displayView(Object[][] veh, Object[][] ph, Object [][] stat) {
    	vehicleTable(veh);
    	phaseTable(ph);
    	statisticsTable(stat);
    }
    
    public void addVehicle(ActionListener listenAdd) {
    	addButton.addActionListener(listenAdd);
    }
    
    public void updateView(Object[][] vehi, Object[][] stati ){
    	vehTabUP(vehi);
    	statTabUP(stati);
    }
    
	public void exitGui(ActionListener exitApp) {
    	exitButton.addActionListener(exitApp);
    }
	
    public void startSim(ActionListener startSimulation) {
    	getStartButton().addActionListener(startSimulation);
//    	VM.setPressed(true);
    }
    
    public JTextField getVehicleT() {
		return vehicleT;
	}

	public JTextField getCrossTimeT() {
		return crossTimeT;
	}

	public JTextField getLengthT() {
		return lengthT;
	}

	public JTextField getCarEmissionT() {
		return carEmissionT;
	}

	public JTextField getCarStatusT() {
		return carStatusT;
	}

	public JComboBox<String> getVehTypeT() {
		return vehTypeT;
	}

	public JComboBox<String> getDirectionT() {
		return directionT;
	}

	public JComboBox<String> getSegmentT() {
		return segmentT;
	}
    
	public JLabel getCo() {
		return co;
	}

	public void setCo(JLabel co) {
		this.co = co;
	}


	public JButton getStartButton() {
		return startButton;
	}


	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}


	


    
}

