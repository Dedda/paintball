/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.gui;

import hotel.db.provider.GuestProvider;
import hotel.db.provider.ReservationProvider;
import hotel.entity.Guest;
import hotel.entity.Reservation;
import hotel.gui.model.GuestListModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dedda
 */
public class GuestListFrame extends javax.swing.JFrame {

    public static final String RESERVATIONS_LBL_TEXT = "Anzahl Buchungen: ";
    public static final String TO_PAY_LBL_TEXT = "Offener Betrag: ";
    
    private List<Guest> guests;
    private GuestProvider guestProvider;
    private ReservationProvider reservationProvider;
    
    /**
     * Creates new form GuestFormFrame
     */
    public GuestListFrame() {
        initComponents();
        setTitle("Gästeliste");
        guestProvider = new GuestProvider();
        reservationProvider = new ReservationProvider();
        loadGuests();
        setLocationRelativeTo(null);
    }

    void loadGuests() {
        this.guests = guestProvider.getAll();
        GuestListModel model = new GuestListModel();
        guests.remove(new Guest(0, "Nicht", "Vorhanden"));
        model.setGuests(guests);
        guestList.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        guestList = new javax.swing.JList();
        searchLbl = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        addGuestBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        openBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        reservationsLbl = new javax.swing.JLabel();
        toPayLbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        guestList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                guestListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(guestList);

        searchLbl.setText("suchen:");

        addGuestBtn.setText("neuer Gast");
        addGuestBtn.setMaximumSize(new java.awt.Dimension(93, 29));
        addGuestBtn.setMinimumSize(new java.awt.Dimension(93, 29));
        addGuestBtn.setPreferredSize(new java.awt.Dimension(93, 29));
        addGuestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGuestBtnActionPerformed(evt);
            }
        });

        removeBtn.setText("löschen");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        openBtn.setText("öffnen");
        openBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBtnActionPerformed(evt);
            }
        });

        refreshBtn.setText("aktualisieren");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        reservationsLbl.setText("Anzahl Buchungen:");
        reservationsLbl.setName(""); // NOI18N

        toPayLbl.setText("Offener Betrag:");

        jButton1.setText("Buchungen anzeigen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addGuestBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(searchLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reservationsLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toPayLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 42, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addGuestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshBtn)
                                .addGap(7, 7, 7)
                                .addComponent(openBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(reservationsLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toPayLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addGuestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGuestBtnActionPerformed
        new AddGuestFrame(this).setVisible(true);
    }//GEN-LAST:event_addGuestBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        loadGuests();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        GuestListModel model = (GuestListModel)guestList.getModel();
        int index[] = guestList.getSelectedIndices();
        Guest guests[] = new Guest[index.length];
        for (int i = 0; i < index.length; i++) {
            guests[i] = model.getGuestInLine(index[i]);
        }
        for (Guest guest : guests) {
            List<Reservation> openReservations = reservationProvider.getOpenForGuest(guest);
            if (openReservations.size() == 0) {
                guestProvider.remove(guest);    
            } else {
                String message = "Gast " + guest.getName() + " " 
                        + guest.getSurname() + " hat noch nicht bezahlte Reservierungen!";
                JOptionPane.showMessageDialog(this, message, "Fehler!", JOptionPane.WARNING_MESSAGE);
            }
        }
        loadGuests();
    }//GEN-LAST:event_removeBtnActionPerformed

    private void openBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBtnActionPerformed
        GuestListModel model = (GuestListModel)guestList.getModel();
        int index[] = guestList.getSelectedIndices();
        Guest guests[] = new Guest[index.length];
        for (int i = 0; i < index.length; i++) {
            guests[i] = model.getGuestInLine(index[i]);
        }
        for (Guest guest : guests) {
            new GuestFrame(guest).setVisible(true);
        }
    }//GEN-LAST:event_openBtnActionPerformed

    private void guestListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_guestListValueChanged
        GuestListModel model = (GuestListModel)guestList.getModel();
        int[] index = guestList.getSelectedIndices();
        Guest guests[] = new Guest[index.length];
        for (int i : index) {
            guests[i] = model.getGuestInLine(i);
        }
        int reservations = 0;
        int toPay = 0;
        for (Guest guest : guests) {
            reservations += reservationProvider.getForGuest(guest).size();
            toPay += guestProvider.toPay(guest);
        }
        reservationsLbl.setText(RESERVATIONS_LBL_TEXT + reservations);
        toPayLbl.setText(TO_PAY_LBL_TEXT + toPay);
    }//GEN-LAST:event_guestListValueChanged

    public GuestProvider getGuestProvider() {
        return guestProvider;
    }

    public void setGuestProvider(GuestProvider guestProvider) {
        this.guestProvider = guestProvider;
    }

    public ReservationProvider getReservationProvider() {
        return reservationProvider;
    }

    public void setReservationProvider(ReservationProvider reservationProvider) {
        this.reservationProvider = reservationProvider;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGuestBtn;
    private javax.swing.JList guestList;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openBtn;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JLabel reservationsLbl;
    private javax.swing.JLabel searchLbl;
    private javax.swing.JTextField searchText;
    private javax.swing.JLabel toPayLbl;
    // End of variables declaration//GEN-END:variables
}
