package hotel.gui;

import hotel.db.provider.StaffProvider;
import hotel.entity.Staff;
import hotel.gui.model.myTable;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author phil
 * completed by Alex Phil 16.06
 */
public class StaffListFrame extends javax.swing.JFrame {

    private StaffProvider provider;
    private List<Staff> staffList;
    private DefaultTableModel model;

    public StaffListFrame() {
        this.provider = new StaffProvider();
        initComponents();

        setTitle("Mitarbeiterübersicht");
        setLocationRelativeTo(null);
        model = (DefaultTableModel) staffTable.getModel();
        this.actFrame();
    }

    public void actFrame() {
        this.staffList = provider.getAll();

        int col = 0;
        int row = 0;
        double cost = 0.0;
        //Table leeren
        for (int r = 0; r < staffTable.getRowCount(); r++) {
            for (int c = 0; c < staffTable.getColumnCount(); c++) {
                staffTable.setValueAt("", r, c);
            }
        }

        for (int i = 1; i < staffList.size(); i++) {

            //Zeilen hinzufügen wenn anzahl der Zeilen des Tables weniger als die Länger der Mitarbeiterliste ist
            if (staffTable.getRowCount() < staffList.size()) {
                model.addRow(new Object[]{null,null,null,null,null,null});
            } else if (staffTable.getRowCount() > staffList.size()) {
                for(int j=0; j < staffTable.getSelectedRowCount(); j ++) {
                    model.removeRow(staffTable.getSelectedRows()[j]);
                }
            }
            
             //Vorname
             model.setValueAt("" + staffList.get(i).getName(), row, col);
             col++;
             //Nachname
             model.setValueAt("" + staffList.get(i).getSurname(), row, col);
             col++;
             //Gehaltsstufe
             model.setValueAt("" + staffList.get(i).getCategory().getName(), row, col);
             col++;
             //Lohn                
             model.setValueAt("" + staffList.get(i).getCategory().getSalary() + " €", row, col);
             col++;
             //Vertragsbeginn
             model.setValueAt("" + staffList.get(i).getRecruitement(), row, col);
             col++;
             //Gefeuert
             model.setValueAt("" + staffList.get(i).getFiring(), row, col);
             col++;
             //in die nächste Zeile springen
             if (col >= 6) {
             col = 0;
             row += 1;
             }
        }
        model.fireTableDataChanged();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        staffTable = new myTable();
        newStaff = new javax.swing.JButton();
        editStaff = new javax.swing.JButton();
        deleteStaff = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(950, 350));
        setPreferredSize(new java.awt.Dimension(950, 350));

        staffTable.setName("StaffTable");
        staffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vorname", "Nachname", "Gehaltsstufe", "Lohn", "Vertragsbeginn", "Vertragsende"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(staffTable);

        newStaff.setText("Neuen Mitarbeiter anlegen");
        newStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newStaffActionPerformed(evt);
            }
        });

        editStaff.setText("Mitarbeiter bearbeiten");
        editStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editStaffActionPerformed(evt);
            }
        });

        deleteStaff.setText("Mitarbeiter löschen");
        deleteStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newStaff)
                        .addGap(18, 18, 18)
                        .addComponent(editStaff)
                        .addGap(18, 18, 18)
                        .addComponent(deleteStaff))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newStaffActionPerformed
        new AddStaffFrame(this).setVisible(true);
    }//GEN-LAST:event_newStaffActionPerformed

    private void editStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editStaffActionPerformed
        for(int i=0; i < staffTable.getSelectedRowCount(); i++) {
            System.out.println(staffList.get(staffTable.getSelectedRows()[i]) == null);
            new AddStaffFrame(this, staffList.get(staffTable.getSelectedRows()[i])).setVisible(true);
        }

    }//GEN-LAST:event_editStaffActionPerformed

    private void deleteStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStaffActionPerformed

        for (int i = 0; i < staffTable.getSelectedRowCount(); i++) {
            provider.remove(staffList.get(staffTable.getSelectedRows()[i]));
            actFrame();
        }
    }//GEN-LAST:event_deleteStaffActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteStaff;
    private javax.swing.JButton editStaff;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newStaff;
    private javax.swing.JTable staffTable;
    // End of variables declaration//GEN-END:variables
}
