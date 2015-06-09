package hotel.gui;

import hotel.db.provider.StaffProvider;
import hotel.entity.Staff;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.TableModel;
import java.util.Date;
import javax.swing.JTable;

public class SalaryFrame extends javax.swing.JFrame {

    private Calendar cal;
    private Calendar compareCal;
    private StaffProvider provider;
    private List<Staff> staffList;
    //private Staff s;

    public SalaryFrame() {
        initComponents();
        yearBox.removeAllItems();
        for (int i = 1990; i < 2016; i++) {
            yearBox.addItem(i);
        }
        cal = Calendar.getInstance();
        compareCal = Calendar.getInstance();
        provider = new StaffProvider();
        staffList = provider.getAll();

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
        salaryTable = new javax.swing.JTable();

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        salaryTable.setName(""); // NOI18N
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
        TableModel mdl = salaryTable.getModel();

        for (Staff s : staffList) {
            cal.setTime(s.getRecruitement());
            compareCal.set(Calendar.YEAR, Integer.parseInt(yearBox.getSelectedItem().toString()));
            compareCal.set(Calendar.MONTH, monthBox.getSelectedIndex());

            if (compareCal.compareTo(cal) >= 0) {
                try {
                    cal.setTime(s.getFiring());
                    if (compareCal.compareTo(cal) <= 0) {
                        //"Mitarbeiter", "Gehaltsstufe", "Monatslohn", "Vertragsbeginn", "Vertragsende"
                        for (int r = 0; r < salaryTable.getRowCount(); r++) {
                            for (int c = 0; c < salaryTable.getColumnCount(); c++) {
                                switch (c) {
                                    case 0:
                                        salaryTable.setValueAt(s.getName() + " " + s.getSurname(), r, c);
                                        break;
                                    case 1:
                                        salaryTable.setValueAt(s.getCategory().getId(), r, c);
                                        break;
                                    case 2:
                                        salaryTable.setValueAt(s.getCategory().getSalary(), r, c);
                                        break;
                                    case 3:
                                        salaryTable.setValueAt(s.getRecruitement(), r, c);
                                        break;
                                    case 4:
                                        salaryTable.setValueAt(s.getFiring(), r, c);
                                        break;
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_monthBoxActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void yearBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearBoxActionPerformed
        // SQL Abfrage für Jahr Monat und Tag des einstelldatum
        yearBox.setSelectedIndex(0);
        if (staffList == null) {
            System.out.println("leer");
            //LEER !!!
        }
        for (Staff s : staffList) {
            cal.setTime(s.getRecruitement());
            System.out.println("monthBox getSelectedIndex " + (monthBox.getSelectedIndex()));
            System.out.println("cal Month " + cal.get(Calendar.MONTH));
            System.out.println("yearBox getSelectedIndex " + (int) yearBox.getSelectedItem());
            System.out.println("cal Year " + cal.get(Calendar.YEAR));
        }
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
