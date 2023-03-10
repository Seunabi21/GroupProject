package coursework;
//package coursework;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;

public class courseGui extends JFrame {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 5006679112448885001L;
	
	static JunctionControler junc;
	
	//	TABLES
		//	VEHICLES TABLE
		JTable vehiclesTable = new JTable();
		String [] vehicleColumnNames = {
			"VEHICLE", "TYPE", "CROSSING TIME", "DIRECTION", "LENGTH", "EMISSION", "STATUS", "SEGMENT"
	    };
        DefaultTableModel vehModel = new DefaultTableModel(junc.vehToObj(),vehicleColumnNames);
        
        //	PHASE TABLES
        JTable phaseTable = new JTable();
        String [] phColumnNames = {
	            "PHASE", "PHASE DURATION"
	        };
        DefaultTableModel phModel = new DefaultTableModel(junc.phaseToObj(), phColumnNames);
		
        //	STATISTICS TABLE
        JTable statisticsTable = new JTable();
        String [] statColumnNames = {
        	"SEGMENT", "WAITING TIME", "WAITING LENGTH", "CROSS TIME"
    	};
        DefaultTableModel statModel = new DefaultTableModel(junc.segToObj(), statColumnNames);
        
        
	//	DEFAULT TABLE RENDERER
        DefaultTableCellRenderer centerAlign = new DefaultTableCellRenderer();
        DefaultTableCellRenderer bgColor = new DefaultTableCellRenderer();
        
		
	//	SCROLLPANE
        JScrollPane vehiclePane;
        JScrollPane phasePane;
        JScrollPane statPane;
        
    //	CO2 LABEL
        JLabel co = new JLabel(junc.CalcTotalEmissions()+" kg",SwingConstants.CENTER);
        
        
        private void update() {
        	centerAlign.setHorizontalAlignment(JLabel.CENTER);
            bgColor.setBackground(new Color(29,112,60)); 
        	
            //	UPDATED VEHICLE TABLE 
	            DefaultTableModel vehModelUpd = new DefaultTableModel(
	        			junc.vehToObj()
	                    ,
	                    new String [] {
	                    		"VEHICLE", "TYPE", "CROSSING TIME", "DIRECTION", "LENGTH", "EMISSION", "STATUS", "SEGMENT"
	                    }
	            );
	            vehiclesTable.setModel(vehModelUpd);
	            
        	//	NEW UPDATED VEHICLE TABLE MODEL SETTINGS
	        	TableColumnModel vehicleModUpd = vehiclesTable.getColumnModel();
		    	vehicleModUpd.getColumn(2).setPreferredWidth(120);
		        vehicleModUpd.getColumn(3).setPreferredWidth(80);
		        vehicleModUpd.getColumn(7).setPreferredWidth(80);
	        
	        //	VEHICLES TABLE CENTER
		        int colCount = vehicleModUpd.getColumnCount();
		        for(int i=0; i<colCount; i++){vehicleModUpd.getColumn(i).setCellRenderer(centerAlign);}
	    	
		        
        	//	UPDATED STATISTICS TABLE
		        DefaultTableModel statModelUpd = new DefaultTableModel(
        			junc.segToObj()
                    ,
                    new String [] {
                    	"SEGMENT", "WAITING TIME", "WAITING LENGTH", "CROSS TIME"
                    }
	            );
	            statisticsTable.setModel(statModelUpd);
	            
        	//	NEW UPDATED STATISTICS TABLE MODEL SETTINGS
	            TableColumnModel statModUpd = statisticsTable.getColumnModel();
		        int statcolCount = statModUpd.getColumnCount();
		        for(int i=0; i<statcolCount; i++){statModUpd.getColumn(i).setCellRenderer(centerAlign);}
        	
            //	UPDATED CO VALUE
	            co.setText(junc.CalcTotalEmissions()+" kg");
        }
        
    
	public courseGui() {
		setTitle("Road Intersection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(2,0,36));
        setSize(1250,700);
        setLocationRelativeTo(null);  
        setLayout(null);
        
        
        Font fontstyle = new Font("SansSerif", 0, 9);
        Font fontstyleBold = new Font("SansSerif",Font.BOLD,10);
        //	Font fontstyleSmall = new Font("SansSerif", 0, 10);
        Color primaryColor = new Color(25, 7, 60);
        Color secondaryColor = new Color(29,112,60);
        centerAlign.setHorizontalAlignment(JLabel.CENTER);
        bgColor.setBackground(new Color(29,112,60));   
        
        
        //	TABLES MODEL
        	vehiclesTable.setModel(vehModel);
		    phaseTable.setModel(phModel);
		    statisticsTable.setModel(statModel);
	        
	    //	TABLES COLUMN MODEL
		    TableColumnModel vehicleMod = vehiclesTable.getColumnModel();
	    	TableColumnModel phaseMod = phaseTable.getColumnModel();
	    	TableColumnModel statMod = statisticsTable.getColumnModel();
	    	
        //	VEHICLES TABLE 
	        //	VEHICLE SETTINGS
			    vehiclesTable.setAutoCreateRowSorter(true);
			    vehiclesTable.setBackground(primaryColor);
		        vehiclesTable.setFont(fontstyle);
		        vehiclesTable.setForeground(new Color(255, 255, 255));
		        vehiclesTable.setGridColor(new Color(0, 0, 0));
		        vehiclesTable.setRowHeight(30);
		        vehicleMod.getColumn(2).setPreferredWidth(120);
		        vehicleMod.getColumn(3).setPreferredWidth(80);
		        vehicleMod.getColumn(7).setPreferredWidth(80);
		        
		    //	VEHICLES TABLE CENTER
		        int colCount = vehicleMod.getColumnCount();
		        for(int i=0; i<colCount; i++){vehicleMod.getColumn(i).setCellRenderer(centerAlign);}
		        
		    //	VEHICLES TABLE HEADER 
		        JTableHeader vehiclesTH = vehiclesTable.getTableHeader();
		        vehiclesTH.setPreferredSize(new Dimension(10,50));
		        vehiclesTH.setFont(fontstyle);
		        vehiclesTH.setAlignmentY(CENTER_ALIGNMENT);
		        vehiclesTH.setBackground(secondaryColor);
		        vehiclesTH.setForeground(new Color(255,255,255));
	    
		    vehiclePane = new JScrollPane(vehiclesTable);
	    //	END OF VEHICLE TABLE 
		    
		    
	    //	PHASES TABLE 
	        //	PHASES SETTINGS
			    phaseTable.setAutoCreateRowSorter(true);
			    phaseTable.setBackground(primaryColor);
		        phaseTable.setFont(fontstyle);
		        phaseTable.setForeground(new Color(255, 255, 255));
		        phaseTable.setGridColor(new Color(0, 0, 0));
		        phaseTable.setRowHeight(30);
		        int colCount1 = phaseMod.getColumnCount();
		        for(int i=0; i<colCount1; i++){phaseMod.getColumn(i).setCellRenderer(centerAlign);}
		      
	        //	PHASES TABLE HEADER 
		        JTableHeader phaseTH = phaseTable.getTableHeader();
		        phaseTH.setPreferredSize(new Dimension(10,50));
		        phaseTH.setFont(fontstyle);
		        phaseTH.setAlignmentY(CENTER_ALIGNMENT);
		        phaseTH.setBackground(secondaryColor);
		        phaseTH.setForeground(new Color(255,255,255));
	        
	        phasePane = new JScrollPane(phaseTable);
        // 	END OF PHASES TABLE
	        
	        
	        
        //	STATISTICS TABLE 
	        //	STATISTICS SETTINGS
			    statisticsTable.setAutoCreateRowSorter(true);
			    statisticsTable.setBackground(primaryColor);
		        statisticsTable.setFont(fontstyle);
		        statisticsTable.setForeground(new Color(255, 255, 255));
		        statisticsTable.setGridColor(new Color(0, 0, 0));
		        statisticsTable.setRowHeight(30);
		        int statcolCount = statMod.getColumnCount();
		        for(int i=0; i<statcolCount; i++){statMod.getColumn(i).setCellRenderer(centerAlign);}
		      
	        //	STATISTICS TABLE HEADER 
		        JTableHeader statTH = statisticsTable.getTableHeader();
		        statTH.setPreferredSize(new Dimension(10,50));
		        statTH.setFont(fontstyle);
		        statTH.setAlignmentY(CENTER_ALIGNMENT);
		        statTH.setBackground(secondaryColor);
		        statTH.setForeground(new Color(255,255,255));
	        
	        statPane = new JScrollPane(statisticsTable);
        // 	END OF STATISTICS TABLE
	        
	        
	    // 	LABELS
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
	        JTextField vehicleT = new JTextField();
	        vehicleT.setMargin(new Insets(5,10,5,5));
	        
	        JTextField crossTimeT = new JTextField("0");
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
	        
	        JTextField lengthT = new JTextField("0");
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
	        
	        JTextField carEmissionT = new JTextField("0");
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
	        
	        JTextField carStatusT = new JTextField("Waiting");
	        carStatusT.setMargin(new Insets(5,10,5,5));
	        carStatusT.setEditable(false);
	        
	        String v1[] = { "","BUS","CAR","TRUCK" };
	        String d1[] = { "","Left", "Right", "Straight" };
	        String s1[] = { "1","2","3","4" };
	        
	        JComboBox<String> vehTypeT =  new JComboBox<String>(v1);
	        JComboBox<String> directionT =  new JComboBox<String>(d1);
	        JComboBox<String> segmentT =  new JComboBox<String>(s1);
	        
        //	END OF TEXTBOX
	        
	        
        //	BUTTONS
	        Icon addIcon = new ImageIcon(getClass().getResource("/images/add.png"));
	        Icon exitIcon = new ImageIcon(getClass().getResource("/images/exit.png"));
	        Icon cancelIcon = new ImageIcon(getClass().getResource("/images/start.png"));
	        
	        JButton addButton = new JButton("Add Vehicle",addIcon);
	        addButton.setBackground(new Color(3, 192, 60));
	        addButton.setForeground(new Color(255, 255, 255));
	        addButton.addActionListener(
	        	new ActionListener() {
	        		public void actionPerformed(ActionEvent ad) {
       					Object[] newVehicle = new Object[] {
       						vehicleT.getText(), 
       						vehTypeT.getSelectedItem().toString(),
       						crossTimeT.getText(),
       						directionT.getSelectedItem().toString(),
       						lengthT.getText(),
       						carEmissionT.getText(),
       						segmentT.getSelectedItem().toString()
        				};
				
						try {
							junc.AddVehicle(newVehicle);
						} catch (DuplicateIdException e) {
							e.printStackTrace();
						}
						update();
	        		}
	        	}
	        );
	        
	        
	        JButton exitButton = new JButton("Exit", exitIcon);
	        exitButton.setBackground(new Color(128, 0, 32));
	        exitButton.setForeground(new Color(255, 255, 255));
	        exitButton.addActionListener(
	        		new ActionListener() {
			            public void actionPerformed(ActionEvent ex) {
			            	JFrame confirmex = new JFrame("EXIT");
			                if(JOptionPane.showConfirmDialog(confirmex, "Are you sure you want to exit","EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
			                	junc.generateReport();
			                	System.exit(0);
			                }
				        }
	        		}
	        );
	        
	        JButton cancelButton = new JButton ("Start", cancelIcon);
	        cancelButton.addActionListener(
	        		new ActionListener() {
			            public void actionPerformed(ActionEvent ca) {
			            	junc.CalcPhases();
			            	update();
			                cancelButton.setText("Continue");
				        }
	        		}
	        );
	        cancelButton.setBackground(new Color(31, 117, 254));
	        cancelButton.setForeground(new Color(255, 255, 255));
        //	END OF BUTTONS
	        
	        
	        
	    //	POSITIONS 
	        //	TABLE POSITIONS
		        vehiclePane.setBounds(20, 60,500,230);
		        add(vehiclePane);
		        phasePane.setBounds(550, 60,200,295);
		        add(phasePane);
		        statPane.setBounds(790, 60,400,175);
		        add(statPane);
		        
	        // HEADER POSITIONS
		        
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
			    
			    cancelButton.setBounds(250,610,150,30);
			    add(cancelButton);
			    
			    exitButton.setBounds(1000,610,150,30);
			    add(exitButton);
		    //	END OF BUTTON POSITION
	    //	END OF POSITIONS
			    
        setVisible(true);
        
        
	}
	
	
	

	public static void main(String[] args) {	
		junc = new JunctionControler();
		new courseGui();
	}

	
	
	
}
