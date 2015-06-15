package hotel.gui;

import hotel.db.provider.StaffProvider;
import hotel.entity.Staff;
import hotel.gui.model.myTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

public class SalaryFrame extends javax.swing.JFrame {

    private Calendar cal;
    private Calendar compareCal;
    private StaffProvider provider;
    private List<Staff> staffList;

    public SalaryFrame() {
        this.provider = new StaffProvider();
        this.staffList = provider.getAll();
        initComponents();
        monthBox.setSelectedIndex(0);
        for (int i = 1990; i < 2016; i++) {
            this.yearBox.addItem("" + i);
        }
        setTitle("Lohnabrechnung");
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        monthBox = new javax.swing.JComboBox();
        preCostLbl = new javax.swing.JLabel();
        costLbl = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        yearBox = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        salaryTable = new myTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        monthBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember" }));
        monthBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBoxActionPerformed(evt);
            }
        });

        preCostLbl.setText("Gesamtkosten:");

        costLbl.setText("10000€");

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        yearBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearBoxActionPerformed(evt);
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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salaryTable.setName("SalaryTable");
        jScrollPane2.setViewportView(salaryTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                        .addComponent(preCostLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(costLbl))
                    .addGroup(layout.createSequentialGroup()
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(cancelBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void monthBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBoxActionPerformed
        for (int i = 0; i < staffList.size(); i++) {

            //Variablen für den Table initalisieren
            int col = 0;
            int row = 0;

            Date rec = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                rec = sdf.parse("" + staffList.get(i).getRecruitement());
            } catch (ParseException ex) {
                System.out.println("Error at parsing recDate");
            }

            Date fir = null;
            //Prüfen ob der Mitarbeiter gefeuert wurde
            if (staffList.get(i).getFiring() != null) {
                try {
                    fir = sdf.parse("" + staffList.get(i).getFiring());
                } catch (ParseException ex) {
                    System.out.println("Error at parsing firingDate");
                }
            }

            while (row < staffList.size()) {
                //Name
                salaryTable.setValueAt("" + staffList.get(row).getName() + " " + staffList.get(i).getSurname(), row, col);
                col++;
                //Gehaltsstufe
                salaryTable.setValueAt(""+staffList.get(row).getCategory().getName(), row, col);
                col++;
                //Lohn                
                salaryTable.setValueAt(""+staffList.get(row).getCategory().getSalary(), row, col);
                col++;
                //Vertragsbeginn
                salaryTable.setValueAt("" + staffList.get(row).getRecruitement(), row, col);
                col++;
                //Vertragsende
                if (staffList.get(row).getFiring() != null) {
                    salaryTable.setValueAt("" + staffList.get(row).getFiring(), row, col);
                } else {
                    salaryTable.setValueAt("-", row, col);
                }
                col++;

                // in die nächste Zeile springen
                if (col >= 5) {
                    col = 0;
                    row += 1;
                }
            }
        }
    }//GEN-LAST:event_monthBoxActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void yearBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearBoxActionPerformed

    }//GEN-LAST:event_yearBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel costLbl;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox monthBox;
    private javax.swing.JLabel preCostLbl;
    private javax.swing.JTable salaryTable;
    private javax.swing.JComboBox yearBox;
    // End of variables declaration//GEN-END:variables
}
