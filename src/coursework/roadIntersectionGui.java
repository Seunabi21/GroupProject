package coursework;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Chris
 * Modified by Ryan 07/03/2022
 * added connection to model
 * 
 * 
 */
public class roadIntersectionGui extends javax.swing.JFrame {
	static JunctionControler junc;

    /**
     * Creates new form roadIntersectionGui
     * @throws java.io.FileNotFoundException
     */
    public roadIntersectionGui(){
        initComponents();
        setTitle("Road Intersection");
        pageHeader.setBackground( new Color(0,0,0,0.3f));
        DefaultTableCellRenderer centerAlign = new DefaultTableCellRenderer();
        centerAlign.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer bgColor = new DefaultTableCellRenderer();
        bgColor.setBackground(new Color(29,112,60));
                
//      Showing Grid
            vehiclesTable.setShowHorizontalLines(true);
            phaseTable.setShowGrid(true);
            statisticsTable.setShowGrid(true);
            addVehicleTable.setShowGrid(true);
            vehiclesTable.setGridColor(new Color(29,112,60));
            phaseTable.setGridColor(new Color(29,112,60));
            statisticsTable.setGridColor(new Color(29,112,60));
            addVehicleTable.setGridColor(new Color(29,112,60));
        
//      BUTTONS
        // BUTTON CURSOR
            exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addVehicleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
//      Setting Column Width
        // TableColumnModel
            TableColumnModel vehicleMod = vehiclesTable.getColumnModel();
            vehicleMod.getColumn(2).setPreferredWidth(120);
        // DefaultTableModel
            DefaultTableModel vehMod = (DefaultTableModel) vehiclesTable.getModel();
            int colCount = vehMod.getColumnCount();
            for(int i=0; i<colCount; i++){vehicleMod.getColumn(i).setCellRenderer(centerAlign);}
        
        // TableColumnModel
            TableColumnModel addvehicleMod = addVehicleTable.getColumnModel();
            addvehicleMod.getColumn(2).setPreferredWidth(120);
        // DefaultTableModel
            DefaultTableModel addVehMod = (DefaultTableModel) addVehicleTable.getModel();
            int colCount1 = addVehMod.getColumnCount();
            for(int i=0; i<colCount1; i++){addvehicleMod.getColumn(i).setCellRenderer(centerAlign);}
        
        // TableColumnModel
            TableColumnModel statisticsMod = statisticsTable.getColumnModel();
            statisticsMod.getColumn(2).setPreferredWidth(100);
        // DefaultTableModel
            DefaultTableModel statMod = (DefaultTableModel) statisticsTable.getModel();
            int colCount2 = statMod.getColumnCount();
            for(int i=0; i<colCount2; i++){statisticsMod.getColumn(i).setCellRenderer(centerAlign);} 
            
        // DefaultTableModel
            DefaultTableModel phasesMod = (DefaultTableModel) phaseTable.getModel();
            int colCount3 = phasesMod.getColumnCount();
            for(int i=0; i<colCount3; i++){phaseTable.getColumnModel().getColumn(i).setCellRenderer(centerAlign);} 
            
            
//      Table Header Design
        JTableHeader vehiclesTH = vehiclesTable.getTableHeader();
        vehiclesTH.setPreferredSize(new Dimension(10,50));
        vehiclesTH.setFont(new Font("San-Serif", Font.BOLD,14));
        vehiclesTH.setDefaultRenderer(centerAlign);
        vehiclesTH.setDefaultRenderer(bgColor);        
        
        JTableHeader phaseTH = phaseTable.getTableHeader();
        phaseTH.setPreferredSize(new Dimension(10,50));
        phaseTH.setFont(new Font("San-Serif", Font.BOLD,14));
        phaseTH.setDefaultRenderer(centerAlign);
        phaseTH.setDefaultRenderer(bgColor);
        
        JTableHeader statisticsTH = statisticsTable.getTableHeader();
        statisticsTH.setPreferredSize(new Dimension(10,50));
        statisticsTH.setFont(new Font("San-Serif", Font.BOLD,14));
        statisticsTH.setDefaultRenderer(centerAlign);
        statisticsTH.setDefaultRenderer(bgColor);
        
        JTableHeader addVehicleTH = addVehicleTable.getTableHeader();
        addVehicleTH.setPreferredSize(new Dimension(10,50));
        addVehicleTH.setFont(new Font("San-Serif", Font.BOLD,14));
        addVehicleTH.setDefaultRenderer(centerAlign);
        addVehicleTH.setDefaultRenderer(bgColor);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	//Initialising components
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vehiclesTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        statisticsTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        phaseTable = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pageHeader = new javax.swing.JPanel();
        vehiclelabel = new javax.swing.JLabel();
        phaseslabel = new javax.swing.JLabel();
        statisticsLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        addVehicleTable = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();
        addVehicleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(2, 0, 36));

 // Vehicle Table Configuration
        vehiclesTable.setBackground(new java.awt.Color(25, 7, 60));
        vehiclesTable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        vehiclesTable.setForeground(new java.awt.Color(255, 255, 255));
        vehiclesTable.setModel(new javax.swing.table.DefaultTableModel(
            	junc.vehToObj()
            ,
            new String [] {
                "Vehicle", "Type", "Crossing Time", "Direction", "Length", "Emission", "Status", "Segment"
            }
        ));
        vehiclesTable.setGridColor(new java.awt.Color(236, 234, 234));
        vehiclesTable.setRowHeight(30);
        vehiclesTable.setSelectionBackground(new java.awt.Color(0, 0, 0));
        vehiclesTable.setShowVerticalLines(false);
        vehiclesTable.getTableHeader().setReorderingAllowed(false);
        vehiclesTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                vehiclesTableAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        
  // statisticsTable Table Configuration
        jScrollPane1.setViewportView(vehiclesTable);

        statisticsTable.setBackground(new java.awt.Color(25, 7, 60));
        statisticsTable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        statisticsTable.setForeground(new java.awt.Color(255, 255, 255));
        statisticsTable.setModel(new javax.swing.table.DefaultTableModel(
        		junc.segToObj(),
            new String [] {
                "Segment", "Waiting Time", "Waiting Length", "Cross Time"
            }
        ));
        statisticsTable.setGridColor(new java.awt.Color(236, 234, 234));
        statisticsTable.setRowHeight(30);
        jScrollPane3.setViewportView(statisticsTable);
        
  //Phase Table Configuration
        phaseTable.setBackground(new java.awt.Color(25, 7, 60));
        phaseTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        phaseTable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        phaseTable.setForeground(new java.awt.Color(255, 255, 255));
        phaseTable.setModel(new javax.swing.table.DefaultTableModel(
            junc.phaseToObj(),
            new String [] {
                "Phase", "Duration"
            }
        ));
        phaseTable.setGridColor(new java.awt.Color(236, 234, 234));
        phaseTable.setIntercellSpacing(new java.awt.Dimension(15, 15));
        phaseTable.setRowHeight(30);
        phaseTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(phaseTable);

        
        jTextField1.setFont(new java.awt.Font("Lucida Fax", 3, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(junc.CalcTotalEmissions()+"");
        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 212, 240));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CO2");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 212, 240));
        jLabel2.setText("g");

        pageHeader.setBackground(new java.awt.Color(69, 73, 109));

        vehiclelabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        vehiclelabel.setForeground(new java.awt.Color(0, 212, 240));
        vehiclelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vehiclelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursework/images/vehicles.png"))); // NOI18N
        vehiclelabel.setText("VEHICLES");
        vehiclelabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60)));
        vehiclelabel.setIconTextGap(12);

        phaseslabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        phaseslabel.setForeground(new java.awt.Color(0, 212, 240));
        phaseslabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phaseslabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursework/images/phases.png"))); // NOI18N
        phaseslabel.setText("PHASES");
        phaseslabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60)));
        phaseslabel.setIconTextGap(7);

        statisticsLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        statisticsLabel.setForeground(new java.awt.Color(0, 212, 240));
        statisticsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statisticsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursework/images/statistics.png"))); // NOI18N
        statisticsLabel.setText("STATISTICS");
        statisticsLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60)));
        statisticsLabel.setIconTextGap(12);

        javax.swing.GroupLayout pageHeaderLayout = new javax.swing.GroupLayout(pageHeader);
        pageHeader.setLayout(pageHeaderLayout);
        pageHeaderLayout.setHorizontalGroup(
            pageHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHeaderLayout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(vehiclelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
                .addComponent(phaseslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(statisticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        pageHeaderLayout.setVerticalGroup(
            pageHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHeaderLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pageHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vehiclelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phaseslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statisticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 212, 240));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ADD VEHICLES");
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60), new java.awt.Color(29, 112, 60)));

        jPanel2.setOpaque(false);

        exitButton.setBackground(new java.awt.Color(128, 0, 32));
        exitButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursework/images/exit.png"))); // NOI18N
        exitButton.setText("Exit");
        exitButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        exitButton.setIconTextGap(5);
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        addVehicleTable.setAutoCreateRowSorter(true);
        addVehicleTable.setBackground(new java.awt.Color(25, 7, 60));
        addVehicleTable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        addVehicleTable.setForeground(new java.awt.Color(255, 255, 255));
        addVehicleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Vehicle", "Type", "Crossing Time", "Direction", "Length", "Emission", "Status", "Segment"
            }
        ));
        addVehicleTable.setGridColor(new java.awt.Color(236, 234, 234));
        addVehicleTable.setRowHeight(30);
        jScrollPane2.setViewportView(addVehicleTable);

        cancelButton.setBackground(new java.awt.Color(31, 117, 254));
        cancelButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursework/images/start.png"))); // NOI18N
        cancelButton.setText("Start");
        cancelButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        addVehicleButton.setBackground(new java.awt.Color(3, 192, 60));
        addVehicleButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        addVehicleButton.setForeground(new java.awt.Color(255, 255, 255));
        addVehicleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursework/images/add.png"))); // NOI18N
        addVehicleButton.setText("Add");
        addVehicleButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        addVehicleButton.setIconTextGap(7);
        addVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVehicleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(161, 161, 161))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(206, 206, 206))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pageHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 2, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pageHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1358, 707));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

 /*
  *  Action Listeners
  */
    private void vehiclesTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_vehiclesTableAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_vehiclesTableAncestorAdded

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        JFrame confirmexit = new JFrame("EXIT");
        if(JOptionPane.showConfirmDialog(confirmexit, "Are you sure you want to exit","EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }
    //Starts Simulation
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
    	junc.CalcPhases();
    	update();
    }//GEN-LAST:event_exitButtonActionPerformed
    
    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_exitButtonMouseClicked

    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton   MouseEntered
    
    //Adds vehicle to simulation
    private void addVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
    	junc.AddVehicle(addVehicleTable.getComponents());
    	junc.CalcPhases();
    	update();
    }//GEN-LAST:event_exitButtonMouseEntered
    
    //Updates ui elements with info from Junction Controller when called.
    private void update() {
    	statisticsTable.setModel(new javax.swing.table.DefaultTableModel(
        		junc.segToObj(),
            new String [] {
                "Segment", "Waiting Time", "Waiting Length", "Cross Time"
            }
        ));
        vehiclesTable.setModel(new javax.swing.table.DefaultTableModel(
            	junc.vehToObj()
            ,
            new String [] {
                "Vehicle", "Type", "Crossing Time", "Direction", "Length", "Emission", "Status", "Segment"
            }
        ));
        jTextField1.setText(junc.CalcTotalEmissions()+"");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
        	junc = new JunctionControler();
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(roadIntersectionGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(roadIntersectionGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(roadIntersectionGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(roadIntersectionGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new roadIntersectionGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVehicleButton;
    private javax.swing.JTable addVehicleTable;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel pageHeader;
    private javax.swing.JTable phaseTable;
    private javax.swing.JLabel phaseslabel;
    private javax.swing.JLabel statisticsLabel;
    private javax.swing.JTable statisticsTable;
    private javax.swing.JLabel vehiclelabel;
    private javax.swing.JTable vehiclesTable;
    // End of variables declaration//GEN-END:variables
}
