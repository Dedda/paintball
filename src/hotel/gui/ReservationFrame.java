/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.gui;

import hotel.db.provider.ReservationProvider;
import hotel.db.provider.RoomProvider;
import hotel.db.provider.ServiceProvider;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.entity.Service;
import hotel.gui.model.RoomListModel;
import hotel.gui.model.ServiceListModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author sgoeppentin
 */
public class ReservationFrame extends javax.swing.JFrame {

    private final Reservation reservation;
    
    /**
     * Creates new form ReservationFrame
     */
    public ReservationFrame(final Reservation reservation) {
        this.reservation = reservation;
        initComponents();
        idValueLbl.setText(reservation.getId() + "");
        guestNameLbl.setText(reservation.getGuest().getFullName());
        peopleNumberLbl.setText(reservation.getPeople() + "");
        arrivalDateLbl.setText(format(reservation.getStart()));
        departureDateLbl.setText(format(reservation.getEnd()));
        payedBtn.setEnabled(!reservation.isPayed());
        cancelReservationBtn.setEnabled(!reservation.isCanceled());
        stateTextLbl.setText(reservation.isCanceled() ? "storniert" : (reservation.isPayed() ? "bezahlt" : "offen"));
        Map<Service, Integer> services = new ServiceProvider().getForReservation(reservation);
        ServiceListModel serviceListModel = new ServiceListModel();
        serviceListModel.setServices(services);
        servicesList2.setModel(serviceListModel);
        List<Room> rooms = new RoomProvider().getForReservation(reservation.getId());
        RoomListModel roomListModel = new RoomListModel();
        roomListModel.setRooms(rooms);
        roomsList.setModel(roomListModel);
        int price = new ReservationProvider().getTotal(reservation);
        priceValueLbl.setText(price + " Euro");
    }

    private String format(final Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
    
    private List<Service> getSelectedServices() {
        ServiceListModel model = (ServiceListModel) servicesList2.getModel();
        return model.getServices();
    }
    
    private Map<Integer, Integer> getGroupedServiceIds() {
        List<Service> services = getSelectedServices();
        Map<Integer, List<Service>> idToServiceMap = services.stream().collect(Collectors.groupingBy(Service::getId));
        Map<Integer, Integer> idToAmountMap = new HashMap<>();
        idToServiceMap.keySet().forEach(i -> idToAmountMap.put(i, idToServiceMap.get(i).size()));
        return idToAmountMap;
    }
    
    private List<Room> getSelectedRooms() {
        int[] index = roomsList.getSelectedIndices();
        RoomListModel model = (RoomListModel) roomsList.getModel();
        List<Room> selected = Arrays.stream(index).mapToObj(i -> model.getRoomInLine(i)).collect(Collectors.toList());
        return selected;
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
        servicesList = new javax.swing.JList();
        headerLbl = new javax.swing.JLabel();
        idLbl = new javax.swing.JLabel();
        guestLbl = new javax.swing.JLabel();
        guestNameLbl = new javax.swing.JLabel();
        peopleLbl = new javax.swing.JLabel();
        peopleNumberLbl = new javax.swing.JLabel();
        servicesLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomsList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        servicesList2 = new javax.swing.JList();
        roomsLbl = new javax.swing.JLabel();
        arrivalLbl = new javax.swing.JLabel();
        departureLbl = new javax.swing.JLabel();
        arrivalDateLbl = new javax.swing.JLabel();
        departureDateLbl = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        payedBtn = new javax.swing.JButton();
        cancelReservationBtn = new javax.swing.JButton();
        stateLbl = new javax.swing.JLabel();
        stateTextLbl = new javax.swing.JLabel();
        priceLbl = new javax.swing.JLabel();
        priceValueLbl = new javax.swing.JLabel();
        idValueLbl = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        addServiceBtn = new javax.swing.JButton();
        removeServiceBtn = new javax.swing.JButton();
        addRoomBtn = new javax.swing.JButton();
        removeRoomBtn = new javax.swing.JButton();

        jScrollPane1.setViewportView(servicesList);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        headerLbl.setText("Reservierung");

        idLbl.setText("ID:");

        guestLbl.setText("Gast:");

        peopleLbl.setText("Personen:");

        servicesLbl.setText("Services");

        jScrollPane2.setViewportView(roomsList);

        jScrollPane3.setViewportView(servicesList2);

        roomsLbl.setText("Räume");

        arrivalLbl.setText("Ankunft:");

        departureLbl.setText("Abreise:");

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        payedBtn.setText("Bezahlt");
        payedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payedBtnActionPerformed(evt);
            }
        });

        cancelReservationBtn.setText("Stornieren");
        cancelReservationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelReservationBtnActionPerformed(evt);
            }
        });

        stateLbl.setText("Status:");

        priceLbl.setText("Preis:");

        saveBtn.setText("Speichern");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        addServiceBtn.setText("+");
        addServiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addServiceBtnActionPerformed(evt);
            }
        });

        removeServiceBtn.setText("-");
        removeServiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeServiceBtnActionPerformed(evt);
            }
        });

        addRoomBtn.setText("+");
        addRoomBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoomBtnActionPerformed(evt);
            }
        });

        removeRoomBtn.setText("-");
        removeRoomBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRoomBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(headerLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(departureLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(departureDateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(arrivalLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(arrivalDateLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addServiceBtn)
                                .addGap(18, 18, 18)
                                .addComponent(removeServiceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(priceLbl))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addRoomBtn)
                                .addGap(18, 18, 18)
                                .addComponent(removeRoomBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                        .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(payedBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(roomsLbl)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                                .addComponent(cancelReservationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(priceValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(peopleLbl)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(peopleNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(guestLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(guestNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(servicesLbl)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(stateLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(stateTextLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(headerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(idLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(idValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guestLbl)
                            .addComponent(guestNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(peopleNumberLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(peopleLbl, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(arrivalLbl)
                            .addComponent(arrivalDateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(departureLbl))
                    .addComponent(departureDateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(servicesLbl)
                            .addComponent(roomsLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cancelReservationBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(payedBtn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addRoomBtn)
                        .addComponent(removeRoomBtn)
                        .addComponent(addServiceBtn)
                        .addComponent(removeServiceBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stateLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stateTextLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelBtn)
                    .addComponent(priceValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLbl))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void payedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payedBtnActionPerformed
        reservation.setPayed(new Date());
        payedBtn.setEnabled(false);
    }//GEN-LAST:event_payedBtnActionPerformed

    private void cancelReservationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelReservationBtnActionPerformed
        reservation.setCanceled(new Date());
        cancelReservationBtn.setEnabled(false);
    }//GEN-LAST:event_cancelReservationBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        ReservationProvider reservationProvider = new ReservationProvider();
        reservationProvider.merge(reservation);
        Map<Integer, Integer> serviceIdToAmountMap = getGroupedServiceIds();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setForReservation(reservation, serviceIdToAmountMap);
    }//GEN-LAST:event_saveBtnActionPerformed

    private void addServiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addServiceBtnActionPerformed
        Service[] options = new Service[2];
        new ServiceProvider().getAll().toArray(options);
        Service selected = (Service) JOptionPane.showInputDialog(this, "Service wählen", "Services", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selected == null) {
            return;
        }
        ServiceListModel oldModel = (ServiceListModel) servicesList2.getModel();
        ServiceListModel model = new ServiceListModel();
        List<Service> services = oldModel.getServices();
        services.add(selected);
        model.setServices(services);
        servicesList2.setModel(model);
    }//GEN-LAST:event_addServiceBtnActionPerformed

    private void removeServiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeServiceBtnActionPerformed
        int[] selected = servicesList2.getSelectedIndices();
        ServiceListModel oldModel = (ServiceListModel) servicesList2.getModel();
        List<Service> toRemove = Arrays.stream(selected).mapToObj(i -> oldModel.getServiceInLine(i)).collect(Collectors.toList());
        List<Service> services = oldModel.getServices();
        toRemove.stream().forEach(service -> services.remove(service));
        ServiceListModel model = new ServiceListModel();
        model.setServices(services);
        servicesList2.setModel(model);
    }//GEN-LAST:event_removeServiceBtnActionPerformed

    private void removeRoomBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRoomBtnActionPerformed
        List<Room> selected = getSelectedRooms();
        RoomListModel model = new RoomListModel();
        List<Room> oldRooms = ((RoomListModel) roomsList.getModel()).getRooms();
        oldRooms.removeAll(selected);
        model.setRooms(oldRooms);
        roomsList.setModel(model);
    }//GEN-LAST:event_removeRoomBtnActionPerformed

    private void addRoomBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomBtnActionPerformed
        List<Room> rooms = ((RoomListModel) roomsList.getModel()).getRooms();
        List<Room> allRooms = new RoomProvider().getAll();
        allRooms.removeAll(rooms);
        Room[] options = new Room[allRooms.size()];
        allRooms.toArray(options);
        Room selected = (Room) JOptionPane.showInputDialog(this, "Raum wählen", "Räume", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selected == null) {
            return;
        }
        rooms.add(selected);
        RoomListModel model = new RoomListModel();
        model.setRooms(rooms);
        roomsList.setModel(model);
    }//GEN-LAST:event_addRoomBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRoomBtn;
    private javax.swing.JButton addServiceBtn;
    private javax.swing.JLabel arrivalDateLbl;
    private javax.swing.JLabel arrivalLbl;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton cancelReservationBtn;
    private javax.swing.JLabel departureDateLbl;
    private javax.swing.JLabel departureLbl;
    private javax.swing.JLabel guestLbl;
    private javax.swing.JLabel guestNameLbl;
    private javax.swing.JLabel headerLbl;
    private javax.swing.JLabel idLbl;
    private javax.swing.JLabel idValueLbl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton payedBtn;
    private javax.swing.JLabel peopleLbl;
    private javax.swing.JLabel peopleNumberLbl;
    private javax.swing.JLabel priceLbl;
    private javax.swing.JLabel priceValueLbl;
    private javax.swing.JButton removeRoomBtn;
    private javax.swing.JButton removeServiceBtn;
    private javax.swing.JLabel roomsLbl;
    private javax.swing.JList roomsList;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel servicesLbl;
    private javax.swing.JList servicesList;
    private javax.swing.JList servicesList2;
    private javax.swing.JLabel stateLbl;
    private javax.swing.JLabel stateTextLbl;
    // End of variables declaration//GEN-END:variables
}
