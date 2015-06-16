package hotel.gui;

import hotel.db.provider.StaffProvider;
import hotel.db.provider.StaffCategoryProvider;
import hotel.entity.StaffCategory;
import hotel.entity.Staff;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author phil
 */
public class AddStaffFrame extends javax.swing.JFrame {

    private List<StaffCategory> categoryList;
    private StaffCategoryProvider staff_category;
    private StaffProvider staffProvider;
    private StaffListFrame slf;

    public AddStaffFrame(StaffListFrame slf) {
        this.slf = slf;
        initComponents();
        staff_category = new StaffCategoryProvider();
        categoryList = staff_category.getAll();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryBox.addItem(categoryList.get(i).getName());
        }
        this.setTitle("Neuer Mitarbeiter");
        setLocationRelativeTo(null);

    }

    public void addStaff() {
        String name;
        String surname;
        String category;
        Date recruitement = null;
        // Gebraucht um String in ein Datum umzuwandeln
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        name = nameTextfield.getText(); //Muss String sein
        surname = surnameTextfield.getText(); //Muss String sein

        category = (String) categoryBox.getSelectedItem();

        try {
            recruitement = sdf.parse(recruitementTextfield.getText()); //recruitementTextfield -> Date als String
        } catch (ParseException ex) {
            System.out.println("Error at parsing the recruitement for new staff");
        }
        Staff staff = new Staff();
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getName().equals(category)) {
                staff.setCategory(categoryList.get(i));
            }
        }
        staff.setName(name);
        staff.setSurname(surname);
        staff.setRecruitement(recruitement);
        new StaffProvider().saveNew(staff);
    }

    public boolean checkName(final String name, final String surname) {
        final String expression = "[A-Z][a-z]+\\s[A-Z][a-z]+";
        if (!name.matches(expression)) {
            return false;
        }
        if (name.split(" ")[0].length() > 30) {
            return false;
        }
        if (name.split(" ")[1].length() > 30) {
            return false;
        }
        if (new StaffProvider().getForName(name, surname) == null) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLbl = new javax.swing.JLabel();
        nameTextfield = new javax.swing.JTextField();
        surnameLbl = new javax.swing.JLabel();
        surnameTextfield = new javax.swing.JTextField();
        categoryLbl = new javax.swing.JLabel();
        recruitementLbl = new javax.swing.JLabel();
        recruitementTextfield = new javax.swing.JTextField();
        categoryBox = new javax.swing.JComboBox();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(190, 300));
        setPreferredSize(new java.awt.Dimension(190, 300));

        nameLbl.setText("Name:");

        surnameLbl.setText("Nachname:");

        categoryLbl.setText("Tätigkeit");

        recruitementLbl.setText("Einstellungsdatum:");

        categoryBox.setToolTipText("");

        cancelButton.setText("Abbrechen");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Speichern");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(recruitementLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recruitementTextfield))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(surnameLbl)
                            .addComponent(nameLbl)
                            .addComponent(categoryLbl))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTextfield)
                            .addComponent(surnameTextfield)
                            .addComponent(categoryBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 124, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLbl)
                    .addComponent(nameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surnameLbl)
                    .addComponent(surnameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryLbl)
                    .addComponent(categoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recruitementLbl)
                    .addComponent(recruitementTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        addStaff();
        slf.actFrame();
        dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox categoryBox;
    private javax.swing.JLabel categoryLbl;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JTextField nameTextfield;
    private javax.swing.JLabel recruitementLbl;
    private javax.swing.JTextField recruitementTextfield;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel surnameLbl;
    private javax.swing.JTextField surnameTextfield;
    // End of variables declaration//GEN-END:variables
}
