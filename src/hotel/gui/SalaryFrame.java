/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.gui;

import hotel.db.provider.StaffProvider;
import hotel.entity.Staff;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.TableModel;
import java.util.Date;

/**
 *
 * @author schaefal
 */
public class SalaryFrame extends javax.swing.JFrame {

    /**
     * Creates new form LohnabrechnungGUI
     */
    public SalaryFrame() {
        initComponents();
        cal = Calendar.getInstance();
        compareCal = Calendar.getInstance();
        provider = new StaffProvider();
        staffList = provider.getAll();

        setTitle("Lohnabrechnung");
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        monthBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        salaryTable = new javax.swing.JTable();
        preCostLbl = new javax.swing.JLabel();
        costLbl = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        yearBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        monthBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember" }));
        monthBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBoxActionPerformed(evt);
            }
        });

        salaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mitarbeiter", "Gehaltsstufe", "Monatslohn", "Vertragsbeginn", "Vertragsende"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Short.class, java.lang.Float.class, java.util.Date.class, java.util.Date.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            private List<List<Object>> data = new ArrayList<List<Object>>();

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            public Object getValueAt(int row, int col) {
                return data.get(row).get(col);
            }

            public void setValueAt(Object value, int row, int col) {
                data.get(row).set(col, value);
                fireTableCellUpdated(row, col);
            }
        });
        jScrollPane1.setViewportView(salaryTable);

        preCostLbl.setText("Gesamtkosten:");

        costLbl.setText("10000€");

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        yearBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1990" }));
        yearBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(preCostLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(costLbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preCostLbl)
                    .addComponent(costLbl)
                    .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void monthBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBoxActionPerformed
        TableModel mdl = salaryTable.getModel();

        for(Staff s : staffList) {
            cal.setTime(s.getRecruitement());
            compareCal.set(Calendar.YEAR, Integer.parseInt(yearBox.getSelectedItem().toString()));
            compareCal.set(Calendar.MONTH, monthBox.getSelectedIndex());
            
            if(compareCal.compareTo(cal) >= 0) {
                try {
                    cal.setTime(s.getFiring());
                
                    if(compareCal.compareTo(cal) <= 0) {
                        //        "Mitarbeiter", "Gehaltsstufe", "Monatslohn", "Vertragsbeginn", "Vertragsende"

                    mdl.setValueAt(s.getName() +" "+ s.getSurname(), mdl.getRowCount(), 0);
                    mdl.setValueAt(s.getCategory().getId(), mdl.getRowCount(), 1);
                    mdl.setValueAt(s.getCategory().getSalary(), mdl.getRowCount(), 2);
                    mdl.setValueAt(s.getRecruitement(), mdl.getRowCount(), 3);
                    mdl.setValueAt(s.getFiring(), mdl.getRowCount(), 4);
                        
                    }
                }
                catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_monthBoxActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void yearBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearBoxActionPerformed
        for(Staff s : staffList) {
            cal.setTime(s.getRecruitement());
            if(monthBox.getSelectedIndex()== cal.get(Calendar.MONTH) && (int)yearBox.getSelectedItem() == cal.get(Calendar.YEAR)) {
                
            }
        }
    }//GEN-LAST:event_yearBoxActionPerformed

    private Calendar cal;
    private Calendar compareCal;
    private StaffProvider provider;
    private List<Staff> staffList;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel costLbl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox monthBox;
    private javax.swing.JLabel preCostLbl;
    private javax.swing.JTable salaryTable;
    private javax.swing.JComboBox yearBox;
    // End of variables declaration//GEN-END:variables
}
